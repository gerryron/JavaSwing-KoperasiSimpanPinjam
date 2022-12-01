package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.TBLPinjam;
import util.ConnectionConfiguration;

public class DataPinjamDAO {
	
	private Connection myConn;

	public DataPinjamDAO() throws Exception {
		myConn = ConnectionConfiguration.getConnectionDatabase();
	}
	
	public List<TBLPinjam> getAllDataPinjamByKodeKasir(String kodeKasir) throws SQLException {
		List<TBLPinjam> listPinjam = new ArrayList<TBLPinjam>();
		
		PreparedStatement statement = myConn.prepareStatement("SELECT * FROM TBLPinjam WHERE Kodeksr = ?");
		statement.setString(1, kodeKasir);
		ResultSet myRs = statement.executeQuery();
		

		while (myRs.next()) {
			listPinjam.add(TBLPinjam.builder()
					.noPinjam(myRs.getString("No_Pinjam"))
					.tanggal(myRs.getDate("Tanggal"))
					.noAnggota(myRs.getString("No_Anggota"))
					.jumlahPinjam(myRs.getInt("JmlPinjam"))
					.kodeKasir(myRs.getString("KodeKsr"))
					.build());
		}
		return listPinjam;
	}
	
	public void saveDatPinjam(TBLPinjam dataPinjam) throws SQLException {
		PreparedStatement preStat = myConn.prepareStatement(
				"INSERT INTO TBLPinjam "
				+ " (No_Pinjam, Tanggal, No_Anggota, JmlPinjam, KodeKsr)"
				+ " values (?,?,?,?,?)");

		preStat.setString(1, dataPinjam.getNoPinjam());
		preStat.setDate(2, new Date(dataPinjam.getTanggal().getTime()));
		preStat.setString(3, dataPinjam.getNoAnggota());
		preStat.setInt(4, dataPinjam.getJumlahPinjam());
		preStat.setString(5, dataPinjam.getKodeKasir());

		preStat.execute();
		preStat.close();
	}
	
	public void deleteDataPinjam(String nomerPinjam) throws SQLException{
		PreparedStatement preStat = myConn.prepareStatement("DELETE FROM TBLPinjam WHERE No_Pinjam = ?");
		preStat.setString(1, nomerPinjam);
		
		preStat.executeUpdate();
		preStat.close();
	}

}
