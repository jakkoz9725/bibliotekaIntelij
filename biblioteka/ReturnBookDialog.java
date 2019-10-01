package com.company.biblioteka;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class ReturnBookDialog extends JDialog {

	JTable table;
	DefaultTableModel model;
	HashMap<String, Object> bookInfo;
	int selectedRow;
	LibraryInterface dataClass;
	JButton borrowBookBtn;

	public ReturnBookDialog(JTable table, DefaultTableModel model, HashMap<String, Object> bookInfo,
			LibraryInterface dataClass, int selectedRow) {

		this.table = table;
		this.model = model;
		this.bookInfo = bookInfo;
		this.selectedRow = selectedRow;
		this.dataClass = dataClass;

		loadUI();
		loadBtnListeners();

	}

	public void loadBtnListeners() {
		borrowBookBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dataClass.getAllBooks();
				for (Object book : dataClass.getAllBooks()) {
					if (((Book) book).getBookId() == Integer.parseInt(bookInfo.get("bookId").toString())) {
						String message = "Czy napewno chcesz zwrocic te ksiazke?";
						Object[] options = { "Tak", "Nie" };
						int n = JOptionPane.showOptionDialog(new JFrame(), message, "", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
						if (n == JOptionPane.OK_OPTION) {
							model.setValueAt("dostepna", selectedRow, 5);
							((Book) book).setAvailable("dostepna");
							JOptionPane.showMessageDialog(null, "Ksiazka zwrocona");
							dispose();
						}
						if (n == JOptionPane.NO_OPTION) {
							dispose();
						}
						if (n == JOptionPane.CLOSED_OPTION) {
							dispose();
						}
					}
				}
			}

		});
	}

	public void loadUI() {

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		UtilDateModel model2 = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model2, p);

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

		JLabel returnDateLbl = new JLabel(bookInfo.get("returnDate").toString());
		returnDateLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		returnDateLbl.setHorizontalAlignment(SwingConstants.LEFT);
		returnDateLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		returnDateLbl.setBounds(142, 161, 201, 39);
		getContentPane().add(returnDateLbl);

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

		JLabel lblTermin = new JLabel("Termin:");
		lblTermin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTermin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTermin.setBounds(40, 161, 80, 39);
		getContentPane().add(lblTermin);

		borrowBookBtn = new JButton("Zwroc");
		borrowBookBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		borrowBookBtn.setBounds(120, 211, 162, 39);
		getContentPane().add(borrowBookBtn);

		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width) / 2 - getWidth() / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height) / 2 - getHeight() / 2);
	}
}