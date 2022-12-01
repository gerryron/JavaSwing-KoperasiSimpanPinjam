package model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.TBLPinjam;

public class DataPinjamTableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int NO_PINJAM = 0;
	private static final int TANGGAL = 1;
	private static final int NO_ANGGOTA = 2;
	private static final int JUMLAH_PINJAM = 3;
	private static final int KODE_KASIR = 4;
	
	private String[] columnNames = {"No Pinjam", "Tanggal", "No Anggota", "Jumlah Pinjam", "Kode Kasir"};
	private List<TBLPinjam> listPinjam;;
	
	public DataPinjamTableModel(List<TBLPinjam> listPinjam) {
		this.listPinjam = listPinjam;
	}

	@Override
	public int getRowCount() {
		return listPinjam.size();
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
		TBLPinjam dataPinjam = listPinjam.get(rowIndex);

		switch (columnIndex) {
		case NO_PINJAM:
			return dataPinjam.getNoPinjam();
		case TANGGAL:
			return dataPinjam.getTanggal();
		case NO_ANGGOTA:
			return dataPinjam.getNoAnggota();
		case JUMLAH_PINJAM:
			return dataPinjam.getJumlahPinjam();
		case KODE_KASIR:
			return dataPinjam.getKodeKasir();
		default:
			return dataPinjam.getNoAnggota();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}
