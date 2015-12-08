package com.zhenhappy.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhenhappy.dto.BaseCompanyInfoDto;
import com.zhenhappy.dto.BaseResponse;
import com.zhenhappy.dto.CompaniesResponse;
import com.zhenhappy.dto.CompanyInfoResponse;
import com.zhenhappy.dto.CompanyInfoResponse.Locations;
import com.zhenhappy.dto.DellWithCompanyRequest;
import com.zhenhappy.dto.ErrorCode;
import com.zhenhappy.dto.GetCollectCompanyRemarkResponse;
import com.zhenhappy.dto.GetCompaniesByTypeRequest;
import com.zhenhappy.dto.GetCompaniesRequest;
import com.zhenhappy.dto.GetCompanyInfoRequest;
import com.zhenhappy.dto.MyCollectCompanyResponse;
import com.zhenhappy.dto.PageDataRequest;
import com.zhenhappy.dto.QueryCompanyRequest;
import com.zhenhappy.entity.ExhibitorList;
import com.zhenhappy.exception.ReturnException;
import com.zhenhappy.service.CompanyService;
import com.zhenhappy.system.SystemConfig;
import com.zhenhappy.util.ApplicationContextUtil;
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
            ExhibitorList company = companyService.getCompanyInfo(request.getCompanyId());
            if (request.getUserId() != null) {
                response.setCollected(companyService.checkCollect(request.getUserId(), request.getCompanyId()));
            }
            company.setLogoUrl(systemConfig.getVal("company_img_action_url") + "/companyImage?imageUrl=" + company.getLogoUrl());
            BeanUtils.copyProperties(company, response);
            response.setCompanyId(company.getCompanyId());

            Locations locations = companyService.getExhibitorNum(request.getCompanyId(), request.getMachineType());
            if (locations.getPoints().size() > 0) {
                response.setExhibitionNo(StringUtils.join(locations.getPoints(), ","));
            }
            response.setPoints(locations.getPoints());
            response.setTileId(locations.getTileId());
        } catch (Exception e) {
            log.error("获得展商详情失败", e);
            response.setErrorCode(ErrorCode.ERROR);
        }

        return response;
    }

    @RequestMapping(value = "addRemark", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse addRemark(@RequestBody DellWithCompanyRequest request) {
        LogUtil.logRequest(log, this, request);
        BaseResponse response = new BaseResponse();
        try {
            companyService.addCollectRemark(request.getUserId(), request.getCompanyId(), request.getRemark());
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
                b.setLogoUrl(systemConfig.getVal("company_img_action_url") + "/companyImage?imageUrl=" + b.getLogoUrl());
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
            List<Map<String, Object>> companies = companyService.queryCompanies(request.getQueryType(),
                    request.getLocal(), request.getCondition(), page, request.getUserId());
            response.setHasNext(page.getHasNext());
            List<BaseCompanyInfoDto> companyInfoDtos = trans(companies);
            if(request.getFromType()!=null&&request.getFromType().intValue()==2){
                for (BaseCompanyInfoDto companyInfoDto : companyInfoDtos) {
                    Locations locations = companyService.getExhibitorNum(companyInfoDto.getCompanyId(), request.getMachineType());
                    companyInfoDto.setPoints(locations.getPoints());
                    companyInfoDto.setMapId(locations.getTileId());
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
            List<Map<String, Object>> datas = companyService.getCompaniesByType(request.getUserId(),
                    request.getFatherTypeCode(), request.getChildTypeCode(), page);
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
    public GetCollectCompanyRemarkResponse getTextRemark(@RequestBody DellWithCompanyRequest request) {
        LogUtil.logRequest(log, this, request);
        GetCollectCompanyRemarkResponse response = new GetCollectCompanyRemarkResponse();
        try {
            String remark = companyService.getCompanyRemarkByUidCompanyId(request.getUserId(), request.getCompanyId());
            response.setRemark(remark);
        } catch (Exception e) {
            log.error("获得展商文本备注失败", e);
            response.setErrorCode(ErrorCode.ERROR);
        }
        return response;
    }

    private List<BaseCompanyInfoDto> trans(List<Map<String, Object>> companies) {
        List<BaseCompanyInfoDto> baseCompanies = new ArrayList<BaseCompanyInfoDto>(companies.size());
        BaseCompanyInfoDto dto = null;
        for (Map<String, Object> data : companies) {
            dto = new BaseCompanyInfoDto();
            dto.setExhibitionNo(String.valueOf(data.get("ExhibitionNo")));
            dto.setCompanyId(Integer.parseInt(String.valueOf(data.get("ID"))));
            dto.setCompany(String.valueOf(data.get("Company")));
            dto.setCompanyE(String.valueOf(data.get("CompanyE")));
            dto.setCountry(String.valueOf( data.get("Country")));
            dto.setIsCollect(Integer.parseInt(String.valueOf(data.get("isCollect"))));
            dto.setFirstCompany(String.valueOf( data.get("FirstCompany")));
            dto.setFirstCompanyE(String.valueOf( data.get("FirstCompanyE")));
            dto.setLogoUrl(systemConfig.getVal("company_img_action_url") + "/companyImage?imageUrl=" + (String) data.get("LogoURL"));
            dto.setShortCompanyName(String.valueOf( data.get("ShortCompanyName")));
            dto.setShortCompanyNameE(String.valueOf( data.get("ShortCompanyNameE")));
            dto.setShortCompanyNameT(String.valueOf( data.get("ShortCompanyNameT")));
            baseCompanies.add(dto);
        }
        return baseCompanies;
    }
    @RequestMapping(value = "companyImage", method = RequestMethod.GET)
	public void getCompanyImage(@RequestParam("imageUrl") String imageUrl, HttpServletResponse response) {
		if(StringUtils.isBlank(imageUrl)){
			return ;
		}
		OutputStream stream = null;
		try {
			stream = response.getOutputStream();
			IOUtils.copy(new FileInputStream(ApplicationContextUtil.getBean(SystemConfig.class).getVal("company_img_local_url")+imageUrl), stream);
			stream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(null != stream){
					stream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
    public SystemConfig getSystemConfig() {
        return systemConfig;
    }

    public void setSystemConfig(SystemConfig systemConfig) {
        this.systemConfig = systemConfig;
    }
}
