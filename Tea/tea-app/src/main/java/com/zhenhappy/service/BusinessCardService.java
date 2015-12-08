package com.zhenhappy.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhenhappy.dao.BusinessCardDao;
import com.zhenhappy.dao.CardHeadDao;
import com.zhenhappy.dao.PayCardDao;
import com.zhenhappy.dao.QRCodeInfoDao;
import com.zhenhappy.dao.UserCardDao;
import com.zhenhappy.dto.BusinessCardInfo;
import com.zhenhappy.dto.GetBusinessCardResponse;
import com.zhenhappy.entity.TBusinessCard;
import com.zhenhappy.entity.TCardHead;
import com.zhenhappy.entity.TUserBusinessCard;
import com.zhenhappy.entity.VQrcodeInfo;
import com.zhenhappy.system.Constants;
import com.zhenhappy.system.SystemConfig;
import com.zhenhappy.util.Page;
import com.zhenhappy.util.QueryFactory;

/**
 * User: Haijian Liang Date: 13-11-20 Time: 下午8:04 Function:
 */
@Service
public class BusinessCardService {

	@Autowired
	private BusinessCardDao businessCardDao;

	@Autowired
	private PayCardDao payCardDao;

	@Autowired
	private UserCardDao userCardDao;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private CardHeadDao cardHeadDao;

	@Autowired
	private SystemConfig systemConfig;

	@Autowired
	private QRCodeInfoDao qrCodeInfoDao;

	public PayCardDao getPayCardDao() {
		return payCardDao;
	}

	public void setPayCardDao(PayCardDao payCardDao) {
		this.payCardDao = payCardDao;
	}

	public UserCardDao getUserCardDao() {
		return userCardDao;
	}

	public void setUserCardDao(UserCardDao userCardDao) {
		this.userCardDao = userCardDao;
	}

	@Transactional
	public TBusinessCard addCard(TBusinessCard card) {
		card.setQrcode(new Long(new Date().getTime()).toString());
		card.setIsDelete(0);
		card.setIsdefault(0);
		businessCardDao.create(card);
		return card;
	}

	@SuppressWarnings("unchecked")
	public List<BusinessCardInfo> loadCardsByUserId(Page page, Integer userid) {

		List<Map<String, Object>> customer_cards = businessCardDao
				.queryPageByJDBCTemplate(
						"select count(*) from t_business_card where user_id=? and isDelete <> 1",
						"select tbc.*,tch.card_id as headUrl from t_business_card tbc left join t_card_head tch on tbc.card_id=tch.card_id where tbc.user_id=? and tbc.isDelete <> 1",
						new Object[] { userid }, page);
		List<BusinessCardInfo> cards = new ArrayList<BusinessCardInfo>(customer_cards.size());
		if (customer_cards != null && customer_cards.size() > 0) {
			for (Map<String, Object> card : customer_cards) {
				BusinessCardInfo b = JSONObject.parseObject(JSONArray.toJSONString(card), BusinessCardInfo.class);
				b.setUserId((Integer) card.get("user_id"));
				b.setCardId((Integer) card.get("card_id"));
				if (card.get("headUrl") != null) {
					b.setHeadUrl(systemConfig.getVal("head_base_url") + "cardHeadImg?cardId=" + b.getCardId());
				} else {
					b.setHeadUrl("");
				}
				b.setCardType(Constants.CARD_TYPE_CUSTOMER);
				cards.add(b);
			}
		}
		return cards;
	}

	@Transactional
	public TBusinessCard updateCardInfo(TBusinessCard card) {
		TBusinessCard t = businessCardDao.query(card.getCardId());
		card.setIsdefault(t.getIsdefault());
		card.setQrcode(t.getQrcode());
		BeanUtils.copyProperties(card, t);
		businessCardDao.update(t);
		return t;
	}

	public TBusinessCard getCardById(Integer id) {
		return businessCardDao.query(id);
	}

	@Transactional
	public void deleteCard(Integer cardId) throws Exception {
		businessCardDao.update("update t_business_card set isdelete = 1 where card_id = ?", new Object[] { cardId },
				QueryFactory.SQL);
	}
	
	@Transactional
	public void setDefaultCard(Integer cardId,Integer userId){
		businessCardDao.update("update t_business_card set isdefault = 0 where user_id = ?", new Object[] { userId },
				QueryFactory.SQL);
		businessCardDao.update("update t_business_card set isdefault = 1 where card_id = ? and user_id = ?", new Object[] { cardId ,userId},
				QueryFactory.SQL);
	}

	@Transactional
	public void collectCard(Integer userId, String qrcode, String remark, Integer cardType) {

		TUserBusinessCard usercard = new TUserBusinessCard();

		usercard.setCardId(qrcode);
		usercard.setCreateTime(new Date());
		usercard.setUserId(userId);
		usercard.setRemark(remark);
		usercard.setCardType(cardType);

		userCardDao.create(usercard);
	}

	public GetBusinessCardResponse getCardInfo(String qrcode, Integer cardType, Integer userId) {
		int collectCount = 0;
		if (userId != null) {
			collectCount = userCardDao.queryCount(
					"select count(*) from t_user_business_card where card_id = ? and user_id = ? and cardType = ?",
					new Object[] { qrcode, userId, cardType }, QueryFactory.SQL);
		}
		GetBusinessCardResponse b = null;
		if (cardType.intValue() == 1) {

			List<Map<String, Object>> customer_cards = jdbcTemplate
					.queryForList(
							"select tbc.*,tch.card_id as headUrl from t_business_card tbc left join t_card_head tch on tbc.card_id=tch.card_id where tbc.qrcode = ?",
							new Object[] { qrcode });
			if (customer_cards != null && customer_cards.size() > 0) {
				Map<String, Object> card = customer_cards.get(0);
				b = JSONObject.parseObject(JSONArray.toJSONString(card), GetBusinessCardResponse.class);
				b.setUserId((Integer) card.get("user_id"));
				b.setCardId((Integer) card.get("card_id"));
				b.setCollected(collectCount > 0);
				if (card.get("headUrl") != null) {
					b.setHeadUrl(systemConfig.getVal("head_base_url") + "cardHeadImg?cardId=" + b.getCardId());
				} else {
					b.setHeadUrl("");
				}
				b.setCardType(Constants.CARD_TYPE_CUSTOMER);
			}
		} else {
			b = new GetBusinessCardResponse();
			List<VQrcodeInfo> cards = qrCodeInfoDao.queryByHql("from VQrcodeInfo where qrcode = ?",
					new Object[] { qrcode });
			if (cards.size() > 0) {
				VQrcodeInfo p = cards.get(0);
				b.setCardType(2);
				b.setHeadUrl("");
				b.setCardType(Constants.CARD_TYPE_SYSTEM);
				b.setName(p.getName());
				b.setAddress(p.getAddress());
				b.setCompany(p.getCompany());
				b.setEmail(p.getEmail());
				b.setFax(p.getFax());
				b.setQrcode(p.getQrcode());
				b.setPhone(p.getPhone());
				b.setTelephone(p.getTelephone());
				b.setPosition(p.getPosition());
				b.setWebsite(p.getWebsite());
				b.setCollected(collectCount > 0);
			}
		}
		return b;
	}

	@Transactional
	public void updateRemark(Integer userid, String cardId, String remark, Integer cardType) {
		userCardDao.update(
				"update t_user_business_card set remark=? where user_id = ? and card_id =? and cardType = ?",
				new Object[] { remark, userid, cardId, cardType }, QueryFactory.SQL);
	}

	@Transactional
	public void removeCollectCard(Integer userId, String qrcode, Integer cardType) {
		userCardDao.update("delete from t_user_business_card where user_id = ? and card_id =? and cardType = ?",
				new Object[] { userId, qrcode, cardType }, QueryFactory.SQL);
	}

	@SuppressWarnings("unchecked")
	public List<BusinessCardInfo> loadMyCollectCards(Integer userId, Page page) {
		List<Map<String, Object>> datas = userCardDao.queryPageByJDBCTemplate(
				"select count(*) from t_user_business_card where user_id=?",
				"select * from t_user_business_card where user_id = ? order by create_time desc",
				new Object[] { userId }, page);

		List<String> customers = new ArrayList<String>();
		List<String> systems = new ArrayList<String>();
		if (datas.size() > 0) {
			for (Map<String, Object> data : datas) {
				if (((Integer) data.get("cardType")).intValue() == Constants.CARD_TYPE_CUSTOMER) {
					customers.add("'" + (String) data.get("card_id") + "'");
				} else {
					systems.add("'" + (String) data.get("card_id") + "'");
				}
			}
			List<BusinessCardInfo> cards = new ArrayList<BusinessCardInfo>(datas.size());
			if (customers.size() > 0) {
				List<Map<String, Object>> customer_cards = jdbcTemplate
						.queryForList(
								"select tbc.*,tch.card_id as headUrl from t_business_card tbc left join t_card_head tch on tbc.card_id=tch.card_id where tbc.qrcode in ("
										+ StringUtils.join(customers, ",") + ")", new Object[] {});
				if (customer_cards != null && customer_cards.size() > 0) {
					for (Map<String, Object> card : customer_cards) {
						BusinessCardInfo b = JSONObject.parseObject(JSONArray.toJSONString(card),
								BusinessCardInfo.class);
						b.setUserId((Integer) card.get("user_id"));
						b.setCardId((Integer) card.get("card_id"));
						if (card.get("headUrl") != null) {
							b.setHeadUrl(systemConfig.getVal("head_base_url") + "cardHeadImg?cardId=" + b.getCardId());
						} else {
							b.setHeadUrl("");
						}
						b.setCardType(Constants.CARD_TYPE_CUSTOMER);
						cards.add(b);
					}
				}

			}
			List<VQrcodeInfo> system_cards = null;
			if (systems.size() > 0) {
				system_cards = qrCodeInfoDao.queryBySql(
						"select * from v_qrcodeinfo where qrcode in(" + StringUtils.join(systems, ",") + ")",
						new Object[] {}, VQrcodeInfo.class);
			}

			if (system_cards != null && system_cards.size() > 0) {
				for (VQrcodeInfo p : system_cards) {
					BusinessCardInfo b = new BusinessCardInfo();
					b.setHeadUrl("");
					b.setCardType(Constants.CARD_TYPE_SYSTEM);
					b.setName(p.getName());
					b.setAddress(p.getAddress());
					b.setCompany(p.getCompany());
					b.setEmail(p.getEmail());
					b.setFax(p.getFax());
					b.setQrcode(p.getQrcode());
					b.setPhone(p.getPhone());
					b.setTelephone(p.getTelephone());
					b.setPosition(p.getPhone());
					b.setWebsite(p.getWebsite());
					cards.add(b);
				}
			}
			return cards;
		} else {
			return new ArrayList<BusinessCardInfo>(0);
		}
	}

	@Transactional
	public void uploadHead(Integer cardId, byte[] headByteArray) {
		TCardHead head = cardHeadDao.query(cardId);
		if (head != null) {
			head.setHeadImg(headByteArray);
			cardHeadDao.update(head);
		} else {
			TCardHead headtemp = new TCardHead();
			headtemp.setCardId(cardId);
			headtemp.setHeadImg(headByteArray);
			cardHeadDao.create(headtemp);
		}
	}

	public byte[] loadImg(Integer cardId) {
		return cardHeadDao.query(cardId).getHeadImg();
	}

	public BusinessCardDao getBusinessCardDao() {
		return businessCardDao;
	}

	public void setBusinessCardDao(BusinessCardDao businessCardDao) {
		this.businessCardDao = businessCardDao;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
