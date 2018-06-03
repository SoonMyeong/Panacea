package com.panacea.admin.model.service;

import static com.panacea.common.JDBCTemplate.close;
import static com.panacea.common.JDBCTemplate.commit;
import static com.panacea.common.JDBCTemplate.getConnection;
import static com.panacea.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.panacea.admin.model.dao.AdminDAO;
import com.panacea.admin.model.exception.AdminException;
import com.panacea.admin.model.vo.Admin;
import com.panacea.doctor.model.vo.Doctor;
import com.panacea.patient.model.vo.Patient;

public class AdminService {

	public int loginAdmin(String userId, String userPw) throws AdminException {
		
		Connection conn = getConnection();
		
		int result = new AdminDAO().loginAdmin(conn,userId,userPw);
		
		close(conn);
		return result;
	}

	public Admin selectAdmin(String userId) throws AdminException {
		
		Connection conn = getConnection();
		
		Admin a = new AdminDAO().selectAdmin(conn,userId);
		
		close(conn);
		return a;
	}

	public String findId(String userName, String phone) throws AdminException {
		
		Connection conn = getConnection();
		
		String findId = new AdminDAO().findId(conn, userName, phone);
		
		close(conn);
		return findId;
	}

	public int findPw(String userId, String password) throws AdminException {
		
		Connection conn = getConnection();
		
		int result = new AdminDAO().findPw(conn, userId, password);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public int findCheck(String userId, String userName, String phone) throws AdminException {

		Connection conn = getConnection();
		
		int result = new AdminDAO().findCheck(conn, userId, userName, phone);
		
		close(conn);
		return result;
	}
	

	public List<Patient> selectPatientList(int cPage, int numPerPage) throws AdminException {
		Connection conn = getConnection();
		List<Patient>list  = new AdminDAO().selectPatientList(conn,cPage,numPerPage);
		close(conn);
		return list;
	}

	public int patientListCount() throws AdminException {
		Connection conn = getConnection();
		int count = new AdminDAO().patientListCount(conn);
		close(conn);
		return count;
	}

	public Patient selectPatient(String pid) throws AdminException {
		Connection conn = getConnection();
		Patient p = new AdminDAO().selectPatient(conn,pid);
		close(conn);
		return p;
	}

	public List<Doctor> selectDoctorList(int cPage, int numPerPage) throws AdminException {
		Connection conn = getConnection();
		List<Doctor> list  = new AdminDAO().selectDoctorList(conn,cPage,numPerPage);
		close(conn);
		return list;
	}

	public int doctorListCount() throws AdminException {
		Connection conn = getConnection();
		int count = new AdminDAO().doctorListCount(conn);
		close(conn);
		return count;
	}

	public Doctor selectDoctor(String did) throws AdminException {
		Connection conn = getConnection();
		Doctor d = new AdminDAO().selectDoctor(conn,did);
		close(conn);
		return d;
	}

	public int insertDoctor(Doctor d) throws AdminException {
		Connection conn = getConnection();
		int result= new AdminDAO().insertDoctor(conn,d);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int deleteDoctor(String id) throws AdminException {
		Connection conn = getConnection();
		int result= new AdminDAO().deleteDoctor(conn,id);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public List<Admin> selectAdminView(int cPage, int numPerPage) throws AdminException {
		Connection conn = getConnection();
		List<Admin> list= new AdminDAO().selectAdminView(conn, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectAdminCount() throws AdminException {
		Connection conn = getConnection();
		int totalAdminCount = new AdminDAO().selectAdminCount(conn);
		close(conn);
		return totalAdminCount;
	}


}
