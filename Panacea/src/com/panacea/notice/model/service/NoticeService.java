package com.panacea.notice.model.service;










import static com.panacea.common.JDBCTemplate.*;
import static com.panacea.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;


import com.panacea.notice.model.dao.NoticeDAO;
import com.panacea.notice.model.exception.NoticeException;
import com.panacea.notice.model.vo.Notice;



public class NoticeService {
	
	public List<Notice> selectList(int cPage,int numPerPage) throws NoticeException {
		Connection conn = getConnection();
		List<Notice> list = new NoticeDAO().selectList(conn,cPage,numPerPage);
		return list;
	}

	public Notice selectOne(int noticeNo) throws NoticeException {
		Connection conn = getConnection();
		Notice n = new NoticeDAO().selectOne(conn, noticeNo);
		close(conn);
		return n;
	}

	public int insertNotice(Notice n) throws NoticeException {
		Connection conn = getConnection();
		int result = new NoticeDAO().insertNotice(conn,n);
		if(result >0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int updateNotice(Notice n) throws NoticeException {
		Connection conn = getConnection();
		int result = new NoticeDAO().updateNotice(conn,n);
		if(result >0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int deleteNotice(int noticeNo2) throws NoticeException {
		Connection conn = getConnection();
		int result = new NoticeDAO().deleteNotice(conn, noticeNo2);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		
		return result;
	}

	public int selectListCount() throws NoticeException {
		
		Connection conn = getConnection();
		int totalMember = new NoticeDAO().selectListCount(conn);
		close(conn);
		return totalMember;
		
	}

	public int selectNoticeCountByAdminId(String searchKeyword) throws NoticeException {
		Connection conn = getConnection();
		int totalMember = new NoticeDAO().selectNoticeCountByAdminId(conn, searchKeyword);
		close(conn);
		return totalMember;
	
	}

	public int selectNoticeTitle(String searchKeyword) throws NoticeException {
		Connection conn = getConnection();
		int totalMember = new NoticeDAO().selectNoticeTitle(conn, searchKeyword);
		close(conn);
		return totalMember;
	}

	public List<Notice> selectNoticeByAdminId(String searchKeyword, int cPage, int numPerPage) throws NoticeException {
		Connection conn = getConnection();
		List<Notice> list= new NoticeDAO().selectNoticeByAdminId(conn,searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<Notice> selectNoticeTitle(String searchKeyword, int cPage, int numPerPage) throws NoticeException {
		Connection conn = getConnection();
		List<Notice> list= new NoticeDAO().selectNoticeTitle(conn,searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int insertNotice2(Notice n) throws NoticeException {
		Connection conn = getConnection();
		int result = new NoticeDAO().insertNotice2(conn,n);
		if(result >0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

}
