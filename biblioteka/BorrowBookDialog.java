package com.company.biblioteka;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class BorrowBookDialog extends JDialog {

	HashMap<String, Object> bookInfo;
	DefaultTableModel model;
	JTable table;
	JButton borrowBookBtn;
	LibraryInterface dataClass;
	int selectedRow;
	
	JDatePanelImpl datePanel;
	JDatePickerImpl datePicker;

	public BorrowBookDialog(JTable table, DefaultTableModel model, HashMap<String, Object> bookInfo,
			LibraryInterface dataClass, int selectedRow) {
		this.bookInfo = bookInfo;
		this.model = model;
		this.table = table;
		this.dataClass = dataClass;
		this.selectedRow = selectedRow;
		loadUI();
		loadBtnListeners();
		
		
		UtilDateModel model2 = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
	}

	public void loadBtnListeners() {
		borrowBookBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<?> listOfBooks = dataClass.getAllBooks();
				for (Object book : listOfBooks) {
					if (((Book<?>) book).getBookId() == Integer.parseInt(bookInfo.get("bookId").toString())) {
						if (datePicker.getModel().getValue() != null) {
							Date selectedDate = (Date) datePicker.getModel().getValue();
							SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
							String strDate = formatter.format(selectedDate);

							Date date = new Date();
							if (date.compareTo(selectedDate) < 0) {

								Object[] options = { "Tak", "Nie" };
								String message = "Czy napewno chcesz wypozyczyc te ksiazke?";
								int n = JOptionPane.showOptionDialog(new JFrame(), message, "",
										JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
										options[1]);
								if (n == JOptionPane.OK_OPTION) {
									JOptionPane.showMessageDialog(null, "Ksiazka wypozyczona");
									((Book<?>) book).setAvailable(strDate);
									model.setValueAt(strDate, selectedRow, 5);
									dispose();
								}
								if (n == JOptionPane.NO_OPTION) {
									dispose();
								}
								if (n == JOptionPane.CLOSED_OPTION) {
									dispose();
								}

							} else
								JOptionPane.showMessageDialog(null, "Wybierz prawidlowa date");
						} else {
							JOptionPane.showMessageDialog(null, "Wybierz date");
						}

					}
				}
			}

		});
	}

	public void loadUI() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(40, 211, 212, 39);
		getContentPane().add(panel);
		panel.setBounds(40, 211, 212, 39);
		getContentPane().add(panel);
		UtilDateModel model2 = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(model2, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormater());
		panel.add(datePicker);
		panel.add(datePicker);

		JLabel titleLbl = new JLabel(bookInfo.get("title").toString());
		titleLbl.setHorizontalAlignment(SwingConstants.LEFT);
		titleLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titleLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		titleLbl.setBounds(142, 11, 201, 39);
		getContentPane().add(titleLbl);

		JLabel authorLbl = new JLabel(bookInfo.get("author").toString());
		authorLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		authorLbl.setHorizontalAlignment(SwingConstants.LEFT);
		authorLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		authorLbl.setBounds(142, 61, 201, 39);
		getContentPane().add(authorLbl);

		JLabel releaseYearLbl = new JLabel(bookInfo.get("releaseYear").toString());
		releaseYearLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		releaseYearLbl.setHorizontalAlignment(SwingConstants.LEFT);
		releaseYearLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		releaseYearLbl.setBounds(142, 111, 201, 39);
		getContentPane().add(releaseYearLbl);

		JLabel lblNewLabel = new JLabel("Tytul:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(40, 11, 80, 39);
		getContentPane().add(lblNewLabel);

		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAutor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAutor.setBounds(40, 63, 80, 39);
		getContentPane().add(lblAutor);

		JLabel label_1 = new JLabel("Tytul:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(40, 113, 80, 39);
		getContentPane().add(label_1);

		JLabel lblWCeluWypozyczenia = new JLabel("W celu wypozyczenia ustaw date przewidzianego zwrotu ksiazki:");
		lblWCeluWypozyczenia.setHorizontalAlignment(SwingConstants.LEFT);
		lblWCeluWypozyczenia.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblWCeluWypozyczenia.setBounds(10, 171, 414, 29);
		getContentPane().add(lblWCeluWypozyczenia);

		borrowBookBtn = new JButton("Wypozycz");
		borrowBookBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		borrowBookBtn.setBounds(262, 211, 162, 39);
		getContentPane().add(borrowBookBtn);

		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width) / 2 - getWidth() / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height) / 2 - getHeight() / 2);

	}
}
