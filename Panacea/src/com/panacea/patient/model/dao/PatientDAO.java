package com.panacea.patient.model.dao;

import static com.panacea.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.panacea.patient.model.exception.PatientException;
import com.panacea.patient.model.vo.Patient;

public class PatientDAO {
	
	private Properties prop = new Properties();
	
	public PatientDAO() {
		
		try {
			
			URL fileURL = PatientDAO.class.getResource("/sql/patient/patient_query.properties");
			String fileName = fileURL.getPath();
			prop.load(new FileInputStream(fileName));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int loginPatient(Connection conn, String userId, String userPw) throws PatientException {
		
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("loginPatient");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				result = rset.getInt("cnt");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new PatientException(e.getMessage());
			
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public Patient selectPatient(Connection conn, String userId) throws PatientException {
		
		Patient p = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectPatient");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				String patient_id = rset.getString("patient_id");
				String password = rset.getString("password");
				String patient_name = rset.getString("patient_name");
				String ssd = rset.getString("ssd");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				
				p = new Patient(patient_id,password,patient_name,ssd,phone,address);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new PatientException(e.getMessage());
			
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return p;
	}

	public String findId(Connection conn, String userName, String phone) throws PatientException {
		
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
				findId = rset.getString("patient_id");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PatientException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return findId;
	}

	public int findPw(Connection conn, String userId, String password) throws PatientException {
		
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
			throw new PatientException(e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int findCheck(Connection conn, String userId, String userName, String phone) throws PatientException {
		
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
			throw new PatientException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
	
	public int insertPatient(Connection conn, Patient p) throws PatientException {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertPatient");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, p.getPatientId());
			pstmt.setString(2, p.getPassword());
			pstmt.setString(3, p.getPatientName());
			pstmt.setString(4, p.getSsd());
			pstmt.setString(5, p.getPhone());
			pstmt.setString(6, p.getAddress());
			
			result = pstmt.executeUpdate();
			
			System.out.println("intserPatient@PatientDAO="+result);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new PatientException(e.getMessage());
			
		} finally {
		
			close(pstmt);
		}
		
		return result;
	}
	

	public int updatePatient(Connection conn, Patient p) throws PatientException {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updatePatient");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, p.getPhone());
			pstmt.setString(2, p.getAddress());
			pstmt.setString(3, p.getPatientId());
			
			result = pstmt.executeUpdate();	
			System.out.println("updatePatient@PatientDAO="+result);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PatientException(e.getMessage());
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int loginCheck(Connection conn, String userId, String password) throws PatientException {
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
			pstmt.setString(3, password);
			//3.쿼리실행
			rset= pstmt.executeQuery();
			//4. 결과를 result에 담기 
			if(rset.next()) {
				result = rset.getInt("loginCheck");
			}
			System.out.println("result@PatientDAO.loginCheck="+result);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new PatientException(e.getMessage());
		}finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public int updatePassword(Connection conn, String userId, String new_password) throws PatientException {
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
			throw new PatientException(e.getMessage());
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int deletePatient(Connection conn, String patientId) throws PatientException {
		PreparedStatement pstmt = null;
		int result =0;
		String query = prop.getProperty("deletePatient");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, patientId);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			throw new PatientException(e.getMessage());
		}finally {
			close(pstmt);
		}
		return result;
	}
}
