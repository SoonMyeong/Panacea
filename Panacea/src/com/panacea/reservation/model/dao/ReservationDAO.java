package com.panacea.reservation.model.dao;

import static com.panacea.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import com.panacea.reservation.model.exception.ReservationException;
import com.panacea.reservation.model.vo.Reservation;

public class ReservationDAO {
	
	private Properties prop = new Properties();
	
	public ReservationDAO() {
		
		try {
			
			URL fileURL = ReservationDAO.class.getResource("/sql/reservation/reservation_query.properties");
			String fileName = fileURL.getPath();
			prop.load(new FileInputStream(fileName));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> checkTime(Connection conn, String patientId, String doctorId, String date) throws ReservationException {
		
		ArrayList<String> time_list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("checkTime");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, date);
			pstmt.setString(2, doctorId);
			pstmt.setString(3, patientId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				String time = rset.getString("time");
				
				time_list.add(time);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ReservationException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return time_list;
	}

	public int insertReservation(Connection conn, String patientId, String partId, String doctorId, String dateTime) throws ReservationException {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertReservation");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, patientId);
			pstmt.setString(2, doctorId);
			pstmt.setString(3, partId);
			pstmt.setString(4, dateTime);
			
			result = pstmt.executeUpdate(); 
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ReservationException(e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public List<Reservation> patientSelectReservationList(Connection conn, String userId, int cPage, int numPerPage) throws ReservationException {
		List<Reservation> list = null;
		PreparedStatement pstmt=null;
		ResultSet rset = null;
		String query = prop.getProperty("patientSelectReservationList");
		String time="";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rset = pstmt.executeQuery();
			
			list= new ArrayList<>();
			while(rset.next()) {
				Reservation r = new Reservation();
	            r.setReservationNo(rset.getInt("reservation_no"));
	            r.setDoctor_name(rset.getString("doctor_name"));
	            r.setDoctor_introduce(rset.getString("doctor_introduce"));
	            
	            
	            time = rset.getString("time");
	            System.out.println(time);
	            r.setReservationDate( sdf.parse(time));
	            r.setCheck_date(rset.getInt("cd"));
	            r.setStatus(rset.getString("status"));
	            
	            r.setDoctorId(rset.getString("doctor_id"));
	            r.setPartName(rset.getString("part_name"));
	            r.setPatientId(rset.getString("patient_id"));
				
				list.add(r);
			}
			System.out.println("list@ReservationDAO="+list);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ReservationException(e.getMessage());
		} catch (ParseException e) {
			e.printStackTrace();
			throw new ReservationException(e.getMessage());
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public Reservation selectReservation(Connection conn, int rno) throws ReservationException {
		Reservation r = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectReservation");
		String time="";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, rno);
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
	            r = new Reservation();
	            r.setReservationNo(rset.getInt("reservation_no"));
	            r.setDoctor_name(rset.getString("doctor_name"));
	            r.setDoctor_introduce(rset.getString("doctor_introduce"));
	            r.setPatientId(rset.getString("patient_id"));
	            r.setPatient_name(rset.getString("pname"));
	            
	            time = rset.getString("time");
	            System.out.println(time);
	            r.setReservationDate(sdf.parse(time));
	            r.setStatus(rset.getString("status"));
	            r.setPatient_ssd(rset.getString("p_ssd"));
	            r.setDoctorId(rset.getString("doctor_id"));
			}
			System.out.println("Reservation@ReservationDAO="+r);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ReservationException(e.getMessage());
		} catch (ParseException e) {
			e.printStackTrace();
			throw new ReservationException(e.getMessage());
		}finally {
			close(rset);
			close(pstmt);
		}
		return r;
	}

	public List<Reservation> doctorSelectReservationList(Connection conn, String userId, int cPage, int numPerPage) throws ReservationException {
		List<Reservation> list = null;
		PreparedStatement pstmt=null;
		ResultSet rset = null;
		String query = prop.getProperty("doctorSelectReservationList");
		String time="";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rset = pstmt.executeQuery();
			
			list= new ArrayList<>();
			while(rset.next()) {
				Reservation r = new Reservation();
				r.setReservationNo(rset.getInt("reservation_no"));
				r.setPatientId(rset.getString("patient_id"));
				r.setPatient_name(rset.getString("patient_name"));
				r.setPatient_ssd(rset.getString("ssd"));
				r.setPatient_phone(rset.getString("phone"));
				
				time = rset.getString("time");
				System.out.println(time);
				r.setReservationDate( sdf.parse(time));
				r.setCheck_date(rset.getInt("cd"));
				r.setStatus(rset.getString("status"));
				
				list.add(r);
			}
			System.out.println("list@ReservationDAO="+list);
		}catch(SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
			throw new ReservationException(e.getMessage());
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int deleteReservation(Connection conn, int rno) throws ReservationException {
		PreparedStatement pstmt =null;
		int result=0;
		String query = prop.getProperty("deleteReservation");
		try {
			
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, rno);
			
			result = pstmt.executeUpdate();
			
			System.out.println("result@ReservationDAO="+result);
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ReservationException(e.getMessage());
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int doctorReservationCount(Connection conn, String userId) throws ReservationException {
		PreparedStatement pstmt = null;
		int count = 0;
		ResultSet rset = null;
		String query = prop.getProperty("doctorReservationListCount");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			//쿼리문실행
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				count = rset.getInt("cnt");
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new ReservationException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return count;
	}
	
	public int patientReservationCount(Connection conn, String userId) throws ReservationException {
		PreparedStatement pstmt = null;
		int count = 0;
		ResultSet rset = null;
		String query = prop.getProperty("patientReservationListCount");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			//쿼리문실행
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				count = rset.getInt("cnt");
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new ReservationException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return count;
	}
	

	public int updateReservation(Connection conn, int rno, String datetime) throws ReservationException {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateReservation");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, datetime);
			pstmt.setInt(2, rno);
			
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ReservationException(e.getMessage());
		}
		finally {
			close(pstmt);
		}
		return result;
	}

	public List<Reservation> patientSelectReservationReviewList(Connection conn, String userId, int cPage,
			int numPerPage) throws ReservationException {
	
		List<Reservation> list = null;
		PreparedStatement pstmt=null;
		ResultSet rset = null;
		String query = prop.getProperty("patientSelectReservationReviewList");
		String time="";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rset = pstmt.executeQuery();
			
			list= new ArrayList<>();
			while(rset.next()) {
				Reservation r = new Reservation();
				r.setReservationNo(rset.getInt("reservation_no"));
				r.setDoctor_name(rset.getString("doctor_name"));
				r.setDoctor_introduce(rset.getString("doctor_introduce"));
				
				
				time = rset.getString("time");
				System.out.println(time);
				r.setReservationDate( sdf.parse(time));
				r.setCheck_date(rset.getInt("cd"));
				r.setStatus(rset.getString("status"));
				
				r.setDoctorId(rset.getString("doctor_id"));
				r.setPartName(rset.getString("part_name"));
				r.setPatientId(rset.getString("patient_id"));
				
				
				list.add(r);
			}
			System.out.println("list@ReservationDAO="+list);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ReservationException(e.getMessage());
		} catch (ParseException e) {
			e.printStackTrace();
			throw new ReservationException(e.getMessage());
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int patientReservationReviewCount(Connection conn, String userId) throws ReservationException {
		PreparedStatement pstmt = null;
		int count = 0;
		ResultSet rset = null;
		String query = prop.getProperty("patientReservationReviewListCount");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			//쿼리문실행
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				count = rset.getInt("cnt");
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new ReservationException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return count;
	}

}
