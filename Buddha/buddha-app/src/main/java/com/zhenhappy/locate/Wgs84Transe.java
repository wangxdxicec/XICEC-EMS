package com.zhenhappy.locate;

import com.jhlabs.map.proj.Projection;
import com.jhlabs.map.proj.ProjectionFactory;

public class Wgs84Transe {

	/**
	 * @param point2d
	 * @return
	 */
	public static Point2D fromWgs84(Point2D point2d) {
		Projection projection = ProjectionFactory
				.fromPROJ4Specification(new String[] { "+proj=utm", "+zone=50",
						"+ellps=WGS84", "+units=m", "+no_defs" });
		com.jhlabs.map.Point2D.Double d = new com.jhlabs.map.Point2D.Double();
		projection.inverseTransform(
				new com.jhlabs.map.Point2D.Double(point2d.getX(), point2d
						.getY()), d);
		return new Point2D(d.x, d.y);
	}
	
	public static Point2D toWgs84(Point2D point2d) {
		Projection projection = ProjectionFactory
				.fromPROJ4Specification(new String[] { "+proj=utm", "+zone=50",
						"+ellps=WGS84", "+units=m", "+no_defs" });
		com.jhlabs.map.Point2D.Double d = new com.jhlabs.map.Point2D.Double();
		projection.transform(
				new com.jhlabs.map.Point2D.Double(point2d.getX(), point2d
						.getY()), d);
		return new Point2D(d.x, d.y);
	}

	public static void main(String[] args) {
		System.out.println(Wgs84Transe.fromWgs84(new Point2D(619842.1793,
				2706898.9574)));
		System.out.println(Wgs84Transe.toWgs84(Wgs84Transe.fromWgs84(new Point2D(619842.1793,
				2706898.9574))));
	}

}
