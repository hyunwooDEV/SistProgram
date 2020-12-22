package com.sist.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sist.dto.StudentDTO;
import com.sist.dto.StudentsRegiCourceDTO;
import com.sist.main.DBUtil;

public class StudentDAO {

	Connection conn;
	Statement stat;
	PreparedStatement pstat;
	CallableStatement cstat;
	ResultSet rs;
	
	/**
	 * 교육생 DAO의 기본 생성자이다.
	 */
	public StudentDAO() {
		
		try {

			conn = DBUtil.open();
			stat = conn.createStatement();

		} catch (Exception e) {
			System.out.println("CStudentDAO.StudentDAO() : " + e.toString());
		}//try 
	
	}//StudentDAO() 
	
	
	public StudentDTO getStudent(String seq) {
		//교육생 테이블 정보 얻어오기
		try {
			String sql = "select * from tblStudent where seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			rs = pstat.executeQuery();
			
			if (rs.next()) {	
				StudentDTO dto = new StudentDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setJumin(rs.getString("jumin"));
				dto.setTel(rs.getString("tel"));
				dto.setRegdate(rs.getString("regdate"));
				
				return dto;			
				}
			
		} catch(Exception e) {
			System.out.println("StudentDAO.getStudent()");
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
	public StudentsRegiCourceDTO getStudentsRegiCource(String seq) { //교육생번호 매개변수
		//교육생, 수강테이블 조인 정보 얻어오기
		
		try {
			String sql = 
					  "select"
					+ " a.seq as studentNum,"
					+ "a.name,"
					+ "a.jumin,"
					+ "a.tel,"
					+ "a.regdate,"
					+ "b.seq as RegiCourceSeq,"
					+ "b.createdCourceNum,"
					+ "b.regiStateNum,"
					+ "(select tblRegistate.regiState from tblRegistate where b.regiStateNum = tblRegistate.seq) as registate"
					+ " from tblStudent a"
					+ " inner join tblRegiCource b"
					+ " on a.seq = b.studentNum"
					+ " where a.seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				StudentsRegiCourceDTO dto = new StudentsRegiCourceDTO();
				
				dto.setSeq(rs.getString("studentNum")); //학생번호
				dto.setName(rs.getString("name")); //교육생명
				dto.setJumin(rs.getString("jumin")); //주민번호
				dto.setTel(rs.getString("tel")); //연락처
				dto.setRegdate(rs.getString("regdate")); //교육생등록일
				dto.setrSeq(rs.getString("RegiCourceSeq")); //수강번호
				dto.setCreatedCourceNum(rs.getString("createdCourceNum")); //개설과정번호
				dto.setRegiStateNum(rs.getString("regiStateNum")); //수강상태번호
				dto.setRegiState(rs.getString("registate")); //수강상태
				
				return dto;
			}
			
			
		} catch(Exception e) {
			System.out.println("StudentsRegiCourceDAO.getStudentsRegiCource()");
			e.printStackTrace();
		}
		
		return null;
	}
	

}
