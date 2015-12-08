package com.zhenhappy.locate;
public class AP implements Comparable<AP>{

	private String ssid;

	private double x;

	private double y;

	private double z;
	
	private double rssi;

	public AP(String ssid, double x, double y, double z) {
		super();
		this.ssid = ssid;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public double getRssi() {
		return rssi;
	}

	public void setRssi(double rssi) {
		this.rssi = rssi;
	}

	@Override
	public int compareTo(AP o) {
		if(this.rssi>=o.getRssi()){
			return 1;
		}else{
			return -1;
		}
	}
	
	@Override
	public String toString() {
		return "x:"+x+" y:"+y+" rssi:"+rssi;
	}

}
