package com.panacea.chart.model.vo;

public class Chart {

	private int chart_no;
	private String doctor_id;
	private String patient_id;
	private String disease_name;
	private String chart_comment;
	
	private String doctor_name;
	private String patient_name;
	private String patient_ssd;
	private String part_name;
	

	public Chart() {
		super();
	}

	
	
	
	

	public Chart(int chart_no, String doctor_id, String patient_id, String disease_name, String chart_comment,
			String doctor_name, String patient_name, String patient_ssd, String part_name) {
		super();
		this.chart_no = chart_no;
		this.doctor_id = doctor_id;
		this.patient_id = patient_id;
		this.disease_name = disease_name;
		this.chart_comment = chart_comment;
		this.doctor_name = doctor_name;
		this.patient_name = patient_name;
		this.patient_ssd = patient_ssd;
		this.part_name = part_name;
	}






	public Chart(String doctor_id, String patient_id, String disease_name, String chart_comment) {
		super();
		this.doctor_id = doctor_id;
		this.patient_id = patient_id;
		this.disease_name = disease_name;
		this.chart_comment = chart_comment;
	}

	public int getChart_no() {
		return chart_no;
	}

	public void setChart_no(int chart_no) {
		this.chart_no = chart_no;
	}

	public String getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(String doctor_id) {
		this.doctor_id = doctor_id;
	}

	public String getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}

	public String getDisease_name() {
		return disease_name;
	}

	public void setDisease_name(String disease_name) {
		this.disease_name = disease_name;
	}

	public String getChart_comment() {
		return chart_comment;
	}

	public void setChart_comment(String chart_comment) {
		this.chart_comment = chart_comment;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
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

	public String getPart_name() {
		return part_name;
	}

	public void setPart_name(String part_name) {
		this.part_name = part_name;
	}

	@Override
	public String toString() {
		return "Chart [chart_no=" + chart_no + ", doctor_id=" + doctor_id + ", patient_id=" + patient_id
				+ ", disease_name=" + disease_name + ", chart_comment=" + chart_comment + ", doctor_name=" + doctor_name
				+ "]";
	}
	
	
}
