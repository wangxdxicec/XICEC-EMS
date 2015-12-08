package com.zhenhappy.system;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * User: Haijian Liang Date: 13-11-18 Time: 下午10:37 Function:
 */
public class Constants {

	static {
		String run_envirment;
		try {
			run_envirment = URLDecoder.decode(Constants.class.getResource("/").getPath(), "utf-8");
			if (run_envirment.indexOf("target") != -1) {
				SYSTEM_BASE_PATH = run_envirment.substring(0, run_envirment.indexOf("target")) + File.separatorChar
						+ "src" + File.separatorChar + "main" + File.separatorChar + "webapp" + File.separatorChar
						+ "WEB-INF";
			} else {
				SYSTEM_BASE_PATH = run_envirment.substring(0, run_envirment.indexOf("classes"));
			}
		} catch (UnsupportedEncodingException e1) {
			SYSTEM_BASE_PATH = "";
			e1.printStackTrace();
		}

	}

	public static final int CARD_TYPE_CUSTOMER = 1;

	public static final int CARD_TYPE_SYSTEM = 2;

	public static final String CHECK_PHONE_SESSION_ID = "CHECK_PHONE_SESSION_ID";

	public static String SYSTEM_BASE_PATH;

}
