package crowdFunding;

import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutUs extends JFrame
{
	Container c=null; 
	public AboutUs()
	{
		c=getContentPane(); //top most visible layer returned
		c.setLayout(null);
		//background
		JPanel backGroundpnl=new JPanel();
		backGroundpnl.setLayout(null);
		backGroundpnl.setBackground(Color.WHITE);
		backGroundpnl.setBounds(0, 0, 1500, 1500);
		c.add(backGroundpnl);
		
	    //////panel=topmost
		JPanel tpnl=new JPanel();
		tpnl.setLayout(null);
		tpnl.setBackground(Color.BLACK);
		tpnl.setBounds(0, 0, 1500, 60);
		backGroundpnl.add(tpnl);
		
		ImageIcon icnLogo=new ImageIcon("images/KickStarter-logo.jpg");
		JLabel lblicnLogo=new JLabel(icnLogo);
		lblicnLogo.setBounds(300, 0, 650, 60);
		tpnl.add(lblicnLogo);	
		
		//design=green panel
		JPanel gpnl=new JPanel();
		gpnl.setBackground(Color.GREEN);
		gpnl.setBounds(0,62, 1500,7);
		backGroundpnl.add(gpnl);
		
		JLabel lblpayTxt=new JLabel("<html><h1>WHAT IS KICKSTARTER?</h1></br></br><p>Kickstater is a crowdfunding platform.Kickstarter is an enormous global community built around creativity </br>and creative projects. Over 10 million people, from every continent on earth, have backed a Kickstarter project."

+" Most come from amazing creative people you probably haven’t heard of — from Grandma Pearl to indie filmmakers to the band down the street."

+"Every artist, filmmaker, designer, developer, and creator on Kickstarter has complete creative control over their work — and the opportunity to share it with a vibrant community of backers.</p></html>");
		lblpayTxt.setFont(lblpayTxt.getFont().deriveFont(20.0f));
		lblpayTxt.setBounds(30,5, 600,600);
		backGroundpnl.add(lblpayTxt);
		

		setTitle("ABOUT US");
		setVisible (true);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
