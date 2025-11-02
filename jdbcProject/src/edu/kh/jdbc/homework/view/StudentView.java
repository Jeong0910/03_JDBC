package edu.kh.jdbc.homework.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.homework.model.dto.Student;
import edu.kh.jdbc.homework.model.service.StudentService;

public class StudentView {

	private StudentService service = new StudentService();
	private Scanner sc = new Scanner(System.in);

	public void mainMenu() {

		int input = 0;

		do {
			try {

				System.out.println("\n===== 학생 관리 프로그램 =====\n");
				System.out.println("1. 학생 등록");
				System.out.println("2. 모든 학생 정보 조회");
				System.out.println("3. 학생 정보 수정");
				System.out.println("4. 학생 정보 삭제");
				System.out.println("5. 전공별 학생 조회");
				System.out.println("0. 프로그램 종료");

				System.out.print("메뉴 선택 : ");
				input = sc.nextInt();
				sc.nextLine();

				switch (input) {
				case 1:
					insertStudents();
					break;
				case 2:
					selectAll();
					break;
				case 3:
					modifyStudent();
					break;
				case 4:
					deleteStudent();
					break;
				case 5:
					selectmajor();
					break;
				case 0:
					System.out.println("\n[프로그램 종료]\n");
					break;
				default:
					System.out.println("\n[메뉴 번호만 입력하세요]\n");

					System.out.println("\n--------------------------------------\n");

				}

			} catch (InputMismatchException e) {

				System.out.println("\n***잘못 입력 하셨습니다***\n");

				input = -1; // while문 에러 방지
				sc.nextLine(); // 문자 제거

			} catch (Exception e) {

				e.printStackTrace();

			}
		} while (input != 0);

	}

	private void selectmajor() throws Exception {

		System.out.println("\n====5. 전공별 조회====\n");

		System.out.print("전공을 입력해주세요 : ");
		String input = sc.next();

		List<Student> majorList = service.selectMajor(input);

		if (majorList.isEmpty()) {
			System.out.println("\n조회 결과가 없습니다.\n");
			return;
		}
		for (Student std : majorList) {
			System.out.println(std);
		}

	}

	private void deleteStudent() throws Exception {

		System.out.println("\n====4. 학생 정보 삭제====\n");

		System.out.print("삭제할 학생 번호 입력해주세요 : ");
		int delete = sc.nextInt();

		int result = service.deleteStudent(delete);

		if (result > 0)
			System.out.println("학생정보가 삭제되었습니다.");
		else
			System.out.println("일치하는 학생이 존재하지 않습니다.");
	}

	private void modifyStudent() throws Exception {

		System.out.println("\n====3. 학생 정보 수정====\n");

		System.out.print("수정할 학생 정보 입력해주세요 : ");
		int studentNo = sc.nextInt();

		int studentCheck = service.selectStudentCheck(studentNo);

		if (studentCheck == 0) {

			System.out.println("일치하는 학생이 존재하지 않습니다.");
			return;

		}

		System.out.print("수정할 이름 : ");
		String name = sc.next();

		System.out.print("수정할 나이 : ");
		int age = sc.nextInt();

		System.out.print("수정할 전공 : ");
		String major = sc.next();

		System.out.print("수정할 입학일 : ");
		String entDate = sc.next();

		int result = service.updateInfo(name, age, major, entDate);

		if (result > 0)
			System.out.println("학생 정보가 변경되었습니다.");
		else
			System.out.println("학생 정보가 변경되지 않았습니다.");

	}

	private void selectAll() throws Exception {

		System.out.println("\n====2. 학생 전체 조회====\n");

		List<Student> studentList = service.selectAll();

		if (studentList.isEmpty()) {
			System.out.println("\n조회 결과가 없습니다.\n");
			return;
		}

		// 조회 결과가 있으면 studentList에 있는 모든 Student 객체를 출력!
		for (Student std : studentList) {
			System.out.println(std);
		}

	}

	private void insertStudents() throws Exception {

		System.out.println("\n====1. 학생 등록====\n");

		System.out.print("학생 이름 : ");
		String studentName = sc.next();

		System.out.print("학생 나이 : ");
		int studentAge = sc.nextInt();

		System.out.print("학생 전공 : ");
		String major = sc.next();

		System.out.print("학생 입학일 : ");
		String entDate = sc.next();

		Student std = new Student();

		// setter를 이용해서 새로운 값을 넣어두자.
		std.setStdName(studentName);
		std.setStdAge(studentAge);
		std.setMajor(major);
		std.setEntDate(entDate);

		// 서비스 호출 후 결과를 받자 - int, 결과 행의 갯수
		// StudentService에 있는 insertStudent() 메서드를 호출하기 위해!
		int result = service.insertStudent(std);

		if (result > 0) {
			System.out.println("\n" + studentName + " 학생이 등록되었습니다.\n");
		} else {
			System.out.println("\n***등록이 실패하였습니다.***\n");
		}

	}
}
