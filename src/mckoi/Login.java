package mckoi;
import java.sql.*;
import javax.swing.JOptionPane;
import java.awt.EventQueue;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JInternalFrame {
	private JButton btnLogin;
	private JTextField txtUser;
	private JTextField txtPass;
	private JLabel lblWallMDI;
	private JLabel lblIcon;
	public static  String nama;
	public static javax.swing.JInternalFrame f;
	public static javax.swing.JInternalFrame f2;

	/**
	 * Create the frame.
	 */
	public Login() {
		super("Login");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 692, 415);
		getContentPane().setLayout(null);
		setVisible(true);
		f=new admin(); //memanggil form admin
		f2=new kasir(); // memanggil form kasir
		
		JLabel lblNo = new JLabel("Username : ");
		lblNo.setForeground(new Color(225,225,225));
		lblNo.setBounds(6, 150, 100, 15);
		getContentPane().add(lblNo);
		
		JLabel lblNama = new JLabel("Password : ");
		lblNama.setForeground(new Color(225,225,225));
		lblNama.setBounds(6, 196, 100, 15);
		getContentPane().add(lblNama);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
			        Connection Con=connect.getCon();
			        String sql = "Select * from login where username='"+txtUser.getText()+"' and password='" + txtPass.getText() + "'";
			        Statement st=Con.createStatement();
			        ResultSet rs = st.executeQuery(sql);
			        if (rs.next()){
			        if (rs.getString(3).equalsIgnoreCase("admin"))
			        {
			            JOptionPane.showMessageDialog(null, "Login Admin Berhasil");
			            utama.tampil(f); 
			            nama=txtUser.getText(); //membedakan user login yang masuk
			            //this.dispose();
			        }else{
			        	JOptionPane.showMessageDialog(null, "Login Kasir Berhasil");
			        	nama=txtUser.getText();
			            utama.tampil(f2);  
			        }
			        }else{
			        JOptionPane.showMessageDialog(null, "Login gagal");
			        }
			        }catch (Exception ex){
			        JOptionPane.showMessageDialog(null, "Login gagal (koneksi)");
			        }
			}
		});
		btnLogin.setBounds(63, 276, 111, 36);
		getContentPane().add(btnLogin);
		
		txtUser = new JTextField();
		txtUser.setBounds(100, 150, 209, 27);
		getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		txtPass = new JTextField();
		txtPass.setBounds(100, 190, 323, 27);
		getContentPane().add(txtPass);
		txtPass.setColumns(10);
		
		lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon("D:/PBO_UAS_1/mckoi/images/png_tambahan/Network-Connection-Control-Panel-icon.png"));
		lblIcon.setBounds(10, 10, 160, 129);
		getContentPane().add(lblIcon);
		
		lblWallMDI = new JLabel("");
		lblWallMDI.setIcon(new ImageIcon("D:/PBO_UAS_1/mckoi/images/nowallpaper-585747.jpeg"));
		lblWallMDI.setBounds(0, 0, 684, 388);
		getContentPane().add(lblWallMDI);

	}

}
