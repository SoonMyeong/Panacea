package com.panacea.admin.model.dao;

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
import java.util.List;
import java.util.Properties;

import com.panacea.admin.model.exception.AdminException;
import com.panacea.admin.model.vo.Admin;
import com.panacea.doctor.model.vo.Doctor;
import com.panacea.patient.model.vo.Patient;

public class AdminDAO {

	private Properties prop = new Properties();
	
	public AdminDAO() {
		
		try {
			
			URL fileURL = AdminDAO.class.getResource("/sql/admin/admin_query.properties");
			String fileName = fileURL.getPath();
			prop.load(new FileInputStream(fileName));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int loginAdmin(Connection conn, String userId, String userPw) throws AdminException {

		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("loginAdmin");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				result = rset.getInt("cnt");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new AdminException(e.getMessage());
			
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public Admin selectAdmin(Connection conn, String userId) throws AdminException {

		Admin a = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectAdmin");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				String adminId = rset.getString("admin_id");
				String password = rset.getString("password");
				String adminName = rset.getString("admin_name");
				String ssd = rset.getString("ssd");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				
				a = new Admin(adminId, password, adminName, ssd, phone, address);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new AdminException(e.getMessage());
			
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return a;
	}

	public String findId(Connection conn, String userName, String phone) throws AdminException {
		
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
				findId = rset.getString("admin_id");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AdminException(e.getMessage());
		} finally { 
			close(rset);
			close(pstmt);
		}
		
		return findId;
	}

	public int findPw(Connection conn, String userId, String password) throws AdminException {
		
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
			throw new AdminException(e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int findCheck(Connection conn, String userId, String userName, String phone) throws AdminException {
		
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
			throw new AdminException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
	
	public List<Patient> selectPatientList(Connection conn, int cPage, int numPerPage) throws AdminException {
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		List<Patient> list = null;
		String query = prop.getProperty("selectPatientList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rset= pstmt.executeQuery();
			
			list = new ArrayList<>();
			
			while(rset.next()) {
				Patient p =null;
				String patient_id = rset.getString("patient_id");
				String password = rset.getString("password");
				String patient_name = rset.getString("patient_name");
				String ssd = rset.getString("ssd");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				
				p = new Patient(patient_id,password,patient_name,ssd,phone,address);
				
				list.add(p);
			}
			System.out.println("admin@AdminDAO="+list);
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new AdminException(e.getMessage());
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int patientListCount(Connection conn) throws AdminException {
		PreparedStatement pstmt = null;
		int count = 0;
		ResultSet rset = null;
		String query = prop.getProperty("patientListCount");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			//쿼리문실행
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				count = rset.getInt("cnt");
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new AdminException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return count;
	}

	public Patient selectPatient(Connection conn, String pid) throws AdminException {
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		Patient p =null;
		String query = prop.getProperty("selectPatient");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pid);
			rset= pstmt.executeQuery();
			
			while(rset.next()) {

				String patient_id = rset.getString("patient_id");
				String password = rset.getString("password");
				String patient_name = rset.getString("patient_name");
				String ssd = rset.getString("ssd");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				
				p = new Patient(patient_id,password,patient_name,ssd,phone,address);
				
			}
			System.out.println("patient@AdminDAO="+p);
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new AdminException(e.getMessage());
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return p;
	}

	public List<Doctor> selectDoctorList(Connection conn, int cPage, int numPerPage) throws AdminException {
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		List<Doctor> list = null;
		String query = prop.getProperty("selectDoctorList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rset= pstmt.executeQuery();
			
			list = new ArrayList<>();
			
			while(rset.next()) {
				Doctor d = null;
				String doctorId = rset.getString("doctor_id");
				String password = rset.getString("password");
				String doctorName = rset.getString("doctor_name");
				String doctorProfile = rset.getString("doctor_profile");
				String ssd = rset.getString("ssd");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				String part_name = rset.getString("part_name");
				String part_id = rset.getString("part_id");
				
				d = new Doctor(doctorId, password, doctorName, doctorProfile, ssd, phone, address, part_name);
				d.setPartId(part_id);
				list.add(d);
			}
			System.out.println("admin_doctor@AdminDAO="+list);
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new AdminException(e.getMessage());
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;	
	}

	public int doctorListCount(Connection conn) throws AdminException {
		PreparedStatement pstmt = null;
		int count = 0;
		ResultSet rset = null;
		String query = prop.getProperty("doctorListCount");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			//쿼리문실행
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				count = rset.getInt("cnt");
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new AdminException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return count;
	}

	public Doctor selectDoctor(Connection conn, String did) throws AdminException {
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		Doctor d =null;
		String query = prop.getProperty("selectDoctor");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, did);
			rset= pstmt.executeQuery();
			
			while(rset.next()) {

				String doctorId = rset.getString("doctor_id");
				String password = rset.getString("password");
				String doctorName = rset.getString("doctor_name");
				String doctorProfile = rset.getString("doctor_profile");
				String ssd = rset.getString("ssd");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				String part_name = rset.getString("part_name");
				String part_id = rset.getString("part_id");
				
				d = new Doctor(doctorId, password, doctorName, doctorProfile, ssd, phone, address, part_name);
				d.setPartId(part_id);
				
			}
			System.out.println("doctor@AdminDAO="+d);
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new AdminException(e.getMessage());
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return d;
	}

	public int insertDoctor(Connection conn, Doctor d) throws AdminException {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertDoctor");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, d.getDoctorId());
			pstmt.setString(2, d.getPassword());
			pstmt.setString(3, d.getDoctorName());
			pstmt.setString(4, d.getDoctorProfile());
			pstmt.setString(5, d.getSsd());
			pstmt.setString(6, d.getPhone());
			pstmt.setString(7, d.getAddress());
			pstmt.setString(8, d.getDoctorIntoduce());
			pstmt.setString(9, d.getPartId());
			
			result = pstmt.executeUpdate();
			System.out.println("intserDoctor@AdminDAO="+result);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AdminException(e.getMessage());
		} finally {	
			close(pstmt);
		}
		return result;
	}

	public int deleteDoctor(Connection conn, String id) throws AdminException {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteDoctor");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,id);

			result = pstmt.executeUpdate();
			System.out.println("deleteDoctor@AdminDAO="+result);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AdminException(e.getMessage());
		} finally {	
			close(pstmt);
		}
		return result;
	}
	
	public List<Admin> selectAdminView(Connection conn, int cPage, int numPerPage) throws AdminException {
		List<Admin> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectAdminList");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			
			//시작 rownum과 마지막 rownum 구하는 공식
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Admin a = new Admin();
				//컬럼명은 대소문자 구분이 없다.
				a.setAdminId(rset.getString("admin_id"));
				a.setPassword(rset.getString("password"));
				a.setAdminName(rset.getString("admin_name"));
				a.setSsd(rset.getString("ssd"));
				a.setPhone(rset.getString("phone"));
				a.setAddress(rset.getString("address"));
				list.add(a);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new AdminException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int selectAdminCount(Connection conn) throws AdminException {
		PreparedStatement pstmt = null;
		int totalMember = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectAdminCount");
		System.out.println("selectAdminCount="+query);
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			
			//쿼리문실행
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				totalMember = rset.getInt("cnt");
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new AdminException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return totalMember;
	}	
}
