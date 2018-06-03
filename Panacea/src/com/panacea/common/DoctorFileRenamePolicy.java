package com.panacea.common;

import java.io.File;
import java.io.IOException;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class DoctorFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File oldFile) {
		File newFile = null;
			//확장자명 가져오기
			String fname = oldFile.getName();
			String ext = "";
			int dot= fname.lastIndexOf(".");
			if(dot>-1) {
				ext = fname.substring(dot);
			}
			
			//새 파일명 생성
			String newFname = oldFile.getName().substring(0, dot)+ext;
			
			//새 파일 생성
			newFile= new File(oldFile.getParent(),newFname);
			System.out.println("newFile@DoctorRenamePolicy="+newFile.getName());
			System.out.println("newFile.exist()="+newFile.exists());
		
			createNewFile(newFile);
		
		return newFile;
	}

	private boolean createNewFile(File newFile) {
		try {
			//파일이 존재하지 않으면, 파일생성 후 true리턴
			//파일이 존재하면, 파일을 생성하지 않고 false리턴
			return newFile.createNewFile();
		} catch (IOException e) {
			return false;
		}		
	}

}
