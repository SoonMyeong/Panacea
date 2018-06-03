package com.panacea.patient.model.vo;

public class Patient {
	
	private String patientId;
	private String password;
	private String patientName;
	private String ssd;
	private String phone;
	private String address;
	
	public Patient() {}

	public Patient(String patientId, String password, String patientName, String ssd, String phone, String address) {
		this.patientId = patientId;
		this.password = password;
		this.patientName = patientName;
		this.ssd = ssd;
		this.phone = phone;
		this.address = address;
	}
	
	public Patient(String patientId, String patientName, String phone, String address) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.phone = phone;
		this.address = address;
	}
	
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
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
		return "Patient [patientId=" + patientId + ", password=" + password + ", patientName=" + patientName + ", ssd="
				+ ssd + ", phone=" + phone + ", address=" + address + "]";
	}
	
}
