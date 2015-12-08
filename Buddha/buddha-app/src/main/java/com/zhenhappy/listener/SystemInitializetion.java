package com.zhenhappy.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import com.zhenhappy.locate.Point2D;
import com.zhenhappy.route.RouteCache;
import com.zhenhappy.service.RouteService;

/**
 * 系统初始化
 * @author mac
 */
public class SystemInitializetion extends ContextLoaderListener{
	
	private static Logger log = Logger.getLogger(SystemInitializetion.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		WebApplicationContext context = (WebApplicationContext) event.getServletContext().getAttribute(
				WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		RouteService routeService = context.getBean(RouteService.class);
		JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
		RouteCache.jdbcTemplate = jdbcTemplate;
		log.info("begin load ios route information.");
		List<Point2D> iosPoints = routeService.getIosPoints();
		log.info("ios route information load from database end");
		log.info("begin cache ios routes in memory");
		RouteCache.initIosCache(iosPoints);
		log.info("cache ios routes in memory end");

		log.info("begin load android route information.");
		List<Point2D> androidPoints = routeService.getAndroidPoints();
		log.info("android route information load from database end");
		log.info("begin cache android routes in memory");
		RouteCache.initAndroidCache(androidPoints);
		log.info("cache android routes in memory end");
	}

}
