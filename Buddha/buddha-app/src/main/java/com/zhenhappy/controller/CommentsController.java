package com.zhenhappy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhenhappy.dto.BaseResponse;
import com.zhenhappy.dto.CommentsRequest;
import com.zhenhappy.dto.ErrorCode;
import com.zhenhappy.entity.TComments;
import com.zhenhappy.service.CommentsService;
import com.zhenhappy.util.LogUtil;

/**
 * 参观预登记
 * 
 * @author rocsky
 * 
 */
@Controller
@RequestMapping(value = "/client/user/")
public class CommentsController extends BaseController {

	public CommentsController() {
		super(CommentsController.class);
	}

	@Autowired
	private CommentsService commentsService;
	
	@RequestMapping(value = "saveComments", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse save(@RequestBody CommentsRequest request) {
		LogUtil.logRequest(log, this, request);
		BaseResponse response = new BaseResponse();
		try {
			TComments comments = new TComments();
			comments.setCommentsId(request.getCommentsId());
			comments.setContent(request.getContent());
			comments.setCreateOn(request.getCreateOn());
			comments.setExhibitorId(request.getExhibitorId());
			comments.setUserId(request.getUserId());
			commentsService.save(comments);
		} catch (Exception e) {
			response.setErrorCode(ErrorCode.ERROR);
			log.error("保存留言失败.", e);
		}
		return response;
	}
}
