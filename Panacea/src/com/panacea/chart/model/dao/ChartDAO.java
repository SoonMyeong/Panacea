package com.panacea.chart.model.dao;

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

import com.panacea.chart.model.exception.ChartException;
import com.panacea.chart.model.vo.Chart;
import com.panacea.doctor.model.dao.DoctorDAO;

public class ChartDAO {

	private Properties prop = new Properties();
	
	public ChartDAO() {
		
		try {
			URL fileURL = DoctorDAO.class.getResource("/sql/chart/chart_query.properties");
			String fileName = fileURL.getPath();
			prop.load(new FileInputStream(fileName));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public int insertChart(Connection conn, Chart c) throws ChartException {
		
		PreparedStatement pstmt = null;
		int result = 0; 
		String query = prop.getProperty("insertChart");
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,c.getDoctor_id());
			pstmt.setString(2, c.getPatient_id());
			pstmt.setString(3, c.getDisease_name());
			pstmt.setString(4, c.getChart_comment());
			
			result = pstmt.executeUpdate();
			
			System.out.println("result@chartDAO="+result);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ChartException(e.getMessage());
		}finally {
			close(pstmt);
		}
		return result;
	}

	public List<Chart> selectDoctor(Connection conn, String userId, int cPage, int numPerPage) throws ChartException {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Chart>list =null;
		String query = prop.getProperty("selectDoctorChart");
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,userId );
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rset.next()) {
				
				int cno = rset.getInt("chart_no");
				String doctor_id = rset.getString("doctor_id");
				String patient_id = rset.getString("patient_id");
				String disease_name = rset.getString("disease_name");
				String disease_comment = rset.getString("chart_comment");
				
				String doctor_name=rset.getString("doctor_name");
				String patient_name=rset.getString("patient_name");
				String patient_ssd = rset.getString("ssd");
				String part_name = rset.getString("part_name");
				
				Chart c = new Chart(cno,doctor_id,patient_id,disease_name,disease_comment,doctor_name,patient_name,patient_ssd,part_name);
				
				list.add(c);
			}
			System.out.println("list@chartDAO="+list);
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ChartException(e.getMessage());
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public List<Chart> selectPatient(Connection conn, String userId, int cPage, int numPerPage) throws ChartException {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Chart>list =null;
		String query = prop.getProperty("selectPatientChart");
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,userId );
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rset.next()) {
				
				int cno = rset.getInt("chart_no");
				String doctor_id = rset.getString("doctor_id");
				String patient_id = rset.getString("patient_id");
				String disease_name = rset.getString("disease_name");
				String disease_comment = rset.getString("chart_comment");
				
				String doctor_name=rset.getString("doctor_name");
				String patient_name=rset.getString("patient_name");
				String patient_ssd = rset.getString("ssd");
				String part_name = rset.getString("part_name");
				
				Chart c = new Chart(cno,doctor_id,patient_id,disease_name,disease_comment,doctor_name,patient_name,patient_ssd,part_name);
				
				list.add(c);
			}
			System.out.println("list@chartDAO="+list);
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ChartException(e.getMessage());
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public Chart selectChart(Connection conn, int cno) throws ChartException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Chart c = null;
		String query = prop.getProperty("selectChart");
		
		try {

			pstmt =conn.prepareStatement(query);
			pstmt.setInt(1, cno);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				int c_no = rset.getInt("chart_no");
				String doctor_id = rset.getString("doctor_id");
				String patient_id = rset.getString("patient_id");
				String disease_name = rset.getString("disease_name");
				String disease_comment = rset.getString("chart_comment");
				
				String doctor_name=rset.getString("doctor_name");
				String patient_name=rset.getString("patient_name");
				String patient_ssd = rset.getString("ssd");
				String part_name = rset.getString("part_name");
				
				c = new Chart(c_no,doctor_id,patient_id,disease_name,disease_comment,doctor_name,patient_name,patient_ssd,part_name);
				
			}
			System.out.println("chart@ChartDAO="+c);
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ChartException(e.getMessage());
		}finally {
			close(rset);
			close(pstmt);
		}
		return c;
	}

	public int doctorChartCount(Connection conn, String userId) throws ChartException {
		PreparedStatement pstmt = null;
		int count = 0;
		ResultSet rset = null;
		String query = prop.getProperty("doctorChartCount");
		
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
			throw new ChartException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return count;
	}
	
	public int patientChartCount(Connection conn, String userId) throws ChartException {
		PreparedStatement pstmt = null;
		int count = 0;
		ResultSet rset = null;
		String query = prop.getProperty("patientChartCount");
		
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
			throw new ChartException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return count;
	}

	public int updateChart(Connection conn, Chart c) throws ChartException {
		PreparedStatement pstmt = null;
		int result=0;
		String query = prop.getProperty("updateChart");
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, c.getDisease_name());
			pstmt.setString(2, c.getChart_comment());
			pstmt.setInt(3, c.getChart_no());
			
			result = pstmt.executeUpdate();
			System.out.println("result@ChartDAO"+result);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ChartException(e.getMessage());
		}finally {
			close(pstmt);
		}
		return result;
	}
	
}
