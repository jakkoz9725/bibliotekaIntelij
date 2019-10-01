package com.company.biblioteka;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class EditBookDialog extends JDialog {
	private JTextField newTitleET;
	private JTextField newAuthorET;
	private JTextField newReleaseYearET;

	JTable table;
	DefaultTableModel model;
	HashMap<String, Object> bookInfo;
	LibraryInterface dataClass;
	int selectedRow;

	String currentlyBooktitle;
	String currentlyBookAuthor;
	String currentlyBookReleaseYear;
	String currentlyBookCategory;
	int bookId;

	JButton editBookData;
	JButton deleteBookBtn;

	JComboBox newCategoryCB;

	String[] booksCategory = { "wszystkie", "fantasy", "horror", "klasyka", "kryminal", "inne" };

	public EditBookDialog(JTable table, DefaultTableModel model, HashMap<String, Object> bookInfo,
			LibraryInterface dataClass, int selectedRow) {
		this.table = table;
		this.model = model;
		this.bookInfo = bookInfo;
		this.dataClass = dataClass;
		this.selectedRow = selectedRow;

		loadUI();
		loadBtnListeners();

		newTitleET.setText(currentlyBooktitle);
		newAuthorET.setText(currentlyBookAuthor);
		newReleaseYearET.setText(currentlyBookReleaseYear);

		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width) / 2 - getWidth() / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height) / 2 - getHeight() / 2);
	}

	public boolean checkData(JComboBox categoryCB) {
		String author = newAuthorET.getText().toString();
		String title = newTitleET.getText().toString();
		String releaseYear = newReleaseYearET.getText().toString();
		String category = categoryCB.getSelectedItem().toString();

		if (author.length() < 4 || author.length() > 22 || Patterns.isNumeric(author)) {
			newAuthorET.setBackground(Color.red);
			System.out.println("RESULT FALSE Author");
			return false;
		} else if (title.length() < 4 || title.length() > 22 || Patterns.isNumeric(title)) {
			newAuthorET.setBackground(Color.green);
			newTitleET.setBackground(Color.red);
			System.out.println("RESULT FALSE title");

			newTitleET.setToolTipText("HALO");

			return false;
		} else if (!Patterns.isNumeric(releaseYear) || releaseYear.length() > 4) {
			System.out.println("RESULT FALSE year");
			newAuthorET.setBackground(Color.green);
			newTitleET.setBackground(Color.green);
			newReleaseYearET.setBackground(Color.red);
			return false;
		} else if (category == "-") {
			System.out.println("RESULT FALSE category");
			newAuthorET.setBackground(Color.green);
			newTitleET.setBackground(Color.green);
			newReleaseYearET.setBackground(Color.green);
			categoryCB.setBackground(Color.red);
			return false;
		} else {
			newAuthorET.setBackground(Color.green);
			newTitleET.setBackground(Color.green);
			newReleaseYearET.setBackground(Color.green);
			categoryCB.setBackground(Color.green);
			System.out.println("RESULT TRUE");
			return true;
		}
	}

	public void loadBtnListeners() {
		editBookData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (checkData(newCategoryCB)) {
//					Book book = new Book(newTitleET.getText().toString(), newAuthorET.getText().toString(),
//							newReleaseYearET.getText().toString(), newCategoryCB.getSelectedItem().toString(),
//							dataClass);
//					dataClass.editBookById((Integer) bookInfo.get("bookId"), book);
					setLocation((Toolkit.getDefaultToolkit().getScreenSize().width) / 2 - getWidth() / 2,
							(Toolkit.getDefaultToolkit().getScreenSize().height) / 2 - getHeight() / 2);
					dispose();
				}
			}

		});

		deleteBookBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				dataClass.deleteBook(bookId);

				model.getDataVector().removeAllElements(); // Clearing table
				dispose();
			}
		});
	}

	public void loadUI() {

		setBounds(100, 100, 538, 372);
		getContentPane().setLayout(null);

		editBookData = new JButton("Edytuj wpis");
		editBookData.setFont(new Font("Tahoma", Font.PLAIN, 16));
		editBookData.setBounds(157, 220, 162, 28);
		getContentPane().add(editBookData);

		newTitleET = new JTextField();
		newTitleET.setBounds(157, 48, 162, 31);
		getContentPane().add(newTitleET);
		newTitleET.setColumns(10);

		newAuthorET = new JTextField();
		newAuthorET.setBounds(157, 86, 162, 31);
		getContentPane().add(newAuthorET);
		newAuthorET.setColumns(10);

		newReleaseYearET = new JTextField();
		newReleaseYearET.setBounds(157, 128, 162, 31);
		getContentPane().add(newReleaseYearET);
		newReleaseYearET.setColumns(10);

		newCategoryCB = new JComboBox(booksCategory);
		newCategoryCB.setBounds(157, 172, 162, 28);
		getContentPane().add(newCategoryCB);

		JLabel lblNewLabel = new JLabel("Tytul:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(73, 47, 74, 28);
		getContentPane().add(lblNewLabel);

		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAutor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAutor.setBounds(67, 86, 80, 28);
		getContentPane().add(lblAutor);

		JLabel lblRokWydania = new JLabel("Rok wydania:");
		lblRokWydania.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRokWydania.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRokWydania.setBounds(29, 127, 118, 28);
		getContentPane().add(lblRokWydania);

		JLabel lblKategoria = new JLabel("Kategoria:");
		lblKategoria.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKategoria.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblKategoria.setBounds(29, 170, 118, 28);
		getContentPane().add(lblKategoria);

		JLabel lblNoweWartosci = new JLabel("Nowe wartosci");
		lblNoweWartosci.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoweWartosci.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNoweWartosci.setBounds(157, 11, 162, 28);
		getContentPane().add(lblNoweWartosci);

		JLabel lblPoprzednieWartosci = new JLabel("Poprzednie wartosci");
		lblPoprzednieWartosci.setHorizontalAlignment(SwingConstants.LEFT);
		lblPoprzednieWartosci.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPoprzednieWartosci.setBounds(337, 11, 162, 28);
		getContentPane().add(lblPoprzednieWartosci);

		JLabel currentlyTitleLbl = new JLabel("tytul");
		currentlyTitleLbl.setHorizontalAlignment(SwingConstants.LEFT);
		currentlyTitleLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		currentlyTitleLbl.setBounds(337, 47, 162, 28);
		getContentPane().add(currentlyTitleLbl);

		JLabel currentlyAuthorLbl = new JLabel("autor");
		currentlyAuthorLbl.setHorizontalAlignment(SwingConstants.LEFT);
		currentlyAuthorLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		currentlyAuthorLbl.setBounds(337, 86, 162, 28);
		getContentPane().add(currentlyAuthorLbl);

		JLabel currentlyReleaseYearLbl = new JLabel("rok wydania");
		currentlyReleaseYearLbl.setHorizontalAlignment(SwingConstants.LEFT);
		currentlyReleaseYearLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		currentlyReleaseYearLbl.setBounds(337, 127, 162, 28);
		getContentPane().add(currentlyReleaseYearLbl);

		JLabel currentlyCategoryLbl = new JLabel("kategoria");
		currentlyCategoryLbl.setHorizontalAlignment(SwingConstants.LEFT);
		currentlyCategoryLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		currentlyCategoryLbl.setBounds(337, 170, 162, 28);
		getContentPane().add(currentlyCategoryLbl);

		JLabel lblLub = new JLabel("lub");
		lblLub.setHorizontalAlignment(SwingConstants.CENTER);
		lblLub.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLub.setBounds(157, 250, 162, 37);
		getContentPane().add(lblLub);

		deleteBookBtn = new JButton("Usun wpis");
		deleteBookBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		deleteBookBtn.setBounds(157, 288, 162, 28);
		getContentPane().add(deleteBookBtn);

		bookId = (Integer) bookInfo.get("bookId");
		currentlyBooktitle = (String) bookInfo.get("title");
		currentlyBookAuthor = (String) bookInfo.get("author");
		currentlyBookReleaseYear = (String) bookInfo.get("releaseYear");
		currentlyBookCategory = (String) bookInfo.get("bookCategory");

		currentlyReleaseYearLbl.setText(currentlyBookReleaseYear);
		currentlyCategoryLbl.setText(currentlyBookCategory);
		currentlyAuthorLbl.setText(currentlyBookAuthor);
		currentlyTitleLbl.setText(currentlyBooktitle);
	}
}
