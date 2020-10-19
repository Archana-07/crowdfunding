package crowdFunding;

import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPage extends JFrame
{
	JTextField txtName=new JTextField();
	JPasswordField txtPwd=new JPasswordField();
	
	DbHandler objDH=new DbHandler();
	
	Container c=null;
	//default constructor
	
	public LoginPage()
	{
		c=getContentPane(); //top most visible layer returned
		c.setLayout(null); // default layout is removed thus set to null 
		
		ImageIcon icn=new ImageIcon("images/cfund.v2.png");
		JLabel lblbg=new JLabel(icn);
		lblbg.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		c.add(lblbg);
		
		//panel 
		JPanel pnl=new JPanel();
		pnl.setLayout(null);
		pnl.setBounds(450, 80, 420, 560);
		pnl.setBorder(BorderFactory.createLineBorder(Color.black));
		lblbg.add(pnl);
		
		//login title
		JLabel lbltitle=new JLabel("LOGIN HOME PAGE");
		lbltitle.setBounds(55, 30, 400, 40);
		lbltitle.setFont(lbltitle.getFont().deriveFont(30.0f));
		pnl.add( lbltitle);
		
		//Email
		JLabel lblUnm=new JLabel("Username");
		lblUnm.setBounds(15, 120, 392, 20);
		pnl.add(lblUnm);
		lblUnm.setFont(lblUnm.getFont().deriveFont(20.0f));
		
		txtName.setBounds(15,150,392,40);
		txtName.setFont(txtName.getFont().deriveFont(20.0f));
		pnl.add(txtName);
		
		//password
		JLabel lblPwd=new JLabel("Password");
		lblPwd.setBounds(15,220,392,20);
		pnl.add(lblPwd);
		lblPwd.setFont(lblPwd.getFont().deriveFont(20.0f));
		
		txtPwd.setBounds(15,250,392,40);
		txtPwd.setFont(txtPwd.getFont().deriveFont(20.0f));
		pnl.add(txtPwd);
		
		//remember me
		JCheckBox chbreme=new JCheckBox("Remember me");
		chbreme.setBounds(15, 320, 392, 20);
		pnl.add(chbreme);
		
		ImageIcon icnBtn=new ImageIcon("images/button1.jpg");
		JButton btnLogin=new JButton(icnBtn);

	    btnLogin.setBounds(15, 350, 392, 40);
		pnl.add(btnLogin);
		
		btnLogin.addActionListener(new ActionListener()  // event handling done if the user clicks login button
		{
			    public void actionPerformed(ActionEvent arg0) 
			  {
				String strUnm=String.valueOf(txtName.getText());
				String strPwd=String.valueOf(txtPwd.getPassword());
				boolean res=objDH.isValidUser(strUnm,strPwd);
				if(res==true)
				{
					dispose();//closes the previous window properly
					UserProfilePage userFrm=new UserProfilePage(strUnm); // after logging in user's profile page opens up 
				
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Invalid Username Or Password");  
					//invalid user message is displayed if user is invalid
				}
			}
		});
		
		//forget password

		JLabel lblfgtpwd=new JLabel("Forgot your password?");
		lblfgtpwd.setBounds(15, 400, 392, 20);
		pnl.add(lblfgtpwd);
		lblfgtpwd.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    { 
		    	//code for saving username and password in database
		    }  
		});
		
		//new user //register

		JLabel lblnewUser=new JLabel("NEW USER? REGISTER HERE");
		lblnewUser.setBounds(15, 450, 392, 20);
		pnl.add(lblnewUser);
		lblnewUser.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    { 
		    	dispose();
		    	 RegisterPage registerpage=new RegisterPage(); // register page opens up and user can register
		    }  
		});
		
		setTitle("USER LOGIN");   // title of the top most visible layer ie window
		setVisible (true);       // its visibility is turned on
		setSize(Toolkit.getDefaultToolkit().getScreenSize()); // this enables the window to appear on the window screen.
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
}
