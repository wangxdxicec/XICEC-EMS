package com.zhenhappy.ems.action.user;

import com.zhenhappy.ems.action.BaseAction;
import com.zhenhappy.ems.dto.Principle;
import com.zhenhappy.ems.dto.TimeResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by wujianbin on 2014-12-15.
 */
@Controller
@SessionAttributes(value = Principle.PRINCIPLE_SESSION_ATTRIBUTE)
@RequestMapping(value = "user")
public class TimeAction extends BaseAction {

    @ResponseBody
    @RequestMapping(value = "getCurrentDate", method = RequestMethod.POST)
    public TimeResponse getCurrentDate() {
        Calendar now = Calendar.getInstance();
        TimeResponse timeResponse = new TimeResponse();
        timeResponse.setYear(now.get(Calendar.YEAR));
        timeResponse.setMonth(now.get(Calendar.MONTH) + 1);
        timeResponse.setDate(now.get(Calendar.DAY_OF_MONTH));
//        System.out.println("年: " + now.get(Calendar.YEAR));
//        System.out.println("月: " + (now.get(Calendar.MONTH) + 1) + "");
//        System.out.println("日: " + now.get(Calendar.DAY_OF_MONTH));
        return timeResponse;
    }

}
