package com.lww.design.graduation.utils;

import com.lww.design.graduation.common.AppConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 *
 * @author      leo.xu
 * @description 常用加密工具类
 * @date
 */
public class EncryUtils {

    private static Logger logger= LoggerFactory.getLogger(EncryUtils.class);

    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;

    public static void main(String[] args) {
        System.out.println(encryPwd("123456"));
        String enc = getSha1("sadamdojwpoqkdwdpasldpokpkapodkoskpoaksodkapsd,la,l;dlas,da"+ AppConstant.PWD_ENCRY_SUFFIX);

        System.out.println(enc+"\tlen:"+enc.length());
//		System.out.println(encryPwd("hiveview"));
    }

    /**
     * 用户密码加密
     * @param password
     * @return
     */
    public static String encryPwd(String password){
        return getMd5(getSha1(password+ AppConstant.PWD_ENCRY_SUFFIX));
    }


    public static String getSha1(String string) {
        return encryCalc(string,"SHA1");
    }

    public static String getMd5(String str){
        return encryCalc(str, "MD5");
    }


    /**
     * SHA1 加密
     * @param str
     * @return
     */
    public static String encryCalc(String str,String encryType){
        if (null == str || 0 == str.length()){
            return null;
        }
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance(encryType);
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            System.out.println(j);
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (NoSuchAlgorithmException e) {
            logger.error("",e);
        } catch (UnsupportedEncodingException e) {
            logger.error("",e);
        }
        return null;
    }
}
