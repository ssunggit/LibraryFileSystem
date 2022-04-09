package menu;

import java.util.Scanner;

import VO.UserVO;
import util.BookImpl;
import util.UserImpl;

public class Menu {
	Scanner sc = new Scanner(System.in);
	BookImpl book = new BookImpl();
	UserImpl user = new UserImpl();

	public void menu() {
		while (true) {
			System.out.println("===============<Welcome To Library>===============");
			System.out.println("반갑습니다. Kopo 도서관입니다. 이용하실 서비스의 번호를 입력해주세요.");
			System.out.println();
			System.out.println("이용하실 서비스의 번호를 입력해주세요.");
			System.out.println("1. 회원가입\t 2. 로그인\t 3. 종료\t");
			System.out.print("=> ");
			int userChoice = Integer.parseInt(sc.nextLine());
			System.out.println();
			if (userChoice == 1) {
				user.join();
				
			} else if (userChoice == 2) {
				user.login();
				
			} else {
				System.out.println("도서관 프로그램을 중단하겠습니다!!");
				
				break;
			}
		}

	}

	// 관리자 메뉴
	public void adminMenu() {
		while (true) {
			System.out.println();
			System.out.println("-------------------- < 관리자 메뉴 > --------------------");
			System.out.println("1. 도서리스트 출력\t 2. 도서추가 \t 3. 도서 삭제\t 4. 로그아웃");
			System.out.print("=> ");
			int userChoice = Integer.parseInt(sc.nextLine());
			System.out.println();
			if (userChoice == 1) {
				book.showBookList();
				
			} else if (userChoice == 2) {
				book.bookAdd();
				
			} else if (userChoice == 3) {
				book.bookDel();
				
			} else {
				System.out.println("로그아웃 되었습니다.");
				
				break;
			}
		}
	}

	// 일반 사용자 메뉴
	public void userMenu() {
		while (true) {
			System.out.println();
			System.out.println("-------------------- < 도서관 메뉴 > --------------------");
			System.out.println("1. 전체 책 조회 \t 2. 대여\t 3. 반납\t 4. 도서대여목록조회 \t 5. 로그아웃");
			System.out.print("=> ");
			int userChoice = Integer.parseInt(sc.nextLine());
			System.out.println();
			if (userChoice == 1) {
				book.showBookList();
				System.out.println();
			} else if (userChoice == 2) {
				book.rental();
				System.out.println();
			} else if (userChoice == 3) {
				book.back();
				System.out.println();
			} else if (userChoice == 4) {
				book.myRentalCheck();
				System.out.println();
			} else {
				System.out.println("로그아웃 되었습니다.");
				System.out.println();
				break;
			}
		}
	}
}