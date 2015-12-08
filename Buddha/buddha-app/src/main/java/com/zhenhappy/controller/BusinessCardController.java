package com.zhenhappy.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhenhappy.dto.BaseResponse;
import com.zhenhappy.dto.BusinessCardInfo;
import com.zhenhappy.dto.CollectCardRequest;
import com.zhenhappy.dto.CreateCardRequest;
import com.zhenhappy.dto.CreateCardResponse;
import com.zhenhappy.dto.DeleteCardRequest;
import com.zhenhappy.dto.ErrorCode;
import com.zhenhappy.dto.GetBusinessCardInfoRequest;
import com.zhenhappy.dto.GetBusinessCardResponse;
import com.zhenhappy.dto.GetMyCardsResponse;
import com.zhenhappy.dto.ModifyCardInfoRequest;
import com.zhenhappy.dto.ModifyHeadResponse;
import com.zhenhappy.dto.PageDataRequest;
import com.zhenhappy.entity.TBusinessCard;
import com.zhenhappy.exception.ReturnException;
import com.zhenhappy.service.BusinessCardService;
import com.zhenhappy.system.SystemConfig;
import com.zhenhappy.util.LogUtil;
import com.zhenhappy.util.Page;

/**
 * User: Haijian Liang Date: 13-11-20 Time: 下午7:53 Function:
 */
@Controller
@RequestMapping(value = "/client/user")
public class BusinessCardController extends BaseController {

	@Autowired
	private BusinessCardService businessCardService;

	@Autowired
	private SystemConfig systemConfig;

	public BusinessCardService getBusinessCardService() {
		return businessCardService;
	}

	public void setBusinessCardService(BusinessCardService businessCardService) {
		this.businessCardService = businessCardService;
	}

	public BusinessCardController() {
		super(BusinessCardController.class);
	}

	@RequestMapping(value = "createCard", method = RequestMethod.POST)
	@ResponseBody
	public CreateCardResponse createCard(@RequestBody CreateCardRequest request) {
		LogUtil.logRequest(log, this, request);
		CreateCardResponse response = new CreateCardResponse();

		try {

			TBusinessCard card = new TBusinessCard();

			BeanUtils.copyProperties(request, card);
			card.setUserId(request.getUserId());
			card.setIsdefault(0);
			businessCardService.addCard(card);
			BeanUtils.copyProperties(card, response);
			response.setCardType(1);
		} catch (HibernateException e) {
			log.error(e);
			response.setErrorCode(ErrorCode.DATAERROR);

		} catch (Exception e) {
			log.error(e);
			response.setErrorCode(ErrorCode.ERROR);

		}
		return response;
	}

	@RequestMapping(value = "deleteCard", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse deleteCard(@RequestBody DeleteCardRequest request) {
		LogUtil.logRequest(log, this, request);
		BaseResponse response = new BaseResponse();

		try {

			businessCardService.deleteCard(request.getCardId());

		} catch (HibernateException e) {

			response.setErrorCode(ErrorCode.DATAERROR);

		} catch (Exception e) {

			response.setErrorCode(ErrorCode.ERROR);

		}

		return response;
	}
	
	@RequestMapping(value = "setDefaultCard", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse setDefaultCard(@RequestBody DeleteCardRequest request) {
		LogUtil.logRequest(log, this, request);
		BaseResponse response = new BaseResponse();

		try {

			businessCardService.setDefaultCard(request.getCardId(),request.getUserId());

		} catch (HibernateException e) {

			response.setErrorCode(ErrorCode.DATAERROR);

		} catch (Exception e) {

			response.setErrorCode(ErrorCode.ERROR);

		}

		return response;
	}

	@RequestMapping(value = "myCards", method = RequestMethod.POST)
	@ResponseBody
	public GetMyCardsResponse getMyCards(@RequestBody PageDataRequest request) {
		LogUtil.logRequest(log, this, request);
		GetMyCardsResponse response = new GetMyCardsResponse();

		try {
			Page page = new Page(request.getPageIndex(), request.getPageSize());
			List<BusinessCardInfo> cards = businessCardService.loadCardsByUserId(page, request.getUserId());
			List<BusinessCardInfo> temps = new ArrayList<BusinessCardInfo>(cards.size());
			for(BusinessCardInfo cardInfo : cards){
				if(cardInfo.getIsdefault().intValue()==1){
					temps.add(0, cardInfo);
				}else{
					temps.add(cardInfo);
				}
			}
			response.setCards(temps);
			response.setHasNext(page.getHasNext());
		} catch (HibernateException e) {
			e.printStackTrace();
			response.setErrorCode(ErrorCode.DATAERROR);

		} catch (Exception e) {
			e.printStackTrace();
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

	@RequestMapping(value = "modifyCard", method = RequestMethod.POST)
	@ResponseBody
	public CreateCardResponse modifyMyCard(@RequestBody ModifyCardInfoRequest request) {
		LogUtil.logRequest(log, this, request);
		CreateCardResponse response = new CreateCardResponse();

		try {

			TBusinessCard card = new TBusinessCard();

			BeanUtils.copyProperties(request, card);
			card.setIsDelete(0);
			businessCardService.updateCardInfo(card);
			response.setCardType(1);
			BeanUtils.copyProperties(card, response);

		} catch (HibernateException e) {
			e.printStackTrace();
			response.setErrorCode(ErrorCode.DATAERROR);

		} catch (Exception e) {
			e.printStackTrace();
			response.setErrorCode(ErrorCode.ERROR);

		}
		return response;
	}
	
	

	@RequestMapping(value = "collectCard", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse collectCard(@RequestBody CollectCardRequest request) {
		LogUtil.logRequest(log, this, request);
		BaseResponse response = new BaseResponse();

		try {

			businessCardService.collectCard(request.getUserId(), request.getQrcode(), request.getRemark(),
					request.getCardType());

		} catch (HibernateException e) {

			response.setErrorCode(ErrorCode.DATAERROR);

		} catch (Exception e) {

			response.setErrorCode(ErrorCode.ERROR);

		}
		return response;
	}

	@RequestMapping(value = "cancelCollectCard", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse cancelColectCard(@RequestBody CollectCardRequest request) {
		LogUtil.logRequest(log, this, request);
		BaseResponse response = new BaseResponse();

		try {
			businessCardService.removeCollectCard(request.getUserId(), request.getQrcode(), request.getCardType());
		} catch (HibernateException e) {

			response.setErrorCode(ErrorCode.DATAERROR);

		} catch (Exception e) {

			response.setErrorCode(ErrorCode.ERROR);

		}
		return response;
	}

	@RequestMapping(value = "updateCollectCard", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse UpdateColectCard(@RequestBody CollectCardRequest request) {
		LogUtil.logRequest(log, this, request);
		BaseResponse response = new BaseResponse();

		try {
			businessCardService.updateRemark(request.getUserId(), request.getQrcode(), request.getRemark(),
					request.getCardType());
		} catch (HibernateException e) {

			response.setErrorCode(ErrorCode.DATAERROR);

		} catch (Exception e) {

			response.setErrorCode(ErrorCode.ERROR);

		}
		return response;
	}

	@RequestMapping(value = "myCollectCards", method = RequestMethod.POST)
	@ResponseBody
	public GetMyCardsResponse myCollectCards(@RequestBody PageDataRequest request) {

		LogUtil.logRequest(log, this, request);
		GetMyCardsResponse response = new GetMyCardsResponse();
		System.out.println(11);
		try {
			Page page = new Page(request.getPageIndex(), request.getPageSize());
			List<BusinessCardInfo> cards = businessCardService.loadMyCollectCards(request.getUserId(), page);
			response.setCards(cards);
			response.setHasNext(page.getHasNext());
		} catch (HibernateException e) {
			e.printStackTrace();
			response.setErrorCode(ErrorCode.DATAERROR);

		} catch (Exception e) {
			e.printStackTrace();
			response.setErrorCode(ErrorCode.ERROR);

		}
		return response;
	}

	@RequestMapping(value = "getBusinessInfo", method = RequestMethod.POST)
	@ResponseBody
	public GetBusinessCardResponse getBusinessCardInfo(@RequestBody GetBusinessCardInfoRequest request) {
		LogUtil.logRequest(log, this, request);
		GetBusinessCardResponse response = new GetBusinessCardResponse();
		try {
			response = businessCardService.getCardInfo(request.getQrcode(), request.getCardType(), request.getUserId());
		} catch (Exception e) {
			log.error("获得用户名片失败", e);
			response.setErrorCode(ErrorCode.ERROR);
		}
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "uploadCardHead", method = RequestMethod.POST)
	public ModifyHeadResponse modifyHead(@RequestParam("cardId") Integer cardId,
			@RequestParam("head") MultipartFile head, HttpServletRequest request) {
		ModifyHeadResponse response = new ModifyHeadResponse();
		try {
			log.info("user:" + cardId + " modify head, orient file name :" + head.getOriginalFilename());
			businessCardService.uploadHead(cardId, head.getBytes());
			response.setNewHeadUrl(systemConfig.getVal("head_base_url") + "cardHeadImg?cardId=" + cardId);
		} catch (ReturnException e) {
			response.setErrorCode(e.getErrorCodeObject());
		} catch (Exception e) {
			response.setErrorCode(ErrorCode.ERROR);
			log.error("修改头像失败！", e);
		}
		return response;
	}

	@RequestMapping(value = "cardHeadImg", method = RequestMethod.GET)
	public void getCardHeadImg(@RequestParam("cardId") Integer cardId, HttpServletResponse response) {
		OutputStream stream = null;
		try {
			stream = response.getOutputStream();
			byte[] img = businessCardService.loadImg(cardId);
			stream.write(img);
			stream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
