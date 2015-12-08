package com.zhenhappy.locate;

import org.apache.log4j.Logger;

public class WifiUtil {

	private static Logger log = Logger.getLogger(WifiUtil.class);

	public static Point2D wifiFocus() {
		return null;
	}

	/**
	 * 计算空间坐标系中两个点的斜距
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	private static double slideDist(Point3D p1, Point3D p2) {
		return Math.sqrt(Math.pow((p1.getX() - p2.getX()), 2) + Math.pow((p1.getY() - p2.getY()), 2)
				+ Math.pow((p1.getZ() - p2.getZ()), 2));
	}

	/**
	 * 计算平距
	 * 
	 * @param s
	 *            两个点的平距
	 * @param z1
	 * @param z2
	 * @return
	 */
	private static double horizontalDist(double s, double z1, double z2) {
		return Math.sqrt(Math.pow(s, 2) - Math.pow((z1 - z2), 2));
	}

	/**
	 * * 计算方位角 * *
	 * 
	 * @param x1
	 *            lat1
	 * @param y1
	 *            lng1
	 * @param x2
	 *            lat2
	 * @param y2
	 *            lng2 * @return
	 */
	private static double azimuthAngle(double x1, double y1, double x2, double y2) {
		double dx, dy, angle = 0;
		dx = x2 - x1;
		dy = y2 - y1;
		if (x2 == x1) {
			angle = Math.PI / 2.0;
			if (y2 == y1) {
				angle = 0.0;
			} else if (y2 < y1) {
				angle = 3.0 * Math.PI / 2.0;
			}
		} else if ((x2 > x1) && (y2 > y1)) {
			angle = Math.atan(dx / dy);
		} else if ((x2 > x1) && (y2 < y1)) {
			angle = Math.PI / 2 + Math.atan(-dy / dx);
		} else if ((x2 < x1) && (y2 < y1)) {
			angle = Math.PI + Math.atan(dx / dy);
		} else if ((x2 < x1) && (y2 > y1)) {
			angle = 3.0 * Math.PI / 2.0 + Math.atan(dy / -dx);
		}
		return (angle * 180 / Math.PI);
	}

	/**
	 * 计算夹角
	 * 
	 * @param d1
	 * @param d2
	 * @param d3
	 * @return
	 * 
	 *         夹角 a= arccos((d1^2+d2^2-d3^2)/2d1d2)
	 * 
	 */
	private static double angle(double d1, double d2, double d3) {
		return Math.acos((pingfang(d1) + pingfang(d2) - pingfang(d3)) / (2 * d1 * d2));
	}

	/**
	 * 
	 * @param x1
	 * @param y1
	 * @param d1
	 * @param az1
	 * @return
	 */
	private static Point2D coord(double x1, double y1, double d1, double az1) {
		double x2 = x1 + d1 * Math.cos(az1 * Math.PI / 180);
		double y2 = y1 + d1 * Math.sin(az1 * Math.PI / 180);
		return new Point2D(x2, y2);
	}

	/**
	 * 通过信号强度获得AP距离
	 * 
	 * @param rssi
	 * @return
	 */
	private static double getLengthByRSSI(double rssi) {
		return 18 + (-55 - rssi) * 3;
	}

	private static double pingfang(double x) {
		return Math.pow(x, 2);
	}

	public static void main(String[] args) {

		AP max1 = new AP("KT08", 619531.296, 2706805.213, 5.315);
		max1.setRssi(-64);
		AP max2 = new AP("KT09", 619462.043, 2706847.778, 4.290);
		max2.setRssi(-66);
		AP max3 = new AP("KT11", 619444.788, 2706810.220, 4.280);
		max3.setRssi(-68);

		locate(max1, max2, max3);
	}

	public static Point2D locate(AP max1, AP max2, AP max3) {

		log.debug("max1:" + max1 + " max2:" + max2 + " max3:" + max3);

		double d1 = getLengthByRSSI(max1.getRssi());
		double zd1 = horizontalDist(d1, max1.getZ(), 1);
		double d2 = getLengthByRSSI(max2.getRssi());
		double zd2 = horizontalDist(d2, max2.getZ(), 1);

		double sap12 = slideDist(new Point3D(max1.getX(), max1.getY(), max1.getZ()),
				new Point3D(max2.getX(), max2.getY(), max2.getZ()));
		double zdap12 = horizontalDist(sap12, max1.getZ(), max2.getZ());

		log.debug("平距离：" + zd1 + "\t" + zd2 + "\t" + zdap12);

		log.debug("拟距：d1:" + d1 + "\td2:" + d2);

		double az1 = azimuthAngle(max1.getX(), max1.getY(), max2.getX(), max2.getY());
		double az2 = azimuthAngle(max1.getX(), max1.getY(), max3.getX(), max3.getY());

		log.debug("方位角：" + az1 + "\t" + az2);

		double jiajiao = angle(zd1, zdap12, d2);

		log.debug("夹角：" + (jiajiao / Math.PI) * 180);

		double caz = az1 - (jiajiao / Math.PI) * 180;

		log.debug("最终方位角：" + caz);

		Point2D p = coord(max1.getX(), max1.getY(), zd1, caz);

		log.debug("x:" + p.getX() + "\ty:" + p.getY());
		return Wgs84Transe.fromWgs84(p);
	}
}
