package user;

import java.awt.EventQueue;



import javax.swing.JFrame;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class User {

	private JFrame frmCurdswingmysql;
	private JTextField txtid;
	private JTextField txtname;
	private JTextField txtage;
	private JTextField txtcity;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User window = new User();
					window.frmCurdswingmysql.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public User() {
		initialize();
		Connection();
		loadData();
	}
	
	//Database connection
	
	Connection con=null;
	PreparedStatement pst;
	ResultSet rs;
	
	public void Connection() {
		try {
		 String url="jdbc:mysql://localhost:3306/jdbs";
		 String username="root";
		 String password="Jey1137";
		  con=DriverManager.getConnection(url,username,password);
		}
		 catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	
	public void clear() {
		txtid.setText("");
		txtname.setText("");
		txtage.setText("");
		txtcity.setText("");
		txtname.requestFocus();
	}
	
	//load table
	public void loadData() {
		try {
			pst=con.prepareStatement("select * from users");
			rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCurdswingmysql = new JFrame();
		frmCurdswingmysql.getContentPane().setBackground(new Color(238, 238, 238));
		frmCurdswingmysql.setTitle("CURD_SWING_Mysql");
		frmCurdswingmysql.getContentPane().setFont(new Font("Dialog", Font.PLAIN, 14));
		frmCurdswingmysql.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User management system");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel.setBounds(12, 30, 222, 34);
		frmCurdswingmysql.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(12, 61, 240, 191);
		frmCurdswingmysql.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblId = new JLabel("id");
		lblId.setFont(new Font("Dialog", Font.BOLD, 14));
		lblId.setBounds(12, 12, 58, 17);
		panel.add(lblId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Dialog", Font.BOLD, 14));
		lblName.setBounds(12, 48, 58, 17);
		panel.add(lblName);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAge.setBounds(12, 82, 58, 17);
		panel.add(lblAge);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCity.setBounds(12, 115, 58, 17);
		panel.add(lblCity);
		
		txtid = new JTextField();
		txtid.setEditable(false);
		txtid.setBounds(86, 10, 114, 21);
		panel.add(txtid);
		txtid.setColumns(10);
		
		txtname = new JTextField();
		txtname.setColumns(10);
		txtname.setBounds(86, 46, 114, 21);
		panel.add(txtname);
		
		txtage = new JTextField();
		txtage.setColumns(10);
		txtage.setBounds(86, 80, 114, 21);
		panel.add(txtage);
		
		txtcity = new JTextField();
		txtcity.setColumns(10);
		txtcity.setBounds(86, 113, 114, 21);
		panel.add(txtcity);
		
		JButton btnSave = new JButton("save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=txtid.getText();
				String name=txtname.getText();
				String age=txtage.getText();
				String city=txtcity.getText();
				
				if(name==null||name.isEmpty()||name.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Pleace Enter a name");
					txtname.requestFocus();
					return;
				}
				if(age==null||age.isEmpty()||age.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Pleace Enter a age");
					txtage.requestFocus();
					return;
				}
				if(city==null||city.isEmpty()||city.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Pleace Enter a name");
					txtcity.requestFocus();
					return;
				}
				
				if(txtid.getText().isEmpty()) {
					try {
						String sql="insert into users (NAME,AGE,CITY) values (?,?,?)";
						pst=con.prepareStatement(sql);
						pst.setString(1, name);
						pst.setString(2, age);
						pst.setString(3, city);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Data insert Success");
						clear();
						loadData();
					} catch(Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnSave.setBounds(12, 144, 69, 27);
		panel.add(btnSave);
		
		JButton btnupdate = new JButton("Update");
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=txtid.getText();
				String name=txtname.getText();
				String age=txtage.getText();
				String city=txtcity.getText();
				
				if(name==null||name.isEmpty()||name.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Pleace Enter a name");
					txtname.requestFocus();
					return;
				}
				if(age==null||age.isEmpty()||age.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Pleace Enter a age");
					txtage.requestFocus();
					return;
				}
				if(city==null||city.isEmpty()||city.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Pleace Enter a name");
					txtcity.requestFocus();
					return;
				}
				
				if(!txtid.getText().isEmpty()) {
					try {
						String sql="update users set NAME=?,AGE=?,CITY=? where ID=?";
						pst=con.prepareStatement(sql);
						pst.setString(1, name);
						pst.setString(2, age);
						pst.setString(3, city);
						pst.setString(4, id);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Data update Success");
						clear();
						loadData();
					} catch(Exception e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnupdate.setBounds(86, 144, 69, 27);
		panel.add(btnupdate);
		
		//Delete
		JButton btndelete = new JButton("Delete");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=txtid.getText();
				if(!txtid.getText().isEmpty()) {
					int result = JOptionPane.showConfirmDialog(null,"Sure? You want to Delete","Delete",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					
					if(result==JOptionPane.YES_OPTION) {
						try {
							String sql="delete from users where ID=?";
							
							pst=con.prepareStatement(sql);
							pst.setString(1, id);
							pst.executeUpdate();
							JOptionPane.showMessageDialog(null,"Data Deleted Success");
							clear();
							loadData();
						}catch(Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btndelete.setBounds(159, 144, 69, 27);
		panel.add(btndelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(264, 61, 293, 191);
		frmCurdswingmysql.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int index=table.getSelectedRow();
				TableModel model=table.getModel();
				
				txtid.setText(model.getValueAt(index, 0).toString());
				txtname.setText(model.getValueAt(index, 1).toString());
				txtage.setText(model.getValueAt(index, 2).toString());
				txtcity.setText(model.getValueAt(index, 3).toString());
			}
		});
		table.setFont(new Font("Dialog", Font.PLAIN, 14));
		table.setRowHeight(30);
		scrollPane.setViewportView(table);
		
		
	}
}
