package com.company.biblioteka;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Book<T> {
	private int bookId;
	private String title;
	private String author;
	private String category;
	private String publicationDate;
	private String available;
	private static String[] booksCategory = { "wszystkie", "fantasy", "horror", "klasyka", "kryminal", "inne" };
	private static String[] bookAvailability = { "wszystkie", "wypozyczone", "dostepna" };

	public Book(String title, String author, String publicationDate, String category, LibraryInterface dataClass) {
		this.bookId = setUniqueId(dataClass);
		this.title = title;
		this.author = author;
		this.publicationDate = publicationDate;
		this.category = category;
		this.setAvailable("dostepna");
	}

	public Book(Integer uniqueId, String title, String author, String publicationDate, String category, String avaiable,
			LibraryInterface dataClass) {
		this.bookId = uniqueId;
		this.title = title;
		this.author = author;
		this.publicationDate = publicationDate;
		this.category = category;
		this.available = avaiable;
	}
	public Book() throws Exception{
		
	}

	public static Integer setUniqueId(LibraryInterface dataClass) {

		class AgeComparator implements Comparator<Book> {
			@Override
			public int compare(Book book1, Book book2) {
				int bookIdOne = book1.getBookId();
				int bookIdTwo = book2.getBookId();

				if (bookIdOne > bookIdTwo) {
					return 1;
				} else if (bookIdOne < bookIdTwo) {
					return -1;
				} else {
					return 0;
				}
			}
		}

		List listOfBooks = dataClass.getAllBooks();
		if (listOfBooks != null) {

			Collections.sort(listOfBooks, new AgeComparator());
			for (Object book : listOfBooks) {
				System.out.println(((Book<?>) book).getBookId() + "    BOOK ID < --");
			}

			if (listOfBooks.size() >= 1) {
				if (((Book<?>) listOfBooks.get(0)).getBookId() != 1) {
					return 1;
				}
			}
			int newUniqueId = 0;
			if (listOfBooks.size() < 2) {
				int value = listOfBooks.size();
				return newUniqueId + value + 1;
			}
			List<Integer> uniqueID = new ArrayList<Integer>();
			for (Object book : listOfBooks) {
				uniqueID.add(((Book) book).getBookId());
			}
			for (int i = 0; i < uniqueID.size() - 1; i++) {
				int value1 = uniqueID.get(i + 1);
				int value2 = uniqueID.get(i);
				if (value1 - value2 != 1) {
					return value2 + 1;
				}
			}

			return listOfBooks.size() + 1;
		}
		return 0;
	}
	@XmlAttribute
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	@XmlElement
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@XmlElement
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	@XmlElement
	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}
	@XmlElement
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	@XmlElement
	public void setAvailable(String available) {
		this.available = available;
	}

	public String getAvaiable() {
		return available;
	}
	public static String[] getBooksCategory() {
		return booksCategory;
	}

	public static String[] getBooksAvailability() {
		return bookAvailability;
	}
}