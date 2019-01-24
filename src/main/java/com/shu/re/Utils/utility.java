package com.shu.re.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class utility {
	public static String [] Split(String str)
	{
		return str.split("|");
	}

	public static boolean EmptyString(String str) {
		return null == str || "".equals(str.trim())||str.equals("null");
	}
	//全局数组
	private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
		"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };



	// 返回形式为数字跟字符串
	private static String byteToArrayString(byte bByte) {
		int iRet = bByte;
		// System.out.println("iRet="+iRet);
		if (iRet < 0) {
			iRet += 256;
		}
		int iD1 = iRet / 16;
		int iD2 = iRet % 16;
		return strDigits[iD1] + strDigits[iD2];
	}

	// 返回形式只为数字
	private static String byteToNum(byte bByte) {
		int iRet = bByte;
		System.out.println("iRet1=" + iRet);
		if (iRet < 0) {
			iRet += 256;
		}
		return String.valueOf(iRet);
	}

	// 转换字节数组为16进制字串
	private static String byteToString(byte[] bByte) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < bByte.length; i++) {
			sBuffer.append(byteToArrayString(bByte[i]));
		}
		return sBuffer.toString();
	}
	public static String Encrypt(String str)
	{
		return GetMD5Code(GetMD5Code(str));
	}
	public static String GetMD5Code(String strObj) {
		String resultString = null;
		try {
			resultString = new String(strObj);
			MessageDigest md = MessageDigest.getInstance("MD5");
			// md.digest() 该函数返回值为存放哈希值结果的byte数组
			resultString = byteToString(md.digest(strObj.getBytes()));
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return resultString;
	}
	public static boolean isNumeric(String str){
		try {  
            int num=Integer.valueOf(str);//把字符串强制转换为数字  
            return true;//如果是数字，返回True  
        } catch (Exception e) {  
            return false;//如果抛出异常，返回False  
        }  
	}



}
