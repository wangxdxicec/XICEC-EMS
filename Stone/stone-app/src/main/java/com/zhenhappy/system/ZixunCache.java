package com.zhenhappy.system;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

public class ZixunCache {

	private static Logger log = Logger.getLogger(ZixunCache.class);

	private static ConcurrentHashMap<String, ZixunCacheItem> zixun_cache = new ConcurrentHashMap<String, ZixunCache.ZixunCacheItem>();

	private static Timer timer = new Timer(true);

	static {
		timer.schedule(new ClearCacheTimer(), 5 * 60 * 1000, 5 * 60 * 1000);
	}

	public static ZixunCacheItem getContent(String url) {
		return zixun_cache.get(url);
	}

	public static void setContent(String url, ZixunCacheItem item) {
		zixun_cache.put(url, item);
	}

	private static class ClearCacheTimer extends TimerTask {

		@Override
		public void run() {
			long dateNow = System.currentTimeMillis();
			long cha = 5 * 60 * 1000;
			log.info("begin clear zixun cache.");
			try {
				Iterator<Entry<String, ZixunCacheItem>> iterator = zixun_cache.entrySet().iterator();
				while (iterator.hasNext()) {
					Entry<String, ZixunCacheItem> entry = iterator.next();
					if ((dateNow - entry.getValue().getLastTime()) > cha) {
						iterator.remove();
					}
				}
			} catch (Exception e) {
				log.error("clear zixun cache error.", e);
			}
			log.info("clear zixun cache end.");
		}

	}

	public static class ZixunCacheItem {

		private Object object;

		private long lastTime;

		public ZixunCacheItem(Object object, long lastTime) {
			super();
			this.object = object;
			this.lastTime = lastTime;
		}

		public Object getObject() {
			return object;
		}

		public void setObject(Object object) {
			this.object = object;
		}

		public long getLastTime() {
			return lastTime;
		}

		public void setLastTime(long lastTime) {
			this.lastTime = lastTime;
		}

	}
}
