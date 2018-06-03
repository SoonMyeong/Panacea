package com.panacea.doctor.model.vo;

public class Doctor {
	
	private String doctorId;
	private String password;
	private String doctorName;
	private String doctorProfile;
	private String ssd;
	private String phone;
	private String address;
	private String doctorIntoduce;
	private String partId;
	private String partName;
	
	public Doctor() {}

	public Doctor(String doctorId, String password, String doctorName, String doctorProfile, String ssd, String phone,
			String address, String doctorIntoduce, String partId) {
		this.doctorId = doctorId;
		this.password = password;
		this.doctorName = doctorName;
		this.doctorProfile = doctorProfile;
		this.ssd = ssd;
		this.phone = phone;
		this.address = address;
		this.doctorIntoduce = doctorIntoduce;
		this.partId = partId;
	}
	
	public Doctor(String doctorId, String doctorName, String partId) {
		super();
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.partId = partId;
	}

	public Doctor(int a, String doctorId, String doctorName, String doctorProfile, String doctorIntoduce, String partId, String partName) {
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.doctorProfile = doctorProfile;
		this.doctorIntoduce = doctorIntoduce;
		this.partId = partId;
		this.partName = partName;
	}
	
	public Doctor(String doctorId, String password, String doctorName, String doctorProfile, String ssd, String phone, String address,
			String partName) {
		super();
		this.doctorId = doctorId;
		this.password = password;
		this.doctorName = doctorName;
		this.doctorProfile = doctorProfile;
		this.ssd = ssd;
		this.phone = phone;
		this.address = address;
		this.partName = partName;
	}
	
	public Doctor(String doctorId, String doctorName,String partName,String doctorProfile, String phone, String address) {
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.doctorProfile = doctorProfile;
		this.phone = phone;
		this.address = address;
		this.partName = partName;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorProfile() {
		return doctorProfile;
	}

	public void setDoctorProfile(String doctorProfile) {
		this.doctorProfile = doctorProfile;
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

	public String getDoctorIntoduce() {
		return doctorIntoduce;
	}

	public void setDoctorIntoduce(String doctorIntoduce) {
		this.doctorIntoduce = doctorIntoduce;
	}

	public String getPartId() {
		return partId;
	}

	public void setPartId(String partId) {
		this.partId = partId;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", password=" + password + ", doctorName=" + doctorName
				+ ", doctorProfile=" + doctorProfile + ", ssd=" + ssd + ", phone=" + phone + ", address=" + address
				+ ", doctorIntoduce=" + doctorIntoduce + ", partId=" + partId + "]";
	}
	
}
