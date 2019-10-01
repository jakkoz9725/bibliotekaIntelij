package com.company.firstImplementation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import biblioteka.Book;
import biblioteka.LibraryInterface;

public class FirstImplementationClass implements LibraryInterface {

	List<Object> books = new ArrayList<Object>();

	public FirstImplementationClass() {
		addDummyData();
	}

	@Override
	public Object getBookById(int bookUniqueId) {
		for (Object book : books) {
			if (((Book) book).getBookId() == bookUniqueId) {
				return book;
			}
		}
		return null;
	}

	@Override
	public List<Object> getAllBooks() {
		// TODO Auto-generated method stub
		return (List<Object>) books;
	}

	@Override
	public void deleteBook(int bookUniqueId) {
		// TODO Auto-generated method stub\

		Iterator<Object> itr = books.iterator();
		while (itr.hasNext()) {
			Object books = itr.next();
			if (((Book) books).getBookId() == bookUniqueId) {
				itr.remove();
				break;
			}
		}

	}

	@Override
	public void addNewBook(Object o) {
		books.add(o);

	}

	public void addDummyData() {
		books.add(new Book(1, "Wladca Pierscieni", "Tolkien", "1337", "fantasy", "dostepna", this));
		books.add(new Book(2, "Wladca Pierscieni", "Tolkien", "1337", "fantasy", "dostepna", this));
		books.add(new Book(3, "Wladca Pierscieni", "Tolkien", "1337", "fantasy", "dostepna", this));
		books.add(new Book(4, "Wladca Pierscieni", "Tolkien", "1337", "fantasy", "dostepna", this));
		books.add(new Book(5, "Wladca Pierscieni", "Tolkien", "1337", "fantasy", "dostepna", this));
		books.add(new Book(6, "Wladca Pierscieni", "Tolkien", "1337", "fantasy", "dostepna", this));
		books.add(new Book(7, "Wladca Pierscieni", "Tolkien", "1337", "fantasy", "dostepna", this));
		books.add(new Book(8, "Wladca Pierscieni", "Tolkien", "1337", "fantasy", "dostepna", this));
		books.add(new Book(9, "Wladca Pierscieni", "Tolkien", "1337", "fantasy", "dostepna", this));
		books.add(new Book(10, "Wiedzmin", "Sapkowski", "1337", "klasyka", "dostepna", this));
		books.add(new Book(11, "Pan Tadeusz", "Mickiewicz", "1337", "horror", "dostepna", this));
		books.add(new Book(12, "Kubus Puchatek", "Jakub Kozlowski", "1337", "klasyka", "dostepna", this));
		books.add(new Book(13, "The New Moon", "Sapkowski", "1337", "inne", "dostepna", this));
		books.add(new Book(14, "Planet Side", "Epic Games", "1337", "inne", "dostepna", this));
		books.add(new Book(15, "Lineage 2", "NCSoft", "1337", "inne", "dostepna", this));
	}
}
