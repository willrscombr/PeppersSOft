package util;

import javax.swing.table.DefaultTableModel;

public class PeppersTableModel extends DefaultTableModel{

	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	};
}
