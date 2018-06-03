package com.panacea.review.model.vo;

import java.sql.Date;

public class ReviewComment {
	
	private int commentNo;
	private int reviewNo;
	private String commentWriter;
	private int commentLevel;
	private int commentRef;
	private String commentContent;
	private Date commentDate;
	private String status;
	private int likeIt;
	private int bad;



	public ReviewComment() {}

	public ReviewComment(int commentNo, int reviewNo, String commentWriter, int commentLevel, int commentRef,
			String commentContent, Date commentDate, String status) {
		this.commentNo = commentNo;
		this.reviewNo = reviewNo;
		this.commentWriter = commentWriter;
		this.commentLevel = commentLevel;
		this.commentRef = commentRef;
		this.commentContent = commentContent;
		this.commentDate = commentDate;
		this.status = status;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	
	public int getLikeIt() {
		return likeIt;
	}

	public void setLikeIt(int likeIt) {
		this.likeIt = likeIt;
	}

	public int getBad() {
		return bad;
	}

	public void setBad(int bad) {
		this.bad = bad;
	}
	

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public String getCommentWriter() {
		return commentWriter;
	}

	public void setCommentWriter(String commentWriter) {
		this.commentWriter = commentWriter;
	}

	public int getCommentLevel() {
		return commentLevel;
	}

	public void setCommentLevel(int commentLevel) {
		this.commentLevel = commentLevel;
	}

	public int getCommentRef() {
		return commentRef;
	}

	public void setCommentRef(int commentRef) {
		this.commentRef = commentRef;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ReviewComment [commentNo=" + commentNo + ", reviewNo=" + reviewNo + ", commentWriter=" + commentWriter
				+ ", commentLevel=" + commentLevel + ", commentRef=" + commentRef + ", commentContent=" + commentContent
				+ ", commentDate=" + commentDate + ", status=" + status + ",likeIt"+likeIt+",bad"+bad+"]";
	}
}
