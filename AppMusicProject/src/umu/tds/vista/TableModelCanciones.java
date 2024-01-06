package umu.tds.vista;

import javax.swing.table.AbstractTableModel;

public class TableModelCanciones extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private Object[][] data;
	private String[] columnNames;

	public TableModelCanciones(Object[][] data, String[] columnNames) {
		this.data = data;
		this.columnNames = columnNames;
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 3;
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		data[rowIndex][columnIndex] = value;
		fireTableCellUpdated(rowIndex, columnIndex);
	}
}
