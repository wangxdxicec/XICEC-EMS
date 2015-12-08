package com.zhenhappy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhenhappy.dto.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhenhappy.dto.CompanyInfoResponse.Locations;
import com.zhenhappy.entity.ExhibitorList;
import com.zhenhappy.entity.TProduct;
import com.zhenhappy.entity.TUserCompany;
import com.zhenhappy.entity.TUserRemark;
import com.zhenhappy.exception.ReturnException;
import com.zhenhappy.service.CompanyService;
import com.zhenhappy.service.ProductService;
import com.zhenhappy.system.SystemConfig;
import com.zhenhappy.util.CMSUtils;
import com.zhenhappy.util.LogUtil;
import com.zhenhappy.util.Page;

/**
 * User: Haijian Liang Date: 13-11-21 Time: 下午10:25 Function:
 */
@Controller
@RequestMapping(value = "/client/user")
public class CompanyController extends BaseController {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private SystemConfig systemConfig;
	@Autowired
	private ProductService productService;

	public CompanyService getCompanyService() {
		return companyService;
	}

	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	public CompanyController() {
		super(CompanyController.class);
	}

	@RequestMapping(value = "companyInfo", method = RequestMethod.POST)
	@ResponseBody
	public CompanyInfoResponse getCompanyInfo(@RequestBody GetCompanyInfoRequest request) {
		LogUtil.logRequest(log, this, request);
		CompanyInfoResponse response = new CompanyInfoResponse();
		try {
			ExhibitorList company = null;
			if (StringUtils.isNotBlank(request.getBooth())) {
				company = companyService.getCompanyInfoByBooth(request.getBooth());
			} else {
				company = companyService.getCompanyInfo(request.getCompanyId());
			}
			if (null == company) {
				company = new ExhibitorList();
			}
			if (request.getUserId() != null) {
				response.setCollected(companyService.checkCollect(request.getUserId(), request.getCompanyId()));
			}

//			company.setLogoUrl(systemConfig.getVal("cdn_zixun_base_url") + "/logo" + company.getLogoUrl());
			company.setLogoUrl("http://m.stonefair.org.cn/manager/user/showLogo?eid=" + company.getCompanyId());
			BeanUtils.copyProperties(company, response);
			response.setCompanyId(company.getCompanyId());
			List<TProduct> products = productService.queryByEid(company.getCompanyId());
			List<ProductInfoResponse> items = new ArrayList<ProductInfoResponse>();
			for (TProduct p : products) {
				ProductInfoResponse item = new ProductInfoResponse();
				BeanUtils.copyProperties(p, item);
				items.add(item);
			}
			response.setProducts(items);

			Locations locations = companyService.getExhibitorNum(request.getCompanyId(), request.getMachineType());
			if (locations.getPoints().size() > 0) {
				response.setExhibitionNo(StringUtils.join(locations.getPoints(), ","));
			}
			response.setPoints(locations.getPoints());
			response.setTileId(locations.getTileId());
			response.setRemark(companyService.getRemark(request.getUserId(), request.getCompanyId(), 1));
		} catch (Exception e) {
			log.error("获得展商详情失败", e);
			response.setErrorCode(ErrorCode.ERROR);
		}

		return response;
	}

	@RequestMapping(value = "getMyNotes", method = RequestMethod.POST)
	@ResponseBody
	public RemarksResponse getMyNotes(@RequestBody GetMyNotesRequest request) {
		LogUtil.logRequest(log, this, request);
		RemarksResponse response = new RemarksResponse();
		try {
			List<RemarkInfoResponse> items = new ArrayList<RemarkInfoResponse>();
			List<ExhibitorList> exhibitorLists = companyService.all();
			if (null != request.getCompanyIds()) {
				for (Integer companyId : request.getCompanyIds()) {
					RemarkInfoResponse remarkInfoResponse = new RemarkInfoResponse();
					for (ExhibitorList exhibitorList : exhibitorLists) {
						if (exhibitorList.getCompanyId().equals(companyId)) {
							CompanyInfoResponse company = new CompanyInfoResponse();
							BeanUtils.copyProperties(exhibitorList, company);
							remarkInfoResponse.setCompany(company);
							remarkInfoResponse.setRemarkType(1);
							items.add(remarkInfoResponse);
							break;
						}
					}
				}
			}
			if (null != request.getWscIds()) {
				for (Integer wscId : request.getWscIds()) {
					RemarkInfoResponse remarkInfoResponse = new RemarkInfoResponse();
					for (WscInfoResponse wsc : CMSUtils.wscs) {
						if (wsc.getWscId().equals(wscId)) {
							remarkInfoResponse.setWsc(wsc);
							remarkInfoResponse.setRemarkType(0);
							items.add(remarkInfoResponse);
							break;
						}
					}
				}
			}
			response.setItems(items);
		} catch (Exception e) {
			log.error("获得文本备注失败", e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

	@RequestMapping(value = "addRemark", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse addRemark(@RequestBody SubTextRemarkRequest request) {
		LogUtil.logRequest(log, this, request);
		BaseResponse response = new BaseResponse();
		try {
			companyService.addRemark(request.getUserId(), request.getItemId(), request.getType(), request.getRemark());
		} catch (Exception e) {
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

	@RequestMapping(value = "collectCompany", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse collectCompany(@RequestBody DellWithCompanyRequest request) {
		LogUtil.logRequest(log, this, request);
		BaseResponse response = new BaseResponse();
		try {
			companyService.collectCompany(request.getUserId(), request.getCompanyId(), request.getRemark());
		} catch (ReturnException e) {
			log.error(e);
			response.setResultCode(e.getErrorCode());
			response.setDes(e.getMessage());
		} catch (Exception e) {
			log.error(e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

	@RequestMapping(value = "cancelCollectCompany", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse cancelCollectCompany(@RequestBody DellWithCompanyRequest request) {
		LogUtil.logRequest(log, this, request);
		BaseResponse response = new BaseResponse();
		try {

			companyService.cancelCollectCompany(request.getUserId(), request.getCompanyId());

		} catch (Exception e) {
			log.error(e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

	@RequestMapping(value = "allCompaniesNew", method = RequestMethod.POST)
	@ResponseBody
	public CompaniesResponse loadAllCompaniesNew(@RequestBody GetCompaniesRequest request) {
		LogUtil.logRequest(log, this, request);
		CompaniesResponse response = new CompaniesResponse();
		try {
			List<ExhibitorList> exhibitorLists = companyService.all();
			List<BaseCompanyInfoDto> items = new ArrayList<BaseCompanyInfoDto>();
			List<TUserCompany> allUserCompanies = new ArrayList<TUserCompany>();
			if (null != request.getUserId()) {
				allUserCompanies = companyService.getAllUserCompaniesByUserId(request.getUserId());
			}
			List<TProduct> products = productService.all();
			for (ExhibitorList exhibitorList : exhibitorLists) {
				BaseCompanyInfoDto item = new BaseCompanyInfoDto();
				//BeanUtils.copyProperties(exhibitorList, item);
				//item.setLogoUrl(systemConfig.getVal("cdn_zixun_base_url") + "/logo" + item.getLogoUrl());
				item.setIsCollect(0);
				if (null != request.getUserId()) {
					for (TUserCompany userCompany : allUserCompanies) {
						if ( exhibitorList.getCompanyId().equals(userCompany.getCompanyId())) {
							item.setIsCollect(1);
							break;
						}
					}
				}
				TProduct product = null;
				for (TProduct p : products) {
					if (p.getEid().equals(item.getCompanyId())) {
						product = p;
					}
				}
				item.setCompanyId(exhibitorList.getCompanyId());
				item.setExhibitionNo(exhibitorList.getExhibitionNo());
				if (1 == request.getLocal()) {// zh
					item.setCompany(exhibitorList.getCompany());
					item.setCountry(exhibitorList.getCountry());
					item.setProduct(null == product ? "" : product.getName());
					item.setGroupName(exhibitorList.getGroupName());
				} else if (2 == request.getLocal()) {// en
					item.setCompany(exhibitorList.getCompanyE());
					item.setCountry(exhibitorList.getCountryE());
					item.setProduct(null == product ? "" : product.getNameE());
					item.setGroupName(exhibitorList.getGroupNameE());
				} else if (3 == request.getLocal()) {
					item.setCompany(exhibitorList.getCompanyT());
					item.setCountry(exhibitorList.getCountryT());
					item.setProduct(null == product ? "" : product.getNameT());
					item.setGroupName(exhibitorList.getGroupNameT());
				}
				item.setCountryE(exhibitorList.getCountryE());
				item.setGroupNameE(exhibitorList.getGroupNameE());
				if (null != request.getCondition() &&
                        !item.getCompany().toLowerCase().contains(request.getCondition().toLowerCase()) &&
                        !item.getExhibitionNo().toLowerCase().contains(request.getCondition().toLowerCase())) {
					continue;
				}
				items.add(item);
			}
			response.setCompanies(items);
		} catch (Exception e) {
			log.error(e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

	@RequestMapping(value = "allCompaniesNewTwo", method = RequestMethod.POST)
	@ResponseBody
	public CompaniesResponse loadAllCompaniesNewTwo(@RequestBody GetCompaniesRequest request) {
		LogUtil.logRequest(log, this, request);
		CompaniesResponse response = new CompaniesResponse();
		try {
			List<ExhibitorList> exhibitorLists = new ArrayList<ExhibitorList>();
			if (request.getType() == 1) {
				exhibitorLists = companyService.loadExhibitorByPid(request.getIds());
			}else if(request.getType() == 2){
				exhibitorLists = companyService.loadExhibitorByCid(request.getIds());
			}else if(request.getType() == 3){
				exhibitorLists = companyService.loadExhibitorByGid(request.getIds());
			}
			List<BaseCompanyInfoDto> items = new ArrayList<BaseCompanyInfoDto>();
			List<TUserCompany> allUserCompanies = new ArrayList<TUserCompany>();
			if (null != request.getUserId()) {
				allUserCompanies = companyService.getAllUserCompaniesByUserId(request.getUserId());
			}
			List<TProduct> products = productService.all();
			for (ExhibitorList exhibitorList : exhibitorLists) {
				BaseCompanyInfoDto item = new BaseCompanyInfoDto();
				//BeanUtils.copyProperties(exhibitorList, item);
				//item.setLogoUrl(systemConfig.getVal("cdn_zixun_base_url") + "/logo" + item.getLogoUrl());
				item.setIsCollect(0);
				if (null != request.getUserId()) {
					for (TUserCompany userCompany : allUserCompanies) {
						if ( exhibitorList.getCompanyId().equals(userCompany.getCompanyId())) {
							item.setIsCollect(1);
							break;
						}
					}
				}
				TProduct product = null;
				for (TProduct p : products) {
					if (p.getEid().equals(item.getCompanyId())) {
						product = p;
					}
				}
				item.setCompanyId(exhibitorList.getCompanyId());
				item.setExhibitionNo(exhibitorList.getExhibitionNo());
				if (1 == request.getLocal()) {// zh
					item.setCompany(exhibitorList.getCompany());
					item.setCountry(exhibitorList.getCountry());
					item.setProduct(null == product ? "" : product.getName());
					item.setGroupName(exhibitorList.getGroupName());
				} else if (2 == request.getLocal()) {// en
					item.setCompany(exhibitorList.getCompanyE());
					item.setCountry(exhibitorList.getCountryE());
					item.setProduct(null == product ? "" : product.getNameE());
					item.setGroupName(exhibitorList.getGroupNameE());
				} else if (3 == request.getLocal()) {
					item.setCompany(exhibitorList.getCompanyT());
					item.setCountry(exhibitorList.getCountryT());
					item.setProduct(null == product ? "" : product.getNameT());
					item.setGroupName(exhibitorList.getGroupNameT());
				}
				item.setCountryE(exhibitorList.getCountryE());
				item.setGroupNameE(exhibitorList.getGroupNameE());
                if (null != request.getCondition() && !item.getCompany().contains(request.getCondition()) && !item.getExhibitionNo().contains(request.getCondition())) {
                    continue;
                }
				items.add(item);
			}
			response.setCompanies(items);
		} catch (Exception e) {
			log.error(e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

	@RequestMapping(value = "allCompanies", method = RequestMethod.POST)
	@ResponseBody
	public CompaniesResponse loadAllCompanies(@RequestBody GetCompaniesRequest request) {
		LogUtil.logRequest(log, this, request);
		CompaniesResponse response = new CompaniesResponse();
		try {
			Page page = new Page(request.getPageIndex(), request.getPageSize());
			List<Map<String, Object>> companies = companyService.loadCompaniesByPage(page, request.getUserId());
			response.setHasNext(page.getHasNext());
			response.setCompanies(trans(companies));
		} catch (Exception e) {
			log.error(e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

	@RequestMapping(value = "myCollectCompanies", method = RequestMethod.POST)
	@ResponseBody
	public MyCollectCompanyResponse myCollectCompanies(@RequestBody PageDataRequest request) {
		LogUtil.logRequest(log, this, request);
		MyCollectCompanyResponse response = new MyCollectCompanyResponse();
		try {
			Page page = new Page(request.getPageIndex(), request.getPageSize());
			List<ExhibitorList> companies = companyService.myCollectCompanies(request.getUserId(), page);
			List<BaseCompanyInfoDto> bc = new ArrayList<BaseCompanyInfoDto>(companies.size());
			for (ExhibitorList e : companies) {
				BaseCompanyInfoDto b = new BaseCompanyInfoDto();
				BeanUtils.copyProperties(e, b);
				// b.setLogoUrl(systemConfig.getVal("cdn_zixun_base_url") +
				// "/logo" + b.getLogoUrl());
				b.setIsCollect(1);
				bc.add(b);
			}
			response.setHasNext(page.getHasNext());
			response.setCompanies(bc);
		} catch (Exception e) {
			log.error(e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "queryCompany", method = RequestMethod.POST)
	@ResponseBody
	public CompaniesResponse queryCompany(@RequestBody QueryCompanyRequest request) {

		LogUtil.logRequest(log, this, request);
		CompaniesResponse response = new CompaniesResponse();
		try {
			Page page = new Page(request.getPageIndex(), request.getPageSize());
			List<Map<String, Object>> companies = companyService.queryCompanies(request.getQueryType(), request.getLocal(), request.getCondition(),
					page, request.getUserId());
			response.setHasNext(page.getHasNext());
			List<BaseCompanyInfoDto> companyInfoDtos = trans(companies);
			if (request.getFromType() != null && request.getFromType().intValue() == 2) {
				for (BaseCompanyInfoDto companyInfoDto : companyInfoDtos) {
					Locations locations = companyService.getExhibitorNum(companyInfoDto.getCompanyId(), request.getMachineType());
					// companyInfoDto.setPoints(locations.getPoints());
					// companyInfoDto.setMapId(locations.getTileId());
				}
			}
			response.setCompanies(companyInfoDtos);
		} catch (Exception e) {
			log.error("查询出错", e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

	@RequestMapping(value = "getCompaniesByType", method = RequestMethod.POST)
	@ResponseBody
	public CompaniesResponse getCompaniesByType(@RequestBody GetCompaniesByTypeRequest request) {
		LogUtil.logRequest(log, this, request);
		CompaniesResponse response = new CompaniesResponse();
		try {
			Page page = new Page(request.getPageIndex(), request.getPageSize());
			List<Map<String, Object>> datas = companyService.getCompaniesByType(request.getUserId(), request.getFatherTypeCode(),
					request.getChildTypeCode(), page);
			response.setCompanies(trans(datas));
			response.setHasNext(page.getHasNext());
		} catch (Exception e) {
			log.error("通过产品类型获得展商列表失败", e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

	/**
	 * 获得展商文本备注。
	 */
	@RequestMapping(value = "getCompanyTestRemark", method = RequestMethod.POST)
	@ResponseBody
	public GetCollectCompanyRemarkResponse getTextRemark(@RequestBody GetTextRemarkRequest request) {
		LogUtil.logRequest(log, this, request);
		GetCollectCompanyRemarkResponse response = new GetCollectCompanyRemarkResponse();
		try {
			String remark = companyService.getRemark(request.getUserId(), request.getItemId(), request.getType());
			response.setRemark(remark);
		} catch (Exception e) {
			log.error("获得文本备注失败", e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

	/**
	 * 获得备注。
	 */
	@RequestMapping(value = "getMyRemark", method = RequestMethod.POST)
	@ResponseBody
	public RemarksResponse getMyRemark(@RequestBody GetTextRemarkRequest request) {
		LogUtil.logRequest(log, this, request);
		RemarksResponse response = new RemarksResponse();
		try {
			List<RemarkInfoResponse> items = new ArrayList<RemarkInfoResponse>();
			List<ExhibitorList> exhibitorLists = new ArrayList<ExhibitorList>();
			List<TUserRemark> remarks = companyService.getMyRemark(request.getUserId());
			if (remarks.size() > 0) {
				exhibitorLists = companyService.all();
			}
			for (TUserRemark remark : remarks) {
				RemarkInfoResponse remarkInfoResponse = new RemarkInfoResponse();
				remarkInfoResponse.setRemarkType(remark.getRemarkType());
				for (ExhibitorList exhibitorList : exhibitorLists) {
					if (remark.getRemarkType().equals(1) && exhibitorList.getCompanyId().equals(remark.getItemId())) {
						CompanyInfoResponse company = new CompanyInfoResponse();
						BeanUtils.copyProperties(exhibitorList, company);
						remarkInfoResponse.setCompany(company);
						break;
					}
				}
				for (WscInfoResponse wsc : CMSUtils.wscs) {
					if (remark.getRemarkType().equals(0) && wsc.getWscId().equals(remark.getItemId())) {
						remarkInfoResponse.setWsc(wsc);
						break;
					}
				}
				if (null != remarkInfoResponse.getCompany() || null != remarkInfoResponse.getWsc()) {
					items.add(remarkInfoResponse);
				}
			}
			response.setItems(items);
		} catch (Exception e) {
			log.error("获得文本备注失败", e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

	private List<BaseCompanyInfoDto> trans(List<Map<String, Object>> companies) {
		List<BaseCompanyInfoDto> baseCompanies = new ArrayList<BaseCompanyInfoDto>(companies.size());
		BaseCompanyInfoDto dto = null;
		for (Map<String, Object> data : companies) {
			dto = new BaseCompanyInfoDto();
			dto.setExhibitionNo((String) data.get("ExhibitionNo"));
			dto.setCompanyId((Integer) data.get("ID"));
			dto.setCompany((String) data.get("Company"));
			// dto.setCompanyE((String) data.get("CompanyE"));
			dto.setCountry((String) data.get("Country"));
			dto.setIsCollect((Integer) data.get("isCollect"));
			// dto.setFirstCompany((String) data.get("FirstCompany"));
			// dto.setFirstCompanyE((String) data.get("FirstCompanyE"));
			// dto.setLogoUrl(systemConfig.getVal("cdn_zixun_base_url") +
			// "/logo" + (String) data.get("LogoURL"));
			baseCompanies.add(dto);
		}
		return baseCompanies;
	}

	public SystemConfig getSystemConfig() {
		return systemConfig;
	}

	public void setSystemConfig(SystemConfig systemConfig) {
		this.systemConfig = systemConfig;
	}

	@ResponseBody
	@RequestMapping(value = "getCountry", method = RequestMethod.POST)
	public CountryResponse getCountry(@RequestBody GetCountryRequest request) {
		LogUtil.logRequest(log, this, request);
		CountryResponse response = new CountryResponse();
		try {
			List<Map<String, Object>> country = companyService.getCountry(request.getLocal());
			response.setCountry(country);
		} catch (Exception e) {
			log.error("获得文本备注失败", e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "getGroup", method = RequestMethod.POST)
	public GroupResponse getGroup(@RequestBody GetGroupRequest request) {
		LogUtil.logRequest(log, this, request);
		GroupResponse response = new GroupResponse();
		try {
			List<Map<String, Object>> group = companyService.getGroup(request.getLocal());
			response.setGroup(group);
		} catch (Exception e) {
			log.error("获得文本备注失败", e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}
}
