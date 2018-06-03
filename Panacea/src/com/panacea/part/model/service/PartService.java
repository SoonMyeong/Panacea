package com.panacea.part.model.service;

import java.sql.Connection;

import com.panacea.part.model.dao.PartDAO;
import com.panacea.part.model.exception.PartException;
import com.panacea.part.model.vo.MedicalPart;
import static com.panacea.common.JDBCTemplate.*;

public class PartService {

	public MedicalPart selectPart(String partId) throws PartException {
		
		Connection conn = getConnection();
		
		MedicalPart part = new PartDAO().selectPart(conn,partId);
		
		close(conn);
		System.out.println("part@PartService="+part);
		return part;
	}

}
