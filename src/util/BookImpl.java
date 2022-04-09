package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import VO.BookVO;
import VO.UserVO;

public class BookImpl implements Book {
	Scanner sc = new Scanner(System.in);
	public static List<BookVO> bookList = new ArrayList<>();
	public static List<BookVO> updatebookList = new ArrayList<>();
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	@Override
	public void rental() {
		FileWriter fw;
		readFileAddList();

		System.out.print("대여할 도서의 제목을 입력하세요 : ");
		String inputTitle = sc.nextLine();
		int test = 0;
		for (int i = 0; i < updatebookList.size(); i++) {
			if (inputTitle.equals(updatebookList.get(i).getTitle())) {
				test = 1;
				if (updatebookList.get(i).getRental() == true) {
					System.out.println("→ 검색하신 [" + updatebookList.get(i).getTitle() + "] 책은 대여 중입니다.");

				} else {
					System.out.println("검색하신 [" + updatebookList.get(i).getTitle() + "] 책이 있습니다.");
					System.out.print("[" + updatebookList.get(i).getTitle() + "] 책을 대여하시겠습니까?(y/n) : ");
					String yesNo = sc.nextLine();

					if (yesNo.equals("y") || yesNo.equals("Y")) {
						updatebookList.get(i).setRental(true);
						updatebookList.get(i).setUserId(UserImpl.loginUserID);
						System.out.println("[" + updatebookList.get(i).getTitle() + "]" + "대여되었습니다.");
					}
				}
			}
		}

		if (test == 0) {
			System.out.println("→ 검색하신 [" + inputTitle + "] 책이 없습니다.");
		}

		try {

			fw = new FileWriter("file\\BookList.txt");
			for (int i = 0; i < updatebookList.size(); i++) {
				fw.write(updatebookList.get(i).getId() + "," + updatebookList.get(i).getTitle() + ","
						+ updatebookList.get(i).getAuthor() + "," + updatebookList.get(i).getPublisher() + ","
						+ updatebookList.get(i).getUserId() + "," + updatebookList.get(i).getRental() + "\r\n");
			}

			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void back() {
		int test = 0;
		FileWriter fw;
		readFileAddList();

		if (updatebookList.size() > 0) {
			// 반납 목록 출력
			for (int i = 0; i < updatebookList.size(); i++) {
				if (UserImpl.loginUserID.equals(updatebookList.get(i).getUserId())) {
					System.out.println("● " + updatebookList.get(i).getTitle());
					test = 1;

				}
			}
		}

		// 반납
		if (test == 1) {
			System.out.print("반납할 책의 제목을 입력해주세요.  => ");
			String backInputTitle = sc.nextLine();
			for (int i = 0; i < updatebookList.size(); i++) {
				if (backInputTitle.equals(updatebookList.get(i).getTitle())) {
					updatebookList.get(i).setRental(false);
					updatebookList.get(i).setUserId(null);
				}

			}
		} else {
			System.out.println("→ 대여한 책이 없습니다. ");
		}

		try {

			fw = new FileWriter("file\\BookList.txt");
			for (int i = 0; i < updatebookList.size(); i++) {
				fw.write(updatebookList.get(i).getId() + "," + updatebookList.get(i).getTitle() + ","
						+ updatebookList.get(i).getAuthor() + "," + updatebookList.get(i).getPublisher() + ","
						+ updatebookList.get(i).getUserId() + "," + updatebookList.get(i).getRental() + "\r\n");
			}

			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void myRentalCheck() {
		readFileAddList();
		int test = 0;
		if (updatebookList.size() > 0) {
			int cnt = 1;
			System.out.println("------------------ < 나의 대여 목록 > ------------------");
			for (int i = 0; i < updatebookList.size(); i++) {
				if (UserImpl.loginUserID.equals(updatebookList.get(i).getUserId())) {
					System.out.print("[" + cnt + "]  ");
					System.out.println(updatebookList.get(i).getTitle());
					cnt++;
					test = 1;
				}
			}
		}
		if (test == 0) {
			System.out.println("→ 대여목록이 없습니다.");
		}

	}

	@Override
	public void showBookList() {
		readFileAddList();
		System.out.println("------------------ < 도서리스트 출력 > ------------------");
		if (updatebookList.size() > 0) {
			for (int i = 0; i < updatebookList.size(); i++) {
				System.out.println("[" + (i + 1) + "번 도서]");
				System.out.println("[제목] " + updatebookList.get(i).getTitle());
				System.out.println("[글쓴이] " + updatebookList.get(i).getAuthor());
				System.out.println("[출판사] " + updatebookList.get(i).getPublisher());
				System.out.println();
			}
		} else {
			System.out.println("→ 현재 등록된 책이 없습니다.");
		}
	}

	// 관리자 기능
	@Override
	public void bookAdd() {
		FileReader fr;
		try {
			BookVO book = new BookVO();
			fr = new FileReader("file\\booklist.txt");
			br = new BufferedReader(fr);

			if (UserImpl.loginUserID.equals("admin")) {
				System.out.println("--------------------- < 도서 등록 > ---------------------");
				// id 자동생성
				book.setId(bookList.size());
				// 대여여부 false
				book.setRental(false);

				System.out.print("책 제목을 입력해주세요 : ");
				book.setTitle(sc.nextLine());

				System.out.print("지은이를 입력해주세요 : ");
				book.setAuthor(sc.nextLine());

				System.out.print("출판사를 입력해주세요 : ");
				book.setPublisher(sc.nextLine());

				bookList.add(book);

				writeBook();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void bookDel() {
		FileWriter fw;
		readFileAddList();

		System.out.println("------------------ < 도서리스트 출력 > ------------------");
		for (int i = 0; i < updatebookList.size(); i++) {
			System.out.print("[" + (i + 1) + "]  ");
			System.out.println(updatebookList.get(i).getTitle());
		}

		System.out.print("삭제할 책의 번호를 입력해주세요 : ");
		int delBook = Integer.parseInt(sc.nextLine()) - 1;

		updatebookList.remove(delBook);

		System.out.println("---------------- < 변경된 도서리스트 출력 > ---------------");
		for (int i = 0; i < updatebookList.size(); i++) {
			System.out.print("[" + (i + 1) + "]  ");
			System.out.println(updatebookList.get(i).getTitle());
		}

		try {

			fw = new FileWriter("file\\BookList.txt");
			for (int i = 0; i < updatebookList.size(); i++) {
				fw.write(updatebookList.get(i).getId() + "," + updatebookList.get(i).getTitle() + ","
						+ updatebookList.get(i).getAuthor() + "," + updatebookList.get(i).getPublisher() + ","
						+ updatebookList.get(i).getUserId() + "," + updatebookList.get(i).getRental() + "\r\n");
			}

			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void writeBook() {

		FileWriter fw;

		try {

			fw = new FileWriter("file\\BookList.txt", true);

			int last = bookList.size() - 1;

			fw.write(bookList.get(last).getId() + "," + bookList.get(last).getTitle() + ","
					+ bookList.get(last).getAuthor() + "," + bookList.get(last).getPublisher() + ","
					+ bookList.get(last).getUserId() + "," + bookList.get(last).getRental() + "\r\n");

			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void readFileAddList() {
		updatebookList.clear();

		FileReader fr;

		try {
			fr = new FileReader("file\\BookList.txt");
			br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] tempArr = line.split(",");

				updatebookList.add(new BookVO(Integer.parseInt(tempArr[0]), tempArr[1], tempArr[2], tempArr[3],
						tempArr[4], Boolean.parseBoolean(tempArr[5])));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
