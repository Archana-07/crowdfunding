package crowdFunding;

import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdminAction extends JFrame
{
	Container c=null; 
	public AdminAction()
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
		
		JLabel lblTxt=new JLabel("WELCOME ADMIN");
		lblTxt.setFont(lblTxt.getFont().deriveFont(25.0f));
		lblTxt.setBounds(100,100,250, 50);
		backGroundpnl.add(lblTxt);
		
		JButton btnRateProject=new JButton(" Review and rate projects ");
		btnRateProject.setBounds(100,300,250, 50);
		backGroundpnl.add(btnRateProject);
		btnRateProject.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				
			}
		});
		
		JButton btnDeleteProject=new JButton(" Delete a project ");
		btnDeleteProject.setBounds(100,400,250, 50);
		backGroundpnl.add(btnDeleteProject);
		btnRateProject.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				
			}
		});

		setTitle("ABOUT US");
		setVisible (true);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
