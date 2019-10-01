package com.company.biblioteka;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class Biblioteka extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField bookTitleET;
	private JTextField bookAuthorET;
	private JTextField bookReleaseYearET;
	String[] bookCategories;
	String[] bookAvailability;

	JButton addBookBtn;
	JButton returnToMenuBtn;
	JButton searchMenuBtn;
	JButton searchBookBtn;
	JButton findBookByCodeBtn;

	JPanel searchMenu;
	JPanel mainMenu;

	JComboBox categoryComboBox;
	JComboBox availabilityComboBox;

	RollOverTable table;
	boolean otherClass = true;
	LibraryInterface implementationClass;

	DefaultTableModel model = new DefaultTableModel();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Biblioteka frame = new Biblioteka();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	};

	public Biblioteka() {
		String implementationPath = "";
		try {
			Scanner input = new Scanner(new File("properties.rtf"));
			implementationPath = input.nextLine();
		} catch (Exception E) {
			JOptionPane.showMessageDialog(null, "Sprawdz plik konfiguracyjny");
		}
		if (!implementationPath.equals("")) {
			try {
				implementationClass = (LibraryInterface) ClassLoader.getSystemClassLoader()
						.loadClass(implementationPath).newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		if (implementationClass == null) {
			implementationClass = new DummyClass();
			JOptionPane.showMessageDialog(null, "Brak implementacji");
		}

		// new firstImplementation.FirstImplementationClass();

		setResizable(false);
		/*
		 * { "wszystkie", "fantasy", "horror", "klasyka", "kryminal", "inne" };
		 * 
		 */
		bookCategories = Book.getBooksCategory();
		/*
		 * { "wszystkie", "wypozyczone", "dostepna" };
		 * 
		 */
		bookAvailability = Book.getBooksAvailability();
		loadUI();
		loadBtnListeners();
		loadMouseListener();

		// implementation.addBooksOnce(); class implementation

	}

	public void loadUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1172, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		mainMenu = new JPanel();
		mainMenu.setBounds(10, 11, 1146, 491);
		contentPane.add(mainMenu);
		mainMenu.setLayout(null);

		searchMenu = new JPanel();
		searchMenu.setLayout(null);
		searchMenu.setBounds(10, 11, 1146, 491);
		contentPane.add(searchMenu);

		table = new RollOverTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setAlignmentX(Component.RIGHT_ALIGNMENT);
		table.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.setDefaultEditor(Object.class, null); // Remove possibility of editing tables
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 17, 826, 392);
		searchMenu.add(scrollPane);

		table.setBackground(Color.GRAY);
		table.setForeground(Color.black);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		table.setRowHeight(35);

		bookTitleET = new JTextField();
		bookTitleET.setHorizontalAlignment(SwingConstants.CENTER);
		bookTitleET.setColumns(10);
		bookTitleET.setBounds(846, 213, 175, 29);
		searchMenu.add(bookTitleET);

		bookAuthorET = new JTextField();
		bookAuthorET.setHorizontalAlignment(SwingConstants.CENTER);
		bookAuthorET.setColumns(10);
		bookAuthorET.setBounds(846, 253, 175, 29);
		searchMenu.add(bookAuthorET);

		bookReleaseYearET = new JTextField();
		bookReleaseYearET.setHorizontalAlignment(SwingConstants.CENTER);
		bookReleaseYearET.setColumns(10);
		bookReleaseYearET.setBounds(846, 293, 175, 29);
		searchMenu.add(bookReleaseYearET);

		JLabel lblTytul = new JLabel("Tytul");
		lblTytul.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTytul.setBounds(1031, 213, 57, 29);
		searchMenu.add(lblTytul);

		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblAutor.setBounds(1031, 253, 57, 29);
		searchMenu.add(lblAutor);

		JLabel lblRokWydania = new JLabel("Rok wydania");
		lblRokWydania.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblRokWydania.setBounds(1031, 293, 87, 29);
		searchMenu.add(lblRokWydania);

		JLabel lblKategoria = new JLabel("Kategoria");
		lblKategoria.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblKategoria.setBounds(1031, 333, 87, 29);
		searchMenu.add(lblKategoria);

		JLabel lblPodajJakNajwiecej = new JLabel("Podaj jak najwiecej informacji");
		lblPodajJakNajwiecej.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblPodajJakNajwiecej.setBounds(846, 178, 272, 24);
		searchMenu.add(lblPodajJakNajwiecej);

		JLabel lblWyszukiwarka = new JLabel("Wyszukiwarka");
		lblWyszukiwarka.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblWyszukiwarka.setBounds(886, 11, 202, 24);
		searchMenu.add(lblWyszukiwarka);

		JLabel lblKiedyZnaszKod = new JLabel("W przypadku gdy znasz kod ksiazki");
		lblKiedyZnaszKod.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblKiedyZnaszKod.setBounds(846, 46, 290, 24);
		searchMenu.add(lblKiedyZnaszKod);

		JLabel lblWPrzeciwnimRazie = new JLabel("W przeciwnim razie");
		lblWPrzeciwnimRazie.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblWPrzeciwnimRazie.setBounds(876, 141, 242, 24);
		searchMenu.add(lblWPrzeciwnimRazie);

		JLabel lblDostepne = new JLabel("Dostepne");
		lblDostepne.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblDostepne.setBounds(1031, 373, 87, 29);
		searchMenu.add(lblDostepne);

		JLabel lblBiblioteka = new JLabel("BIBLIOTEKA");
		lblBiblioteka.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 98));
		lblBiblioteka.setBounds(270, 11, 688, 153);
		mainMenu.add(lblBiblioteka);

		searchMenuBtn = new JButton("Wyszukiwarka");
		searchMenuBtn.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		searchMenuBtn.setBounds(487, 168, 208, 58);
		mainMenu.add(searchMenuBtn);

		searchBookBtn = new JButton("Szukaj");
		searchBookBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		searchBookBtn.setBounds(846, 420, 242, 60);
		searchMenu.add(searchBookBtn);

		findBookByCodeBtn = new JButton("Znam kod");
		findBookByCodeBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		findBookByCodeBtn.setBounds(846, 81, 242, 29);
		searchMenu.add(findBookByCodeBtn);

		addBookBtn = new JButton("Dodaj ksiazke");
		addBookBtn.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		addBookBtn.setBounds(487, 237, 208, 58);
		mainMenu.add(addBookBtn);

		returnToMenuBtn = new JButton("Wroc do menu");
		returnToMenuBtn.setBounds(10, 420, 139, 60);
		searchMenu.add(returnToMenuBtn);

		Object[] columns = { "Tytul", "Autor", "Data publikacji", "Kategoria", "Unikalny kod", "Dostepna" };
		model.setColumnIdentifiers(columns);
		Font font = new Font("", 1, 17);

		categoryComboBox = new JComboBox(bookCategories);
		((JLabel) categoryComboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		categoryComboBox.setBounds(846, 333, 175, 29);
		searchMenu.add(categoryComboBox);

		availabilityComboBox = new JComboBox(bookAvailability);
		availabilityComboBox.setBounds(846, 373, 175, 29);
		searchMenu.add(availabilityComboBox);
		((JLabel) availabilityComboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

		JTableUtilities.setCellsAlignment(table, SwingConstants.CENTER);

		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width) / 2 - getWidth() / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height) / 2 - getHeight() / 2);
	}

	public void loadBtnListeners() {
		returnToMenuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenu.setVisible(true);
				searchMenu.setVisible(false);
			}
		});
		addBookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// JOptionPane.showMessageDialog(null,"Book deleted Succesfully"); //Dialog
				NewBookDialog newBookDialog = new NewBookDialog(bookCategories, implementationClass);
				newBookDialog.setModal(true);
				newBookDialog.setVisible(true);
			}
		});
		searchMenuBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.getDataVector().removeAllElements();
				model.fireTableDataChanged();
				searchMenu.setVisible(true);
				mainMenu.setVisible(false);
			}
		});
		searchBookBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				model.getDataVector().removeAllElements(); // Clearing table
				// Search engine
				ArrayList<Object> listOfBooks = (ArrayList<Object>) implementationClass.getAllBooks();
				model.fireTableDataChanged();
				String bookTitle = bookTitleET.getText().toString();
				String bookAuthor = bookAuthorET.getText().toString();
				String bookRealaseYear = bookReleaseYearET.getText().toString();
				String selectedCategory = (String) categoryComboBox.getSelectedItem();
				String selectedAvaiablity = (String) availabilityComboBox.getSelectedItem();
				if (implementationClass.getClass().equals(DummyClass.class)) {
					JOptionPane.showMessageDialog(null, "Brak implementacji"); // Dialog
				} else {
					if (listOfBooks != null) {

						for (Object book : listOfBooks) {
							if (!bookTitle.isEmpty()) {
								System.out.println("Book not empty");
								if (!((Book) book).getTitle().toLowerCase().contains(bookTitle.toLowerCase())) {
									continue;
								}
							}
							if (!bookAuthor.isEmpty()) {
								System.out.println("Author not empty");
								if (!((Book) book).getAuthor().toLowerCase().contains(bookAuthor.toLowerCase())) {
									continue;
								}
							}
							if (!bookRealaseYear.isEmpty()) {
								if (!((Book) book).getPublicationDate().toLowerCase()
										.contains(bookRealaseYear.toLowerCase())) {
									continue;
								}
							}
							if (selectedCategory.equals(bookCategories[0])) {
							} else {
								System.out.println(((Book) book).getCategory());
								if (!((Book) book).getCategory().toLowerCase().equals(selectedCategory.toLowerCase())) {
									continue;
								}
							}

							// { "wszystkie","wypozyczone","dostepna" };
							if (!selectedAvaiablity.equals(bookAvailability[0])) {
								if (selectedAvaiablity.contentEquals(bookAvailability[2])) {
									if (!((Book) book).getAvaiable().equals(bookAvailability[2])) {
										continue;
									}
								} else if (selectedAvaiablity.contentEquals(bookAvailability[1])) {
									if (((Book) book).getAvaiable().equals(bookAvailability[2])) {
										continue;
									}
								}
							}

							Object[] row = new Object[6];
							row[0] = (((Book) (book)).getTitle());
							row[1] = ((Book) book).getAuthor();
							row[2] = ((Book) book).getPublicationDate();
							row[3] = ((Book) book).getCategory();
							row[4] = ((Book) book).getBookId();
							row[5] = ((Book) book).getAvaiable();

							table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
							model.addRow(row);

							TableColumnModel columnModel = table.getColumnModel();
							for (int column = 0; column < table.getColumnCount(); column++) {
								int width = 15;
								for (int rowz = 0; rowz < table.getRowCount(); rowz++) {
									TableCellRenderer renderer = table.getCellRenderer(rowz, column);
									Component comp = table.prepareRenderer(renderer, rowz, column);
									width = Math.max(comp.getPreferredSize().width + 1, width);
								}
								if (width > 300)
									width = 300;
								columnModel.getColumn(column).setPreferredWidth(width);
							}
						}
					}

				}
			}
		});

		findBookByCodeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (implementationClass.getClass().equals(DummyClass.class)) {
					JOptionPane.showMessageDialog(null, "Brak implementacji"); // Dialog
				} else {
					model.getDataVector().removeAllElements();
					model.fireTableDataChanged();
					// TODO Auto-generated method stub
					JTextField bookId = new JTextField();
					Object[] message = { "Kod ksiazki : ", bookId };
					Object[] options = { "Szukaj", "Anuluj" };

					int n = JOptionPane.showOptionDialog(new JFrame(), message, "", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
					if (n == JOptionPane.OK_OPTION) { // Afirmative
						if (Patterns.isNumeric(bookId.getText().toString())) {
							Object lookinForBook = implementationClass
									.getBookById(Integer.parseInt(bookId.getText().toString()));
							if (lookinForBook != null) {
								Object[] row = new Object[6];
								row[0] = ((Book) lookinForBook).getTitle();
								row[1] = ((Book) lookinForBook).getAuthor();
								row[2] = ((Book) lookinForBook).getPublicationDate();
								row[3] = ((Book) lookinForBook).getCategory();
								row[4] = ((Book) lookinForBook).getBookId();
								row[5] = ((Book) lookinForBook).getAvaiable();
								model.addRow(row);
							} else {
								JOptionPane.showMessageDialog(null, "Brak wynikow, sprobuj normalnego wyszukiwania");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Musisz podac wartosc liczbowa");
						}
					}
				}
			}
		});
	}

	public Object[] getInformation(String str) {
		String word1;
		if (str.equals("dostepna")) {
			word1 = "Wypozycz";
		} else {
			word1 = "Zwrot";

		}
		Object[] options = { word1, "Edycja" };
		return options;
	}

	public void loadMouseListener() {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int clickedTable = table.getSelectedRow();
				HashMap<String, Object> bookInfo = new HashMap<String, Object>();
				bookInfo.put("title", model.getValueAt(clickedTable, 0));
				bookInfo.put("author", model.getValueAt(clickedTable, 1));
				bookInfo.put("releaseYear", model.getValueAt(clickedTable, 2));
				bookInfo.put("bookCategory", model.getValueAt(clickedTable, 3));
				bookInfo.put("bookId", model.getValueAt(clickedTable, 4));
				bookInfo.put("bookAvailable", model.getValueAt(clickedTable, 5));
				Object[] options = getInformation(bookInfo.get("bookAvailable").toString());
				String message = "Wybierz co chcesz zrobic";
				int n = JOptionPane.showOptionDialog(new JFrame(), message, "", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
				if (n == JOptionPane.CLOSED_OPTION) {

				} else {
					String bookStatus = (String) model.getValueAt(clickedTable, 5);
					// JOptionPane.showMessageDialog(null,"Book deleted Succesfully"); //Dialog
					// poopout
					if (n == JOptionPane.OK_OPTION) {

						if (bookStatus.equals("dostepna")) {
							BorrowBookDialog borrowBookDialog = new BorrowBookDialog(table, model, bookInfo,
									implementationClass, clickedTable);
							borrowBookDialog.setModal(true);
							borrowBookDialog.setVisible(true);
						} else {
							bookInfo.put("returnDate", bookStatus);
							ReturnBookDialog returnBookDialog = new ReturnBookDialog(table, model, bookInfo,
									implementationClass, clickedTable);
							returnBookDialog.setModal(true);
							returnBookDialog.setVisible(true);
						}
					}
					if (n == JOptionPane.NO_OPTION) {
						EditBookDialog editDialog = new EditBookDialog(table, model, bookInfo, implementationClass,
								clickedTable);
						editDialog.setModal(true);
						editDialog.setVisible(true);
					}

					table.getSelectionModel().clearSelection();
				}
			}
		});
	}
}
