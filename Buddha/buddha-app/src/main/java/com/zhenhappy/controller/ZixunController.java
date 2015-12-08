package com.zhenhappy.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zhenhappy.dto.ErrorCode;
import com.zhenhappy.exception.ReturnException;
import com.zhenhappy.system.SystemConfig;
import com.zhenhappy.system.ZixunCache;
import com.zhenhappy.system.ZixunCache.ZixunCacheItem;

@Controller
@RequestMapping(value = "client")
public class ZixunController extends BaseController {

	private static Logger log = Logger.getLogger(ZixunController.class);
	
	@Autowired
	private SystemConfig systemConfig;

	public ZixunController() {
		super(ZixunController.class);
	}

	@RequestMapping(value = "/alone/{name}")
	public void dispatchAloneInterface(
			@PathVariable(value = "name") String name,
			@RequestParam(value = "local", required = false) Integer local,
			HttpServletResponse response) {
		log.info("请求栏目："+name+" local:"+local);
		String baseUrl = systemConfig.getVal("cdn_zixun_base_url");
		String language = "";
		if (local != null && local.intValue() == 2) {
			language = "en";
		}
		if(local != null && local.intValue() == 3){
			language = "tw";
		}
		String url = baseUrl + "/" + name + language + ".htm";
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/channel/{name}/{pageIndex}")
	public JSONObject dispatchChannelInterface(
			@PathVariable(value = "name") String name,
			@RequestParam(value = "local", required = false) Integer local,
			@PathVariable(value = "pageIndex") Integer pageIndex,
			HttpServletResponse response) throws Exception {
		log.info("请求栏目："+name+" local:"+local+" page:"+pageIndex);
		try {
			String language = "";
			if (local != null && local.intValue() == 2) {
				language = "en";
			}
			if(local !=null && local.intValue() == 3){
				language = "tw";
			}
			String baseUrl = systemConfig.getVal("zixun_base_url");
			String url = baseUrl + "/" + name + language + "/index_"
					+ pageIndex + ".htm";
			ZixunCacheItem temp = ZixunCache.getContent(url);
			JSONObject object = null;
			if (temp != null) {
				object = (JSONObject) temp.getObject();
			}
			if (object == null) {
				String json = getString(url);
				object = JSONObject.parseObject(json);
				object.put("resultCode", 0);
				object.put("des", "加载成功！");
				ZixunCache.setContent(url, new ZixunCacheItem(object,
						new Date().getTime()));
			}
			response.setContentType("application/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/channel/map")
	public JSONObject dispatchChannelInterface(
			@RequestParam(value = "machineType") Integer machine,
			HttpServletResponse response) throws Exception {
		log.info("请求地图："+machine);
		try {
			String baseUrl = systemConfig.getVal("zixun_base_url");
			String url = baseUrl + "/allversion"
					+ (machine.intValue() == 1 ? "ios" : "android") + "/index.htm";
			ZixunCacheItem temp = ZixunCache.getContent(url);
			JSONObject object = null;
			if (temp != null) {
				object = (JSONObject) temp.getObject();
			}
			if (object == null) {
				String json = getString(url);
				object = JSONObject.parseObject(json);
				object.put("resultCode", 0);
				object.put("des", "加载成功！");
				ZixunCache.setContent(url, new ZixunCacheItem(object,
						new Date().getTime()));
			}
			response.setContentType("application/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@RequestMapping(value = "downloadApp")
	public void downloadApp(HttpServletRequest request,
			HttpServletResponse response) {
		String ua = request.getHeader("user-agent");
		ua = ua.toLowerCase();
		String android = systemConfig.getVal("android");
		String ios = systemConfig.getVal("ios");
		String[] android_uas = android.split(",");
		String[] ios_uas = ios.split(",");
		for (String android_ua : android_uas) {
			if (ua.indexOf(android_ua) != -1) {
				try {
					log.info("UA:" + ua + " redirect android");
					response.sendRedirect(systemConfig.getVal("android_d"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		for (String ios_ua : ios_uas) {
			if (ua.indexOf(ios_ua) != -1) {
				log.info("UA:" + ua + " redirect ios");
				try {
					response.sendRedirect(systemConfig.getVal("ios_d"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		try {
			response.sendRedirect(systemConfig.getVal("other_d"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String getString(String requestUrl) {
		HttpGet get = null;
		try {
			HttpClient client = new DefaultHttpClient();
			get = new HttpGet(requestUrl);
			HttpResponse response = client.execute(get);
			if (response.getStatusLine().getStatusCode() == 200) {
				String jsonResult = getResponseString(response, "UTF-8");
				return jsonResult;
			} else {
				throw new ReturnException(ErrorCode.CMS_ERROR);
			}
		} catch (Exception e) {
			log.error("请求资讯服务器失败！"+e.getMessage(), e);
			throw new ReturnException(ErrorCode.CMS_ERROR);
		} finally {
			get.releaseConnection();
		}
	}

	public String getResponseString(HttpResponse response, String charset)
			throws IOException {
		InputStream inputStream = response.getEntity().getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputStream, charset));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		return buffer.toString();
	}
}
