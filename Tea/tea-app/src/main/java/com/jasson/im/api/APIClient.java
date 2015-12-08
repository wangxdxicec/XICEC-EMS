/*
 * Created on 2005-4-8
 * History:
 *    2005-4-8  龙良华 初次编码实现
 *    2005-4-11 张吉文 整理代码，添加JavaDoc
 */
package com.jasson.im.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;


/**
 * 用于发送短信、接收短信、接收回执的类。
 *
 * @version 2.2
 * @since JDK 1.4.1
 */
public class APIClient
{
	public final static int IMAPI_SUCC				 = 0;   	// 操作成功
	public final static int IMAPI_CONN_ERR			 = -1;	    // 连接数据库出错
	public final static int IMAPI_CONN_CLOSE_ERR	 = -2;	    // 数据库关闭失败
	public final static int IMAPI_INS_ERR			 = -3;	    // 数据库插入错误
	public final static int IMAPI_DEL_ERR			 = -4;	    // 数据库删除错误
	public final static int IMAPI_QUERY_ERR			 = -5;	    // 数据库查询错误
	public final static int IMAPI_DATA_ERR			 = -6;	    // 参数错误
	public final static int IMAPI_API_ERR			 = -7;		// API标识非法
	public final static int IMAPI_DATA_TOOLONG		 = -8; 		// 消息内容太长
	public final static int IMAPI_INIT_ERR			 = -9;	    // 没有初始化或初始化失败
	public final static int IMAPI_IFSTATUS_INVALID 	 = -10; 	// API接口处于暂停（失效）状态
	public final static int IMAPI_GATEWAY_CONN_ERR 	 = -11; 	// 短信网关未连接
	
	public final static int SM_TYPE_NORMAL	         = 0; 		// 短信类型:普通短信
	public final static int SM_TYPE_PDU	             = 2; 		// 短信类型:PDU短信
	private final static String DB_CONFIG="db.properties";
	
    /*接口登录名。*/
    private String dbUser = null;
    /*接口登录密码。*/
    private String dbPwd = null;
    /*接口编码。*/
    private String apiCode_ = null;
    /*数据库地址。*/
    private String dbUrl = null;
    /*数据库连接。*/
    private Connection conn = null;
    /*数据库端口*/
    private int port=0;
    /*指定字符集*/
    private String characterEncoding=null;


    /**
     * 初始化APIClient对象。
     *
     * @param dbIP    信息机的IP地址
     * @param dbUser  接口登录名
     * @param dbPwd   接口登录密码
     * @param apiCode 接口编码
     * @return
     */
    public int init(String dbIP, String dbUser, String dbPwd, String apiCode)
    {
        /*release(); //释放以前的dbco,apiOpera对象

        this.dbUser = dbUser;
        this.dbPwd = dbPwd;
        this.apiCode_ = apiCode;
        this.port=getPort();
        this.dbUrl = "jdbc:mysql://" + dbIP +":"+port +"/im";

        return testConnect();*/
    	//this.port=getPort();
    	loadProperties();
    	return init(dbIP,dbUser,dbPwd,apiCode,"im",port);
    }
    
    /**
     * 初始化APIClient对象。
     *
     * @param dbIP    信息机的IP地址
     * @param dbUser  接口登录名
     * @param dbPwd   接口登录密码
     * @param apiCode 接口编码
     * @param dbName  数据库名称
     * @return
     */
    public int init(String dbIP, String dbUser, String dbPwd, String apiCode ,String dbName)
    {
        /*release(); //释放以前的dbco,apiOpera对象

        this.dbUser = dbUser;
        this.dbPwd = dbPwd;
        this.apiCode_ = apiCode;
        this.port=getPort();
        this.dbUrl = "jdbc:mysql://" + dbIP+":"+port + "/" + dbName;

        return testConnect();*/
    	//this.port=getPort();
    	loadProperties();
    	return init(dbIP,dbUser,dbPwd,apiCode,dbName,port);
    }
    
    /**
     * 从配置文件中获取端口
     * @return
     * by xiarenji 2013-7-15
     */
    private void loadProperties()
    {
    	Properties p=null;
		try {
			InputStream is=this.getClass().getClassLoader().getResourceAsStream(DB_CONFIG);
			p = new Properties();
			p.load(is);
		} catch (Exception e) {
			System.out.println("获取端口失败");
		}
		this.port=Integer.parseInt(p.getProperty("port"));
		this.characterEncoding=p.getProperty("characterEncoding");
    }
    
    /**
     * 初始化APIClient对象。
     * @param dbIP
     * @param dbUser
     * @param dbPwd
     * @param apiCode
     * @param dbName
     * @param port
     * by xiarenji 2013-7-15
     */
    public int init(String dbIP, String dbUser, String dbPwd, String apiCode ,String dbName,int port){
    	release(); //释放以前的dbco,apiOpera对象

        this.dbUser = dbUser;
        this.dbPwd = dbPwd;
        this.apiCode_ = apiCode;
        if(characterEncoding!=null&&!"".equals(characterEncoding)){
        	this.dbUrl = "jdbc:mysql://" + dbIP+":"+port + "/" + dbName+"?characterEncoding="+characterEncoding;
        }else{
        	this.dbUrl = "jdbc:mysql://" + dbIP+":"+port + "/" + dbName;
        }
        
        return testConnect();
    }

    /**
     * 发送短信内容到一个手机号码。
     *
     * @param mobile  短信发送的目的手机号码
     * @param content 短信内容(编码为GB)，超过2000个字符部分会被截断
     * @param smID    短信ID，0到99999999中的某一整数。确保唯一后可以用来找到对应的回执、回复
     * @return 整数。0=成功
     */
    public int sendSM(String mobile, String content, long smID)
    {
        return this.sendSM(new String[]{mobile}, content, smID , smID);
    }
    /**
     * 发送短信内容到一个手机号码。
     *
     * @param mobiles  短信发送的目的手机号码数组
     * @param content  短信内容(编码为GB)，超过2000个字符部分会被截断
     * @param smID     短信ID，0到99999999中的某一整数。确保唯一后可以用来找到对应的回执、回复
     * @return 整数。   0=成功
     */
    public int sendSM(String[] mobiles, String content, long smID )
    {
    	return sendSM(mobiles , content , smID , smID);
    }
    
    /**
     * 发送短信内容到一群手机号码。
     *
     * @param mobiles 短信发送的所有目的手机号码构成的数组
     * @param content 短信内容(编码为GB)，超过2000个字符部分会被截断
     * @param smID    短信ID，0到99999999中的某一整数。确保唯一后可以用来找到对应的回执、回复
     * @param srcID   终端源地址
     * @return 整数。0=成功
     */
    public int sendSM(String[] mobiles, String content, long smID, long srcID)
    {
    	return sendSM(mobiles, content, smID, srcID, "");
    }
    
    /**
     * 发送短信内容到一群手机号码。
     *
     * @param mobiles 	短信发送的所有目的手机号码构成的数组
     * @param content 	短信内容(编码为GB)，超过2000个字符部分会被截断
     * @param sendTime 	发送时间（格式:yyyy-MM-dd HH:mm:ss）
     * @param smID    	短信ID，0到99999999中的某一整数。确保唯一后可以用来找到对应的回执、回复
     * @param srcID   	终端源地址
     * @return 	整数。0=成功
     */
    public int sendSM(String[] mobiles, String content, String sendTime, long smID, long srcID)
    {
    	return sendSM(mobiles, content, smID, srcID, "", sendTime);
    }

    /**
     * 发送Wap Push短信到一个手机号码。
     *
     * @param mobile  短信发送的目的手机号码
     * @param content 短信内容(编码为GB)，超过2000个字符部分会被截断
     * @param smID    短信ID，0到99999999中的某一整数。确保唯一后可以用来找到对应的回执、回复
     * @param url Wap短信的url地址,不能超过100个字符
     * @return 整数。0=成功
     */
    public int sendSM(String mobile, String content, long smID, String url)
	{
    	return this.sendSM(new String[]{mobile}, content, smID, url);
	}

    /**
     * 发送短信。
     *
     * @param mobiles 短信发送的所有目的手机号码构成的数组
     * @param content 短信内容(编码为GB)，超过2000个字符部分会被截断
     * @param smID    短信ID，0到99999999中的某一整数。确保唯一后可以用来找到对应的回执、回复
     * @param url     Wap短信的url地址,不能超过100个字符
     * @return 整数。0=成功
     */
    public int sendSM(String[] mobiles, String content, long smID, String url)
    {
    	return sendSM(mobiles, content , smID , smID , url);
    }
   
    /**
     * 发送短信。
     *
     * @param mobiles 短信发送的所有目的手机号码构成的数组
     * @param content 短信内容(编码为GB)，超过2000个字符部分会被截断
     * @param smID    短信ID，0到99999999中的某一整数。确保唯一后可以用来找到对应的回执、回复
     * @param srcID   终端源地址 
     * @param url     Wap短信的url地址,不能超过100个字符
     * @return 整数。0=成功
     */
    public int sendSM(String[] mobiles, String content, long smID, long srcID, String url)
    {
    	return sendSM(mobiles, content , smID , srcID , url, null);
    }
    
    /**
     * 发送短信。
     *
     * @param mobiles  短信发送的所有目的手机号码构成的数组
     * @param content  短信内容(编码为GB)，超过2000个字符部分会被截断
     * @param smID     短信ID，0到99999999中的某一整数。确保唯一后可以用来找到对应的回执、回复
     * @param srcID    终端源地址 
     * @param url      Wap短信的url地址,不能超过100个字符
     * @param sendTime 发送时间（格式:yyyy-MM-dd HH:mm:ss），立即发送时为null
     * @return 整数。0=成功
     */
   public int sendSM(String[] mobiles, String content, long smID, long srcID, String url, String sendTime)
    {
	   return sendSmAndPdu(mobiles, content, smID, srcID, url, sendTime,
      		 0, 0, 0, "", "", "", 0, APIClient.SM_TYPE_NORMAL);
    }
    
    /**
     * 不支持srcID的方法，定时发送一条PDU短信到一个手机号码集。
     * 
     * @param mobiles		短信发送的多个目的手机号码的数组
     * @param content		短信内容，超过2000个字符部分会被截断
     * @param smID			短信ID，0到99999999中的某一整数。确保唯一后可以用来找到对应的回执、回复
     * @param sendTime		定时发送时间，为空时立即发送
     * @param tpPID   		默认为0，具体请参见cmpp21或 cmpp30协议文档。
     * @param tpUdhi		默认为0，具体请参见cmpp21或 cmpp30协议文档。
     * @param feeTerminalID 被计费用户的号码，默认为空
     * @param feeType		资费类别，默认为空。
     * @param feeCode		资费代码
     * @param feeUserType	计费用户类型
     * @return int 			0:成功;其他:失败
     */
    public int sendPDU(String[] mobiles, byte[] content, long smID,  
    		int msgFmt, int tpPID, int tpUdhi, String feeTerminalID, String feeType, String feeCode, int feeUserType)
    {    
    	return sendPDU(mobiles, content , smID , smID, 
    			msgFmt,  tpPID, tpUdhi, feeTerminalID, feeType, feeCode, feeUserType);//如果不支持srcID,令srcID为smID.
    }
    
    /**
     * 支持srcID的方法，定时发送一条PDU短信到一个手机号码集。
     * 
     * @param mobiles		短信发送的多个目的手机号码的数组
     * @param content		短信内容，超过2000个字符部分会被截断
     * @param smID			短信ID，0到99999999中的某一整数。确保唯一后可以用来找到对应的回执、回复
     * @param srcID			终端源地址，0到99999999中的某一整数。用来作源地址显示在终端上
     * @param sendTime		定时发送时间，为空时立即发送
     * @param msgFmt		短信编码格式（最终通过cmpp21协议发送至网关的短信格式，具体类型请参见cmpp21或 cmpp30协议文档；）默认为0。
     * @param tpPID			默认为0
     * @param tpUdhi		默认为0
     * @param feeTerminalID 被计费用户的号码
     * @param feeType		资费类别
     * @param feeCode		资费代码
     * @param feeUserType	计费用户类型
     * @return  int      	0:成功;其他:失败	
     */
    public int sendPDU(String[] mobiles, byte[] content, long smID, long srcID, 
    		int msgFmt, int tpPID, int tpUdhi, String feeTerminalID, String feeType, String feeCode, int feeUserType)
    {
    	String contentStr = binary2Hex(content);
    	return sendSmAndPdu(mobiles, contentStr, smID, srcID, "", null,
        		 msgFmt, tpPID, tpUdhi, feeTerminalID, feeType, feeCode, feeUserType, APIClient.SM_TYPE_PDU);
    }
    
    /**
     * 最后PDU和普通短信都调用此方法，由此方法来实现具体的短信发送。
     * 
     * @param mobiles  	 	短信发送的多个目的手机号码的数组
     * @param content  	 	短信内容，超过2000个字符部分会被截断
     * @param smID    	 	短信ID，0到99999999中的某一整数。确保唯一后可以用来找到对应的回执、回复
     * @param srcID   		终端源地址，0到99999999中的某一整数。用来作源地址显示在终端上
     * @param sendTime 	 	定时发送时间，为空或为null时立即发送
     * @param msgFmt		短信编码格式（最终通过cmpp21协议发送至网关的短信格式，具体类型请参见cmpp21或 cmpp30协议文档；）
     * @param tpPID			默认为0
     * @param tpUdhi    	默认为0
     * @param feeTerminalID 被计费用户的号码
     * @param feeType		资费类别
     * @param feeCode		资费代码
     * @param feeUserType	计费用户类型
     * @param smType	    短信类型
     * @return int  		0:成功;其他:失败
     */
    private int sendSmAndPdu(String[] mobiles, String content, long smID, long srcID,String url, String sendTime,
    		int msgFmt, int tpPID, int tpUdhi, String feeTerminalID, String feeType, String feeCode, int feeUserType, int smType)
    {
    	if(dbUrl == null)
            return APIClient.IMAPI_INIT_ERR;
    	 if(mobiles == null || mobiles.length == 0)
         {
             return APIClient.IMAPI_DATA_ERR;
         }
        //手机号码连接成一起
         StringBuffer mobileBuf = new StringBuffer();
         for(int i = 0; i < mobiles.length; i++)
         {
             mobileBuf.append(",").append(mobiles[i]);
         }
         if(mobileBuf.length() > 1)
             mobileBuf.delete(0, 1);
         else
             return APIClient.IMAPI_DATA_ERR;
        
         //替换内容特殊字符
         String contenttmp = replaceSpecilAlhpa(content);
         if(contenttmp.length() < 1)
             return APIClient.IMAPI_DATA_ERR;

         //检查截取字符
         if(contenttmp.length() > 2000)
             contenttmp = contenttmp.substring(0, 2000);

         //检查短信ID是否合法
         if(!checkSmID(smID) || !checkSmID(srcID))
             return APIClient.IMAPI_DATA_ERR;

         //检查URL是否合法
         url = nullConvert(url).trim();
         if(url.length() > 0)
         {
         	if(url.length() > 85)
         	{
         		return APIClient.IMAPI_DATA_TOOLONG;
         	}
         	if((url + contenttmp).length() > 120)
         	{
         		return APIClient.IMAPI_DATA_TOOLONG;
         	}
         }
         
         // 判断网关是否关闭，接口是否可用
         int ret = checkGatConn();
         if(ret != 1){
         	return ret;
         }
         
         // 日期格式是否正确
         if(sendTime != null && !"".equals(sendTime)){
         	if(isDateTime(sendTime)==null){
         		return APIClient.IMAPI_DATA_ERR;
         	}
         }
         
         // 如果参数是null容错处理为""   (String feeTerminalID, String feeType, String feeCode)
         if( APIClient.SM_TYPE_PDU == smType )
         {
        	 feeTerminalID = nullConvert(feeTerminalID).trim();
        	 feeType = nullConvert(feeType).trim();
        	 feeCode = nullConvert(feeCode).trim();   	 
         }
         
          
        return mTInsert(mobileBuf.toString(), contenttmp, smID, srcID, url, sendTime,
        		msgFmt, tpPID, tpUdhi, feeTerminalID, feeType, feeCode, feeUserType, smType);
         
    	 
    	
    }
    
   
    /**
	 * 接收来自所有手机的短信。
	 *
	 * @return MOItem[]。所接收到的所用短信构成的数组，每个MOItem元素对应一条所接收的短信；
	 *         null表示接收失败，数组长度为0表示目前无短信。
	 */
    public MOItem[] receiveSM()
    {
    	return receiveSM( -1 );
    }
    
    /**
	 * 接收来自所有手机的短信。
	 *
	 * @param  amount    取得的Mo记录数量(当amount为－1时，不加limit限制条件)
	 * @return MOItem[]。所接收到的所用短信构成的数组，每个MOItem元素对应一条所接收的短信；
	 *         null表示接收失败，数组长度为0表示目前无短信。
	 */
    public MOItem[] receiveSM(int amount)
    {
    	return receiveSM(-1L, amount);
    }
    
    /**
	 * 接收来自所有手机的短信。
	 * 
	 * @param srcID 	手机上显示尾号(当srcID为－1时，不加srcID限制条件)
	 * @param amount	取得的Mo记录数量(当amount为－1时，不加limit限制条件)
	 *
	 * @return MOItem[]。所接收到的所用短信构成的数组，每个MOItem元素对应一条所接收的短信；
	 *         null表示接收失败，数组长度为0表示目前无短信。
	 */
    public MOItem[] receiveSM(long srcID, int amount)
    {
        if(dbUrl == null) //未初始化
            return null;

        if(this.conn == null)
        {
            int state = initConnect();
            if(state != 0)
                return null;
        }

        Statement st = null;
        ResultSet rs = null;

        String getMoSql = "select * from api_mo_" + this.apiCode_;
        if(srcID != -1){
        	getMoSql += " where SM_ID="+srcID;
        }
        if(amount != -1){
        	getMoSql += " limit "+amount;
        }else {
        	getMoSql += " limit 5000";
        }	
        String delMoSql = "delete from api_mo_" + this.apiCode_ + " where AUTO_SN in (";

        ArrayList moList = new ArrayList();
        StringBuffer snBuf = new StringBuffer("-1");
        try
        {
            st = this.conn.createStatement();
            rs = st.executeQuery(getMoSql);
            while(rs.next())
            {
                MOItem mItemtmp = new MOItem();
                mItemtmp.setSmID(rs.getLong("SM_ID"));
                mItemtmp.setContent(iso2gbk(rs.getString("CONTENT")));
                mItemtmp.setMobile(rs.getString("MOBILE"));
                mItemtmp.setMoTime(rs.getString("MO_TIME"));
                mItemtmp.setMsgFmt(rs.getInt("MSG_FMT"));

                snBuf.append(",").append(rs.getString("AUTO_SN"));
                moList.add(mItemtmp);
            }
            rs.close();
            st.executeUpdate(delMoSql + snBuf.toString() + ")");
        } catch(Exception e)
        {
            releaseConn();
            return null;
        } finally
        {
            closeStatment(st);
        }

        MOItem[] moItem = new MOItem[0];
        return (MOItem[])moList.toArray(moItem);
    }


    /**
     * 接收来自所有手机的短信回执。
     *
     * @return RPTItem[]。所接收到的所用短信回执构成的数组，每个RPTItem元素对应一条所接收的
     *         短信回执；null表示接收失败，数组长度为0表示目前无短信回执。
     */
    public RPTItem[] receiveRPT()
    {
    	return receiveRPT( -1 );
    }
    
    /**
     * 接收来自所有手机的短信回执。
     * 
     * @param amount		取得的回执记录数量（当amount为－1时，不加limit限制条件）
     *
     * @return RPTItem[]。所接收到的所用短信回执构成的数组，每个RPTItem元素对应一条所接收的
     *         短信回执；null表示接收失败，数组长度为0表示目前无短信回执。
     */
    public RPTItem[] receiveRPT( int amount)
    {
    	return  receiveRPT( -1L, amount);
    }
    
    /**
     * 接收来自所有手机的短信回执。
     * @param smID 			短信smID（当smID为－1时，不加smID限制条件）
     * @param amount		取得的回执记录数量（当amount为－1时，不加limit限制条件）
     *
     * @return RPTItem[]。所接收到的所用短信回执构成的数组，每个RPTItem元素对应一条所接收的
     *         短信回执；null表示接收失败，数组长度为0表示目前无短信回执。
     */
    public RPTItem[] receiveRPT(long smID, int amount)
    {
        if(dbUrl == null)   //未初始化
            return null;

        ResultSet rs = null;
        Statement st = null;
        if(this.conn == null)
        {
            int state = initConnect();
            if(state != 0)
                return null;
        }

        String getRPTSql = "select * from api_rpt_" + this.apiCode_;
        if(smID != -1){
        	getRPTSql += " where SM_ID="+smID;
        }
        if(amount != -1){
        	getRPTSql += " limit "+amount;
        }else{
        	getRPTSql += " limit 5000";
        }
        String delRPTSql = "delete from api_rpt_" + this.apiCode_ + " where AUTO_SN in (";

        RPTItem[] rptItem = null;
        ArrayList rptList = new ArrayList();

        StringBuffer snBuf = new StringBuffer("-1");
        try
        {
            st = this.conn.createStatement();
            rs = st.executeQuery(getRPTSql);
            while(rs.next())
            {
                RPTItem rptItemtmp = new RPTItem();
                rptItemtmp.setSmID(rs.getLong("SM_ID"));
                rptItemtmp.setCode(rs.getInt("RPT_CODE"));
                rptItemtmp.setMobile(rs.getString("MOBILE"));
                rptItemtmp.setDesc(iso2gbk(rs.getString("RPT_DESC")));
                rptItemtmp.setRptTime(rs.getString("RPT_TIME"));

                snBuf.append(",").append(rs.getString("AUTO_SN"));

                rptList.add(rptItemtmp);
            }
            rs.close();

            st.executeUpdate(delRPTSql + snBuf.toString() + ")");

        } catch(SQLException e)
        {
            releaseConn();
//            if(e.getErrorCode() == 1146 || e.getErrorCode() == 1142)
//                return new RPTItem[0];
//            else
                return null;
        } catch(Exception ex)
        {
            return null;
        } finally
        {
            closeStatment(st);
        }

        rptItem = new RPTItem[0];
        return (RPTItem[]) rptList.toArray(rptItem);
    }

    /**
     * 关闭APIClient对象与信息机的连接，释放APIClient对象所占用的所有资源。
     */
    public void release()
    {
        this.dbUser = null;
        this.dbPwd = null;
        this.apiCode_ = null;
        this.dbUrl = null;
        this.releaseConn();
    }


    /*
     * 测试数据库连接
     *
     * @return
     */
    private int testConnect()
    {
        Statement st = null;
        ResultSet rs = null;
        //创建数据库连接
        try
        {
            if(this.conn != null)
            {
                releaseConn();
            }

            getConn();

            st = this.conn.createStatement();

        } catch(Exception e)
        {
            return APIClient.IMAPI_CONN_ERR;//连接数据库失败
        }
        try
		{
			String tableName = "api_mo_" + this.apiCode_;
			rs = st.executeQuery("select * from " + tableName + " limit 1");
			rs.close();
		}catch(SQLException e)
		{
            return APIClient.IMAPI_API_ERR;//apiID不存在
		} finally
        {
            try
            {
                st.close();
            } catch(Exception ex)
            {
            }
        }
        return APIClient.IMAPI_SUCC;
    }

    /*
     * 初始化数据库连接conn
     *
     * @return int 0:成功，-1:连接失败
     */
    private int initConnect()
    {
        try
        {
            getConn();
        } catch(Exception e)
        {
            return APIClient.IMAPI_CONN_ERR;
        }

        return APIClient.IMAPI_SUCC;
    }

    /**
     * 得到数据库连接
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void getConn()
        throws ClassNotFoundException, SQLException
    {
        Class.forName("org.gjt.mm.mysql.Driver");
        this.conn = DriverManager.getConnection(this.dbUrl, this.dbUser,
            this.dbPwd);
    }

    /*
     * 释放数据库连接conn
     *
     * @return void
     */
    private void releaseConn()
    {
        if(this.conn != null)
        {
            try
            {
                this.conn.close();
            } catch(SQLException e)
            {
            }
        }
        this.conn = null;
    }
    
    /**
     *  PDU和普通短信发送向数据库插入一条数据.
     * 
     *  @return 整数。0=成功，-1：连接失败，-3：数据库插入错误
     */
    private int mTInsert(String mobile, String content, long smID, long srcID, String url, String sendTime,
    		int msgFmt, int tpPID, int tpUdhi, String feeTerminalID, String feeType, String feeCode, int feeUserType, int smType)
    { 
        if(this.conn == null)
        {
            int state = initConnect();
            if(state != 0)
            {
                return APIClient.IMAPI_CONN_ERR;
            }
        }
    	String sendMTSql = "";
  	
		sendMTSql = "insert into api_mt_" + this.apiCode_
				+ " (SM_ID,SRC_ID,MOBILES,CONTENT,IS_WAP,URL,SEND_TIME,MSG_FMT,TP_PID,TP_UDHI,FEE_TERMINAL_ID,FEE_TYPE,FEE_CODE,FEE_USER_TYPE,SM_TYPE) values ("
				+ smID + "," + srcID + ",'" + mobile + "','" + content + "', ";
			
		
		if (url != null && url.trim().length() != 0) 
		{
			sendMTSql +=  "1,'" + url + "',";
		}
		else
		{
			sendMTSql +=  "0,'',";
		}
		if (sendTime == null || "".equals(sendTime.trim())) 
		{
			sendMTSql += " null ,";
		} 
		else 
		{
			sendMTSql += "'" + sendTime + "',";
		}

		sendMTSql += msgFmt + "," + tpPID + "," + tpUdhi + ",'" + feeTerminalID
				+ "','" + feeType + "','" + feeCode + "'," + feeUserType + ","
				+ smType + ")";
			
		
		Statement st = null;
        try
        {
            st = this.conn.createStatement();
            st.executeUpdate(gb2Iso(sendMTSql));
        } catch(Exception e)
        {   
            releaseConn();
            return APIClient.IMAPI_INS_ERR;
        } finally
        {
            closeStatment(st);
        }
        return APIClient.IMAPI_SUCC;
    }

    /*
     * 关闭数据库声明st
     *
     * @return void
     */
    private void closeStatment(Statement st)
    {
        try
        {
            st.close();
        } catch(Exception e)
        {
        }
    }


    /*
     * 替换字符串中的非法字符
     *
     *@param content 输入的短信内容
     *@return String
     */
    private String replaceSpecilAlhpa(String content)
    {
        if(content == null || content.trim().length() == 0)
            return "";

        String spec_char = "\\'";
        String retStr = "";
        for(int i = 0; i < content.length(); i++)
        {
            if(spec_char.indexOf(content.charAt(i)) >= 0)
            {
                retStr += "\\";
            }
            retStr += content.charAt(i);
        }
        return retStr;
    }

    /*
     * 检查短信的smID,在0~99999999
     *
     *@param smID 短信ID
     *@return boolean
     */
    private boolean checkSmID(long smID)
    {
        //smID在0~99999999之间
        if(smID < 0 || smID > 99999999)
            return false;

        return true;
    }

    /*
     * 将字符串转换成iso8859-1类型
     *
     * @param str
     * @return String
     */
    private String gb2Iso(String str)
    {
        if(str == null)
        {
            return "";
        }
        String temp = "";
        try
        {
            byte[] buf = str.trim().getBytes("GBK");
            temp = new String(buf, "iso8859-1");
        } catch(UnsupportedEncodingException e)
        {
            temp = str;
        }
        return temp;
    }
   
    
    /**
     * 获取网关连接参数、接口开通参数
     * 
     * 检验网关是否连接(1：连通 0：未连接)
     * 判断接口是否可用(1：运行状态 0：调试状态现在一个状态：2：暂停（失效）状态)
     * @return ret
     */
    private int checkGatConn(){
    	int ret = 1;
    	ResultSet rs = null;
        Statement st = null;
        if(this.conn == null)
        {
            initConnect();
        }
    	
    	String sql = "select if_status,conn_succ_status from tbl_api_info as api where api.if_code='"+this.apiCode_+"' limit 1";
    	
    	try
		{
    		st = this.conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
            	if("2".equals(rs.getString("if_status"))){
            		ret = APIClient.IMAPI_IFSTATUS_INVALID;
            	}
            	if("0".equals(rs.getString("conn_succ_status"))){
            		ret = APIClient.IMAPI_GATEWAY_CONN_ERR;
            	}
            }
			rs.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
            return APIClient.IMAPI_API_ERR;//apiID不存在
		} finally
        {
            try
            {
                st.close();
            } catch(Exception ex)
            {
            	ex.printStackTrace();
            }
        }
    	return ret;
    }
    
    /**
     *
	 * 检验是否为日期(YYYY-MM-dd HH:mm:ss),如果不是，返回null，否则返回日期
	 * 
	 * @param str
	 * @return
     */
    public static String isDateTime(String str){
    	int temp;
		if(str == null)
			return null;
		if(str.length() != 19)
			return null;
		
		temp = Integer.parseInt(str.substring(5, 7));
		if(12 < temp || temp < 1)
			return null;

		temp = Integer.parseInt(str.substring(8, 10));
		if(31 < temp || temp < 1)
			return null;

		temp = Integer.parseInt(str.substring(11, 13));
		if(23 < temp || temp < 0)
			return null;
		temp = Integer.parseInt(str.substring(14, 16));
		if(59 < temp || temp < 0)
			return null;
		temp = Integer.parseInt(str.substring(17, 19));
		if(59 < temp || temp < 0)
			return null;
		Date returnDate = null;
		DateFormat df = DateFormat.getDateInstance();

		try
		{
			returnDate = df.parse(str);
			return returnDate.toString();
		}catch(Exception e)
		{
			return null;
		}
    }
    /**
     * 将字节数组类型的数据转化成16进制的字符串
     * @param bys
     * @return String
     */
    private String binary2Hex(byte[] bys) {
        if (bys == null || bys.length < 1)
            return null;

        StringBuffer sb = new StringBuffer(100);

        for (int i=0;i<bys.length;i++) {
            if (bys[i] >= 16)
                sb.append(Integer.toHexString(bys[i]));
            else if (bys[i] >= 0)
                sb.append("0" + Integer.toHexString(bys[i]));
            else
                sb.append(Integer.toHexString(bys[i]).substring(6, 8));
        }

        return sb.toString();
    }
    
    /**
     * 将字符串转换成gbk类型
     * 
     * @param str
     * @return
     */
    private String iso2gbk(String str){
    	if(str == null)
    	{
    		return "";
    	}
		String temp = "";
		try {
			byte[] buf = str.trim().getBytes("iso8859-1");
			temp = new String(buf, "GBK");
		} catch (UnsupportedEncodingException e) {
			temp = str;
		}
		return temp;
    }
    
    /**
     * 将字符串转换成非NULL的String
     * 
     * @param str
     * @return 非null的String
     */
    private String nullConvert(String str)
    {
    	if( str == null )
    	{
    		str = "";
    	}
    	return str;
    	
    }

	public String getCharacterEncoding() {
		return characterEncoding;
	}

	public void setCharacterEncoding(String characterEncoding) {
		this.characterEncoding = characterEncoding;
	}
   
}
