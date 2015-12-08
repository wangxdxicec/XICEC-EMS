package com.zhenhappy.ems.manager.service;

import com.zhenhappy.ems.dao.ExhibitorInfoDao;
import com.zhenhappy.ems.entity.*;
import com.zhenhappy.ems.manager.dto.*;
import com.zhenhappy.ems.manager.entity.TExhibitorBooth;
import com.zhenhappy.ems.service.CountryProvinceService;
import com.zhenhappy.ems.service.ExhibitorService;

import com.zhenhappy.ems.service.InvoiceService;
import org.springframework.beans.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
    private ContactManagerService contactManagerService;
	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private CustomerInfoManagerService customerInfoManagerService;
	@Autowired
	private JoinerManagerService joinerManagerService;
	@Autowired
	private TVisaManagerService tVisaManagerService;
	@Autowired
	private CountryProvinceService countryProvinceService;

	/**
	 * 导出展商数据
	 * @param exhibitors
	 * @return
	 */
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

	/**
	 * 导出客商数据
	 * @param customers
	 * @return
	 */
	public List<ExportCustomerInfo> exportCustomer(List<WCustomer> customers) {
		List<ExportCustomerInfo> exportCustomerInfos = new ArrayList<ExportCustomerInfo>();
		if(customers.size() > 0){
			for(WCustomer customer:customers){
				ExportCustomerInfo exportCustomerInfo = new ExportCustomerInfo();
				exportCustomerInfo.setName((customer.getFirstName() == null ? "":customer.getFirstName()) + " " + (customer.getLastName() == null ? "":customer.getLastName()));
				exportCustomerInfo.setPhone((customer.getMobilePhoneCode() == null ? "":customer.getMobilePhoneCode()) + (customer.getMobilePhone() == null ? "":customer.getMobilePhone()));
				if(StringUtils.isNotEmpty(customer.getTelephone())){
					if(StringUtils.isNotEmpty(customer.getTelephoneCode2())){
						exportCustomerInfo.setTel((customer.getTelephoneCode() == null ? "":customer.getTelephoneCode()) + (customer.getTelephone() == null ? "":customer.getTelephone()) + "," + (customer.getTelephoneCode2() == null ? "":customer.getTelephoneCode2()));
					}else{
						exportCustomerInfo.setTel((customer.getTelephoneCode() == null ? "":customer.getTelephoneCode()) + (customer.getTelephone() == null ? "":customer.getTelephone()));
					}
				}else{
					exportCustomerInfo.setTel("");
				}
				if(StringUtils.isNotEmpty(customer.getFax())){
					if(StringUtils.isNotEmpty(customer.getFaxCode2())){
						exportCustomerInfo.setFaxString((customer.getFaxCode() == null ? "":customer.getFaxCode()) + (customer.getFax() == null ? "":customer.getFax()) + "," + (customer.getFaxCode2() == null ? "":customer.getFaxCode2()));
					}else{
						exportCustomerInfo.setFaxString((customer.getFaxCode() == null ? "":customer.getFaxCode()) + (customer.getFax() == null ? "":customer.getFax()));
					}
				}else{
					exportCustomerInfo.setFaxString("");
				}
				if(customer.getCountry() != null){
					WCountry country = countryProvinceService.loadCountryById(customer.getCountry());
					exportCustomerInfo.setCountryString(country.getChineseName());
				}else{
					exportCustomerInfo.setCountryString("");
				}
				BeanUtils.copyProperties(customer, exportCustomerInfo);
				exportCustomerInfos.add(exportCustomerInfo);
			}
		}
		return exportCustomerInfos;
	}

    /**
     * 导出展商联系人列表
     * @param exhibitors
     * @return
     */
    public List<ExportContact> exportContacts(List<TExhibitor> exhibitors) {
        List<ExportContact> exportContacts = new ArrayList<ExportContact>();
        if(exhibitors != null){
            for(TExhibitor exhibitor:exhibitors){
                List<TContact> contacts = contactManagerService.loadContactByEid(exhibitor.getEid());
                String booth_number = exhibitorManagerService.loadBoothNum(exhibitor.getEid());
                if(contacts != null){
                    for(TContact contact:contacts){
                        ExportContact exportContact = new ExportContact();
                        BeanUtils.copyProperties(contact,exportContact);
                        exportContact.setBoothNumber(booth_number);
                        exportContact.setCompany(exhibitor.getCompany());
                        exportContact.setCompanye(exhibitor.getCompanye());
                        exportContacts.add(exportContact);
                    }
                }
            }
        }
        return exportContacts;
    }

	/**
	 * 导出展商参展人员列表
	 * @param exhibitors
	 * @return
	 */
	public List<ExportExhibitorJoiner> exportExhibitorJoiners(List<TExhibitor> exhibitors) {
		List<ExportExhibitorJoiner> exportExhibitorJoiners = new ArrayList<ExportExhibitorJoiner>();
		if(exhibitors != null){
			for(TExhibitor exhibitor:exhibitors){
				List<TExhibitorJoiner> joiners = joinerManagerService.loadExhibitorJoinerByEid(exhibitor.getEid());
				String booth_number = exhibitorManagerService.loadBoothNum(exhibitor.getEid());
				if(joiners != null){
					for(TExhibitorJoiner joiner:joiners){
						ExportExhibitorJoiner exportExhibitorJoiner = new ExportExhibitorJoiner();
						BeanUtils.copyProperties(joiner,exportExhibitorJoiner);
						exportExhibitorJoiner.setBoothNumber(booth_number);
						exportExhibitorJoiner.setCompany(exhibitor.getCompany());
						exportExhibitorJoiner.setCompanye(exhibitor.getCompanye());
						exportExhibitorJoiners.add(exportExhibitorJoiner);
					}
				}
			}
		}
		return exportExhibitorJoiners;
	}

	/**
	 * 导出国外展商VISA数据
	 * @param tVisas
	 * @return
	 */
	public List<ExportTVisa> exportTVisas(List<TVisa> tVisas) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		List<ExportTVisa> queryExportTVisas = new ArrayList<ExportTVisa>();
		if(tVisas != null){
			for(TVisa tVisa:tVisas){
				TExhibitor exhibitor = exhibitorManagerService.loadExhibitorByEid(tVisa.getEid());
				if(exhibitor != null){
					ExportTVisa queryExportTVisa = new ExportTVisa();
					queryExportTVisa.setExhibitor(exhibitor.getCompanye());
					queryExportTVisa.setPassportName(tVisa.getPassportName());
					queryExportTVisa.setPassportNo(tVisa.getPassportNo());
					queryExportTVisa.setNationality(tVisa.getNationality());
					if(tVisa.getBirth() != null) queryExportTVisa.setBirth(sdf.format(tVisa.getBirth()));
					else queryExportTVisa.setBirth("");
					if (tVisa.getGender() == 1) queryExportTVisa.setGender("Mr.");
					else if (tVisa.getGender() == 2) queryExportTVisa.setGender("Miss");
					queryExportTVisa.setAddress(tVisa.getAddress());
					queryExportTVisa.setApplyFor(tVisa.getApplyFor());
					if(tVisa.getFrom() != null) queryExportTVisa.setFrom(sdf.format(tVisa.getFrom()));
					else queryExportTVisa.setFrom("");
					if(tVisa.getTo() != null) queryExportTVisa.setTo(sdf.format(tVisa.getTo()));
					else queryExportTVisa.setTo("");
					queryExportTVisa.setNeedPost("");
					queryExportTVisa.setExpressTp("");
					queryExportTVisa.setExpressNo("");
					if(tVisa.getExpDate() != null) queryExportTVisa.setExpDate(sdf.format(tVisa.getExpDate()));
					else queryExportTVisa.setExpDate("");
					if(tVisa.getCreateTime() != null) queryExportTVisa.setCreateTime(sdf.format(tVisa.getCreateTime()));
					else queryExportTVisa.setCreateTime("");
					if(tVisa.getJoinerId() != null){
						TExhibitorJoiner joiner = joinerManagerService.loadExhibitorJoinerById(tVisa.getJoinerId());
						if(joiner != null){
							queryExportTVisa.setJobTitle(joiner.getPosition());
							tVisa.setJobTitle(joiner.getPosition());
							queryExportTVisa.setEmail(joiner.getEmail());
							tVisa.setEmail(joiner.getEmail());
							queryExportTVisa.setTel(joiner.getTelphone());
							tVisa.setTel(joiner.getTelphone());
						}else{
							queryExportTVisa.setJobTitle("");
							queryExportTVisa.setEmail("");
							queryExportTVisa.setTel("");
						}
					}
					TExhibitorInfo exhibitorInfo = exhibitorManagerService.loadExhibitorInfoByEid(tVisa.getEid());
					if(exhibitorInfo != null){
						if(StringUtils.isNotEmpty(exhibitorInfo.getCompanyEn())){
							queryExportTVisa.setCompanyName(exhibitorInfo.getCompanyEn());
							queryExportTVisa.setEmailCompany(exhibitorInfo.getEmail());
							tVisa.setCompanyName(exhibitorInfo.getCompanyEn());
						}else if(StringUtils.isNotEmpty(exhibitorInfo.getCompany())){
							queryExportTVisa.setCompanyName(exhibitorInfo.getCompany());
							tVisa.setCompanyName(exhibitorInfo.getCompany());
						}
						queryExportTVisa.setCompanyWebsite(exhibitorInfo.getWebsite());
						tVisa.setCompanyWebsite(exhibitorInfo.getWebsite());
						queryExportTVisa.setFax(exhibitorInfo.getFax());
						tVisa.setFax(exhibitorInfo.getFax());
					}else{
						queryExportTVisa.setCompanyName("");
						queryExportTVisa.setCompanyWebsite("");
						queryExportTVisa.setFax("");
					}
					String boothNum = exhibitorManagerService.loadBoothNum(exhibitor.getEid());
					if(StringUtils.isNotEmpty(boothNum)){
						queryExportTVisa.setBoothNo(boothNum);
						tVisa.setBoothNo(boothNum);
					}else{
						queryExportTVisa.setBoothNo("");
					}
					queryExportTVisas.add(queryExportTVisa);
					tVisaManagerService.saveOrUpdate(tVisa);
				}else{
					System.out.println("此Id没有对应展商:" + tVisa.getEid());
					ExportTVisa queryExportTVisa = new ExportTVisa();
					queryExportTVisa.setExhibitor("找不到对应展商");
					queryExportTVisa.setPassportName(tVisa.getPassportName());
					queryExportTVisa.setPassportNo(tVisa.getPassportNo());
					queryExportTVisa.setNationality(tVisa.getNationality());
					if(tVisa.getBirth() != null) queryExportTVisa.setBirth(sdf.format(tVisa.getBirth()));
					else queryExportTVisa.setBirth("");
					if (tVisa.getGender() == 1) queryExportTVisa.setGender("Mr.");
					else if (tVisa.getGender() == 2) queryExportTVisa.setGender("Miss");
					queryExportTVisa.setJobTitle("");
					queryExportTVisa.setCompanyName("");
					queryExportTVisa.setBoothNo("");
					queryExportTVisa.setAddress(tVisa.getAddress());
					queryExportTVisa.setApplyFor(tVisa.getApplyFor());
					if(tVisa.getFrom() != null) queryExportTVisa.setFrom(sdf.format(tVisa.getFrom()));
					else queryExportTVisa.setFrom("");
					if(tVisa.getTo() != null) queryExportTVisa.setTo(sdf.format(tVisa.getTo()));
					else queryExportTVisa.setTo("");
					queryExportTVisa.setEmail("");
					queryExportTVisa.setEmailCompany("");
					queryExportTVisa.setCompanyWebsite("");
					queryExportTVisa.setTel("");
					queryExportTVisa.setFax("");
					queryExportTVisa.setNeedPost("");
					queryExportTVisa.setExpressTp("");
					queryExportTVisa.setExpressNo("");
					if(tVisa.getExpDate() != null) queryExportTVisa.setExpDate(sdf.format(tVisa.getExpDate()));
					else queryExportTVisa.setExpDate("");
					if(tVisa.getCreateTime() != null) queryExportTVisa.setCreateTime(sdf.format(tVisa.getCreateTime()));
					else queryExportTVisa.setCreateTime("");
					queryExportTVisas.add(queryExportTVisa);
				}
			}
		}
		return queryExportTVisas;
	}

	/**
	 * 导出国外客商VISA数据
	 * @param wVisas
	 * @return
	 */
	public List<ExportWVisa> exportWVisas(List<WVisa> wVisas) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		List<ExportWVisa> queryExportWVisas = new ArrayList<ExportWVisa>();
		if(wVisas != null){
			for(WVisa wVisa:wVisas){
				WCustomer customerInfo = customerInfoManagerService.loadCustomerInfoById(wVisa.getWCustomerInfo());
				if(customerInfo != null) {
					ExportWVisa queryExportWVisa = new ExportWVisa();
					queryExportWVisa.setCustomer("客商");
					queryExportWVisa.setPassportName(wVisa.getFullPassportName());
					queryExportWVisa.setPassportNo(wVisa.getPassportNo());
					queryExportWVisa.setDateOfBirth(sdf.format(wVisa.getDateOfBirth()));
					queryExportWVisa.setNationality(wVisa.getNationality());
					if ("male".equalsIgnoreCase(wVisa.getGender())) queryExportWVisa.setGender("Mr.");
					else if ("female".equalsIgnoreCase(wVisa.getGender())) queryExportWVisa.setGender("Miss");
					queryExportWVisa.setPosition(customerInfo.getPosition());
					queryExportWVisa.setCompany(customerInfo.getCompany());
					queryExportWVisa.setAddress(customerInfo.getAddress());
					queryExportWVisa.setChineseEmbassy(wVisa.getChineseEmbassy());
					queryExportWVisa.setDurationBeginTime(sdf.format(wVisa.getDurationBeginTime()));
					queryExportWVisa.setDurationEndTime(sdf.format(wVisa.getDurationEndTime()));
					queryExportWVisa.setEmail(customerInfo.getEmail());
					queryExportWVisa.setWebsite(customerInfo.getWebsite());
					queryExportWVisa.setTelephone(customerInfo.getTelephone());
					queryExportWVisa.setFax(customerInfo.getFax());
					if(wVisa.getNeedPost()) queryExportWVisa.setNeedPost("Yes");
					else if (!wVisa.getNeedPost()) queryExportWVisa.setNeedPost("No");
					queryExportWVisa.setExpressTp(wVisa.getExpressTp());
					queryExportWVisa.setExpressNo(wVisa.getExpressNo());
					queryExportWVisa.setExpDate(sdf.format(wVisa.getExpDate()));
					queryExportWVisa.setCreateTime(sdf.format(wVisa.getCreateTime()));
					queryExportWVisas.add(queryExportWVisa);
				}else{
					System.out.println("此Id没有对应客商:" + wVisa.getWCustomerInfo());
					ExportWVisa queryExportWVisa = new ExportWVisa();
					queryExportWVisa.setCustomer("找不到对应客商");
					queryExportWVisa.setPassportName(wVisa.getFullPassportName());
					queryExportWVisa.setPassportNo(wVisa.getPassportNo());
					queryExportWVisa.setDateOfBirth(sdf.format(wVisa.getDateOfBirth()));
					queryExportWVisa.setNationality(wVisa.getNationality());
					if ("male".equalsIgnoreCase(wVisa.getGender())) queryExportWVisa.setGender("Mr.");
					else if ("female".equalsIgnoreCase(wVisa.getGender())) queryExportWVisa.setGender("Miss");
					queryExportWVisa.setPosition("");
					queryExportWVisa.setCompany("");
					queryExportWVisa.setAddress("");
					queryExportWVisa.setChineseEmbassy(wVisa.getChineseEmbassy());
					queryExportWVisa.setDurationBeginTime(sdf.format(wVisa.getDurationBeginTime()));
					queryExportWVisa.setDurationEndTime(sdf.format(wVisa.getDurationEndTime()));
					queryExportWVisa.setEmail("");
					queryExportWVisa.setWebsite("");
					queryExportWVisa.setTelephone("");
					queryExportWVisa.setFax("");
					if(wVisa.getNeedPost()) queryExportWVisa.setNeedPost("Yes");
					else if (!wVisa.getNeedPost()) queryExportWVisa.setNeedPost("No");
					queryExportWVisa.setExpressTp(wVisa.getExpressTp());
					queryExportWVisa.setExpressNo(wVisa.getExpressNo());
					queryExportWVisa.setExpDate(sdf.format(wVisa.getExpDate()));
					queryExportWVisa.setCreateTime(sdf.format(wVisa.getCreateTime()));
					queryExportWVisas.add(queryExportWVisa);
				}
			}
		}
		return queryExportWVisas;
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
				for (int i = 1; i < 15; i++) {
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
					if(StringUtils.isNotEmpty(exhibitor.getCompanye())){
						destFile = new File(destDir + "\\" + exhibitor.getCompanye().replaceAll("/", "") + boothNumber.replaceAll("/", "") + "." + FilenameUtils.getExtension(exhibitorInfo.getLogo().replaceAll("/", "\\\\\\\\")));
					}else{
						destFile = new File(destDir + "\\" + exhibitor.getCompany().replaceAll("/", "") + boothNumber.replaceAll("/", "") + "." + FilenameUtils.getExtension(exhibitorInfo.getLogo().replaceAll("/", "\\\\\\\\")));
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
