package com.panacea.reservation.model.vo;

import java.util.Date;

public class Reservation {
	
	private int reservationNo;
	private String patientId;
	private String doctorId;
	private String partId;
	private Date reservationDate;
	private String status;
	
	private String doctor_name;
	private String doctor_introduce;
	
	private String patient_name;
	private String patient_ssd;
	private String patient_phone;
	
	private int check_date;
	private String partName;
	
	public Reservation() {}

	public Reservation(int reservationNo, String patientId, String doctorId, String partId,
			Date reservationDate, String status) {
		this.reservationNo = reservationNo;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.partId = partId;
		this.reservationDate = reservationDate;
		this.status = status;
	}
	
	public Reservation(int reservationNo, String doctor_name, String doctor_introduce,
			Date reservationDate, String status) {
		this.reservationNo = reservationNo;
		this.doctor_name = doctor_name;
		this.doctor_introduce = doctor_introduce;
		this.reservationDate = reservationDate;
		this.status = status;
	}

	public int getReservationNo() {
		return reservationNo;
	}

	public void setReservationNo(int reservationNo) {
		this.reservationNo = reservationNo;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getpartId() {
		return partId;
	}

	public void setpartId(String partId) {
		this.partId = partId;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPartId() {
		return partId;
	}

	public void setPartId(String partId) {
		this.partId = partId;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public String getDoctor_introduce() {
		return doctor_introduce;
	}

	public void setDoctor_introduce(String doctor_introduce) {
		this.doctor_introduce = doctor_introduce;
	}

	public String getPatient_name() {
		return patient_name;
	}

	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}

	public String getPatient_ssd() {
		return patient_ssd;
	}

	public void setPatient_ssd(String patient_ssd) {
		this.patient_ssd = patient_ssd;
	}

	public String getPatient_phone() {
		return patient_phone;
	}

	public void setPatient_phone(String patient_phone) {
		this.patient_phone = patient_phone;
	}

	public int getCheck_date() {
		return check_date;
	}

	public void setCheck_date(int check_date) {
		this.check_date = check_date;
	}
	
	

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	@Override
	public String toString() {
		return "Reservation [reservationNo=" + reservationNo + ", patientId=" + patientId + ", doctorId=" + doctorId
				+ ", partId=" + partId + ", reservationDate=" + reservationDate + ", status=" + status
				+ "]";
	}
	
}
