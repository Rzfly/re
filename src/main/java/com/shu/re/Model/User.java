package com.shu.re.Model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import org.apache.commons.codec.binary.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class User {

	private String uuid;
	private String uid;//学号
	private String name;//姓名
	private String password;//密码
	private int type; // 用户类型：0=管理员，1=学生

	public User() {
		this.setUuid(UUID.randomUUID().toString());
	}//自带uuid

	public User setUuid(String uuid) {
		this.uuid = uuid;
		return this;
	}

	@Id
	@Column(name = "uuid")
	public String getUuid() {
		return uuid;
	}

	@Column(name = "uid")
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "type")
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	public String doMD5(String str)  {
		String newstr = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			newstr = bytesToHex(Base64.encodeBase64(md5.digest(str.getBytes())));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return newstr;
	}

	// 把数组每一字节换成16进制连成md5字符串
	private static String bytesToHex(byte[] bytes) {
		StringBuffer md5str = new StringBuffer();
		int digital;
		for (int i = 0; i < bytes.length; i++) {
			digital = bytes[i];
			if (digital < 0) {
				digital += 256;
			}
			if (digital < 16) {
				md5str.append("0");
			}
			md5str.append(Integer.toHexString(digital));
		}
		return md5str.toString().toLowerCase();
	}
	
}
