package com.company.biblioteka;

import java.util.List;

public interface LibraryInterface {

	Object getBookById(int bookUniqueId);
	
	List<Object> getAllBooks();

	void deleteBook(int bookUniqueId);
	
	void addNewBook(Object newBook);

}
