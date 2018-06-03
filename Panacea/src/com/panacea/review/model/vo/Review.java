package com.panacea.review.model.vo;

import java.sql.Date;

public class Review {
	private int reviewNo;
	private String patientId;
	private String reviewTitle;
	private String reviewContent;
	private int grade;
	private int hits;
	private Date reviewDate;

	
	
	public Review() {}


	public Review(int reviewNo, String patientId, String reviewTitle, String reviewContent, int grade, int hits,
			Date reviewDate) {
		super();
		this.reviewNo = reviewNo;
		this.patientId = patientId;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
		this.grade = grade;
		this.hits = hits;
		this.reviewDate = reviewDate;
	}





	public int getReviewNo() {
		return reviewNo;
	}


	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}


	public String getPatientId() {
		return patientId;
	}


	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}


	public String getReviewTitle() {
		return reviewTitle;
	}


	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}
	
	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}


	public int getGrade() {
		return grade;
	}


	public void setGrade(int grade) {
		this.grade = grade;
	}


	public int getHits() {
		return hits;
	}


	public void setHits(int hits) {
		this.hits = hits;
	}


	public Date getReviewDate() {
		return reviewDate;
	}


	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}


	@Override
	public String toString() {
		return  reviewNo + "," + patientId + "," + reviewTitle + ","
				+reviewContent+","+ grade + "," + hits + "," + reviewDate;
	}
	
	
	
	
	
	

}
