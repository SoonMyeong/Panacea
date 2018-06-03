package com.panacea.reservation.model.service;

import static com.panacea.common.JDBCTemplate.close;
import static com.panacea.common.JDBCTemplate.commit;
import static com.panacea.common.JDBCTemplate.getConnection;
import static com.panacea.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.panacea.reservation.model.dao.ReservationDAO;
import com.panacea.reservation.model.exception.ReservationException;
import com.panacea.reservation.model.vo.Reservation;

public class ReservationService {

	public ArrayList<String> checkTime(String patientId, String doctorId, String date) throws ReservationException {
		
		Connection conn = getConnection();
		
		ArrayList<String> time_list = new ReservationDAO().checkTime(conn, patientId, doctorId, date);
		
		close(conn);
		return time_list;
	}

	public int insertReservation(String patientId, String partId, String doctorId, String dateTime) throws ReservationException {
		
		Connection conn = getConnection();
		
		int result = new ReservationDAO().insertReservation(conn, patientId, partId, doctorId, dateTime);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public List<Reservation> patientSelectReservationList(String userId,int cPage, int numPerPage) throws ReservationException {
		Connection conn = getConnection();
		List<Reservation> list =null;
		list = new ReservationDAO().patientSelectReservationList(conn,userId,cPage,numPerPage);
		close(conn);
		return list;
	}

	public Reservation selectReservation(int rno) throws ReservationException {
		Connection conn = getConnection();
		Reservation r = null;
		r = new ReservationDAO().selectReservation(conn,rno);
		close(conn);
		return r;
	}

	public List<Reservation> doctorSelectReservation(String userId, int cPage, int numPerPage) throws ReservationException {
		Connection conn = getConnection();
		List<Reservation> list =null;
		list = new ReservationDAO().doctorSelectReservationList(conn, userId,cPage,numPerPage);
		close(conn);
		return list;
	}

	public int deleteReservation(int rno) throws ReservationException {
		Connection conn = getConnection();
		int result = new ReservationDAO().deleteReservation(conn,rno);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}

	public int doctorReservationCount(String userId) throws ReservationException {
		Connection conn = getConnection();
		int count = new ReservationDAO().doctorReservationCount(conn,userId);
		close(conn);
		return count;
	}
	
	public int patientReservationCount(String userId) throws ReservationException {
		Connection conn = getConnection();
		int count = new ReservationDAO().patientReservationCount(conn,userId);
		close(conn);
		return count;
	}
	

	public int updateReservation(int rno, String datetime) throws ReservationException {
		Connection conn = getConnection();
		int result = new ReservationDAO().updateReservation(conn,rno,datetime);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}

	public List<Reservation> patientSelectReservationReviewList(String userId, int cPage, int numPerPage) throws ReservationException {
		Connection conn = getConnection();
		List<Reservation> list =null;
		list = new ReservationDAO().patientSelectReservationReviewList(conn,userId,cPage,numPerPage);
		close(conn);
		return list;
	}

	public int patientReservationReviewCount(String userId) throws ReservationException {
		Connection conn = getConnection();
		int count = new ReservationDAO().patientReservationReviewCount(conn,userId);
		close(conn);
		return count;
	}

}
