package com.zhenhappy.ems.action.user;

import com.zhenhappy.ems.action.BaseAction;
import com.zhenhappy.ems.entity.TArticle;
import com.zhenhappy.ems.service.AticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by wujianbin on 2014-06-29.
 */
@Controller
@RequestMapping(value = "/user/")
public class NoticeAction extends BaseAction{

    @Autowired
    private AticleService aticleService;

    @RequestMapping(value = "/notice/{pageIndex}")
    public ModelAndView listNotice(@PathVariable(value = "pageIndex") int pageIndex){
        ModelAndView modelAndView = new ModelAndView("/user/notice/list");
        List<TArticle> articles = aticleService.loadAll();
        modelAndView.addObject("articles",articles);
        return modelAndView;
    }

    @RequestMapping(value = "/noticeInfo")
    public ModelAndView noticeInfo(@RequestParam("id") Integer id){
        ModelAndView modelAndView = new ModelAndView("/user/notice/info");
        TArticle article = aticleService.loadArticle(id);
        modelAndView.addObject("article",article);
        return modelAndView;
    }
}
