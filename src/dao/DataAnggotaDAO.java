package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.TBLAnggota;
import util.ConnectionConfiguration;

public class DataAnggotaDAO {

	private Connection myConn;

	public DataAnggotaDAO() throws Exception {
		myConn = ConnectionConfiguration.getConnectionDatabase();
	}

	public List<TBLAnggota> getAllDataAnggota() throws SQLException {
		List<TBLAnggota> listAnggota = new ArrayList<TBLAnggota>();

		Statement myStmt = null;
		ResultSet myRs = null;
		
		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery("SELECT * FROM TBLAnggota");

		while (myRs.next()) {
			listAnggota.add(TBLAnggota.builder()
					.noAnggota(myRs.getString("No_Anggota"))
					.nama(myRs.getString("Nama"))
					.wajib(myRs.getInt("Wajib"))
					.pokok(myRs.getInt("Pokok"))
					.saldo(myRs.getInt("Saldo"))
					.build());
		}
		close(myStmt, myRs);
		return listAnggota;
	}
	
	public List<String> getAllNoAnggota() throws SQLException {
		List<String> listNoAnggota = new ArrayList<String>();

		Statement myStmt = null;
		ResultSet myRs = null;
		
		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery("SELECT no_anggota FROM TBLAnggota");

		while (myRs.next()) {
			listNoAnggota.add(myRs.getString("No_Anggota"));
		}
		close(myStmt, myRs);
		return listNoAnggota;
	}

	public void saveDataAnggota(TBLAnggota dataAnggota) throws SQLException {
		PreparedStatement preStat = myConn.prepareStatement(
				"INSERT INTO TBLAnggota "
				+ " (No_Anggota, Nama, Wajib, Pokok, Saldo)"
				+ " values (?,?,?,?,?)");

		preStat.setString(1, dataAnggota.getNoAnggota());
		preStat.setString(2, dataAnggota.getNama());
		preStat.setInt(3, dataAnggota.getWajib());
		preStat.setInt(4, dataAnggota.getPokok());
		preStat.setInt(5, dataAnggota.getSaldo());

		preStat.execute();
		preStat.close();
	}

	public void updateDataAnggota(TBLAnggota dataAnggota) throws SQLException {
		PreparedStatement preStat = myConn.prepareStatement(
				"UPDATE TBLAnggota SET Nama = ?, Wajib = ?, "
				+ " Pokok = ?, Saldo = ? WHERE No_Anggota = ?");

		preStat.setString(1, dataAnggota.getNama());
		preStat.setInt(2, dataAnggota.getWajib());
		preStat.setInt(3, dataAnggota.getPokok());
		preStat.setInt(4, dataAnggota.getSaldo());
		preStat.setString(5, dataAnggota.getNoAnggota());

		preStat.executeUpdate();
		preStat.close();
	}

	public void deleteDataAnggota(String noAnggota) throws SQLException{
		PreparedStatement preStat = myConn.prepareStatement("DELETE FROM TBLAnggota WHERE No_Anggota = ?");
		preStat.setString(1, noAnggota);
		
		preStat.executeUpdate();
		preStat.close();
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);
	}

	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			myStmt.close();
		}

		if (myConn != null) {
			myConn.close();
		}
	}

}
