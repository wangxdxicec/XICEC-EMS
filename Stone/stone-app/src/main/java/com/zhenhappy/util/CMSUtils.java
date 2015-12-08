package com.zhenhappy.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.zhenhappy.dto.ErrorCode;
import com.zhenhappy.dto.WscInfoResponse;
import com.zhenhappy.dto.WscSpeakerInfoResponse;
import com.zhenhappy.dto.WscTopicInfoResponse;
import com.zhenhappy.exception.ReturnException;
import com.zhenhappy.system.SystemConfig;

/**
 * 
 * @author 苏志锋
 * 
 */
public class CMSUtils {
	private static Logger log = Logger.getLogger(CMSUtils.class);

	public static List<WscInfoResponse> wscs = new ArrayList<WscInfoResponse>();

	public static boolean hasData = false;

	public static void check() {
		if (!hasData) {
			flush();
		}
	}

	public static synchronized void flush() {
		String baseUrl = ApplicationContextUtil.getBean(SystemConfig.class).getVal("cms_base_url");
		// String baseUrl = "http://sf.jinhongxin.com";
		List<WscSpeakerInfoResponse> rawSpeakers = getSpeakers(baseUrl);
		List<WscInfoResponse> rawWscs = getWscs(baseUrl);
		List<WscTopicInfoResponse> rawTopics = getTopics(baseUrl);
		for (WscInfoResponse wsc : rawWscs) {
			for (String speaker : wsc.getSpeaker().split(",")) {
				wsc.getSpeakers().add(getSpeaker(rawSpeakers, String.valueOf(speaker.hashCode())));
			}
			for (String topic : wsc.getTopic().split(",")) {
				wsc.getTopics().add(getTopic(rawTopics, String.valueOf(topic.hashCode())));
			}
		}
		wscs = rawWscs;
		hasData = true;
	}

	private static WscSpeakerInfoResponse getSpeaker(List<WscSpeakerInfoResponse> rawSpeakers, String speakerId) {
		for (WscSpeakerInfoResponse item : rawSpeakers) {
			if (item.getSpeakerId().trim().equals(speakerId.trim())) {
				return item;
			}
		}
		throw new RuntimeException("找不到speaker:" + speakerId);
	}

	private static WscTopicInfoResponse getTopic(List<WscTopicInfoResponse> rawTopics, String topicId) {
		for (WscTopicInfoResponse item : rawTopics) {
			if (item.getTopicId().trim().equals(topicId.trim())) {
				return item;
			}
		}
		throw new RuntimeException("找不到topic:" + topicId);
	}
	private static List<WscTopicInfoResponse> getTopics(String baseUrl) {
		List<WscTopicInfoResponse> items = getList(baseUrl + "/topic/", WscTopicInfoResponse.class);
		for (WscTopicInfoResponse item : items) {
			if (StringUtils.isBlank(item.getTopicId())) {
				item.setTopicId(String.valueOf(item.getName().hashCode()));
			} else {
				item.setTopicId(String.valueOf(item.getTopicId().hashCode()));
			}
		}
		return items;
	}

	private static List<WscSpeakerInfoResponse> getSpeakers(String baseUrl) {
		List<WscSpeakerInfoResponse> items = getList(baseUrl + "/speaker/", WscSpeakerInfoResponse.class);
		for (WscSpeakerInfoResponse item : items) {
			if (StringUtils.isBlank(item.getSpeakerId())) {
				item.setSpeakerId(String.valueOf(item.getName().hashCode()));
			} else {
				item.setSpeakerId(String.valueOf(item.getSpeakerId().hashCode()));
			}
			item.setLogoUrl(baseUrl + item.getLogoUrl());
		}
		return items;
	}

	private static List<WscInfoResponse> getWscs(String baseUrl) {
		List<WscInfoResponse> items = getList(baseUrl + "/wsc/", WscInfoResponse.class);
		return items;
	}

	public static <T> List<T> getList(String requestUrl, Class<T> clazz) {
		return JSON.parseArray(getString(requestUrl), clazz);
	}

	public static <T> T get(String requestUrl, Class<T> clazz) {
		return JSON.parseObject(getString(requestUrl), clazz);
	}

	public static String getString(String requestUrl) {
		HttpGet get = null;
		try {
			HttpClient client = new DefaultHttpClient();
			get = new HttpGet(requestUrl);
			HttpResponse response = client.execute(get);
			if (response.getStatusLine().getStatusCode() == 200) {
				String jsonResult = getResponseString(response, "UTF-8");
				jsonResult = jsonResult.replace("2014-03", "2015-03").replace("'", "\\'").replace("wscspeakerwsc", "'");
				return jsonResult;
			} else {
				throw new ReturnException(ErrorCode.CMS_ERROR);
			}
		} catch (Exception e) {
			log.error("请求cms失败！URL=" + requestUrl, e);
			throw new ReturnException(ErrorCode.CMS_ERROR);
		} finally {
			get.releaseConnection();
		}
	}

	private static String getResponseString(HttpResponse response, String charset) throws IOException {
		InputStream inputStream = response.getEntity().getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		return buffer.toString();
	}
}
