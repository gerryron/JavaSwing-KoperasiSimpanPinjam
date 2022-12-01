package model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.TBLAnggota;

public class DataAnggotaTableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int NO_ANGGOTA = 0;
	private static final int NAMA = 1;
	private static final int WAJIB = 2;
	private static final int POKOK = 3;
	private static final int SALDO = 4;
	
	private String[] columnNames = {"No", "Nama", "Wajib", "Pokok", "Saldo"};
	private List<TBLAnggota> listAnggota;
	
	public DataAnggotaTableModel(List<TBLAnggota> listAnggota) {
		this.listAnggota = listAnggota;
	}

	@Override
	public int getRowCount() {
		return listAnggota.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		TBLAnggota dataAnggota = listAnggota.get(rowIndex);

		switch (columnIndex) {
		case NO_ANGGOTA:
			return dataAnggota.getNoAnggota();
		case NAMA:
			return dataAnggota.getNama();
		case WAJIB:
			return dataAnggota.getWajib();
		case POKOK:
			return dataAnggota.getPokok();
		case SALDO:
			return dataAnggota.getSaldo();
		default:
			return dataAnggota.getNoAnggota();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}
