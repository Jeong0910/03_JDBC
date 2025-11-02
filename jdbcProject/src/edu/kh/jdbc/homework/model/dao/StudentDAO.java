package edu.kh.jdbc.homework.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static edu.kh.jdbc.homework.common.JDBCTemplate.*;
import edu.kh.jdbc.homework.model.dto.Student;

// 데이터 접근 객체
// DB에 접근하는 용도의 객체
// DB에 접근하여 Java에서 원하는 결과를 얻기 위해
// SQL을 수행하고 결과를 반환받는 역할
public class StudentDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public int insertStudent(Connection conn, Student std) throws Exception {

		// 결과 저장용 변수 선언
		int result = 0;

		try {

			String sql = """
					INSERT INTO KH_STUDENT
					VALUES (SEQ_STD_NO.NEXTVAL, ?, ?, ?, ?)
					""";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, std.getStdName());
			pstmt.setInt(2, std.getStdAge());
			pstmt.setString(3, std.getMajor());
			pstmt.setString(4, std.getEntDate());

			result = pstmt.executeUpdate();

		} finally {

			close(pstmt);

		}

		return result;
	}

	public List<Student> selectAll(Connection conn) throws Exception {

		List<Student> studentList = new ArrayList<Student>();

		try {

			String sql = """
					SELECT STD_NO, STD_NAME, STD_AGE, MAJOR, ENT_DATE,
					TO_CHAR(ENT_DATE, 'YYYY"년" MM"월" DD"일"') ENT_DATE
					FROM KH_STUDENT
					ORDER BY STD_NO
					""";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				int studentNo = rs.getInt("STD_NO");
				String studentName = rs.getString("STD_NAME");
				int studentAge = rs.getInt("STD_AGE");
				String major = rs.getString("MAJOR");
				String entDate = rs.getString("ENT_DATE");

				Student std = new Student(studentNo, studentName, studentAge, major, entDate);

				studentList.add(std);
			}

		} finally {

			close(rs);
			close(pstmt);

		}

		return studentList;
	}

	public int selectStudentCheck(Connection conn, int studentNo) throws Exception {

		int studentCheck = 0;

		try {

			String sql = """
					SELECT STD_NO
					FROM KH_STUDENT
					WHERE STD_NO = ?
					""";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, studentNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				studentNo = rs.getInt("STD_NO");
			}

		} finally {

			close(rs);
			close(pstmt);

		}

		return studentCheck;
	}

	public int updateInfo(Connection conn, String name, int age, String major, String entDate) throws Exception {

		int result = 0;

		try {

			String sql = """
					UPDATE KH_STUDENT
					SET STD_NAME = ?
					AND STD_AGE = ?
					AND MAJOR = ?
					AND ENT_DATE = ?
					WHERE STD_NO = ?
					""";

			pstmt.setString(1, name);
			pstmt.setInt(2, age);
			pstmt.setString(3, major);
			pstmt.setString(4, entDate);

			result = pstmt.executeUpdate();

		} finally {

			close(pstmt);

		}

		return result;
	}

	public int deleteStudent(Connection conn, int delete) throws Exception {

		int result = 0;

		try {

			String sql = """
					DELETE FROM KH_STUDENT
					WHERE STD_NO = ?
					""";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, delete);

			result = pstmt.executeUpdate();

		} finally {

			close(pstmt);

		}

		return result;
	}

	public List<Student> selectMajor(Connection conn, String input) throws Exception {

		List<Student> majorList = new ArrayList<Student>();

		try {

			String sql = """
					SELECT STD_NO, STD_NAME, STD_AGE, MAJOR,
					TO_CHAR(ENT_DATE, 'YYYY"-"MM"-"DD') ENT_DATE
					FROM KH_STUDENT
					WHERE MAJOR = ? 
					ORDER BY STD_NO ASC
					""";

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, input);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				int studentNo = rs.getInt("STD_NO");
				String studentName = rs.getString("STD_NAME");
				int studentAge = rs.getInt("STD_AGE");
				String major = rs.getString("MAJOR");
				String entDate = rs.getString("ENT_DATE");

				Student std = new Student(studentNo, studentName, studentAge, major, entDate);

				majorList.add(std);
			}

		} finally {

			close(rs);
			close(pstmt);

		}

		return majorList;

	}
}
