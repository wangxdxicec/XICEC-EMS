package com.zhenhappy.ems.action.user;

import com.zhenhappy.ems.action.BaseAction;
import com.zhenhappy.ems.dto.BaseResponse;
import com.zhenhappy.ems.dto.Principle;
import com.zhenhappy.ems.dto.QueryJoinersResponse;
import com.zhenhappy.ems.entity.TExhibitorJoiner;
import com.zhenhappy.ems.service.JoinerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wujianbin on 2014-04-20.
 */
@Controller
@SessionAttributes(value = Principle.PRINCIPLE_SESSION_ATTRIBUTE)
@RequestMapping(value = "user")
public class JoinerAction extends BaseAction {

    @Autowired
    private JoinerService joinerService;

    @RequestMapping(value = "queryJoiners", method = RequestMethod.GET)
    public String queryJoiners() {
        return "/user/joiner/index";
    }

    /**
     * query all joiners in exhibitor.
     *
     * @param principle
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryJoiners", method = RequestMethod.POST)
    public QueryJoinersResponse queryJoiners(@ModelAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE) Principle principle) {
        QueryJoinersResponse response = new QueryJoinersResponse();
        try {
            List<TExhibitorJoiner> joiners = joinerService.queryAllJoinersByEid(principle.getExhibitor().getEid());
            response.setJoiners(joiners);
        } catch (Exception e) {
            response.setResultCode(1);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "saveJoiner", method = RequestMethod.POST)
    public BaseResponse saveOrUpdate(@RequestBody TExhibitorJoiner joiner,@ModelAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE) Principle principle) {
        BaseResponse response = new BaseResponse();
        try {
            joiner.setEid(principle.getExhibitor().getEid());
            joinerService.saveOrUpdate(joiner);
            response.setResultCode(0);
        } catch (Exception e) {
            response.setResultCode(1);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "deleteJoiner", method = RequestMethod.POST)
    public BaseResponse deleteJoiner(@RequestParam("jid") Integer jid,@ModelAttribute(Principle.PRINCIPLE_SESSION_ATTRIBUTE) Principle principle) {
        BaseResponse response = new BaseResponse();
        try {
            if(joinerService.delete(jid,principle.getExhibitor().getEid())>0){
                response.setResultCode(0);
            }else{
                response.setResultCode(1);
            }
        } catch (Exception e) {
            response.setResultCode(1);
        }
        return response;
    }
}
