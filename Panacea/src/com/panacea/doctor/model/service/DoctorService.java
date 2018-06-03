package com.panacea.doctor.model.service;

import static com.panacea.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.panacea.doctor.model.dao.DoctorDAO;
import com.panacea.doctor.model.exception.DoctorException;
import com.panacea.doctor.model.vo.Doctor;
import com.panacea.patient.model.dao.PatientDAO;

public class DoctorService {
	
	public static final int LOGIN_OK= 1;
	public static final int WRONG_PASSWORD= 0;
	public static final int ID_NOT_EXIST= -1;

	public int loginDoctor(String userId, String userPw) throws DoctorException {
		
		Connection conn = getConnection();
		
		int result = new DoctorDAO().loginDoctor(conn,userId,userPw);
		
		close(conn);
		return result;
	}

	public Doctor selectDoctor(String userId) throws DoctorException {
		
		Connection conn = getConnection();
		
		Doctor d = new DoctorDAO().selectDoctor(conn,userId);
		
		close(conn);
		return d;
	}

	public ArrayList<Doctor> selectPartDoctors(String partId) throws DoctorException {
		
		Connection conn = getConnection();
		
		ArrayList<Doctor> partDoctors = new DoctorDAO().selectPartDoctors(conn, partId);
		
		close(conn);
		return partDoctors;
	}

	public ArrayList<Doctor> searchDoctorbyName(String doctorName) throws DoctorException {

		Connection conn = getConnection();
		
		ArrayList<Doctor> searchDoctors = new DoctorDAO().searchDoctorbyName(conn, doctorName);
		
		close(conn);
		return searchDoctors;
	}

	public Doctor selectDoctorbyId(String doctorId) throws DoctorException {
		
		Connection conn = getConnection();
		
		Doctor doctor = new DoctorDAO().selectDoctorbyId(conn,doctorId);
		
		close(conn);
		return doctor;
	}

	public String findId(String userName, String phone) throws DoctorException {
		
		Connection conn = getConnection();
		
		String findId = new DoctorDAO().findId(conn, userName, phone);
		
		close(conn);
		return findId;
	}

	public int findPw(String userId, String password) throws DoctorException {
		
		Connection conn = getConnection();
		
		int result = new DoctorDAO().findPw(conn, userId, password);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public int findCheck(String userId, String userName, String phone) throws DoctorException {
		
		Connection conn = getConnection();
		
		int result = new DoctorDAO().findCheck(conn, userId, userName, phone);
		
		close(conn);
		return result;
	}
	

	public int updateDoctor(Doctor d) throws DoctorException {
		Connection conn = getConnection();
		int result= new DoctorDAO().updateDoctor(conn,d);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int loginCheck(String userId, String now_password) throws DoctorException {
		Connection conn = getConnection();
		int result = new DoctorDAO().loginCheck(conn,userId,now_password);
		close(conn);
		return result;
	}

	public int updatePassword(String userId, String new_password) throws DoctorException {
		Connection conn = getConnection();
		int result = new DoctorDAO().updatePassword(conn,userId,new_password);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}


}
