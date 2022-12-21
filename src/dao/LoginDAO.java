/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Statement;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import entity.TBLKasir;

/**
 *
 * @author NAKES
 */
public class LoginDAO {

    private Connection myConn;

    public LoginDAO() throws Exception {
        Properties props = new Properties();
        props.load(new FileInputStream("application.properties"));

        String user = props.getProperty("DATABASE_USERNAME");
        String password = props.getProperty("DATABASE_PASSWORD");
        String dbUrl = props.getProperty("DATABASE_URL");

        // Create connection to database
        myConn = DriverManager.getConnection(dbUrl, user, password);
    }

    public TBLKasir login(String kodeKsr, String passwordKsr) throws Exception {
        TBLKasir result = new TBLKasir();
        PreparedStatement preStat = null;
        ResultSet rs = null;

        try {
            preStat = myConn.prepareStatement("SELECT * FROM tblkasir where KODEKSR = ? AND PASSWORDKSR = ?");
            preStat.setString(1, kodeKsr);
            preStat.setString(2, passwordKsr);

            rs = preStat.executeQuery();
       
            if(rs.next()) {
                result = new TBLKasir(rs.getString("KODEKSR"), 
                		rs.getString("NAMAKSR"), 
                		rs.getString("PASSWORDKSR"));
            }

        } finally {
            close(preStat, rs);
        }
        return result;
    }

    private void close(Statement myStmt, ResultSet myRs) throws SQLException {
        close(null, myStmt, myRs);
    }

    private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
            throws SQLException {

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
