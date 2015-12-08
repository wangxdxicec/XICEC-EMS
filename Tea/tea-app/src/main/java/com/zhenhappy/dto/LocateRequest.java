package com.zhenhappy.dto;

import java.util.List;

public class LocateRequest extends BaseRequest {
	
	private List<ApInfo> aps;

	public List<ApInfo> getAps() {
		return aps;
	}

	public void setAps(List<ApInfo> aps) {
		this.aps = aps;
	}

	public static class ApInfo {
		
		private String ssid;

		private double signal_strength;

		public String getSsid() {
			return ssid;
		}

		public void setSsid(String ssid) {
			this.ssid = ssid;
		}

		public double getSignal_strength() {
			return signal_strength;
		}

		public void setSignal_strength(double signal_strength) {
			this.signal_strength = signal_strength;
		}
	}
}
