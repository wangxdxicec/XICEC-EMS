package com.zhenhappy.ems.manager.action.user;

import com.zhenhappy.ems.manager.action.BaseAction;
import com.zhenhappy.ems.manager.dto.*;
import com.zhenhappy.ems.manager.service.WVisaManagerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wujianbin on 2014-10-30.
 */
@Controller
@RequestMapping(value = "user")
@SessionAttributes(value = ManagerPrinciple.MANAGERPRINCIPLE)
public class WVisaAction extends BaseAction {

    private static Logger log = Logger.getLogger(WVisaAction.class);

    @Autowired
    private WVisaManagerService wVisaManagerService;

	@RequestMapping(value = "wVisa")
	public ModelAndView directToCustomerVisa() {
		ModelAndView modelAndView = new ModelAndView("user/visa/wvisa");
		return modelAndView;
	}

	/**
	 * 分页查询国外客商VISA信息
	 *
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "queryWVisaByPage")
	public QueryWVisaResponse queryWVisaByPage(@ModelAttribute QueryWVisaRequest request) {
		QueryWVisaResponse response = new QueryWVisaResponse();
		try {
			response = wVisaManagerService.queryWVisaByPage(request);
		} catch (Exception e) {
			response.setResultCode(1);
			log.error("query wvisa error.", e);
		}
		return response;
	}
}
