package com.zhenhappy.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zhenhappy.dao.*;
import com.zhenhappy.entity.*;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import taobe.tec.jcc.JChineseConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.zhenhappy.dto.CompanyInfoResponse.Locations;
import com.zhenhappy.dto.CompanyInfoResponse.Point;
import com.zhenhappy.dto.ErrorCode;
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
	private ExhibitorClassDao exhibitorClassDao;
	@Autowired
	private ExhibitorInfoDao exhibitorInfoDao;
	@Autowired
	private ExhibitorDao exhibitorDao;

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
	
	public List<ExhibitorList> all(){
		return companyDao.query();
	}

	public List loadExhibitorByPid(List<Integer> ids) throws Exception {
		String pids = StringUtils.join(ids, ", ");
		List<TExhibitorClass> exhibitorClasses = exhibitorClassDao.queryBySql("select * from t_exhibitor_class where class_id in (" + pids + ")", new Object[]{}, TExhibitorClass.class);
		if(exhibitorClasses.size() > 0){
            List<Integer> einfoids = new ArrayList<Integer>();
            for (TExhibitorClass exhibitorClass:exhibitorClasses){
                einfoids.add(exhibitorClass.getEinfoId());
            }
            String einfoid = StringUtils.join(einfoids, ", ");
            List<TExhibitorInfo> exhibitorInfos = exhibitorInfoDao.queryBySql("select * from t_exhibitor_info where einfoid in (" + einfoid + ")", new Object[]{}, TExhibitorInfo.class);
            if(exhibitorInfos.size() > 0){
                List<Integer> eids = new ArrayList<Integer>();
                for (TExhibitorInfo exhibitorInfo:exhibitorInfos){
                    eids.add(exhibitorInfo.getEid());
                }
                String eid = StringUtils.join(eids, ", ");
                List<TExhibitor> exhibitors =  exhibitorDao.queryBySql("select * from t_exhibitor where eid in (" + eid + ")", new Object[]{}, TExhibitor.class);
                if(exhibitors.size() > 0){
                    List<ExhibitorList> exhibitorList = new ArrayList<ExhibitorList>();
                    for (TExhibitor t:exhibitors){
                        List<TExhibitorInfo> infos = exhibitorInfoDao.queryByHql("from TExhibitorInfo where eid=?", new Object[]{t.getEid()});
                        if(infos.size() > 0){
                            ExhibitorList exhibitor = new ExhibitorList();
                            exhibitor.setCompanyId(t.getEid());
                            String country = jdbcTemplate.queryForObject("select ChineseName from WCountryInfo where id = ?", new Object[]{t.getCountry()}, String.class);
                            String countryE = jdbcTemplate.queryForObject("select CountryValue from WCountryInfo where id = ?", new Object[]{t.getCountry()}, String.class);
                            String countryT = jdbcTemplate.queryForObject("select ChineseName_T from WCountryInfo where id = ?", new Object[]{t.getCountry()}, String.class);
                            exhibitor.setCountry(country);
                            exhibitor.setCountryE(countryE);
                            exhibitor.setCountryT(countryT);
                            exhibitor.setCompany(t.getCompany());
                            exhibitor.setCompanyE(t.getCompanye());
                            exhibitor.setCompanyT(t.getCompanyt());
                            exhibitor.setAddress(infos.get(0).getAddress());
                            exhibitor.setAddressE(infos.get(0).getAddressEn());
                            if(StringUtils.isNotEmpty(infos.get(0).getAddress())) exhibitor.setAddressT(JChineseConvertor.getInstance().s2t(infos.get(0).getAddress()));
                            exhibitor.setPostCode(infos.get(0).getZipcode());
                            exhibitor.setTelephone(infos.get(0).getPhone());
                            exhibitor.setFax(infos.get(0).getEmail());
                            exhibitor.setWebsite(infos.get(0).getWebsite());
                            exhibitor.setMainProduct(infos.get(0).getMainProduct());
                            exhibitor.setMainProductE(infos.get(0).getMainProductEn());
                            exhibitorList.add(exhibitor);
                        }
                    }
                    return exhibitorList;
                }
                else{
                    return null;
                }
            }else{
                return null;
            }
        }else{
            return null;
        }

	}

	public List loadExhibitorByCid(List<Integer> ids) throws Exception {
		List<TExhibitor> exhibitors =  exhibitorDao.queryBySql("select * from t_exhibitor where country=?", new Object[]{ids.get(0)}, TExhibitor.class);
		if(exhibitors.size() > 0){
            List<ExhibitorList> exhibitorList = new ArrayList<ExhibitorList>();
            for (TExhibitor t:exhibitors){
                List<TExhibitorInfo> infos = exhibitorInfoDao.queryByHql("from TExhibitorInfo where eid=?", new Object[]{t.getEid()});
                if(infos.size() > 0){
                    ExhibitorList exhibitor = new ExhibitorList();
                    exhibitor.setCompanyId(t.getEid());
                    String country = jdbcTemplate.queryForObject("select ChineseName from WCountryInfo where id = ?", new Object[]{t.getCountry()}, String.class);
                    String countryE = jdbcTemplate.queryForObject("select CountryValue from WCountryInfo where id = ?", new Object[]{t.getCountry()}, String.class);
                    String countryT = jdbcTemplate.queryForObject("select ChineseName_T from WCountryInfo where id = ?", new Object[]{t.getCountry()}, String.class);
                    exhibitor.setCountry(country);
                    exhibitor.setCountryE(countryE);
                    exhibitor.setCountryT(countryT);
                    exhibitor.setCompany(t.getCompany());
                    exhibitor.setCompanyE(t.getCompanye());
                    exhibitor.setCompanyT(t.getCompanyt());
                    exhibitor.setAddress(infos.get(0).getAddress());
                    exhibitor.setAddressE(infos.get(0).getAddressEn());
                    if(StringUtils.isNotEmpty(infos.get(0).getAddress())) exhibitor.setAddressT(JChineseConvertor.getInstance().s2t(infos.get(0).getAddress()));
                    exhibitor.setPostCode(infos.get(0).getZipcode());
                    exhibitor.setTelephone(infos.get(0).getPhone());
                    exhibitor.setFax(infos.get(0).getEmail());
                    exhibitor.setWebsite(infos.get(0).getWebsite());
                    exhibitor.setMainProduct(infos.get(0).getMainProduct());
                    exhibitor.setMainProductE(infos.get(0).getMainProductEn());
                    exhibitorList.add(exhibitor);
                }
            }
            return exhibitorList;
        }else{
            return null;
        }
	}

	public List loadExhibitorByGid(List<Integer> ids) throws Exception {
		List<TExhibitor> exhibitors =  exhibitorDao.queryBySql("select * from t_exhibitor where [group]=?", new Object[]{ids.get(0)}, TExhibitor.class);
		if(exhibitors.size() > 0){
            List<ExhibitorList> exhibitorList = new ArrayList<ExhibitorList>();
            for (TExhibitor t:exhibitors){
                List<TExhibitorInfo> infos = exhibitorInfoDao.queryByHql("from TExhibitorInfo where eid=?", new Object[]{t.getEid()});
                if(infos.size() > 0){
                    ExhibitorList exhibitor = new ExhibitorList();
                    exhibitor.setCompanyId(t.getEid());
                    String country = jdbcTemplate.queryForObject("select ChineseName from WCountryInfo where id = ?", new Object[]{t.getCountry()}, String.class);
                    String countryE = jdbcTemplate.queryForObject("select CountryValue from WCountryInfo where id = ?", new Object[]{t.getCountry()}, String.class);
                    String countryT = jdbcTemplate.queryForObject("select ChineseName_T from WCountryInfo where id = ?", new Object[]{t.getCountry()}, String.class);
                    exhibitor.setCountry(country);
                    exhibitor.setCountryE(countryE);
                    exhibitor.setCountryT(countryT);
                    exhibitor.setCompany(t.getCompany());
                    exhibitor.setCompanyE(t.getCompanye());
                    exhibitor.setCompanyT(t.getCompanyt());
                    exhibitor.setAddress(infos.get(0).getAddress());
                    exhibitor.setAddressE(infos.get(0).getAddressEn());
                    if(StringUtils.isNotEmpty(infos.get(0).getAddress())) exhibitor.setAddressT(JChineseConvertor.getInstance().s2t(infos.get(0).getAddress()));
                    exhibitor.setPostCode(infos.get(0).getZipcode());
                    exhibitor.setTelephone(infos.get(0).getPhone());
                    exhibitor.setFax(infos.get(0).getEmail());
                    exhibitor.setWebsite(infos.get(0).getWebsite());
                    exhibitor.setMainProduct(infos.get(0).getMainProduct());
                    exhibitor.setMainProductE(infos.get(0).getMainProductEn());
                    exhibitorList.add(exhibitor);
                }
            }
            return exhibitorList;
        }else{
            return null;
        }
	}

	public List<TUserCompany> getAllUserCompaniesByUserId(Integer userId){
		List<TUserCompany> t = userCompanyDao.queryBySql("select * from t_user_company where user_id = ?", new Object[]{userId},TUserCompany.class);
		return t;
	}

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
			if (language.intValue() == 1) {
				return companyDao
						.queryPageByJDBCTemplate(
								"select count(*) from ExhibitorList where company like '%" + param + "%'",
								"select e.*,ISNULL(tuc.user_id, 0) as isCollect from (select top "
										+ page.getPageSize()
										+ " * from ExhibitorList where company like '%"
										+ param
										+ "%' and id not in(select top "
										+ page.getPageSize()
										* (page.getPageIndex() - 1)
										+ " id from ExhibitorList where  company like '%"
										+ param
										+ "%' )) e left JOIN (select 1 as user_id,company_id from t_user_company WHERE user_id="
										+ (userId == null ? new Integer(-1) : userId)
										+ ")  tuc on e.ID = tuc.company_id", new Object[] {}, page);
			} else {
				return companyDao
						.queryPageByJDBCTemplate(
								"select count(*) from ExhibitorList where companye like '%" + param + "%'",
								"select e.*,ISNULL(tuc.user_id, 0) as isCollect from (select top "
										+ page.getPageSize()
										+ " * from ExhibitorList where companye like '%"
										+ param
										+ "%' and id not in(select top "
										+ page.getPageSize()
										* (page.getPageIndex() - 1)
										+ " id from ExhibitorList where  companye like '%"
										+ param
										+ "%' )) e left JOIN (select 1 as user_id,company_id from t_user_company WHERE user_id="
										+ (userId == null ? new Integer(-1) : userId)
										+ ")  tuc on e.ID = tuc.company_id", new Object[] {}, page);
			}
		} else if (searchType.intValue() == 2) {
			return companyDao.queryPageByJDBCTemplate("select count(*) from ExhibitorList where exhibitionno like '%"
					+ param + "%'",
					"select e.*,ISNULL(tuc.user_id, 0) as isCollect from (select top " + page.getPageSize()
							+ " * from ExhibitorList where exhibitionno like '%" + param
							+ "%' and id not in(select top " + page.getPageSize() * (page.getPageIndex() - 1)
							+ " id from ExhibitorList where  exhibitionno like '%" + param
							+ "%' )) e left JOIN (select 1 as user_id,company_id from t_user_company WHERE user_id="
							+ (userId == null ? new Integer(-1) : userId) + ")  tuc on e.ID = tuc.company_id",
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
								+ " id from ExhibitorList)) e left JOIN (select 1 as user_id,company_id from t_user_company WHERE user_id="
								+ (userId == null ? new Integer(-1) : userId) + ")  tuc on e.ID = tuc.company_id",
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

	public ExhibitorList getCompanyInfoByBooth(String booth) {
		StringBuilder sqlWhere = new StringBuilder(" where 1=1 ");
		sqlWhere.append(" and t.exhibitionNo=?");
		List<ExhibitorList> companys = companyDao.queryByHql("select t from ExhibitorList t " + sqlWhere.toString(), new Object[] { booth });
		// return remarks;
		if (companys.size() > 0) {
			return companys.get(0);
		}
		return null;
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

	@Autowired
	private UserRemarkDao userRemarkDao;

	public String getRemark(Integer userId, Integer itemId, Integer type) {
		StringBuilder sqlWhere = new StringBuilder(" where 1=1 ");
		sqlWhere.append(" and t.userId=?");
		sqlWhere.append(" and t.itemId=?");
		sqlWhere.append(" and t.remarkType=?");
		List<TUserRemark> remarks = userRemarkDao.queryByHql("select t from TUserRemark t " + sqlWhere.toString(), new Object[] { userId, itemId,
				type });
		if (remarks.size() > 0) {
			return remarks.get(0).getRemark();
		}
		return "";
	}

	public void addRemark(Integer userId, Integer itemId, Integer type, String remark) {
		userRemarkDao.update("delete from t_user_remark where user_id = ? and item_id =?  and type=?", new Object[] { userId, itemId, type },
				QueryFactory.SQL);
		userRemarkDao.create(new TUserRemark(itemId, userId, type, remark));
	}

	public List<TUserRemark> getMyRemark(Integer userId) {
		StringBuilder sqlWhere = new StringBuilder(" where 1=1 ");
		sqlWhere.append(" and t.userId=?");
		sqlWhere.append(" and t.remarkType is not null");
		List<TUserRemark> remarks = userRemarkDao.queryByHql("select t from TUserRemark t " + sqlWhere.toString(), new Object[] { userId });
		return remarks;
	}

	public List<Map<String, Object>> getCountry(Integer local){
		List<Map<String, Object>> country = new ArrayList<Map<String, Object>>();
        switch(local){
            case 1:
                country = jdbcTemplate.queryForList("select id,ChineseName as country from WCountryInfo", new Object[]{});
                break;
            case 2:
                country = jdbcTemplate.queryForList("select id,CountryValue as country from WCountryInfo", new Object[]{});
                break;
            case 3:
                country = jdbcTemplate.queryForList("select id,ChineseName_T as country from WCountryInfo", new Object[]{});
                break;
        }
        int ii = country.size();
        for (int i = ii; i >0; i--){
            List<Map<String, Object>> exhibitor = new ArrayList<Map<String, Object>>();
            exhibitor = jdbcTemplate.queryForList("select eid as exhibitor from t_exhibitor where country = ?", new Object[]{i});
            if(exhibitor.size() == 0) country.remove(i-1);
        }
		return country;
	}

	public List<Map<String, Object>> getGroup(Integer local){
		List<Map<String, Object>> group = new ArrayList<Map<String, Object>>();
		if(local == 1){
			group = jdbcTemplate.queryForList("select id,group_name as groupName from t_exhibitor_group", new Object[]{});
		}else if(local == 2){
			group = jdbcTemplate.queryForList("select id,group_name_en as groupName from t_exhibitor_group", new Object[]{});
		}else if(local == 3){
			group = jdbcTemplate.queryForList("select id,group_name_tw as groupName from t_exhibitor_group", new Object[]{});
		}
		return group;
	}
}
