package com.panacea.admin.model.vo;

public class Admin {
	
	private String adminId;
	private String password;
	private String adminName;
	private String ssd;
	private String phone;
	private String address;
	
	public Admin() {}

	public Admin(String adminId, String password, String adminName, String ssd, String phone, String address) {
		this.adminId = adminId;
		this.password = password;
		this.adminName = adminName;
		this.ssd = ssd;
		this.phone = phone;
		this.address = address;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getSsd() {
		return ssd;
	}

	public void setSsd(String ssd) {
		this.ssd = ssd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", password=" + password + ", adminName=" + adminName + ", ssd=" + ssd
				+ ", phone=" + phone + ", address=" + address + "]";
	}
	
}
