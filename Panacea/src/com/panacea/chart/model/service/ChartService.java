package com.panacea.chart.model.service;

import static com.panacea.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.panacea.chart.model.dao.ChartDAO;
import com.panacea.chart.model.exception.ChartException;
import com.panacea.chart.model.vo.Chart;

public class ChartService {

	public int insertChart(Chart c) throws ChartException {
		Connection conn = getConnection();
		int result= new ChartDAO().insertChart(conn,c);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public List<Chart> selectDoctor(String userId, int cPage, int numPerPage) throws ChartException {
		Connection conn = getConnection();
		List<Chart> list = new ChartDAO().selectDoctor(conn,userId,cPage,numPerPage);
		close(conn);
		return list;
	}

	public List<Chart> selectPatient(String userId,int cPage, int numPerPage) throws ChartException {
		Connection conn = getConnection();
		List<Chart> list = new ChartDAO().selectPatient(conn,userId,cPage, numPerPage);
		close(conn);
		return list;
	}

	public Chart selectChart(int cno) throws ChartException {
		Connection conn = getConnection();
		Chart c = new ChartDAO().selectChart(conn,cno);
		close(conn);
		return c;
	}

	public int doctorChartCount(String userId) throws ChartException {
		Connection conn = getConnection();
		int result = new ChartDAO().doctorChartCount(conn,userId);
		close(conn);
		return result;
	}

	public int patientChartCount(String userId) throws ChartException {
		Connection conn = getConnection();
		int result = new ChartDAO().patientChartCount(conn,userId);
		close(conn);
		return result;
	}

	public int updateChart(Chart c) throws ChartException {
		Connection conn = getConnection();
		int result = new ChartDAO().updateChart(conn,c);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	
	
}
