package crowdFunding;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class RegisterPage extends JFrame
{

	JTextField txtUnm=new JTextField();
	JTextField txtEmail=new JTextField();
	JPasswordField txtPwd=new JPasswordField();
	JPasswordField txtconPwd=new JPasswordField();
	JComboBox cmbCountry;
	JTextField txtAge=new JTextField();
	int userId;
	
	DbHandler objDH=new DbHandler();
	
	Container c=null;
	
	//default constructor
	public RegisterPage()
	{
		c=getContentPane(); //top most visible layer returned
		c.setLayout(null);
		
		ImageIcon icn=new ImageIcon("images/cfund.v1.png");
		JLabel lblbg=new JLabel(icn);
		lblbg.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		c.add(lblbg);
		
		//scrollpane
		JScrollPane scroll = new JScrollPane( lblbg );
		setContentPane( scroll );		
		
		//panel 
		JPanel pnl=new JPanel();
		pnl.setLayout(null);
		pnl.setBounds(450, 70, 420, 820);
		lblbg.add(pnl);
        pnl.setBorder(BorderFactory.createLineBorder(Color.black));
        
        String strRegLbl[]={"Name","E-Mail","Password","Re-Enter Password","Country"};
		int[] yposLbl={90,195,295,390,485};
		
		//registration labels-"Name","E-Mail","Password","Re-Enter Password"
		for(int i=0;i<5;i++)
		{
			JLabel lblObj=new JLabel(strRegLbl[i]);
			lblObj.setBounds(15, yposLbl[i], 392, 25);
			pnl.add(lblObj);
			lblObj.setFont(lblObj.getFont().deriveFont(20.0f));
		}
		
		//register title
		JLabel lbltitle=new JLabel("REGISTRATION PAGE");
		lbltitle.setBounds(55, 20, 400, 40);
		pnl.add( lbltitle);
		lbltitle.setFont(lbltitle.getFont().deriveFont(30.0f));
		
		txtUnm.setBounds(15,125,392,40);
		txtUnm.setFont(txtUnm.getFont().deriveFont(20.0f));
		pnl.add(txtUnm);
		
		txtEmail.setBounds(15,225,392,40);
		txtEmail.setFont(txtEmail.getFont().deriveFont(20.0f));
		pnl.add(txtEmail);
		
		txtPwd.setBounds(15, 325, 392, 40);
		txtPwd.setFont(txtPwd.getFont().deriveFont(20.0f));
		pnl.add(txtPwd);

		txtconPwd.setBounds(15,420,392,40);
		txtconPwd.setFont(txtconPwd.getFont().deriveFont(20.0f));
		pnl.add(txtconPwd);
		
		//selecting countries

		cmbCountry=new JComboBox(); //combobox to provide choice of user
		cmbCountry.setFont(cmbCountry.getFont().deriveFont(16.0f));
		cmbCountry.setBounds(15, 520, 392, 40);
		pnl.add(cmbCountry);
		

		Vector <String> vecCname =objDH.getAllCountries();
		for(String str:vecCname)
		{
			cmbCountry.addItem(str);
		}
		
		//asking for newsletter
		Checkbox chbreme=new Checkbox("DISCOVER NEW PROJECTS WITH OUR WEEKLY NEWSLETTER");
		chbreme.setBounds(15, 590, 400, 30);
		pnl.add(chbreme);
		chbreme.addItemListener(new ItemListener()  
		{ 
			public void itemStateChanged(ItemEvent arg0)
			{
				objDH.provideNewsletter();
	     	} 
		});	
		
		//age
		JLabel lblAge=new JLabel("Age");
		lblAge.setBounds(15,630,392,40);
		pnl.add(lblAge);
		lblAge.setFont(lblAge.getFont().deriveFont(20.0f));
		
		txtAge.setBounds(15,670,392,40);
		txtAge.setFont(txtAge.getFont().deriveFont(20.0f));
		pnl.add(txtAge);
		
		
		//register button
		ImageIcon icnBtn=new ImageIcon("images/REGISTER.jpg");
		JButton btnRegister=new JButton(icnBtn);
		btnRegister.setBounds(15, 740, 392, 40);
		pnl.add(btnRegister);
		//pnl.setLayout(null);
		
		btnRegister.addActionListener(new ActionListener()
		{
			
			    public void actionPerformed(ActionEvent arg0) 
			  {
			    	userId=objDH.getMaxId("id","user_account" );
			    	userId+=1;
			    	
			    String strId=String.valueOf(userId);
				String strUnm=txtUnm.getText();
				String strPwd=String.valueOf(txtPwd.getPassword());
				String strEmail=txtEmail.getText();
				int intCountry_id= cmbCountry.getSelectedIndex();
				int intAge=Integer.parseInt(txtAge.getText());
				String strNewsletter=String.valueOf(chbreme.getState());
				int intProj_sup=0;
				int intTot_amount=0;
				
				intCountry_id+=1;
				objDH.insertUserAccount(strId,strUnm, strPwd, strEmail,intProj_sup,intTot_amount, intCountry_id,strNewsletter,intAge);
				
				UserProfilePage ufrm=new UserProfilePage(strUnm);//user's profile page opens up
			}
		});
		
		setTitle("REGISTRATION PAGE");
		setVisible (true);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}


}
