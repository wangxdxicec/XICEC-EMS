package com.zhenhappy.service;

import java.util.Collections;
import java.util.List;

import com.zhenhappy.dto.LocateRequest.ApInfo;
import com.zhenhappy.locate.AP;
import com.zhenhappy.locate.Point2D;
import com.zhenhappy.locate.WifiUtil;

public abstract class LocateService {

	/**
	 * 通过AP信号强度获得定位
	 * @param aps
	 * @return
	 */
	public Point2D locate(List<ApInfo> apinfos){
		List<AP> aps = loadAP(apinfos);
		Collections.sort(aps);
		AP max1 = aps.get(0);
		AP max2 = aps.get(1);
		AP max3 = aps.get(2);
		return WifiUtil.locate(max1, max2, max3);
	}
	
	public abstract List<Point2D> navigate(Integer machineType,Point2D start,Point2D end);
	
	protected abstract List<AP> loadAP(List<ApInfo> apInfos);
}
