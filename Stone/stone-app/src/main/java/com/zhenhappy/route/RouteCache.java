package com.zhenhappy.route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zhenhappy.locate.Point2D;
import com.zhenhappy.locate.Wgs84Transe;
import com.zhenhappy.util.Md5Util;

public class RouteCache {
	
	private static Logger log = Logger.getLogger(RouteCache.class);

	private static List<Point2D> ios_StartPoints = new ArrayList<Point2D>();
	private static HashMap<Route, List<Point2D>> ios_RoutesCache = new HashMap<RouteCache.Route, List<Point2D>>();

	private static List<Point2D> android_StartPoints = new ArrayList<Point2D>();
	private static HashMap<Route, List<Point2D>> android_RoutesCache = new HashMap<RouteCache.Route, List<Point2D>>();

	public static JdbcTemplate jdbcTemplate;
	
	public static void initIosCache(List<Point2D> points) {
		ios_StartPoints = points;
		log.info("cache ios route count :"+ios_StartPoints.size());
	}

	public static void initAndroidCache(List<Point2D> points) {
		android_StartPoints = points;
		log.info("cache android route count :"+android_StartPoints.size());
	}

	/**
	 * 查找最近的点
	 * 
	 * @param point
	 * @return
	 */
	private static Point2D findPoint(Point2D point, Integer machineType) {
		Point2D temp = null;
		double shortest = -1;
		List<Point2D> points = machineType.intValue() == 1 ? ios_StartPoints : android_StartPoints;
		for (Point2D p : points) {
			double distance = Math
					.sqrt(Math.pow((point.getX() - p.getX()), 2) + Math.pow((point.getY() - p.getY()), 2));
			if (shortest == -1 || distance < shortest) {
				shortest = distance;
				temp = p;
			}
		}
		return temp;
	}

	/**
	 * 查找路径
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<Point2D> findRoute(Point2D start, Point2D end, Integer machineType) {
		Point2D startShortest = findPoint(Wgs84Transe.toWgs84(start), machineType);
		Point2D endShortest = findPoint(Wgs84Transe.toWgs84(end), machineType);
		Route route = new Route(startShortest, endShortest);
		String key = Md5Util.md5(route.hashCode() + "");
		try{
			String routeString = jdbcTemplate.queryForObject("select route from t_route_"+(machineType.intValue()==1?"ios":"android")+"_index where hid=?",new Object[]{key}, String.class);
			String[] pointsString = routeString.split(";");
			List<Point2D> point2ds = new ArrayList<Point2D>(pointsString.length);
			for(String p:pointsString){
				String[] xy = p.split(",");
				point2ds.add(new Point2D(Double.parseDouble(xy[0]), Double.parseDouble(xy[1])));
			}
			return point2ds;
			//			log.info(routeString);
		}catch (Exception e) {
			
		}
		return new ArrayList<Point2D>();
	}

	public static class Route {

		private Point2D start;

		private Point2D end;

		public Route(Point2D start, Point2D end) {
			super();
			this.start = start;
			this.end = end;
		}

		public Route() {

		}

		public Point2D getStart() {
			return start;
		}

		public void setStart(Point2D start) {
			this.start = start;
		}

		public Point2D getEnd() {
			return end;
		}

		public void setEnd(Point2D end) {
			this.end = end;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((end == null) ? 0 : end.hashCode());
			result = prime * result + ((start == null) ? 0 : start.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Route other = (Route) obj;
			if (end == null) {
				if (other.end != null)
					return false;
			} else if (!end.equals(other.end))
				return false;
			if (start == null) {
				if (other.start != null)
					return false;
			} else if (!start.equals(other.start))
				return false;
			return true;
		}

	}
}
