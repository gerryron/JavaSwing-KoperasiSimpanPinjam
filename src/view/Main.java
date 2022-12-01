package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

/**
 *
 * @author NAKES
 */
public class Main {

    private JFrame frame;
    private String kodeKasir;

    /**
     * Launch the application.
     */
    public static void main(String kodeKasir) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main window = new Main(kodeKasir);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Main(String kodeKasir) {
    	this.kodeKasir = kodeKasir;
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

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu mnNewMenu = new JMenu("Data");
        menuBar.add(mnNewMenu);

        JMenuItem mntmNewMenuItem = new JMenuItem("Data Anggota");
        mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DataAnggotaView.main();
            }
        });
        mnNewMenu.add(mntmNewMenuItem);

        JMenuItem mntmNewMenuItem2 = new JMenuItem("Data Pinjaman");
        mntmNewMenuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DataPinjamView.main(kodeKasir);
            }
        });
        mnNewMenu.add(mntmNewMenuItem2);
    }
}
