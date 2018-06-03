package com.panacea.patient.model.service;

import com.panacea.patient.model.dao.PatientDAO;
import com.panacea.patient.model.exception.PatientException;
import com.panacea.patient.model.vo.Patient;
import static com.panacea.common.JDBCTemplate.*;

import java.sql.Connection;

public class PatientService {

	public static final int LOGIN_OK= 1;
	public static final int WRONG_PASSWORD= 0;
	public static final int ID_NOT_EXIST= -1;

	public int loginPatient(String userId, String userPw) throws PatientException {
		
		Connection conn = getConnection();
		
		int result = new PatientDAO().loginPatient(conn,userId,userPw);
		
		close(conn);
		return result;
	}

	public Patient selectPatient(String userId) throws PatientException {
		
		Connection conn = getConnection();
		
		Patient p = new PatientDAO().selectPatient(conn,userId);
		
		close(conn);
		return p;
	}

	public String findId(String userName, String phone) throws PatientException {
		
		Connection conn = getConnection();
		
		String findId = new PatientDAO().findId(conn, userName, phone);
		
		close(conn);
		return findId;
	}

	public int findPw(String userId, String password) throws PatientException {
		
		Connection conn = getConnection();
		
		int result = new PatientDAO().findPw(conn, userId, password);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public int findCheck(String userId, String userName, String phone) throws PatientException {

		Connection conn = getConnection();
		
		int result = new PatientDAO().findCheck(conn, userId, userName, phone);
		
		close(conn);
		return result;
	}
	
	public int insertPatient(Patient p) throws PatientException {
		
		Connection conn= getConnection();
		int result = new PatientDAO().insertPatient(conn,p);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}
	
	
	public int loginCheck(String userId, String password) throws PatientException {
		Connection conn = getConnection();
		int result = new PatientDAO().loginCheck(conn,userId,password);
		close(conn);
		return result;
	}
	

	public int updatePatient(Patient p) throws PatientException {
		
		Connection conn= getConnection();
		int result = new PatientDAO().updatePatient(conn,p);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}


	public int updatePassword(String userId, String new_password) throws PatientException {
		
		Connection conn = getConnection();
		int result = new PatientDAO().updatePassword(conn,userId,new_password);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}


	public int deletePatient(String patientId) throws PatientException {
		
		Connection conn = getConnection();
		int result = new PatientDAO().deletePatient(conn,patientId);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}

}
