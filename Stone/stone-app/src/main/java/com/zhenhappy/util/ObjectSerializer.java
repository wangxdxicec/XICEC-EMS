/**
 * [Product]
 * SchoolSocial
 * [Copyright]
 * Copyright © 2013 ICSS All Rights Reserved.
 * [FileName]
 * ObjectSerialization.java
 * [History]
 * Version Date Author Content
 * -------- --------- ---------- ------------------------
 * 1.0.0 2013-8-11 wangsu 最初版本
 */
package com.zhenhappy.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.persistence.Entity;
import org.apache.log4j.Logger;

/**
 * <b>Summary: </b> TODO
 */
@Entity public class ObjectSerializer {

	private static Logger log = Logger.getLogger(ObjectSerializer.class);

	/**
	 * <b>Summary: </b> 
	 * jdkSerial 基于JDK的序列化.
	 * @param object
	 * @return
	 */
	public static byte[] jdkSerial(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			// 序列化
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {
			log.error("serial error,object:" + object);
			return null;
		} finally {
			try {
				baos.close();
				oos.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * <b>Summary: </b> 
	 * jdkDeserial 基于JDK的对象反序列化
	 * @param bytes
	 * @return
	 */
	public static Object jdkDeserial(byte[] bytes) {
		ByteArrayInputStream bais = null;
		try {
			// 反序列化
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {

		}finally{
			try {
				bais.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
