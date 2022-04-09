package util;

public interface Book {
	// 사용자
	public void rental();

	public void back();

	public void myRentalCheck();

	// 관리자
	public void showBookList();

	public void bookAdd();


	public void bookDel();

}
