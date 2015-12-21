package com.zhenhappy.ems.manager.action.user;

import com.zhenhappy.ems.manager.action.BaseAction;
import com.zhenhappy.ems.manager.dto.*;
import com.zhenhappy.ems.manager.service.TVisaManagerService;
import com.zhenhappy.ems.manager.service.WVisaManagerService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;

/**
 * Created by wujianbin on 2014-11-26.
 */
@Controller
@RequestMapping(value = "user")
@SessionAttributes(value = ManagerPrinciple.MANAGERPRINCIPLE)
public class TVisaAction extends BaseAction {

    private static Logger log = Logger.getLogger(TVisaAction.class);

    @Autowired
    private TVisaManagerService tVisaManagerService;

	@RequestMapping(value = "tVisa")
	public ModelAndView directToExhbitorVisa() {
		ModelAndView modelAndView = new ModelAndView("user/visa/tvisa");
		return modelAndView;
	}

	/**
	 * 分页查询国外展商VISA信息
	 *
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "queryTVisaByPage")
	public QueryTVisaResponse queryTVisaByPage(@ModelAttribute QueryTVisaRequest request) {
		QueryTVisaResponse response = new QueryTVisaResponse();
		try {
			response = tVisaManagerService.queryTVisaByPage(request);
		} catch (Exception e) {
			response.setResultCode(1);
			log.error("query tvisa error.", e);
		}
		return response;
	}

	/**
	 * 显示护照
	 * @param response
	 * @param vid
	 */
	@RequestMapping(value = "showPassportPage", method = RequestMethod.GET)
	public void showLogo(HttpServletResponse response, @RequestParam("vid") Integer vid) {
		try {
			String logoFileName = tVisaManagerService.queryByVid(vid).getPassportPage();
			if (StringUtils.isNotEmpty(logoFileName)) {
                if(logoFileName.toLowerCase().contains(".pdf")) response.setContentType("application/pdf");
				File logo = new File(logoFileName);
				if (!logo.exists()) return;
				OutputStream outputStream = response.getOutputStream();
				FileUtils.copyFile(logo, outputStream);
				outputStream.close();
				outputStream.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
