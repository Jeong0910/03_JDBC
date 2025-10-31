package edu.kh.jdbc.homework.view;

import java.util.InputMismatchException;
import java.util.Scanner;

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
				case 1 : insertStudents(); break;
				case 2 : selectAll(); break;
				case 3 : modifyStudent(); break;
				case 4 : deleteStudent(); break;
				case 5 : selectmajor(); break;
				case 0 : System.out.println("\n[프로그램 종료]\n"); break;
				default : System.out.println("\n[메뉴 번호만 입력하세요]\n");
				
				System.out.println("\n--------------------------------------\n");
				
				}
				
			} catch (InputMismatchException e) {
				
				System.out.println("\n***잘못 입력 하셨습니다***\n");
				
				input = -1; // while문 에러 방지
				sc.nextLine(); // 문자 제거
				
			} catch(Exception e) {
				
				e.printStackTrace();
				
			}
		} while(input != 0);
		
	}

	private void selectmajor() {
		// TODO Auto-generated method stub
		
	}

	private void deleteStudent() {
		// TODO Auto-generated method stub
		
	}

	private void modifyStudent() {
		// TODO Auto-generated method stub
		
	}

	private void selectAll() {
		// TODO Auto-generated method stub
		
	}

	private void insertStudents() {
		
		System.out.println("\n====1. 학생 등록====\n");
		
		System.out.print("학생 이름 : ");
		String studentName = sc.next();
		
		System.out.print("학생 나이 : ");
		int studentAge = sc.nextInt();
		
		System.out.print("학생 전공 : ");
		String major = sc.next();
		
		System.out.print("학생 입학일 : ");
		String entDate = sc.next();

		
	}
}
