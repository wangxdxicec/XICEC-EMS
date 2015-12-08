package com.zhenhappy.ems.service;

import com.zhenhappy.ems.dao.TExhibitorMsgDao;
import com.zhenhappy.ems.entity.TExhibitorMsg;

import com.zhenhappy.util.Page;
import com.zhenhappy.util.QueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by wujianbin on 2014-08-27.
 */
@Service
public class MsgService {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Autowired
    private TExhibitorMsgDao msgDao;

    @Transactional
    public TExhibitorMsg getMsg(Integer id) {
        List datas = hibernateTemplate.find("from TExhibitorMsg where id = ?", id);
        return (TExhibitorMsg) (datas.size() > 0 ? datas.get(0) : null);
    }

    @Transactional
    public List<TExhibitorMsg> getMsgByEid(Integer eid) {
        List datas = hibernateTemplate.find("from TExhibitorMsg where eid = ?", eid);
        return datas.size() > 0 ? datas : null;
    }

    @Transactional
    public void sendMsg(TExhibitorMsg msg) {
        hibernateTemplate.save(msg);
    }

    @Transactional
    public void markIsRead(Integer id) {
        if (id != null) {
            TExhibitorMsg msg = getMsg(id);
            if (msg != null) {
                msg.setIsRead(1);
                hibernateTemplate.update(msg);
            }
        }
    }

    @Transactional
    public void markIsReadByEid(Integer eid) {
        if (eid != null) {
            List<TExhibitorMsg> msgs = getMsgByEid(eid);
            if (msgs.size() > 0) {
                for (TExhibitorMsg msg : msgs) {
                    msg.setIsRead(1);
                    hibernateTemplate.update(msg);
                }
            }
        }
    }

    @Transactional
    public void deleteMsg(Integer id) {
        if (id != null) {
            TExhibitorMsg msg = getMsg(id);
            if (msg != null) {
                msg.setIsDelete(1);
                hibernateTemplate.update(msg);
            }
        }
    }

    @Transactional
    public void deleteMsgByEid(Integer eid) {
        if (eid != null) {
            List<TExhibitorMsg> msgs = getMsgByEid(eid);
            if (msgs.size() > 0) {
                for (TExhibitorMsg msg : msgs) {
                    msg.setIsDelete(1);
                    hibernateTemplate.update(msg);
                }
            }
        }
    }

    /**
     * @param eid
     * @param readState   0 not read,1 has read
     * @param deleteState 0 not delete,1 has delete
     * @return
     */
    public List<TExhibitorMsg> loadMessageByEid(Integer eid, Integer readState, Integer deleteState, Page page) {
        List<TExhibitorMsg> msgs = null;
        if (readState != null && deleteState != null) {
            msgs = msgDao.queryPageByHQL("select count(*) from TExhibitorMsg where eid = ? and isRead=? and isDelete=?", "from TExhibitorMsg where eid = ? and isRead=? and isDelete=?", new Object[]{eid, readState, deleteState}, page);
        } else if (readState == null && deleteState != null) {
            msgs = msgDao.queryPageByHQL("select count(*) from TExhibitorMsg where eid = ? and isDelete=?", "from TExhibitorMsg where eid = ? and isDelete=?", new Object[]{eid, deleteState}, page);
        } else if (readState != null && deleteState == null) {
            msgs = msgDao.queryPageByHQL("select count(*) from TExhibitorMsg where eid = ? and isRead=?", "from TExhibitorMsg where eid = ? and isRead=?", new Object[]{eid, readState}, page);
        } else {
            msgs = msgDao.queryPageByHQL("select count(*) from TExhibitorMsg where eid = ?", "from TExhibitorMsg where eid = ?", new Object[]{eid}, page);
        }
        return msgs;
    }

    /**
     * count all unread message
     *
     * @param eid
     * @return
     */
    public Long countUnreadMessage(Integer eid) {
        Long count = (Long) msgDao.queryForObject("select count(*) from TExhibitorMsg where eid = ? and isRead=? and isDelete=?", new Object[]{eid, 0, 0}, QueryFactory.HQL);
        return count;
    }

    @Transactional
    public void deleteMessage(Integer eid, Integer messageId) {
        msgDao.update("update TExhibitorMsg set isDelete=1 where eid = ? and id = ?", new Object[]{eid, messageId}, QueryFactory.HQL);
    }

    @Transactional
    public void readMessage(Integer eid, Integer messageId) {
        msgDao.update("update TExhibitorMsg set isRead=1 where eid = ? and id = ?", new Object[]{eid, messageId}, QueryFactory.HQL);
    }
}
