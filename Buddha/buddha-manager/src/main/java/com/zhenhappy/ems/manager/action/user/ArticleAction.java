package com.zhenhappy.ems.manager.action.user;

import java.util.Date;

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
import com.zhenhappy.ems.entity.TArticle;
import com.zhenhappy.ems.manager.action.BaseAction;
import com.zhenhappy.ems.manager.dto.AddArticleRequest;
import com.zhenhappy.ems.manager.dto.ManagerPrinciple;
import com.zhenhappy.ems.manager.dto.ModifyArticleRequest;
import com.zhenhappy.ems.manager.dto.QueryArticleRequest;
import com.zhenhappy.ems.manager.dto.QueryArticleResponse;
import com.zhenhappy.ems.manager.service.ArticleManagerService;
import com.zhenhappy.ems.service.ArticleService;

/**
 * Created by wujianbin on 2014-07-02.
 */
@Controller
@RequestMapping(value = "user")
@SessionAttributes(value = ManagerPrinciple.MANAGERPRINCIPLE)
public class ArticleAction extends BaseAction {

    private static Logger log = Logger.getLogger(ArticleAction.class);

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleManagerService articleManagerService;

    /**
     * 分页查询文章
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryArticlesByPage")
    public QueryArticleResponse queryArticlesByPage(@ModelAttribute QueryArticleRequest request) {
    	QueryArticleResponse response = new QueryArticleResponse();
        try {
        	response = articleManagerService.loadArticlesByPage(request);
        } catch (Exception e) {
            response.setResultCode(1);
            log.error("query articles error.", e);
        }
        return response;
    }
    
    @RequestMapping(value = "article")
    public ModelAndView directToArticle() {
        ModelAndView modelAndView = new ModelAndView("/user/article");
        return modelAndView;
    }
    
    @ResponseBody
    @RequestMapping(value = "addArticle", method = RequestMethod.POST)
    public BaseResponse addArticle(@ModelAttribute AddArticleRequest request, @ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
        BaseResponse response = new BaseResponse();
        try {
            TArticle article = new TArticle();
            article.setTitle(request.getTitle());
            article.setTitleEn(request.getTitleEn());
            article.setDigest(request.getDigest());
            article.setDigestEn(request.getDigestEn());
            article.setContent(request.getContent());
            article.setContentEn(request.getContentEn());
            article.setCreateTime(new Date());
            if(principle.getAdmin().getId() != null || "".equals(principle.getAdmin().getId())){
            	article.setCreateAdmin(principle.getAdmin().getId());
            }else{
            	throw new Exception();
            }
            articleService.add(article);
        } catch (Exception e) {
            log.error("add article error.", e);
            response.setResultCode(1);
        }
        return response;
    }
    
    @ResponseBody
    @RequestMapping(value = "modifyArticle", method = RequestMethod.POST)
    public BaseResponse modifyArticle(@ModelAttribute ModifyArticleRequest request, @ModelAttribute(ManagerPrinciple.MANAGERPRINCIPLE) ManagerPrinciple principle) {
        BaseResponse response = new BaseResponse();
        try {
            TArticle article = articleService.query(request.getId());
            if(article != null){
            	article.setTitle(request.getTitle());
            	article.setTitleEn(request.getTitleEn());
            	article.setDigest(request.getDigest());
            	article.setDigestEn(request.getDigestEn());
            	article.setContent(request.getContent());
            	article.setContentEn(request.getContentEn());
            	article.setUpdateTime(new Date());
            	if(principle.getAdmin().getId() != null || "".equals(principle.getAdmin().getId())){
            		article.setUpdateAdmin(principle.getAdmin().getId());
            	}else{
            		throw new Exception();
            	}
            	articleService.update(article);
            }else{
        		throw new Exception();
        	}
        } catch (Exception e) {
            log.error("modify article error.", e);
            response.setResultCode(1);
        }
        return response;
    }
    
    @ResponseBody
    @RequestMapping(value = "deleteArticles", method = RequestMethod.POST)
    public BaseResponse deleteArticles(@RequestParam(value = "ids", defaultValue = "") Integer[] ids) {
        BaseResponse response = new BaseResponse();
        try {
        	if(ids == null) throw new Exception();
        	articleManagerService.deleteArticlesByIds(ids);
        } catch (Exception e) {
        	log.error("delete articles error.", e);
            response.setResultCode(1);
        }
        return response;
    }
}
