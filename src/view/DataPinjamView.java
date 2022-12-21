package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import dao.DataAnggotaDAO;
import dao.DataPinjamDAO;
import entity.TBLPinjam;
import model.DataPinjamTableModel;

import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;

public class DataPinjamView {

	private JFrame frmDataPinjam;
	private JTextField noPinjamField;
	private JTextField jumlahPinjamField;
	private JTextField kodeKasirField;
	private JTable table;
	private JDateChooser dateChooser;
	private JComboBox<String> noAnggotaCombo;
	
	private List<TBLPinjam> listPinjam = new ArrayList<>();
	private DataPinjamTableModel tableModel;
	private DataPinjamDAO dataPinjamDAO;
	private DataAnggotaDAO dataAnggotaDAO;

	/**
	 * Launch the application.
	 */
	public static void main(String kodeKasir) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataPinjamView window = new DataPinjamView(kodeKasir);
					window.frmDataPinjam.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DataPinjamView(String kodeKasir) {
		kodeKasirField = new JTextField();
		kodeKasirField.setText(kodeKasir);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			dataPinjamDAO = new DataPinjamDAO();
			dataAnggotaDAO = new DataAnggotaDAO();
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, 
					"Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		frmDataPinjam = new JFrame();
		frmDataPinjam.setTitle("Data Pinjam");
		frmDataPinjam.setBounds(100, 100, 537, 392);
		frmDataPinjam.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setForeground(SystemColor.activeCaption);
        frmDataPinjam.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
	
        JLabel noAnggotaLabel = new JLabel("Nomer Pinjam");
        noAnggotaLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        noAnggotaLabel.setBounds(10, 12, 100, 30);
        panel.add(noAnggotaLabel);
        
        JLabel namaLabel = new JLabel("Tanggal");
        namaLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        namaLabel.setBounds(10, 42, 100, 30);
        panel.add(namaLabel);
        
        JLabel wajibLabel = new JLabel("No Anggota");
        wajibLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        wajibLabel.setBounds(10, 74, 100, 30);
        panel.add(wajibLabel);
        
        JLabel pokokLabel = new JLabel("Jumlah Pinjam");
        pokokLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        pokokLabel.setBounds(10, 106, 100, 30);
        panel.add(pokokLabel);
        
        JLabel saldoLabel = new JLabel("Kode Kasir");
        saldoLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        saldoLabel.setBounds(10, 139, 100, 30);
        panel.add(saldoLabel);
        
        noPinjamField = new JTextField();
        noPinjamField.setBounds(117, 11, 280, 30);
        panel.add(noPinjamField);
        noPinjamField.setColumns(10);
        
        jumlahPinjamField = new JTextField();
        jumlahPinjamField.setColumns(10);
        jumlahPinjamField.setBounds(117, 107, 280, 30);
        panel.add(jumlahPinjamField);
        
        kodeKasirField.setEditable(false);
        kodeKasirField.setColumns(10);
        kodeKasirField.setBounds(117, 140, 280, 30);
        panel.add(kodeKasirField);
        
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			TBLPinjam dataPinjam = TBLPinjam.builder()
        					.noPinjam(noPinjamField.getText())
        					.tanggal(dateChooser.getDate())
        					.noAnggota(noAnggotaCombo.getSelectedItem().toString())
        					.jumlahPinjam(Integer.valueOf(jumlahPinjamField.getText()))
        					.kodeKasir(kodeKasirField.getText())
        					.build();
        			dataPinjamDAO.saveDatPinjam(dataPinjam);
        			clearForm();
        			showTable();
        			JOptionPane.showMessageDialog(null, 
        					"Berhasil simpan data", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
        		} catch (NumberFormatException nfe) {
        			JOptionPane.showMessageDialog(null, 
							"Format tidak sesuai " + nfe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        		} catch (SQLException sqle) {
					JOptionPane.showMessageDialog(null, 
							"Gagal simpan data " + sqle.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, 
							"Unknown Error " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
        	}
        });
        addButton.setBounds(407, 12, 104, 60);
        panel.add(addButton);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 189, 497, 153);
		panel.add(scrollPane);
		
		table = new JTable();
		showTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				noPinjamField.setText(tableModel.getValueAt(i, 0).toString());
				dateChooser.setDate((Date) tableModel.getValueAt(i, 1));
				noAnggotaCombo.setSelectedItem(tableModel.getValueAt(i, 2).toString());
				jumlahPinjamField.setText(tableModel.getValueAt(i, 3).toString());
				kodeKasirField.setText(tableModel.getValueAt(i, 4).toString());
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pilihan = JOptionPane.showConfirmDialog(null, 
						"Apakah anda yakin ingin membersihkan form?", "Batalkan ?", JOptionPane.YES_NO_OPTION);
				if (pilihan == 0) {
					if (pilihan == 0) {
						clearForm();
					}	
				}
			}
		});
		btnClear.setBounds(407, 106, 104, 63);
		panel.add(btnClear);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
        			dataPinjamDAO.deleteDataPinjam(noPinjamField.getText());
        			clearForm();
        			showTable();
        			JOptionPane.showMessageDialog(null, 
        					"Berhasil hapus data", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
        		} catch (SQLException sqle) {
					JOptionPane.showMessageDialog(null, 
							"Gagal hapus data " + sqle.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, 
							"Unknown Error " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		deleteButton.setBounds(407, 75, 104, 30);
		panel.add(deleteButton);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(117, 42, 280, 30);
		panel.add(dateChooser);
		
		noAnggotaCombo = new JComboBox<String>();
		noAnggotaCombo.setBounds(117, 74, 280, 30);
		noAnggotaCombo.addItem("- Pilih");
		try {
			dataAnggotaDAO.getAllNoAnggota().forEach(
					(noAnggota) -> noAnggotaCombo.addItem(noAnggota));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, 
					"Gagal memuat data nomer anggota", "Error", JOptionPane.ERROR_MESSAGE);
		}
		panel.add(noAnggotaCombo);
	}
	
	private void showTable() {
		try {
			listPinjam.clear();
			listPinjam = dataPinjamDAO.getAllDataPinjamByKodeKasir(kodeKasirField.getText());
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, 
					"Gagal Memuat Tabel", "Error", JOptionPane.ERROR_MESSAGE);
		}
		tableModel = new DataPinjamTableModel(listPinjam);
		table.setModel(tableModel);
	}
	
	private void clearForm() {
		noPinjamField.setText("");
		dateChooser.setDate(new Date());
		noAnggotaCombo.setSelectedIndex(0);
		jumlahPinjamField.setText("");
	}
}
