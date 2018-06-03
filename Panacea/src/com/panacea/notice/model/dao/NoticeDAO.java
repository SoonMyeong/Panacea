package com.panacea.notice.model.dao;

import static com.panacea.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.panacea.notice.model.exception.NoticeException;
import com.panacea.notice.model.vo.Notice;

public class NoticeDAO {

private Properties prop = new Properties();
public NoticeDAO() {
	String fileName = NoticeDAO.class.getResource("/sql/notice/notice-query.properties").getPath();
	try {
		prop.load(new FileReader(fileName));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}

	public List<Notice> selectList(Connection conn, int cPage, int numPerPage) throws NoticeException {
		
		List<Notice> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectNoticeListByPaging");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			int first_rownum = cPage*numPerPage-(numPerPage-1);
			int final_rownum = cPage*numPerPage;
		
			pstmt.setInt(1,first_rownum);
			pstmt.setInt(2,final_rownum);
			
			rset = pstmt.executeQuery();
			
			
			while(rset.next()) {
				Notice n = new Notice();
				n.setNoticeNo(rset.getInt("notice_no"));
				n.setAdminId(rset.getString("admin_Id"));
				n.setNoticeTitle(rset.getString("notice_title"));
				n.setNoticeContent(rset.getString("notice_content"));
				n.setNoticeFile(rset.getString("notice_file"));
				n.setNoticeFileRename(rset.getString("notice_file_rename"));
				n.setNoticeDate(rset.getDate("notice_date"));
				n.setStatus(rset.getString("status"));
				n.setEmphasize(rset.getInt("emphasize"));
				list.add(n);
			}
			//System.out.println("list@NoticeDAO="+list);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NoticeException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public Notice selectOne(Connection conn, int noticeNo) throws NoticeException {
		Notice notice = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectOne");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				notice = new Notice();
				notice.setNoticeNo(rset.getInt("notice_no"));
				notice.setAdminId(rset.getString("admin_id"));
				notice.setNoticeTitle(rset.getString("notice_title"));
				notice.setNoticeContent(rset.getString("notice_content"));
				notice.setNoticeFile(rset.getString("notice_file"));
				notice.setNoticeFileRename(rset.getString("notice_file_rename"));
				notice.setNoticeDate(rset.getDate("notice_date"));
				notice.setStatus(rset.getString("status"));
			}
			System.out.println("notice@NoticeDAO="+notice);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NoticeException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return notice;
	}

	public int insertNotice(Connection conn, Notice n) throws NoticeException {
		int result =0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertNotice");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, n.getAdminId());
			pstmt.setString(2, n.getNoticeTitle());
			pstmt.setString(3, n.getNoticeContent());
			pstmt.setString(4, n.getNoticeFile());
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NoticeException(e.getMessage());
		}
		return result;
	}

	public int updateNotice(Connection conn, Notice n) throws NoticeException {
		int result =0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateNotice");
		

		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setString(3, n.getNoticeFile());
			pstmt.setInt(4, n.getNoticeNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NoticeException(e.getMessage());
		}
		return result;
	}

	public int deleteNotice(Connection conn, int noticeNo2) throws NoticeException {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteNotice"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setInt(1, noticeNo2);
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NoticeException(e.getMessage());
		}
		
		return result;
	}

	public int selectListCount(Connection conn) throws NoticeException {
		int totalMember= 0;
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		String query = prop.getProperty("selectListCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			rset.next();
			totalMember = rset.getInt("cnt");
			System.out.println("totalMember@noticeDAO="+totalMember);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new NoticeException(e.getMessage());
		}finally {
			close(rset);
			close(pstmt);
		}
		return totalMember;
	}

	public int selectNoticeCountByAdminId(Connection conn, String searchKeyword) throws NoticeException {
	
		int totalMember = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectNoticeCountByAdminId");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			rset = pstmt.executeQuery();
			
			rset.next();
			totalMember = rset.getInt("cnt");
			//System.out.println("totalMember@AdminDAO="+totalMember);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NoticeException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalMember;
		
	}

	public int selectNoticeTitle(Connection conn, String searchKeyword) throws NoticeException {
		PreparedStatement pstmt = null;
		int totalMember = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectNoticeTitle");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			
			//쿼리문실행
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				totalMember = rset.getInt("cnt");
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new NoticeException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return totalMember;
	}

	public List<Notice> selectNoticeByAdminId(Connection conn, String searchKeyword, int cPage, int numPerPage) throws NoticeException {
		List<Notice> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectPagedNoticeByUserId");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Notice n = new Notice();
				n.setNoticeNo(rset.getInt("notice_no"));
				n.setAdminId(rset.getString("admin_Id"));
				n.setNoticeTitle(rset.getString("notice_title"));
				n.setNoticeContent(rset.getString("notice_content"));
				n.setNoticeFile(rset.getString("notice_file"));
				n.setNoticeFileRename(rset.getString("notice_file_rename"));
				n.setNoticeDate(rset.getDate("notice_date"));
				n.setStatus(rset.getString("status"));
				list.add(n);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new NoticeException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public List<Notice> selectNoticeTitle(Connection conn, String searchKeyword, int cPage, int numPerPage) throws NoticeException {
		List<Notice> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectPagedNoticeTitle");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Notice n = new Notice();
				n.setNoticeNo(rset.getInt("notice_no"));
				n.setAdminId(rset.getString("admin_Id"));
				n.setNoticeTitle(rset.getString("notice_title"));
				n.setNoticeContent(rset.getString("notice_content"));
				n.setNoticeFile(rset.getString("notice_file"));
				n.setNoticeFileRename(rset.getString("notice_file_rename"));
				n.setNoticeDate(rset.getDate("notice_date"));
				n.setStatus(rset.getString("status"));
				list.add(n);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new NoticeException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int insertNotice2(Connection conn, Notice n) throws NoticeException {
		int result =0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertNotice2");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, n.getAdminId());
			pstmt.setString(2, n.getNoticeTitle());
			pstmt.setString(3, n.getNoticeContent());
			pstmt.setString(4, n.getNoticeFile());
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NoticeException(e.getMessage());
		}
		return result;
	}



	

}
