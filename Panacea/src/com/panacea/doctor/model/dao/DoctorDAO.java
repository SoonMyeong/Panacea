package com.panacea.doctor.model.dao;

import static com.panacea.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.panacea.doctor.model.exception.DoctorException;
import com.panacea.doctor.model.vo.Doctor;

public class DoctorDAO {

	private Properties prop = new Properties();
	
	public DoctorDAO() {
		
		try {
			
			URL fileURL = DoctorDAO.class.getResource("/sql/doctor/doctor_query.properties");
			String fileName = fileURL.getPath();
			prop.load(new FileInputStream(fileName));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int loginDoctor(Connection conn, String userId, String userPw) throws DoctorException {

		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("loginDoctor");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				result = rset.getInt("cnt");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new DoctorException(e.getMessage());
			
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public Doctor selectDoctor(Connection conn, String userId) throws DoctorException {

		Doctor d = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectDoctor");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				String doctorId = rset.getString("doctor_id");
				String password = rset.getString("password");
				String doctorName = rset.getString("doctor_name");
				String doctorProfile = rset.getString("doctor_profile");
				String ssd = rset.getString("ssd");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				String doctorIntoduce = rset.getString("doctor_introduce");
				String partId = rset.getString("part_id");
				
				String partName = rset.getString("part_name");
				
				d = new Doctor(doctorId, password, doctorName, doctorProfile, ssd, phone, address, doctorIntoduce, partId);
				d.setPartName(partName);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new DoctorException(e.getMessage());
			
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return d;
	}

	public ArrayList<Doctor> selectPartDoctors(Connection conn, String partId) throws DoctorException {
		
		ArrayList<Doctor> partDoctors = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectPartDoctors");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,partId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				String doctorId = rset.getString("doctor_id");
				String doctorName = rset.getString("doctor_name");
				String doctorProfile = rset.getString("doctor_profile");
				String doctorIntroduce = rset.getString("doctor_introduce");
				String partName = rset.getString("part_name");
				
				partDoctors.add(new Doctor(1, doctorId, doctorName,doctorProfile,doctorIntroduce, partId, partName));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DoctorException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return partDoctors;
	}

	public ArrayList<Doctor> searchDoctorbyName(Connection conn, String doctorName) throws DoctorException {
		
		ArrayList<Doctor> searchDoctors = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("searchDoctorbyName");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,"%"+doctorName+"%");
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				String doctorId = rset.getString("doctor_id");
				String doctorRealName = rset.getString("doctor_name");
				String doctorProfile = rset.getString("doctor_profile");
				String doctorIntroduce = rset.getString("doctor_introduce");
				String partId = rset.getString("part_id");
				String partName = rset.getString("part_name");
				
				searchDoctors.add(new Doctor(1, doctorId,doctorRealName,doctorProfile,doctorIntroduce,partId,partName));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DoctorException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return searchDoctors;
	}

	public Doctor selectDoctorbyId(Connection conn, String doctorId) throws DoctorException {
		
		Doctor d = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectDoctorbyId");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, doctorId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				String doctorName = rset.getString("doctor_name");
				String partId = rset.getString("part_id");
				
				d = new Doctor(doctorId, doctorName, partId);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DoctorException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return d;
	}

	public String findId(Connection conn, String userName, String phone) throws DoctorException {
		
		String findId = "";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("findId");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userName);
			pstmt.setString(2, phone);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				findId = rset.getString("doctor_id");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DoctorException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return findId;
	}

	public int findPw(Connection conn, String userId, String password) throws DoctorException {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("findPw");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, password);
			pstmt.setString(2, userId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DoctorException(e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int findCheck(Connection conn, String userId, String userName, String phone) throws DoctorException {
		
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("findCheck");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userName);
			pstmt.setString(3, phone);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DoctorException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
	

	public int updateDoctor(Connection conn, Doctor d) throws DoctorException {
		int result=0;
		PreparedStatement pstmt=null;
		String query = prop.getProperty("updateDoctor");
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, d.getPartName());
			pstmt.setString(2,d.getDoctorProfile());
			pstmt.setString(3, d.getPhone());
			pstmt.setString(4,d.getAddress());
			pstmt.setString(5, d.getDoctorId());
			
			result=pstmt.executeUpdate();
			
			System.out.println("result@DoctorDAO="+result);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new DoctorException(e.getMessage());
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int loginCheck(Connection conn, String userId, String now_password) throws DoctorException {
		int result = -1;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("loginCheck");
		
		try {
			//1.미완성쿼리를 가지고 객체 생성
			pstmt=conn.prepareStatement(query);
			//2.쿼리문 변수대입
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			pstmt.setString(3, now_password);
			//3.쿼리실행
			rset= pstmt.executeQuery();
			//4. 결과를 result에 담기 
			if(rset.next()) {
				result = rset.getInt("loginCheck");
			}
			System.out.println("result@DoctorDAO.loginCheck="+result);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new DoctorException(e.getMessage());
		}finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public int updatePassword(Connection conn, String userId, String new_password) throws DoctorException {
		PreparedStatement pstmt = null;
		int result =0;
		String query = prop.getProperty("updatePassword");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, new_password);
			pstmt.setString(2, userId);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			throw new DoctorException(e.getMessage());
		}finally {
			close(pstmt);
		}
		return result;
	}
}
