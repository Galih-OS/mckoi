package mckoi;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Dimension;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

public class utama extends JFrame {
	private JButton btnLogin;
	private JMenuItem mi = new JMenuItem("Login");
	private JPanel contentPane;
	
	public static JDesktopPane desktop;
	/**
	 * Launch the application.
	 */
	public utama() {
		super("Form Utama");
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\PBO_UAS_1\\mckoi\\images\\png_login\\c.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,800,600);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		desktop = new JDesktopPane();
		desktop.setBounds(0,0,800,600);
		getContentPane().add(desktop);
		desktop.add(mi);
		
		btnLogin = new JButton("Login");
		//btnSimpan.setIcon(new ImageIcon("/home/resa/resa/Aplikasi Java/SwingComponents/src/Gambar/Simpan.png"));
		btnLogin.setBounds(350,250,100,40);
		getContentPane().add(btnLogin);
		

		/*desktop.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent me){
				if(me.getButton() == 3) //klik kanan
				{
					pop.show(me.getComponent(),me.getX(),me.getY());
				}
			}
		});*/
		
		btnLogin.addActionListener(new ActionListener ()
		{
			@Override
			public void actionPerformed(ActionEvent act)
			{
				desktop.add(new Login());
				btnLogin.setVisible(false);
			}
		});
		
	}
	public static void tampil(javax.swing.JInternalFrame f){
		Dimension size=desktop.getSize();
		desktop.add(f);
		f.setVisible(true);
		f.setSize(size);
		f.setLocation(0,0);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					utama frame = new utama();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
}
