package com.zhenhappy.ems.manager.action.user;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.zhenhappy.ems.dto.BaseResponse;
import com.zhenhappy.ems.entity.TTag;
import com.zhenhappy.ems.manager.action.BaseAction;
import com.zhenhappy.ems.manager.dto.AddTagRequest;
import com.zhenhappy.ems.manager.dto.ManagerPrinciple;
import com.zhenhappy.ems.manager.dto.ModifyTagRequest;
import com.zhenhappy.ems.manager.dto.QueryTagRequest;
import com.zhenhappy.ems.manager.dto.QueryTagResponse;
import com.zhenhappy.ems.manager.exception.DuplicateTagException;
import com.zhenhappy.ems.manager.service.TagManagerService;

/**
 * Created by wujianbin on 2014-07-21.
 */
@Controller
@RequestMapping(value = "user")
@SessionAttributes(value = ManagerPrinciple.MANAGERPRINCIPLE)
public class TagAction extends BaseAction {
	
	@Autowired
    private TagManagerService tagManagerService;
	
    private static Logger log = Logger.getLogger(TagAction.class);

    @RequestMapping(value = "tag")
    public ModelAndView directToArticle() {
        ModelAndView modelAndView = new ModelAndView("/user/tag");
        return modelAndView;
    }
    
    /**
     * 分页查询所有标签
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryTags")
    public QueryTagResponse queryTags(@ModelAttribute QueryTagRequest request) {
    	QueryTagResponse response = new QueryTagResponse();
        try {
        	response = tagManagerService.loadTagsByPage(request);
        } catch (Exception e) {
            response.setResultCode(1);
            log.error("query tags error.", e);
        }
        return response;
    }
    
    @ResponseBody
    @RequestMapping(value = "addTag", method = RequestMethod.POST)
    public BaseResponse addTag(@ModelAttribute AddTagRequest request, @ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
        BaseResponse response = new BaseResponse();
        try {
        	TTag tag = tagManagerService.loadTagByName(request.getName());
        	if(tag == null){
        		tag = new TTag();
                tag.setName(request.getName());
                tag.setCreateTime(new Date());
                if(principle.getAdmin().getId() != null || "".equals(principle.getAdmin().getId())){
                	tag.setAdminUser(principle.getAdmin().getId());
                }
                tagManagerService.addTag(tag);
        	}else{
        		throw new DuplicateTagException("所属人冲突");
        	}
        } catch (DuplicateTagException e) {
            response.setResultCode(2);
            response.setDescription("所属人冲突");
        } catch (Exception e) {
            log.error("add tag error.", e);
            response.setResultCode(1);
        }
        return response;
    }
    
    @ResponseBody
    @RequestMapping(value = "modifyTag", method = RequestMethod.POST)
    public BaseResponse modifyTag(@ModelAttribute ModifyTagRequest request, @ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
        BaseResponse response = new BaseResponse();
        try {
        	TTag tag = tagManagerService.loadTagById(request.getId());
        	tag.setName(request.getName());
        	tag.setUpdateTime(new Date());
        	if(principle.getAdmin().getId() != null || "".equals(principle.getAdmin().getId())){
            	tag.setAdminUser(principle.getAdmin().getId());
            	tag.setUpdateTime(new Date());
            }
        	tagManagerService.modifyTag(tag);
        } catch (Exception e) {
        	log.error("modify tag error.", e);
            response.setResultCode(1);
        }
        return response;
    }
    
    @ResponseBody
    @RequestMapping(value = "removeTags", method = RequestMethod.POST)
    public BaseResponse removeTags(@RequestParam(value = "tids", defaultValue = "") Integer[] tids, @ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
        BaseResponse response = new BaseResponse();
        try {
        	if(tids == null) throw new Exception();
        	tagManagerService.removeTagsByTids(tids);
        } catch (Exception e) {
        	log.error("remove tags error.", e);
            response.setResultCode(1);
        }
        return response;
    }
}
