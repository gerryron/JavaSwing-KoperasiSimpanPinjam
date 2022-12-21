package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class HomeView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2240813315457562745L;
	private JFrame frame;
	private String kodeKasir;
	private String namaKasir;

	/**
	 * Launch the application.
	 */
	public static void main(String kodeKasir, String namaKasir) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeView window = new HomeView(kodeKasir, namaKasir);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomeView(String kodeKasir, String namaKasir) {
		this.kodeKasir = kodeKasir;
		this.namaKasir = namaKasir;
        frame = new JFrame();
        initialize();
	}
	
	private void initialize() {
		frame.setTitle("Koperasi Simpan Pinjam");
        frame.setBounds(100, 100, 562, 333);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE, dd MMMM yyyy        "
                + "hh:mm:ss", new Locale("id", "ID"));
        String currDate = dateFormat.format(new Date());
        JLabel lblNewLabel_1 = new JLabel(currDate);
        lblNewLabel_1.setForeground(Color.RED);
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1.setBounds(308, 233, 228, 14);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel = new JLabel("WELCOME, " + namaKasir.toUpperCase());
        lblNewLabel.setForeground(Color.RED);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(10, 11, 526, 61);
        panel.add(lblNewLabel);
        
        JButton btnNewButton = new JButton("Data Pinjam");
        btnNewButton.setBackground(Color.CYAN);
        btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnNewButton.setBounds(285, 83, 251, 149);
        btnNewButton.setFocusPainted(false);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DataPinjamView.main(kodeKasir);
            }
        });
        panel.add(btnNewButton);
        
        JButton btnDataAnggota = new JButton("Data Anggota");
        btnDataAnggota.setBackground(new Color(192, 107, 175));
        btnDataAnggota.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnDataAnggota.setBounds(10, 83, 251, 149);
        btnDataAnggota.setFocusPainted(false);
        btnDataAnggota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DataAnggotaView.main();
            }
        });
        panel.add(btnDataAnggota);
	}
}
