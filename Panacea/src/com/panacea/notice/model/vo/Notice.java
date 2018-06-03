package com.panacea.notice.model.vo;

import java.sql.Connection;
import java.sql.Date;

public class Notice {
	
	private int noticeNo;
	private String adminId;
	private String noticeTitle;
	private String noticeContent;
	private String noticeFile;
	private String noticeFileRename;
	private Date noticeDate;
	private String status;
	
	private int emphasize;
	
	public int getEmphasize() {
		return emphasize;
	}

	public void setEmphasize(int emphasize) {
		this.emphasize = emphasize;
	}

	public Notice() {}

	public Notice(int noticeNo, String adminId, String noticeTitle, String noticeContent, String noticeFile,
			String noticeFileRename, Date noticeDate, String status) {
		this.noticeNo = noticeNo;
		this.adminId = adminId;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeFile = noticeFile;
		this.noticeFileRename = noticeFileRename;
		this.noticeDate = noticeDate;
		this.status = status;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getNoticeFile() {
		return noticeFile;
	}

	public void setNoticeFile(String noticeFile) {
		this.noticeFile = noticeFile;
	}

	public String getNoticeFileRename() {
		return noticeFileRename;
	}

	public void setNoticeFileRename(String noticeFileRename) {
		this.noticeFileRename = noticeFileRename;
	}

	public Date getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", adminId=" + adminId + ", noticeTitle=" + noticeTitle
				+ ", noticeContent=" + noticeContent + ", noticeFile=" + noticeFile + ", noticeFileRename="
				+ noticeFileRename + ", noticeDate=" + noticeDate + ", status=" + status + "]";
	}

	
}
