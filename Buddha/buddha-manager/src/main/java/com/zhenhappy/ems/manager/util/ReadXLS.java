/*  
 * Created on July 14, 2014 
 *  
 * To change the template for this generated file go to  
 * Window>Preferences>Java>Code Generation>Code and Comments  
 */  
package com.zhenhappy.ems.manager.util; 
  
import java.io.*;   
import java.util.ArrayList;
import java.util.List;

import com.zhenhappy.ems.entity.TContact;
import com.zhenhappy.ems.entity.TExhibitor;
import com.zhenhappy.ems.entity.TExhibitorInfo;
import com.zhenhappy.ems.manager.entity.TExhibitorBooth;

import jxl.*;   
  
/**  
 * @author wujianbin 
 *  
 * To change the template for this generated type comment go to  
 * Window>Preferences>Java>Code Generation>Code and Comments  
 */  
public class ReadXLS {   
  
    public static void main(String[] args) {   
    	try {
            Workbook book = Workbook.getWorkbook(new File("D:\\Test.xls"));
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            // 得到单元格
//            for (int j = 0; j < sheet.getRows(); j++) {
//            	for (int i = 0; i < 12; i++) {
//                    Cell cell = sheet.getCell(i, j);
//                    System.out.print(i+cell.getContents().replaceAll("\n", ",") + "|");
//                }
//                System.out.println();
//            }
            for (int j = 1; j < sheet.getRows(); j++) {
            	TExhibitor exhibitor = new TExhibitor();
            	TExhibitorBooth booth = new TExhibitorBooth();
            	TExhibitorInfo exhibitorInfo = new TExhibitorInfo();
            	List<TContact> contacts = new ArrayList<TContact>();
            	for (int i = 0; i < 12; i++) {
                    Cell cell = sheet.getCell(i, j);
                    switch (i) {
						case 0:	//展位号
							booth.setBoothNumber(cell.getContents());
							break;
						case 1:	//组织机构代码
							exhibitor.setUsername(cell.getContents());
							exhibitor.setPassword(cell.getContents());
							break;
						case 2:	//公司名称(中文)
							exhibitor.setCompany(cell.getContents());
							break;
						case 3:	//公司名称(英文)
							exhibitor.setCompanye(cell.getContents());
							break;
						case 4:	//电话
							exhibitorInfo.setPhone(cell.getContents());
							break;
						case 5:	//传真
							exhibitorInfo.setFax(cell.getContents());
							break;
						case 6:	//网址
							exhibitorInfo.setWebsite(cell.getContents());
							break;
						case 7:	//公司地址
							exhibitorInfo.setAddress(cell.getContents());
							break;
						case 8:	//联系人
							String[] names = cell.getContents().split("\n");
							for(String name:names){
								TContact contact = new TContact();
								contact.setName(name);
								contacts.add(contact);
							}
							break;
						case 9:	//联系人
							String[] position = cell.getContents().split("\n");
							if(contacts.size() == position.length){
								for(int t = 0;t < contacts.size(); t ++){
						            contacts.get(t).setPosition(position[t]);
						        }
							}
							break;
						case 10://手机
							String[] phone = cell.getContents().split("\n");
							if(contacts.size() == phone.length){
								for(int t = 0;t < contacts.size(); t ++){
						            contacts.get(t).setPhone(phone[t]);
						        }
							}
							break;
						case 11://邮箱
							String[] email = cell.getContents().split("\n");
							if(contacts.size() == email.length){
								for(int t = 0;t < contacts.size(); t ++){
						            contacts.get(t).setEmail(email[t]);
						        }
							}
							break;
						default:
							break;
					}
                }
                System.out.println(contacts);
            }
            book.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}