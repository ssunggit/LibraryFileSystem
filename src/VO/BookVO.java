package VO;

import java.util.Objects;

public class BookVO {

	private int id;
	private String title;
	private String author;
	private String publisher;
	// 책을 대여한 사용자의 id
	private String userId;
	private boolean rental;
	

	public BookVO() {

	}

	public BookVO(int id, String title, String author, String publisher, String userId, boolean rental) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.rental = rental;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean getRental() {
		return rental;
	}

	public void setRental(boolean rental) {
		this.rental = rental;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		return "BookVO [id=" + id + ", title=" + title + ", author=" + author + ", publisher=" + publisher + ", rental="
				+ rental + ", userId=" + userId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, id, publisher, rental, title, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookVO other = (BookVO) obj;
		return Objects.equals(author, other.author) && id == other.id && Objects.equals(publisher, other.publisher)
				&& rental == other.rental && Objects.equals(title, other.title) && userId == other.userId;
	}

	
	
	
}
