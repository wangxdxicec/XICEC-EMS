/**
 * [Product]
 * SchoolSocial
 * [Copyright]
 * Copyright © 2012 ICSS All Rights Reserved.
 * [FileName]
 * SecurityUtil.java
 * [History]
 * Version Date Author Content
 * -------- --------- ---------- ------------------------
 * 1.0.0 2012-12-7 Administrator 最初版本
 */
package com.zhenhappy.security;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Summary:
 * <p/>
 * 安全加密方法
 */
public class SecurityUtil {

    /**
     * Summary:
     * <p/>
     * decryption DES解密算法
     *
     * @param encryptionContent
     * @return
     */
    public static String decryption(String encryptionContent) {
        return encryptionContent;
    }

    /**
     * <b>Summary: </b> hash5: 计算字符串的MD5摘要
     *
     * @param data
     * @return
     */
    public static String hash5(String data) {
        return DigestUtils.md5Hex(data);
    }

}
