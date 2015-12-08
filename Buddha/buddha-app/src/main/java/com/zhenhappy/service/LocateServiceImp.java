package com.zhenhappy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zhenhappy.dto.LocateRequest.ApInfo;
import com.zhenhappy.locate.AP;
import com.zhenhappy.locate.Point2D;
import com.zhenhappy.route.RouteCache;

@Service
public class LocateServiceImp extends LocateService{

	@Override
	protected List<AP> loadAP(List<ApInfo> apInfos) {
		AP max1 = new AP("KT08", 619531.296,2706805.213,5.315);
		max1.setRssi(-64);
		AP max2 = new AP("KT09", 619462.043,2706847.778,4.290);
		max2.setRssi(-66);
		AP max3 = new AP("KT11", 619444.788,2706810.220,4.280);
		max3.setRssi(-68);
		List<AP> aps = new ArrayList<AP>();
		aps.add(max1);
		aps.add(max2);
		aps.add(max3);
		return aps;
	}

	@Override
	public List<Point2D> navigate(Integer machineType, Point2D start, Point2D end) {
		return RouteCache.findRoute(start, end, machineType);
//		List<Point2D> points = new ArrayList<Point2D>();
//		if(machineType.intValue()==1){
//			points.add(new Point2D(118.182629248199,24.4711100324241));
//			points.add(new Point2D(118.182550318244,24.471110262753));
//			points.add(new Point2D(118.182440295414,24.471110583743));
//			points.add(new Point2D(118.182361365454,24.4711108139732));
//			points.add(new Point2D(118.182254874775,24.4711111243473));
//			points.add(new Point2D(118.182175944809,24.4711113544808));
//			points.add(new Point2D(118.182067685063,24.4711116677269));
//			points.add(new Point2D(118.181988754131,24.4711119004792));
//			points.add(new Point2D(118.181989230183,24.4711967966081));
//			points.add(new Point2D(118.182068158216,24.4711965647797));
//			points.add(new Point2D(118.182176421012,24.4711962533133));
//			points.add(new Point2D(118.18225535103,24.4711960231776));
//			points.add(new Point2D(118.18236184178,24.4711957128004));
//			points.add(new Point2D(118.182440771794,24.4711954825679));
//			points.add(new Point2D(118.182550794698,24.4711951615748));
//			points.add(new Point2D(118.182629724706,24.4711949312436));
//		}else{
//			points.add(new Point2D(118.177780762956,24.4736909208746));
//			points.add(new Point2D(118.177701831851,24.4736911053807));
//			points.add(new Point2D(118.17759180702,24.4736913615144));
//			points.add(new Point2D(118.177512874924,24.4736915459295));
//			points.add(new Point2D(118.177406382348,24.4736917942669));
//			points.add(new Point2D(118.177327450246,24.4736919785852));
//			points.add(new Point2D(118.177219188556,24.4736922288362));
//			points.add(new Point2D(118.177140255488,24.4736924157732));
//			points.add(new Point2D(118.177140672121,24.4737773106441));
//			points.add(new Point2D(118.177219601313,24.4737771255415));
//			points.add(new Point2D(118.177327866052,24.4737768770703));
//			points.add(new Point2D(118.177406798207,24.4737766927499));
//		}
//		return points;
	}

}
