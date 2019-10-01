package com.company.biblioteka;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class RollOverTable extends JTable {

	private int rollOverRowIndex = -1;

	public RollOverTable(TableModel model) {
		super(model);
		RollOverListener lst = new RollOverListener();
		addMouseMotionListener(lst);
		addMouseListener(lst);
	}

	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component c = super.prepareRenderer(renderer, row, column);
		if (isRowSelected(row) || (row == rollOverRowIndex)) {
			c.setForeground(new Color(255, 255, 255));
			c.setBackground(new Color(153, 145, 252));
		} else {
			if (getModel().getValueAt(row, 5) != "dostepna") {
				c.setForeground(new Color(8, 4, 7));
				c.setBackground(new Color(255, 0, 0, 100));
			} else {
				c.setForeground(new Color(8, 4, 7));
				c.setBackground(new Color(84, 196, 74));
			}
		}
		return c;
	}

	private class RollOverListener extends MouseInputAdapter {

		public void mouseExited(MouseEvent e) {
			rollOverRowIndex = -1;
			repaint();
		}

		public void mouseMoved(MouseEvent e) {
			int row = rowAtPoint(e.getPoint());
			if (row != rollOverRowIndex) {
				rollOverRowIndex = row;
				repaint();
			}
		}
	}
}