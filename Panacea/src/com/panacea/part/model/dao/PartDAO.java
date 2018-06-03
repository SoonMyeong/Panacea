package com.panacea.part.model.dao;

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

import com.panacea.part.model.exception.PartException;
import com.panacea.part.model.vo.MedicalPart;


public class PartDAO {
	
	private Properties prop = new Properties();
	
	public PartDAO() {
		
		try {
			
			URL fileURL = PartDAO.class.getResource("/sql/part/part_query.properties");
			String fileName = fileURL.getPath();
			prop.load(new FileInputStream(fileName));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public MedicalPart selectPart(Connection conn, String partId) throws PartException {
		
		MedicalPart part = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectPart");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, partId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				String partName = rset.getString("part_name");
				String partIntroduce = rset.getString("part_introduce");
				part = new MedicalPart(partId, partName, partIntroduce);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PartException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		
		System.out.println("part@PartDAO="+part);
		return part;
	}

}
