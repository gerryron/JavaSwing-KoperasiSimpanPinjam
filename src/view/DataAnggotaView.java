package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.DataAnggotaDAO;
import entity.TBLAnggota;
import model.DataAnggotaTableModel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DataAnggotaView {

	private JFrame frmDataAnggota;
	private JTextField noAnggotaField;
	private JTextField namaField;
	private JTextField wajibField;
	private JTextField pokokField;
	private JTextField saldoField;
	private JTable table;
	
	private List<TBLAnggota> listAnggota = new ArrayList<>();
	private DataAnggotaTableModel tableModel;
	private DataAnggotaDAO dataAnggotaDAO;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataAnggotaView window = new DataAnggotaView();
					window.frmDataAnggota.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DataAnggotaView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			dataAnggotaDAO = new DataAnggotaDAO();
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, 
					"Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		frmDataAnggota = new JFrame();
		frmDataAnggota.setTitle("Data Anggota");
		frmDataAnggota.setBounds(100, 100, 537, 392);
		frmDataAnggota.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setForeground(SystemColor.activeCaption);
        frmDataAnggota.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        
        JLabel noAnggotaLabel = new JLabel("Nomer Anggota");
        noAnggotaLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        noAnggotaLabel.setBounds(10, 12, 100, 30);
        panel.add(noAnggotaLabel);
        
        JLabel namaLabel = new JLabel("Nama");
        namaLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        namaLabel.setBounds(10, 42, 100, 30);
        panel.add(namaLabel);
        
        JLabel wajibLabel = new JLabel("Wajib");
        wajibLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        wajibLabel.setBounds(10, 74, 100, 30);
        panel.add(wajibLabel);
        
        JLabel pokokLabel = new JLabel("Pokok");
        pokokLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        pokokLabel.setBounds(10, 106, 100, 30);
        panel.add(pokokLabel);
        
        JLabel saldoLabel = new JLabel("Saldo");
        saldoLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        saldoLabel.setBounds(10, 139, 100, 30);
        panel.add(saldoLabel);
        
        noAnggotaField = new JTextField();
        noAnggotaField.setBounds(117, 12, 280, 30);
        panel.add(noAnggotaField);
        noAnggotaField.setColumns(10);
        
        namaField = new JTextField();
        namaField.setColumns(10);
        namaField.setBounds(117, 43, 280, 30);
        panel.add(namaField);
        
        wajibField = new JTextField();
        wajibField.setColumns(10);
        wajibField.setBounds(117, 75, 280, 30);
        panel.add(wajibField);
        
        pokokField = new JTextField();
        pokokField.setColumns(10);
        pokokField.setBounds(117, 107, 280, 30);
        panel.add(pokokField);
        
        saldoField = new JTextField();
        saldoField.setColumns(10);
        saldoField.setBounds(117, 140, 280, 30);
        panel.add(saldoField);
        
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			TBLAnggota dataAnggota = TBLAnggota.builder()
        					.noAnggota(noAnggotaField.getText())
        					.nama(namaField.getText())
        					.wajib(Integer.valueOf(wajibField.getText()))
        					.pokok(Integer.valueOf(pokokField.getText()))
        					.saldo(Integer.valueOf(saldoField.getText()))
        					.build();
        			dataAnggotaDAO.saveDataAnggota(dataAnggota);
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
        addButton.setBounds(407, 12, 100, 30);
        panel.add(addButton);
        
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			TBLAnggota dataAnggota = TBLAnggota.builder()
        					.noAnggota(noAnggotaField.getText())
        					.nama(namaField.getText())
        					.wajib(Integer.valueOf(wajibField.getText()))
        					.pokok(Integer.valueOf(pokokField.getText()))
        					.saldo(Integer.valueOf(saldoField.getText()))
        					.build();
        			dataAnggotaDAO.updateDataAnggota(dataAnggota);
        			clearForm();
        			showTable();
        			JOptionPane.showMessageDialog(null, 
        					"Berhasil ubah data", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
        		} catch (NumberFormatException nfe) {
        			JOptionPane.showMessageDialog(null, 
							"Format tidak sesuai " + nfe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        		} catch (SQLException sqle) {
					JOptionPane.showMessageDialog(null, 
							"Gagal ubah data " + sqle.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, 
							"Unknown Error " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
        	}
        });
        editButton.setBounds(407, 42, 100, 30);
        panel.add(editButton);
        
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
        			dataAnggotaDAO.deleteDataAnggota(noAnggotaField.getText());
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
        deleteButton.setBounds(407, 74, 100, 30);
        panel.add(deleteButton);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 189, 497, 153);
		panel.add(scrollPane);
		
		table = new JTable();
		showTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				noAnggotaField.setText(tableModel.getValueAt(i, 0).toString());
				namaField.setText(tableModel.getValueAt(i, 1).toString());
				wajibField.setText(tableModel.getValueAt(i, 2).toString());
				pokokField.setText(tableModel.getValueAt(i, 3).toString());
				saldoField.setText(tableModel.getValueAt(i, 4).toString());
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
		btnClear.setBounds(407, 106, 100, 63);
		panel.add(btnClear);
	}
	
	private void showTable() {
		try {
			listAnggota.clear();
			listAnggota = dataAnggotaDAO.getAllDataAnggota();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, 
					"Gagal Memuat Tabel", "Error", JOptionPane.ERROR_MESSAGE);
		}
		tableModel = new DataAnggotaTableModel(listAnggota);
		table.setModel(tableModel);
	}
	
	private void clearForm() {
		noAnggotaField.setText("");
		namaField.setText("");
		wajibField.setText("");
		pokokField.setText("");
		saldoField.setText("");
	}
}
