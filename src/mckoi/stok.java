package mckoi;

import java.awt.EventQueue;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class stok extends JInternalFrame {
	private JTable table;
	private JTextField txtNProduk;
	private DefaultTableModel tabelModel;
	private JTextField txtidP;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					stok frame = new stok();
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
	public stok() {
		setFrameIcon(new ImageIcon("D:\\PBO_UAS_1\\mckoi\\images\\png_tambahan\\Misc-New-Database-icon.png"));
		setBounds(100, 100, 450, 428);
		this.setTitle("Stok");
		getContentPane().setLayout(null);
		
		final JSpinner st = new JSpinner();
		st.setBounds(113, 352, 60, 20);
		getContentPane().add(st);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		scrollPane.setBounds(10, 11, 600, 250);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int a = table.getSelectedRow();
		        
		        if(a == -1)
		        {
		            return;
		        }
		        
		        String idp = (String) tabelModel.getValueAt(a, 0);
		        txtidP.setText(idp);
		        String nama= (String) tabelModel.getValueAt(a, 1);
		        txtNProduk.setText(nama);
		        int stok= (Integer) tabelModel.getValueAt(a, 2);
		        st.setValue(stok);
			}
		});
		scrollPane.setViewportView(table);
		tabelModel = new DefaultTableModel();
        tabelModel.addColumn("ID Produk");
        tabelModel.addColumn("Nama Produk");
        tabelModel.addColumn("Stok Produk");
		table.setModel(tabelModel);	
        tampilTabel();
		
		txtNProduk = new JTextField();
		txtNProduk.setBounds(113, 324, 349, 20);
		getContentPane().add(txtNProduk);
		txtNProduk.setColumns(10);
		
		
		JButton btnUbah = new JButton("Ubah Maintance Stok");
		btnUbah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
			        Connection konek = connect.getCon();
			        String query = "UPDATE stok SET  stok_produk = ? WHERE id = ?";
			        PreparedStatement prepare = konek.prepareStatement(query);
			       
			        prepare.setInt(1, (Integer) st.getValue()); //menambah stok
			        prepare.setString(2, txtidP.getText());
			        
			        prepare.executeUpdate();
			        JOptionPane.showMessageDialog(null, "Data berhasil diubah");
			        prepare.close();
			        }
			        
			        catch(Exception ex)
			        {
			            JOptionPane.showMessageDialog(null, "Data gagal diubah");
			            System.out.println(ex);
			        }
			        finally
			        {
			            tampilTabel();
			           //txtIDSup.setEnabled(true);
			            //refresh();            
			        }
			}
		});
		btnUbah.setBounds(193, 351, 176, 23);
		getContentPane().add(btnUbah);
		
		JLabel lblNamaProduk = new JLabel("Nama Produk :");
		lblNamaProduk.setBounds(10, 327, 104, 14);
		getContentPane().add(lblNamaProduk);
		
		JLabel lblStokProduk = new JLabel("Stok Produk :");
		lblStokProduk.setBounds(10, 355, 104, 14);
		getContentPane().add(lblStokProduk);
	
		
		JLabel lblIdProduk = new JLabel("ID Produk :");
		lblIdProduk.setBounds(10, 296, 104, 14);
		getContentPane().add(lblIdProduk);
		
		txtidP = new JTextField();
		txtidP.setColumns(10);
		txtidP.setBounds(113, 293, 349, 20);
		getContentPane().add(txtidP);

	}
	public void tampilTabel()
    {
        tabelModel.getDataVector().removeAllElements();
        tabelModel.fireTableDataChanged();
        try
        {
            Connection konek = connect.getCon();
            Statement state = konek.createStatement();
            String query = "SELECT p.id, p.Nama, s.stok_produk FROM stok s, produk p where s.id=p.id"; //mengambil id product
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[3];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getInt(3);
                
                tabelModel.addRow(obj);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
}
