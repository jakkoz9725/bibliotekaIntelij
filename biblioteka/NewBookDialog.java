package com.company.biblioteka;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class NewBookDialog extends JDialog {
	private JTextField authorET;
	private JTextField titleET;
	private JTextField releaseYearET;
	private String[] bookCategories;
	private JButton addNewBookBtn;
	private JComboBox categoryCB;
	private boolean isDataCorrect;
	private LibraryInterface dataClass;

	public NewBookDialog(String[] values, LibraryInterface dataClass) { // implementation class here
		bookCategories = values;
		this.dataClass = dataClass;
		loadUI();
		loadBtnListeners();
	}

	public void loadBtnListeners() {
		addNewBookBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isDataCorrect = checkIfDataCorrect();
				if (isDataCorrect) {
					{
						dataClass.addNewBook(getBookFromUI());
//						dataClass.addNewBook(Book.setUniqueId(dataClass), titleET.getText().toString(), authorET.getText().toString(), releaseYearET.getText().toString(), categoryCB.getSelectedItem().toString());
						setLocation((Toolkit.getDefaultToolkit().getScreenSize().width) / 2 - getWidth() / 2,
								(Toolkit.getDefaultToolkit().getScreenSize().height) / 2 - getHeight() / 2);
						if(dataClass.getClass().equals(DummyClass.class)) {
							JOptionPane.showMessageDialog(null, "Brak implementacji"); // Dialog
						}else {
							JOptionPane.showMessageDialog(null, "Ksiazka dodana prawidlowo"); // Dialog
						}
						dispose();
					}
				}
			}
		});

	}

	public boolean checkIfDataCorrect() {
		String author = authorET.getText().toString();
		String title = titleET.getText().toString();
		String releaseYear = releaseYearET.getText().toString();
		String category = categoryCB.getSelectedItem().toString();

		if (author.length() < 4 || author.length() > 22 || Patterns.isNumeric(author)) {
			authorET.setBackground(Color.red);
			return false;
		} else if (title.length() < 4 || title.length() > 22 || Patterns.isNumeric(title)) {
			authorET.setBackground(Color.green);
			titleET.setBackground(Color.red);
			return false;
		} else if (!Patterns.isNumeric(releaseYear) || releaseYear.length() > 4) {
			authorET.setBackground(Color.green);
			titleET.setBackground(Color.green);
			releaseYearET.setBackground(Color.red);
			return false;
		} else if (category == "-") {
			System.out.println("RESULT FALSE category");
			authorET.setBackground(Color.green);
			titleET.setBackground(Color.green);
			releaseYearET.setBackground(Color.green);
			categoryCB.setBackground(Color.red);
			return false;
		} else {
			authorET.setBackground(Color.green);
			titleET.setBackground(Color.green);
			releaseYearET.setBackground(Color.green);
			categoryCB.setBackground(Color.green);
			System.out.println("RESULT TRUE");
			return true;
		}
	}

	public Book getBookFromUI() {
		Book book = new Book(authorET.getText().toString(), titleET.getText().toString(),
				releaseYearET.getText().toString(), categoryCB.getSelectedItem().toString(), dataClass);
		return book;
	}

	public void loadUI() {
		setResizable(false);
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setBounds(100, 100, 500, 366);
		getContentPane().setLayout(null);

		authorET = new JTextField();
		authorET.setToolTipText("Autor ksiazki");
		authorET.setHorizontalAlignment(SwingConstants.CENTER);
		authorET.setBounds(141, 26, 164, 30);
		getContentPane().add(authorET);
		authorET.setColumns(10);

		titleET = new JTextField();
		titleET.setHorizontalAlignment(SwingConstants.CENTER);
		titleET.setColumns(10);
		titleET.setBounds(141, 67, 164, 30);
		getContentPane().add(titleET);

		releaseYearET = new JTextField();
		releaseYearET.setHorizontalAlignment(SwingConstants.CENTER);
		releaseYearET.setColumns(10);
		releaseYearET.setBounds(141, 108, 164, 30);
		getContentPane().add(releaseYearET);

		categoryCB = new JComboBox(bookCategories);
		((JLabel) categoryCB.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		categoryCB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		categoryCB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		categoryCB.setBounds(141, 149, 164, 30);
		getContentPane().add(categoryCB);

		addNewBookBtn = new JButton("Dodaj ksiazke");
		addNewBookBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		addNewBookBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addNewBookBtn.setBounds(141, 221, 164, 34);
		getContentPane().add(addNewBookBtn);

		JLabel lblNewLabel = new JLabel("Autor");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(35, 26, 96, 28);
		getContentPane().add(lblNewLabel);

		JLabel lblTytul = new JLabel("Tytul");
		lblTytul.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTytul.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTytul.setBounds(35, 67, 96, 28);
		getContentPane().add(lblTytul);

		JLabel lblRokWydania = new JLabel("Rok wydania");
		lblRokWydania.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRokWydania.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblRokWydania.setBounds(10, 108, 121, 28);
		getContentPane().add(lblRokWydania);

		JLabel lblKategoria = new JLabel("Kategoria");
		lblKategoria.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKategoria.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblKategoria.setBounds(35, 149, 96, 28);
		getContentPane().add(lblKategoria);

		JLabel label = new JLabel("Od 4 do 22 znakow");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		label.setBounds(315, 26, 169, 28);
		getContentPane().add(label);

		JLabel label_1 = new JLabel("Od 4 do 22 znakow");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		label_1.setBounds(315, 67, 169, 28);
		getContentPane().add(label_1);

		JLabel lblRokBezLiter = new JLabel("Rok bez liter");
		lblRokBezLiter.setHorizontalAlignment(SwingConstants.LEFT);
		lblRokBezLiter.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		lblRokBezLiter.setBounds(315, 108, 169, 28);
		getContentPane().add(lblRokBezLiter);

		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width) / 2 - getWidth() / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height) / 2 - getHeight() / 2);
	}
}
