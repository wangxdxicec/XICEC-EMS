package com.zhenhappy.ems.service;

import com.zhenhappy.ems.dao.ExhibitorDao;
import com.zhenhappy.ems.dao.ExhibitorInfoDao;
import com.zhenhappy.ems.dto.ExhibitorBooth;
import com.zhenhappy.ems.dto.ProductType;
import com.zhenhappy.ems.dto.ProductTypeCheck;
import com.zhenhappy.ems.entity.*;
import com.zhenhappy.util.QueryFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by wujianbin on 2014-04-03.
 */
@Service
public class ExhibitorService {

    @Autowired
    private ExhibitorDao exhibitorDao;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ExhibitorInfoDao exhibitorInfoDao;

    private static Logger log = Logger.getLogger(ExhibitorService.class);

    public TExhibitor getExhibitorByUsernamePassword(String username, String password) {
        List<TExhibitor> exhibitors = exhibitorDao.queryByHql("from TExhibitor where username = ? and password = ?", new Object[]{username, password});
        return exhibitors.size() > 0 ? exhibitors.get(0) : null;
    }

	public TExhibitor getExhibitorByEid(Integer eid) {
		List<TExhibitor> exhibitors = exhibitorDao.queryByHql("from TExhibitor where eid = ?", new Object[]{eid});
		return exhibitors.size() > 0 ? exhibitors.get(0) : null;
	}

    public void modifyPassword(Integer userId, String newPassword, String oldPassword) throws Exception {
        int modifed = exhibitorDao.update("update t_exhibitor set password =? where eid = ? and password = ? ", new Object[]{newPassword, userId, oldPassword}, QueryFactory.SQL);
        if (modifed == 0 && !newPassword.equals(oldPassword)) {
            throw new Exception("旧密码错误");
        }
    }

    /**
     * create exhibitor information
     *
     * @param exhibitorInfo
     */
    @Transactional()
    public void insert(TExhibitorInfo exhibitorInfo) {
        exhibitorInfoDao.create(exhibitorInfo);
    }

    @Transactional
    public void update(TExhibitorInfo exhibitorInfo) {
        exhibitorInfoDao.update(exhibitorInfo);
    }

    public TExhibitorInfo query(Integer einfoId) {
        return exhibitorInfoDao.query(einfoId);
    }

    public List<ProductTypeCheck> loadAllProductTypesWithCheck(Integer einfoId) {
        List<ProductTypeCheck> productTypeChecks = new ArrayList<ProductTypeCheck>();
        List<Map<String, Object>> items = jdbcTemplate.queryForList("select tec.id,tec.parent_class_id,tec.class_id,tpt.is_other from t_exhibitor_class tec,t_product_type tpt  where tec.einfo_id = ? and tec.class_id = tpt.id", new Object[]{einfoId});
        for (Map<String, Object> item : items) {
            if(item.get("parent_class_id") != null){
                ProductTypeCheck productTypeCheck = new ProductTypeCheck();
                productTypeCheck.setIsOther((Integer) item.get("is_other"));
                productTypeCheck.setParentTypeId((Integer) item.get("parent_class_id"));
                productTypeCheck.setSubTypeId((Integer) item.get("class_id"));
                if (productTypeCheck.getIsOther() != null && ((Integer) item.get("is_other")).intValue() == 1) {
                    //load other description
                    try {
                        productTypeCheck.setOtherDescription(jdbcTemplate.queryForObject("select name from t_exhibitor_class_other where exhibitor_class_id = " + ((Integer) item.get("id")), String.class));
                    } catch (DataAccessException e) {
                        //jdbcTemplate.update("update t_exhibitor_class set einfo_id = " + (-einfoId) + " where id = " + ((Integer) item.get("id")));
                    }
                }
                productTypeChecks.add(productTypeCheck);
            }else{
//                jdbcTemplate.update("update t_exhibitor_class set einfo_id = " + (-einfoId) + " where id = " + ((Integer) item.get("id")));
            }
        }
        return productTypeChecks;
    }

    public List<ProductType> loadAllProductTypes() {
        List<TProductType> allTypes = hibernateTemplate.find("from TProductType");
        //wrap to DTO
        //filter parent type
        Map<Integer, ProductType> parent_types = new HashMap<Integer, ProductType>();
        for (TProductType type : allTypes) {
            if (type.getLevel().intValue() == 1) {
                ProductType parent = new ProductType();
                parent.setId(type.getId());
                parent.setIsOther(type.getIsOther());
                parent.setLevel(1);
                parent.setTypeName(type.getClassName());
                parent.setTypeNameEN(type.getClassNameEn());
                parent.setOrder(type.getSort());
                parent_types.put(type.getId(), parent);
            }
        }
        //handle children
        for (TProductType type : allTypes) {
            if (type.getLevel().intValue() == 2) {
                //child
                ProductType child = new ProductType();
                child.setId(type.getId());
                child.setIsOther(type.getIsOther());
                child.setLevel(2);
                child.setTypeName(type.getClassName());
                child.setTypeNameEN(type.getClassNameEn());
                child.setOrder(type.getSort());
                parent_types.get(type.getParentId()).getChildrenTypes().add(child);
            }
        }
        List<ProductType> allProductTypes = new ArrayList<ProductType>();
        for (ProductType parentType : parent_types.values()) {
            allProductTypes.add(parentType);
            Collections.sort(parentType.getChildrenTypes());
        }
        Collections.sort(allProductTypes);
        return allProductTypes;
    }

    /**
     * load exhibitor information by exhibitor id;
     *
     * @param exhibitorId
     * @return
     */
    public TExhibitorInfo loadExhibitorInfoByEid(Integer exhibitorId) {
        List<TExhibitorInfo> exhibitorInfos = exhibitorInfoDao.queryByHql("from TExhibitorInfo where eid = ?", new Object[]{exhibitorId});
        return exhibitorInfos.size() > 0 ? exhibitorInfos.get(0) : null;
    }

    @Transactional
    public void saveExhibitorProductClass(List<ProductTypeCheck> selecteds, Integer einfo_id) {
        jdbcTemplate.execute("delete from t_exhibitor_class_other where exhibitor_class_id in (select id from t_exhibitor_class where einfo_id =" + einfo_id + " )");
        jdbcTemplate.execute("delete from t_exhibitor_class where einfo_id = " + einfo_id);
        for (ProductTypeCheck check : selecteds) {
            TExhibitorClass exhibitorClass = new TExhibitorClass();
            exhibitorClass.setCreateTime(new Date());
            exhibitorClass.setClassId(check.getSubTypeId());
            exhibitorClass.setParentClassId(check.getParentTypeId());
            exhibitorClass.setEinfoId(einfo_id);
            hibernateTemplate.save(exhibitorClass);
            //if other
            if (check.getIsOther().intValue() == 1) {
                TExhibitorClassOther exhibitorClassOther = new TExhibitorClassOther();
                exhibitorClassOther.setCreateTime(new Date());
                exhibitorClassOther.setExhibitorClassId(exhibitorClass.getId());
                exhibitorClassOther.setName(check.getOtherDescription());
                hibernateTemplate.save(exhibitorClassOther);
            }
        }
    }

    public String loadBoothNum(Integer eid) {
        List<Map<String, Object>> results = jdbcTemplate.queryForList("select * from t_exhibitor_booth where eid=?", eid);
        if (results.size() > 0) {
            return (String) results.get(0).get("booth_number");
        } else {
            return "";
        }
    }

    public ExhibitorBooth loadBoothInfo(Integer eid) {
        List<Map<String, Object>> results = jdbcTemplate.queryForList("select * from t_exhibitor_booth where eid=?", eid);
        if (results.size() > 0) {
            ExhibitorBooth booth = new ExhibitorBooth();
            booth.setBoothNumber((String) results.get(0).get("booth_number"));
            booth.setExhibitorArea((String) results.get(0).get("exhibitor_area"));
            return booth;
        } else {
            return null;
        }
    }

    public ExhibitorInfoDao getExhibitorInfoDao() {
        return exhibitorInfoDao;
    }

    public void setExhibitorInfoDao(ExhibitorInfoDao exhibitorInfoDao) {
        this.exhibitorInfoDao = exhibitorInfoDao;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public ExhibitorDao getExhibitorDao() {
        return exhibitorDao;
    }

    public void setExhibitorDao(ExhibitorDao exhibitorDao) {
        this.exhibitorDao = exhibitorDao;
    }
}
