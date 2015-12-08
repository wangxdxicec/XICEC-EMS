package com.zhenhappy.controller;

import org.apache.log4j.Logger;

public class BaseController {
	
	protected Logger log;
	
	protected BaseController() {
	}

	public BaseController(Class clazz){
		log = Logger.getLogger(clazz);
	}
}
