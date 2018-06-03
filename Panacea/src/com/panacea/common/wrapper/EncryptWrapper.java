package com.panacea.common.wrapper;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncryptWrapper extends HttpServletRequestWrapper {

	public EncryptWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String key) {
		String value = "";
		
		if(key !=null && (key.equals("userPw")||key.equals("password")||key.equals("now_password")||key.equals("new_password"))) {
			value = super.getParameter(key);
			System.out.println("암호화전 : " + value);
		
		    value = getSha512(value);
		    System.out.println("암호화후 : " + value);
		} else {
			value = super.getParameter(key);
		}
		
		return value;
	}

	public String getPassword(String value) {
		System.out.println("암호화전 : " + value);
	    value = getSha512(value);
	    System.out.println("암호화후 : " + value);
		return value;
	}
	
	private String getSha512(String password) {
		String encPwd = null;
		MessageDigest md = null;
		
		try {
			md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		byte[] bytes = password.getBytes(Charset.forName("UTF-8"));
		md.update(bytes);
		
		byte[] encryptedBytes = md.digest();
		encPwd = Base64.getEncoder().encodeToString(encryptedBytes);
		
		return encPwd;
	}
}
