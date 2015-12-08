package com.zhenhappy.ems.manager.service;

import com.zhenhappy.ems.dao.ExhibitorInfoDao;
import com.zhenhappy.ems.entity.TContact;
import com.zhenhappy.ems.entity.TExhibitor;
import com.zhenhappy.ems.entity.TExhibitorInfo;
import com.zhenhappy.ems.entity.TInvoiceApply;
import com.zhenhappy.ems.manager.dto.ImportExhibitorsRequest;
import com.zhenhappy.ems.manager.dto.QueryExhibitorInfo;
import com.zhenhappy.ems.manager.entity.TExhibitorBooth;
import com.zhenhappy.ems.service.ExhibitorService;

import com.zhenhappy.ems.service.InvoiceService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import taobe.tec.jcc.JChineseConvertor;

/**
 * Created by wujianbin on 2014-08-25.
 */
@Service
public class ImportExportService extends ExhibitorService {

	private static Logger log = Logger.getLogger(ImportExportService.class);

	@Autowired
	private ExhibitorManagerService exhibitorManagerService;
	@Autowired
	private ExhibitorInfoDao exhibitorInfoDao;
	@Autowired
	private ContactManagerService contactService;
	@Autowired
	private InvoiceService invoiceService;

	public List<QueryExhibitorInfo> exportExhibitor(List<TExhibitor> exhibitors) {
		List<QueryExhibitorInfo> queryExhibitorInfos = new ArrayList<QueryExhibitorInfo>();
		if(exhibitors != null){
			for(TExhibitor exhibitor:exhibitors){
				TExhibitorInfo exhibitorInfo = exhibitorManagerService.loadExhibitorInfoByEid(exhibitor.getEid());
				if(exhibitorInfo != null){
					QueryExhibitorInfo queryExhibitorInfo = new QueryExhibitorInfo();
					queryExhibitorInfo.setBoothNumber(exhibitorManagerService.loadBoothNum(exhibitor.getEid()));
					queryExhibitorInfo.setCompany(exhibitorInfo.getCompany());
					queryExhibitorInfo.setCompanyEn(exhibitorInfo.getCompanyEn());
					queryExhibitorInfo.setPhone(exhibitorInfo.getPhone());
					queryExhibitorInfo.setFax(exhibitorInfo.getFax());
					queryExhibitorInfo.setEmail(exhibitorInfo.getEmail());
					queryExhibitorInfo.setWebsite(exhibitorInfo.getWebsite());
					queryExhibitorInfo.setAddress(exhibitorInfo.getAddress());
					queryExhibitorInfo.setAddressEn(exhibitorInfo.getAddressEn());
					queryExhibitorInfo.setZipcode(exhibitorInfo.getZipcode());
					queryExhibitorInfo.setProductType(exhibitorManagerService.queryExhibitorClassByEinfoid(exhibitorInfo.getEinfoid()));
					queryExhibitorInfo.setMainProduct(exhibitorInfo.getMainProduct());
					queryExhibitorInfo.setMainProductEn(exhibitorInfo.getMainProductEn());
					queryExhibitorInfo.setMark(exhibitorInfo.getMark());
					TInvoiceApply invoice = invoiceService.getByEid(exhibitorInfo.getEid());
					if(invoice != null){
						if(StringUtils.isNotEmpty(invoice.getInvoiceNo())) {
							queryExhibitorInfo.setInvoiceNo(invoice.getInvoiceNo());
						}else{
							queryExhibitorInfo.setInvoiceNo("");
						}
						if(StringUtils.isNotEmpty(invoice.getTitle())){
							queryExhibitorInfo.setInvoiceTitle(invoice.getTitle());
						}else{
							queryExhibitorInfo.setInvoiceTitle("");
						}
					}else{
						queryExhibitorInfo.setInvoiceNo("");
						queryExhibitorInfo.setInvoiceTitle("");
					}
					queryExhibitorInfos.add(queryExhibitorInfo);
				}else{
					QueryExhibitorInfo queryExhibitorInfo = new QueryExhibitorInfo();
					queryExhibitorInfo.setBoothNumber(exhibitorManagerService.loadBoothNum(exhibitor.getEid()));
					queryExhibitorInfo.setCompany(exhibitor.getCompany());
					queryExhibitorInfo.setCompanyEn(exhibitor.getCompanye());
					queryExhibitorInfos.add(queryExhibitorInfo);
				}
			}
		}
		return queryExhibitorInfos;
	}

	public List<String> importExhibitor(File importFile, ImportExhibitorsRequest request) {
		Integer count = 0;
		List<String> report = new ArrayList<String>();
		try {
			Workbook book = Workbook.getWorkbook(importFile);
			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(0);
			// 得到单元格
			for (int j = 1; j < sheet.getRows(); j++) {
				// 展位号
				TExhibitorBooth booth = new TExhibitorBooth();
				Cell boothTmp = sheet.getCell(0, j);
				String boothNo = boothTmp.getContents().trim().replaceAll(" ", "");
				if(StringUtils.isEmpty(boothNo)) {
//					System.out.println("第" + (j+1) + "行有问题,原因:展位号为空");
					report.add("第" + (j+1) + "行有问题,原因:展位号为空");
					continue;
				}
				if(exhibitorManagerService.queryBoothByBoothNum(boothNo) != null) {
//					System.out.println("第" + (j+1) + "行有问题,原因:展位号=" + boothNo + "有重复");
					report.add("第" + (j+1) + "行有问题,原因:展位号=" + boothNo + "有重复");
					continue;
				}
				booth.setBoothNumber(boothNo);
				booth.setExhibitionArea(boothNo.substring(0,1) + "厅");

				TExhibitor exhibitor = new TExhibitor();
				TExhibitorInfo exhibitorInfo = new TExhibitorInfo();
				List<TContact> contacts = new ArrayList<TContact>();
				String company = null;
				String companye = null;
				for (int i = 1; i < 16; i++) {
					Cell cell = sheet.getCell(i, j);
					switch (i) {
						case 1:	//用户名
							exhibitor.setUsername(cell.getContents().trim().replaceAll(" ", ""));
							break;
						case 2:	//密码
							exhibitor.setPassword(cell.getContents().trim().replaceAll(" ", ""));
							break;
						case 3:	//组织机构代码
							String organizationCode = cell.getContents().trim().replaceAll(" ", "");
							if(organizationCode == null || "".equals(organizationCode)){
								exhibitorInfo.setOrganizationCode(organizationCode);
							}else{
								if(organizationCode.length() == 10){
									exhibitorInfo.setOrganizationCode(organizationCode);
								}else{
									exhibitorInfo.setOrganizationCode(organizationCode);
									report.add("第" + (j+1) + "行有问题,原因:组织机构代码=" + organizationCode + "的长度不是10,但不影响此展商账号添加,请手动修改");
									break;
								}
							}
							break;
						case 4:	//公司名称(中文)
							company = cell.getContents().trim();
							break;
						case 5:	//公司名称(英文)
							companye = cell.getContents().trim();
							break;
						case 6:	//电话
							exhibitorInfo.setPhone(cell.getContents().trim());
							break;
						case 7:	//传真
							exhibitorInfo.setFax(cell.getContents().trim());
							break;
						case 8:	//网址
							exhibitorInfo.setWebsite(cell.getContents().trim().replaceAll(" ", ""));
							break;
						case 9:	//公司地址(中文)
							exhibitorInfo.setAddress(cell.getContents().trim());
							break;
						case 10://公司地址(英文)
							exhibitorInfo.setAddressEn(cell.getContents().trim());
							break;
						case 11://联系人姓名
							String[] names = cell.getContents().trim().split("\n");
							for(String name:names){
								TContact contact = new TContact();
								contact.setName(name);
								contacts.add(contact);
							}
							break;
						case 12://联系人职务
							String[] position = cell.getContents().trim().split("\n");
							if(contacts.size() != position.length){
								report.add("第" + (j+1) + "行有问题,原因:联系人职务不能联系人姓名一一对应,但不影响此展商账号添加,多出的联系人职务将丢失");
								break;
							}
							if(contacts.size() > 0){
								for(int t = 0;t < contacts.size(); t ++){
									contacts.get(t).setPosition(position[t]);
								}
							}
							break;
						case 13://手机
							String[] phone = cell.getContents().trim().split("\n");
							if(contacts.size() != phone.length){
								report.add("第" + (j+1) + "行有问题,原因:联系人手机号不能联系人姓名一一对应,但不影响此展商账号添加,多出的联系人手机号将丢失");
								break;
							}
							if(contacts.size() > 0){
								for(int t = 0;t < contacts.size(); t ++){
									contacts.get(t).setPhone(phone[t]);
								}
							}
							break;
						case 14://邮箱
							String[] email = cell.getContents().trim().replaceAll(" ", "").split("\n");
							if(contacts.size() != email.length){
								report.add("第" + (j+1) + "行有问题,原因:联系人邮箱不能联系人姓名一一对应,但不影响此展商账号添加,多出的联系人邮箱将丢失");
								break;
							}
							if(contacts.size() > 0){
								for(int t = 0;t < contacts.size(); t ++){
									contacts.get(t).setEmail(email[t]);
								}
							}
							break;
						case 15:
							exhibitor.setExhibitionArea(cell.getContents().trim().replaceAll(" ", ""));
						default:
							break;
					}
				}
				if(StringUtils.isEmpty(company) && StringUtils.isEmpty(companye)){
//					System.out.println("第" + (j+1) + "行有问题,原因:公司中文名和英文名都为空");
					report.add("第" + (j+1) + "行有问题,原因:公司中文名和英文名都为空");
					continue;//公司中文名和英文名都为空
				}else if((exhibitorManagerService.loadAllExhibitorByCompany(company) != null) || (exhibitorManagerService.loadAllExhibitorByCompanye(companye) != null)){
//					System.out.println("第" + (j+1) + "行有问题,原因:公司中文名"+ company +"或英文名"+ companye +"存在重复");
					report.add("第" + (j+1) + "行有问题,原因:公司中文名"+ company +"或英文名"+ companye +"存在重复");
					continue;//公司中文名或英文名存在重复
				}
				exhibitor.setCompany(company);
				exhibitorInfo.setCompany(company);
				exhibitor.setCompanye(companye);
				exhibitorInfo.setCompanyEn(companye);
				exhibitor.setCompanyt(JChineseConvertor.getInstance().s2t(company.trim()));
				if(request.getCountry() != null) exhibitor.setCountry(request.getCountry());
				if(request.getProvince() != null) exhibitor.setProvince(request.getProvince());
				if(request.getArea() != null) exhibitor.setArea(request.getArea());
				if(request.getGroup() != null) exhibitor.setGroup(request.getGroup());
				if(request.getTag() != null) exhibitor.setTag(request.getTag());
				exhibitor.setCreateTime(new Date());
				exhibitor.setCreateUser(1);
				exhibitor.setIsLogout(0);
				exhibitor.setContractId("");
				Integer eid = (Integer) getHibernateTemplate().save(exhibitor);

				if(eid != null){
					exhibitor.setEid(eid);
					booth.setEid(eid);
					booth.setMark("");
					booth.setCreateTime(new Date());
					booth.setCreateUser(1);
					exhibitorManagerService.bindBoothInfo(booth);

					exhibitorInfo.setEid(eid);
					exhibitorInfo.setPhone(contacts.get(0).getPhone());
					exhibitorInfo.setEmail(contacts.get(0).getEmail());
					exhibitorInfo.setCreateTime(new Date());
					exhibitorInfo.setAdminUser(1);
					exhibitorInfoDao.create(exhibitorInfo);

					for(TContact contact:contacts){
						contact.setEid(eid);
						contact.setIsDelete(0);
						contactService.addContact(contact);
					}
				}
				count ++;
			}
			report.add("共导入:" + count + "条数据");
			book.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return report;
	}

	public void copyLogo(Integer[] eids, String destDir) throws IOException {
		List<TExhibitor> exhibitors = new ArrayList<TExhibitor>();
		if(eids == null){
			exhibitors = exhibitorManagerService.loadAllExhibitors();
		}else{
			exhibitors = exhibitorManagerService.loadSelectedExhibitors(eids);
		}
		for(TExhibitor exhibitor:exhibitors){
			TExhibitorInfo exhibitorInfo = loadExhibitorInfoByEid(exhibitor.getEid());
			if(exhibitorInfo != null){
				if(StringUtils.isNotEmpty(exhibitorInfo.getLogo())){
					String boothNumber = loadBoothNum(exhibitor.getEid());
					File srcFile = new File(exhibitorInfo.getLogo().replaceAll("\\\\\\\\", "\\\\").replaceAll("/", "\\\\"));
					if (srcFile.exists() == false) continue;
					File destFile = null;
					if(StringUtils.isNotEmpty(exhibitor.getCompany())){
						destFile = new File(destDir + "\\" + exhibitor.getCompany().replaceAll("/", "") + boothNumber.replaceAll("/", "") + "." + FilenameUtils.getExtension(exhibitorInfo.getLogo().replaceAll("/", "\\\\\\\\")));
					}else{
						destFile = new File(destDir + "\\" + exhibitor.getCompanye().replaceAll("/", "") + boothNumber.replaceAll("/", "") + "." + FilenameUtils.getExtension(exhibitorInfo.getLogo().replaceAll("/", "\\\\\\\\")));
					}
					if(destFile != null) FileUtils.copyFile(srcFile, destFile);
				}
			}
		}
	}

	public void WriteStringToFile(String str, String filePath) {
		try {
			FileOutputStream fos = new FileOutputStream(filePath);
			fos.write(str.getBytes());
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
