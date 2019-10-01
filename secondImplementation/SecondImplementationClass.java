package com.company.secondImplementation;

import java.awt.List;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.eclipse.core.runtime.ICoreRunnable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.ui.di.UISynchronize;

import biblioteka.Book;
import biblioteka.LibraryInterface;

public class SecondImplementationClass implements LibraryInterface {

	public SecondImplementationClass() throws Exception {

//		Job job = new Job("JOB") {
//
//			@Override
//			protected IStatus run(IProgressMonitor monitor) {
//				// TODO Auto-generated method stub
//				System.out.println("works here");
//				return null;
//			}
//		};
//		CheckStatusJob job2 = new CheckStatusJob("Job2");
//		job2.schedule(5000);
		
		Schuede sch = new Schuede();
		sch.earlyStartup();
		

//		CheckStatusJob job = new CheckStatusJob();

		// JAXBContext context = JAXBContext.newInstance(Book.class);
//		Marshaller m = context.createMarshaller();
//// for pretty-print XML in JAXB
//		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//		StringWriter writer = new StringWriter();
//// Write to list to a writer
//		m.marshal(arr, writer);
//		String result = writer.toString();
//		// write the content to a physical file
//		new FileWriter("jaxbTest.xml").write(result);

	}

	@Override
	public Object getBookById(int bookUniqueId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBook(int bookUniqueId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addNewBook(Object newBook) {
		// TODO Auto-generated method stub

	}

	@Override
	public java.util.List<Object> getAllBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	public void works() {
//		ArrayList books = new ArrayList();
//		books.add(new Book(1, "Wladca Pierscieni", "Tolkien", "1337", "fantasy", "dostepna", this));
//		books.add(new Book(2, "Wladca Pierscieni", "Tolkien", "1337", "fantasy", "dostepna", this));
//		books.add(new Book(3, "Wladca Pierscieni", "Tolkien", "1337", "fantasy", "dostepna", this));
//		books.add(new Book(4, "Wladca Pierscieni", "Tolkien", "1337", "fantasy", "dostepna", this));
//		books.add(new Book(5, "Wladca Pierscieni", "Tolkien", "1337", "fantasy", "dostepna", this));
//		books.add(new Book(6, "Wladca Pierscieni", "Tolkien", "1337", "fantasy", "dostepna", this));
//		books.add(new Book(7, "Wladca Pierscieni", "Tolkien", "1337", "fantasy", "dostepna", this));
//		books.add(new Book(8, "Wladca Pierscieni", "Tolkien", "1337", "fantasy", "dostepna", this));
//		books.add(new Book(9, "Wladca Pierscieni", "Tolkien", "1337", "fantasy", "dostepna", this));
//		books.add(new Book(10, "Wiedzmin", "Sapkowski", "1337", "klasyka", "dostepna", this));
//		books.add(new Book(11, "Pan Tadeusz", "Mickiewicz", "1337", "horror", "dostepna", this));
//		books.add(new Book(12, "Kubus Puchatek", "Jakub Kozlowski", "1337", "klasyka", "dostepna", this));
//		books.add(new Book(13, "The New Moon", "Sapkowski", "1337", "inne", "dostepna", this));
//		books.add(new Book(14, "Planet Side", "Epic Games", "1337", "inne", "dostepna", this));
//		books.add(new Book(15, "Lineage 2", "NCSoft", "1337", "inne", "dostepna", this));
//
//		XMLEncoder e = new XMLEncoder((new FileOutputStream("Test.xml")));
//		e.writeObject(books);
//		e.close();
//
//		ArrayList<Book> booksRecived = new ArrayList();
//		XMLDecoder de = new XMLDecoder((new FileInputStream("Test.xml")));
//		booksRecived = (ArrayList<Book>) de.readObject();
//		de.close();
//
//		for (Book booksy : booksRecived) {
//			System.out.println(booksy.getBookId());
//		}
	}
}
