package edu.kh.jdbc.homework.model.service;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.homework.common.JDBCTemplate;
import edu.kh.jdbc.homework.model.dao.StudentDAO;
import edu.kh.jdbc.homework.model.dto.Student;

// 데이터를 가공하고 트랜잭션(commit, rollback) 관리 수행.
public class StudentService {

	private StudentDAO dao = new StudentDAO();

	public int insertStudent(Student std) throws Exception {

		// 커넥션 생성
		Connection conn = JDBCTemplate.getConnection();

		// DAO 메서드 호출 후 결과 반환
		int result = dao.insertStudent(conn, std);

		if (result > 0) {
			JDBCTemplate.commit(conn); // 저장
		} else {
			JDBCTemplate.rollback(conn); // 실패시 롤백
		}
		JDBCTemplate.close(conn); // 안전하게 커넥션 반환
		return result; // 결과 반환
	}

	public List<Student> selectAll() throws Exception {

		Connection conn = JDBCTemplate.getConnection();

		List<Student> studentList = dao.selectAll(conn);

		JDBCTemplate.close(conn);

		return studentList;
	}

	public int selectStudentCheck(int studentNo) throws Exception {

		Connection conn = JDBCTemplate.getConnection();

		int studentCheck = dao.selectStudentCheck(conn, studentNo);

		JDBCTemplate.close(conn);

		return studentCheck;
	}

	public int updateInfo(String name, int age, String major, String entDate) throws Exception {

		Connection conn = JDBCTemplate.getConnection();

		int result = dao.updateInfo(conn, name, age, major, entDate);

		if (result > 0)
			JDBCTemplate.commit(conn);
		else
			JDBCTemplate.rollback(conn);

		JDBCTemplate.close(conn);

		return result;
	}

	public int deleteStudent(int delete) throws Exception {

		Connection conn = JDBCTemplate.getConnection();

		int result = dao.deleteStudent(conn, delete);

		if (result > 0) {

			JDBCTemplate.commit(conn);

		} else {

			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.close(conn);

		return result;
	}

	public List<Student> selectMajor(String input) throws Exception {

		Connection conn = JDBCTemplate.getConnection();

		List<Student> majorList = dao.selectMajor(conn, input);

		JDBCTemplate.close(conn);

		return majorList;
	}

}
