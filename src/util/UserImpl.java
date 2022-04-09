package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import VO.UserVO;
import menu.Menu;

public class UserImpl implements User {
	Scanner sc = new Scanner(System.in);

	public static List<UserVO> userArr = new ArrayList<>();
	// 로그인한 유저의 아이디
	public static String loginUserID;

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	// 관리자 추가
	public UserImpl() {
		UserVO user = new UserVO();
		user.setName("관리자");
		user.setPhone("01012345678");
		user.setId("admin");
		user.setPw("0000");
		userArr.add(user);
	}

	@Override
	public void join() {
		FileReader fr;
		try {
			fr = new FileReader("file/UserInfo.txt");
			br = new BufferedReader(fr);
			UserVO user = new UserVO();
			System.out.println("--------------------- < 회원가입 > ---------------------");
			System.out.print("▶ 아이디 : ");
			String inputId = sc.nextLine();

			loop: for (int i = 0; i < userArr.size(); i++) {
				if ((userArr.get(i).getId()).equals(inputId)) {
					// 반복문의 처음으로 돌아감
					System.out.println("→ ID가 중복되었습니다. 다시 한번 입력해주세요. ");
					System.out.print("▶ 아이디 : ");
					inputId = sc.nextLine();
					i--;
					continue loop;
				}
			}
			user.setId(inputId);

			System.out.print("▶ 비밀번호 : ");
			user.setPw(sc.nextLine());

			System.out.print("▶ 이름 : ");
			user.setName(sc.nextLine());

			System.out.print("▶ 전화번호 : ");
			user.setPhone(sc.nextLine());

			userArr.add(user);

			System.out.println("→ 회원가입되었습니다.");

			writeUser();

			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void login() {
		System.out.println("---------------------- < 로그인 > ----------------------");
		System.out.print("▶ 아이디: ");
		String inputId = sc.nextLine();

		System.out.print("▶ 비밀번호 : ");
		String inputPw = sc.nextLine();

		int test = 0;

		for (int i = 0; i < userArr.size(); i++) {
			if (inputId.equals(userArr.get(i).getId()) && inputPw.equals(userArr.get(i).getPw())) {
				test = 1;
				System.out.println("→ " + userArr.get(i).getName() + " 님 환영합니다. ");
				loginUserID = userArr.get(i).getId();
				break;
			}
		}

		if (test == 0) {
			System.out.println("→ 로그인에 실패하였습니다.");
		}

		if (test == 1) {
			Menu m = new Menu();
			// 관리자는 관리자메뉴로 이동
			if (loginUserID.equals("admin")) {
				m.adminMenu();
			} else {
				// 일반사용자는 일반메뉴로 이동
				m.userMenu();
			}
		}

	}

	public void writeUser() {

		FileWriter fw;

		try {
			fw = new FileWriter("file\\UserInfo.txt", true);
			int last = userArr.size()-1;
			fw.write(userArr.get(last).getId() + "," + userArr.get(last).getPw() + "," + userArr.get(last).getName()
					+ "," + userArr.get(last).getPhone() + "\r\n");

			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
