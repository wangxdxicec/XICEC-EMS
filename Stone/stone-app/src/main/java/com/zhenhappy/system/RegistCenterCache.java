package com.zhenhappy.system;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.zhenhappy.dto.ErrorCode;
import com.zhenhappy.exception.ReturnException;

public class RegistCenterCache {

	private static Logger log = Logger.getLogger(RegistCenterCache.class);

	private static Map<String, CheckCode> phone_code = new ConcurrentHashMap<String, CheckCode>();

	private static Timer timer = new Timer(true);

	static {
		timer.schedule(new ClearCacheTimer(), 10 * 60 * 1000, 10 * 60 * 1000);
	}

	public static void put(String phone, String checkphone) {
		CheckCode checkCode = new CheckCode();
		checkCode.setCode(checkphone);
		checkCode.setDate(new Date());
		phone_code.put(phone, checkCode);
	}

	public static String getCheckCode(String phone) {
		CheckCode checkCode = phone_code.get(phone);
		if (checkCode != null) {
			return checkCode.getCode();
		} else {
			return null;
		}
	}

	public static String getAndClear(String phone) {
		CheckCode checkCode = phone_code.remove(phone);
		if (checkCode != null) {
			return checkCode.getCode();
		} else {
			return null;
		}
	}

	public static void canSend(String phone) {
		CheckCode checkCode = phone_code.get(phone);
		if (checkCode != null) {
			Date date = checkCode.getDate();
			Date now = new Date();
			long cha = now.getTime() - date.getTime();
			if (cha < 60000) {
				throw new ReturnException(ErrorCode.CHECKCODE_FREQUENCE);
			}
		}
	}

	public static class CheckCode {
		private String code;
		private Date date;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}
	}

	private static class ClearCacheTimer extends TimerTask {

		@Override
		public void run() {
			long dateNow = System.currentTimeMillis();
			long cha = 1 * 60 * 60 * 1000;
			log.info("begin clear regist cache.");
			try {
				Iterator<Entry<String, CheckCode>> iterator = phone_code.entrySet().iterator();
				while (iterator.hasNext()) {
					Entry<String, CheckCode> entry = iterator.next();
					if ((dateNow - entry.getValue().getDate().getTime()) > cha) {
						iterator.remove();
					}
				}
			} catch (Exception e) {
				log.error("clear regist cache error.", e);
			}
			log.info("clear regist cache end.");
		}

	}
}
