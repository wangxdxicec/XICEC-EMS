package com.zhenhappy.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.zhenhappy.dao.CompanyDao;
import com.zhenhappy.dao.UserCompanyDao;
import com.zhenhappy.dto.CompanyInfoResponse.Locations;
import com.zhenhappy.dto.CompanyInfoResponse.Point;
import com.zhenhappy.dto.ErrorCode;
import com.zhenhappy.entity.ExhibitorList;
import com.zhenhappy.entity.TUserCompany;
import com.zhenhappy.exception.ReturnException;
import com.zhenhappy.system.SystemConfig;
import com.zhenhappy.util.Page;
import com.zhenhappy.util.QueryFactory;

/**
 * User: Haijian Liang Date: 13-11-21 Time: 下午8:23 Function:
 */
@Service
public class CompanyService {

	@Autowired
	private CompanyDao companyDao;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SystemConfig systemConfig;

	public UserCompanyDao getUserCompanyDao() {
		return userCompanyDao;
	}

	public void setUserCompanyDao(UserCompanyDao userCompanyDao) {
		this.userCompanyDao = userCompanyDao;
	}

	@Autowired
	private UserCompanyDao userCompanyDao;

	/**
	 * @param searchType
	 *            1展商名称 2展位号 3产品类别
	 * @param language
	 *            1为中文，2英文
	 * @param param
	 * @return
	 */
	public List queryCompanies(Integer searchType, Integer language, String param, Page page,
			Integer userId) {
		if (searchType.intValue() == 1) {
			if (language.intValue() == 3) {
				return companyDao
						.queryPageByJDBCTemplate(
								"select count(*) from ExhibitorList where company like '%" + param + "%'",
								"select e.*,ISNULL(tuc.user_id, 0) as isCollect from (select top "
										+ page.getPageSize()
										+ " * from ExhibitorList where ShortCompanyNameT like '%"
										+ param
										+ "%' and id not in(select top "
										+ page.getPageSize()
										* (page.getPageIndex() - 1)
										+ " id from ExhibitorList where  ShortCompanyNameT like '%"
										+ param
										+ "%'  order by exhibitionNo asc) order by exhibitionNo asc) e left JOIN (select 1 as user_id,company_id from t_user_company WHERE user_id="
										+ (userId == null ? new Integer(-1) : userId)
										+ ")  tuc on e.ID = tuc.company_id order by exhibitionNo asc", new Object[] {}, page);
			} else if(language.intValue() == 2){
				return companyDao
						.queryPageByJDBCTemplate(
								"select count(*) from ExhibitorList where companye like '%" + param + "%'",
								"select e.*,ISNULL(tuc.user_id, 0) as isCollect from (select top "
										+ page.getPageSize()
										+ " * from ExhibitorList where ShortCompanyNameE like '%"
										+ param
										+ "%' and id not in(select top "
										+ page.getPageSize()
										* (page.getPageIndex() - 1)
										+ " id from ExhibitorList where  ShortCompanyNameE like '%"
										+ param
										+ "%'  order by exhibitionNo asc) order by exhibitionNo asc) e left JOIN (select 1 as user_id,company_id from t_user_company WHERE user_id="
										+ (userId == null ? new Integer(-1) : userId)
										+ ")  tuc on e.ID = tuc.company_id order by exhibitionNo asc", new Object[] {}, page);
			}else{
				return companyDao
						.queryPageByJDBCTemplate(
								"select count(*) from ExhibitorList where company like '%" + param + "%'",
								"select e.*,ISNULL(tuc.user_id, 0) as isCollect from (select top "
										+ page.getPageSize()
										+ " * from ExhibitorList where ShortCompanyName like '%"
										+ param
										+ "%' and id not in(select top "
										+ page.getPageSize()
										* (page.getPageIndex() - 1)
										+ " id from ExhibitorList where  ShortCompanyName like '%"
										+ param
										+ "%'  order by exhibitionNo asc) order by exhibitionNo asc) e left JOIN (select 1 as user_id,company_id from t_user_company WHERE user_id="
										+ (userId == null ? new Integer(-1) : userId)
										+ ")  tuc on e.ID = tuc.company_id order by exhibitionNo asc", new Object[] {}, page);
			}
		} else if (searchType.intValue() == 2) {
			return companyDao.queryPageByJDBCTemplate("select count(*) from ExhibitorList where exhibitionno like '%"
					+ param + "%'",
					"select e.*,ISNULL(tuc.user_id, 0) as isCollect from (select top " + page.getPageSize()
							+ " * from ExhibitorList where exhibitionno like '%" + param
							+ "%' and id not in(select top " + page.getPageSize() * (page.getPageIndex() - 1)
							+ " id from ExhibitorList where  exhibitionno like '%" + param
							+ "%'  order by exhibitionNo asc) order by exhibitionNo asc) e left JOIN (select 1 as user_id,company_id from t_user_company WHERE user_id="
							+ (userId == null ? new Integer(-1) : userId) + ")  tuc on e.ID = tuc.company_id order by exhibitionNo asc",
					new Object[] {}, page);
		} else {
			return ListUtils.EMPTY_LIST;
		}
	}

	public List<Map<String, Object>> loadCompaniesByPage(Page page, Integer userId) {
		List<Map<String, Object>> ctemps = companyDao
				.queryPageByJDBCTemplate(
						"select count(1) from exhibitorlist",
						"select e.*,ISNULL(tuc.user_id, 0) as isCollect from (select top "
								+ page.getPageSize()
								+ " * from ExhibitorList where id not in(select top "
								+ page.getPageSize()
								* (page.getPageIndex() - 1)
								+ " id from ExhibitorList order by exhibitionNo asc)  order by exhibitionNo asc) e left JOIN (select 1 as user_id,company_id from t_user_company WHERE user_id="
								+ (userId == null ? new Integer(-1) : userId) + ")  tuc on e.ID = tuc.company_id order by e.exhibitionNo asc",
						new Object[] {}, page);
		return ctemps;
	}

	@Transactional
	public void collectCompany(Integer userId, Integer companyId, String remark) throws Exception {
		Integer count = (Integer) companyDao.queryForObject(
				"select count(1) from t_user_company where user_id=? and company_id=?", new Object[] { userId,
						companyId }, QueryFactory.SQL);
		if (count.intValue() > 0) {
			throw new ReturnException(ErrorCode.ALREADY_COLLECT);
		} else {
			TUserCompany userCompany = new TUserCompany();
			userCompany.setRemark(remark);
			userCompany.setUserId(userId);
			userCompany.setDelete(0);
			userCompany.setCreateTime(new Date());
			userCompany.setCompanyId(companyId);
			userCompanyDao.create(userCompany);
		}
	}

	@Transactional
	public void addCollectRemark(Integer userId, Integer companyId, String remark) throws Exception {
		Integer count = (Integer) companyDao.queryForObject(
				"select count(1) from t_user_company where user_id=? and company_id=?", new Object[] { userId,
						companyId }, QueryFactory.SQL);
		if (count.intValue() > 0) {
			companyDao.update("update t_user_company set remark = ? where user_id = ? and company_id = ?",
					new Object[] { remark, userId, companyId }, QueryFactory.SQL);
		} else {
			TUserCompany userCompany = new TUserCompany();
			userCompany.setRemark(remark);
			userCompany.setUserId(userId);
			userCompany.setDelete(0);
			userCompany.setCreateTime(new Date());
			userCompany.setCompanyId(companyId);
			userCompanyDao.create(userCompany);
		}
	}

	public String getCompanyRemarkByUidCompanyId(Integer userId, Integer companyId) {
		try {
			String remark = jdbcTemplate.queryForObject(
					"select remark from t_user_company where user_id = ? and company_id =?", new Object[] { userId,
							companyId }, String.class);
			return remark;
		} catch (EmptyResultDataAccessException e) {
			return "";
		}
	}

	public boolean checkCollect(Integer userId, Integer companyId) {
		int count = jdbcTemplate.queryForInt("select count(*) from t_user_company where user_id =? and company_id= ?",
				new Object[] { userId, companyId });
		return count > 0 ? true : false;
	}

	@Transactional
	public void cancelCollectCompany(Integer userId, Integer companyId) {
		userCompanyDao.update("delete from t_user_company where user_id = ? and company_id =?", new Object[] { userId,
				companyId }, QueryFactory.SQL);
	}

	public List<ExhibitorList> myCollectCompanies(Integer userId, Page page) {
		return companyDao.queryPageByHQL("select count(*) from TUserCompany tuc where tuc.userId=?",
				"select e from ExhibitorList e,TUserCompany tuc where tuc.userId=? and tuc.companyId=e.id",
				new Object[] { userId }, page);
	}

	public List<Map<String, Object>> getCompaniesByType(Integer userId, String fatherTypeCode, String childTypeCode,
			Page page) {
		String r = fatherTypeCode + "-" + childTypeCode;

		return companyDao.queryPageByJDBCTemplate("select count(*) from ExhibitorList where productTypeDetail like '%"
				+ r + "%'",
				"select e.*,ISNULL(tuc.user_id, 0) as isCollect from (select top " + page.getPageSize()
						+ " * from ExhibitorList where productTypeDetail like '%" + r + "%' and id not in(select top "
						+ page.getPageSize() * (page.getPageIndex() - 1)
						+ " id from ExhibitorList where  productTypeDetail like '%" + r
						+ "%' )) e left JOIN (select 1 as user_id,company_id from t_user_company WHERE user_id="
						+ (userId == null ? new Integer(-1) : userId) + ")  tuc on e.ID = tuc.company_id",
				new Object[] {}, page);
	}

	public ExhibitorList getCompanyInfo(Integer companyId) {
		return companyDao.query(companyId);
	}

	public Locations getExhibitorNum(Integer companyId,Integer machineType) {
		String tec_table_name = machineType.intValue()==1?"t_exhibitorno_cordinary_ios":"t_exhibitorno_cordinary_android";
		Locations locations = new Locations();
		String th = systemConfig.getVal("usercode_zhanhui");
		List<Map<String, Object>> temp = jdbcTemplate
				.queryForList(
						"select tec.exhibitorNo as zhanweiNum,tec.x,tec.y,er.serviceDesk as serviceDesk from ExhibitorRelation er,"+tec_table_name+" tec where er.ExhibitorListID = ? and er.LocationNo = tec.ExhibitorNo and er.Th = ?",
						new Object[] { companyId, th });
		List<Point> points = JSONArray.parseArray(JSONArray.toJSONString(temp), Point.class);
		locations.setPoints(points);
		if (points.size() > 0) {
			List<String> desks = new ArrayList<String>();
			for (Point point : points) {
				desks.add("'" + point.getServiceDesk() + "'");
			}
			String sql = "select distinct tileId from t_tile_servicedesk where serviceDesk in ("
					+ StringUtils.join(desks, ",") + ")";
			List<Integer> tileIds = jdbcTemplate.queryForList(sql, Integer.class);
			if (tileIds.size() > 0) {
				locations.setTileId(tileIds.get(0));
			}
		}
		return locations;
	}

	public CompanyDao getCompanyDao() {
		return companyDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}
}
