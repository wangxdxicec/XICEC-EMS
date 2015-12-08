package com.zhenhappy.ems.action.user;

import com.zhenhappy.ems.action.BaseAction;
import com.zhenhappy.ems.dto.BaseResponse;
import com.zhenhappy.ems.dto.Principle;
import com.zhenhappy.ems.entity.TInvoiceApply;
import com.zhenhappy.ems.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * Created by wujianbin on 2014-05-13.
 */
@Controller
@RequestMapping(value = "/user/invoice")
@SessionAttributes(value = Principle.PRINCIPLE_SESSION_ATTRIBUTE)
public class InvoiceAction extends BaseAction {

    @Autowired
    private InvoiceService invoiceService;

    @RequestMapping(value = "index")
    public ModelAndView index(@ModelAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE) Principle principle) {
        TInvoiceApply invoiceApply = invoiceService.getByEid(principle.getExhibitor().getEid());
        if (invoiceApply == null) {
            return new ModelAndView("/user/invoice/index");
        } else {
            ModelAndView modelAndView = new ModelAndView("/user/invoice/update");
            modelAndView.addObject("invoice", invoiceApply);
            return modelAndView;
        }
    }

    @ResponseBody
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public BaseResponse save(@RequestParam("title") String title, @RequestParam("exhibitorNo") String exhibitorNo, @RequestParam("invoiceNo") String invoiceNo, @RequestParam(value = "id",required = false) Integer id, @ModelAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE) Principle principle) {
        BaseResponse response = new BaseResponse();
        try {
            TInvoiceApply apply = new TInvoiceApply();
            apply.setId(id);
            apply.setCreateTime(new Date());
            apply.setEid(principle.getExhibitor().getEid());
            apply.setExhibitorNo(exhibitorNo);
            apply.setTitle(title);
            apply.setInvoiceNo(invoiceNo);
            invoiceService.create(apply);
            response.setResultCode(0);
        } catch (Exception e) {
            response.setResultCode(1);
        }
        return response;
    }
}
