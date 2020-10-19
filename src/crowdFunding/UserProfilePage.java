package crowdFunding;

import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class UserProfilePage extends JFrame
{
	Container c=null;
	int i;
	DbHandler objDH=new DbHandler();

	
	public UserProfilePage(String strUnm)
	{
	
		
		c=getContentPane(); //top most visible layer returned
		c.setLayout(null);
		String strId=objDH.getUnm_IdFromUser_Account(strUnm);
		
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
		//inner contents
		
		String strTopPnlLbl1[]={"Explore","Start a project","Logout"};
		int[] xposLbl={10,110,1250};
		for(int i=0;i<3;i++)
		{
			JLabel lblTopMenu=new JLabel(strTopPnlLbl1[i]);
			lblTopMenu.setFont(lblTopMenu.getFont().deriveFont(20.0f));
			lblTopMenu.setForeground(Color.WHITE);
			lblTopMenu.setBounds(xposLbl[i], 15, 140,40);
			tpnl.add(lblTopMenu);
			
		
		//switch-performing different tasks for user
		
		lblTopMenu.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseEntered(MouseEvent e)  
		    { 
		    	lblTopMenu.setForeground(Color.GRAY);
		    }  
		    public void mouseExited(MouseEvent e)  
		    { 
		    	lblTopMenu.setForeground(Color.WHITE);
		    } 
		    public void mouseClicked(MouseEvent e)
		    {
		    	switch(lblTopMenu.getText())
		    	{
		    	case "Explore":
		    		ExplorePage exp=new ExplorePage(strUnm);
		    		break;
		    	case "Start a project":
		    		NewProject np=new NewProject(strUnm);
		    		break;
//		    	case "For you":
//		    		break;
//		    	case " ":
//		    		
		    	case "Logout":
		    		dispose();
		    		JOptionPane.showMessageDialog(null, "You have successfully logged out from Kickstarter");
		    		WelcomePage wlcmfrm=new WelcomePage();
		    		break;
		    	}
		    }
	});
		}	
		
		ImageIcon icnLogo=new ImageIcon("images/KickStarter-logo.jpg");
		JLabel lblicnLogo=new JLabel(icnLogo);
		lblicnLogo.setBounds(300, 0, 650, 60);
		tpnl.add(lblicnLogo);	
		
		//design=green panel
		JPanel gpnl=new JPanel();
		gpnl.setBackground(Color.GREEN);
		gpnl.setBounds(0,62, 1500,7);
		backGroundpnl.add(gpnl);
		
		//panel=leftmost//user accessibility
	    //////panel=topmost
		JPanel menuPnl=new JPanel();
		menuPnl.setLayout(null);
		menuPnl.setBackground(Color.BLACK);
		menuPnl.setBounds(0, 70, 300, 700);
		backGroundpnl.add(menuPnl);
		
		//inner contents of side panel
		
		String strSidePnlLbl[]={" My Stuff  ","Backed projects","My Projects","    Settings","Account","Edit profile"};
		int[] yposSideLbl={110,160,210,280,330,380};
		for(i=0;i<6;i++)
		{
			JLabel lblLeftTxt=new JLabel(strSidePnlLbl[i]);
			lblLeftTxt.setFont(lblLeftTxt.getFont().deriveFont(20.0f));
			lblLeftTxt.setForeground(Color.WHITE);
			lblLeftTxt.setBounds(20,yposSideLbl[i], 250,40);
			
			if(i==0||i==3)
			{
				
				lblLeftTxt.setOpaque(true);
				lblLeftTxt.setBackground(Color.WHITE);
				lblLeftTxt.setBounds(5,yposSideLbl[i], 290,40);
				lblLeftTxt.setForeground(Color.BLACK);
				
			}
			else
			{
				lblLeftTxt.addMouseListener(new MouseAdapter()  
				{  
				    public void mouseEntered(MouseEvent e)  
				    { 
				    	lblLeftTxt.setForeground(Color.GRAY);
				    }  
				    public void mouseExited(MouseEvent e)  
				    { 
				    	lblLeftTxt.setForeground(Color.WHITE);
				    } 
				    public void mouseClicked(MouseEvent e)
				    {
				    	switch(lblLeftTxt.getText())
				    	{
				    	case "Backed projects":
				    		break;
				    	case "My projects":
				    		
				    		objDH.myProjects("7");
				    		break;
				    	case "Account":
				    		AccountPage acObj=new AccountPage(strId);
				    		break;
				    	case "Edit profile":
				    		 acObj=new AccountPage(strId);
				    		break;
				    		
				    	}
				    }
				});
			}
			menuPnl.add(lblLeftTxt);
		}
		
		//displaying user is logged in username
		JLabel lblUserTxt=new JLabel("You are logged in as "+strUnm);
		lblUserTxt.setForeground(Color.WHITE);
		lblUserTxt.setFont(lblUserTxt.getFont().deriveFont(15.0f));
		lblUserTxt.setBounds(20,500, 300,40);
		menuPnl.add(lblUserTxt);
		
		//displaying the details of user like funded projects
//		
		UserDetailsForUpdate userProjSup=objDH.getAllUserDetails(strId);
		String temp=String.valueOf("Projects supported : "+userProjSup.intProj_sup);
		JLabel lblProjSupported=new JLabel(temp);
		lblProjSupported.setFont(lblProjSupported.getFont().deriveFont(20.0f));
		lblProjSupported.setBounds(350,150, 400,60);
		backGroundpnl.add(lblProjSupported);
		
		String temp1=String.valueOf("Total amount supported : "+userProjSup.intTot_amount);
		JLabel userProjAmtSupA=new JLabel(temp1);
		userProjAmtSupA.setFont(userProjAmtSupA.getFont().deriveFont(20.0f));
		userProjAmtSupA.setBounds(350,200, 400,60);
		backGroundpnl.add(userProjAmtSupA);
		
		objDH.myProjects(strId);
		
		JButton JbtnDetailsOfProjSupp=new JButton("DETAILS");
		JbtnDetailsOfProjSupp.setBounds(350,280, 150,30);
		backGroundpnl.add(JbtnDetailsOfProjSupp);
		
		JbtnDetailsOfProjSupp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				JFrame UserPageDetails=new JFrame();
				UserPageDetails.setLayout(null);
				UserPageDetails.setBounds(100, 100, 600, 700);				
				LinkedList<ProjByCatgegory> objListProjByCat = objDH.getAllProjectsByUserIdSuppByUser(strId);
				
				int ypos=50;
				for (ProjByCatgegory pbg : objListProjByCat) 						
				{
					String projNames=String.valueOf(pbg.project_name);
				
					JLabel lblprojOfUser=new JLabel(projNames);
					lblprojOfUser.setFont(lblprojOfUser.getFont().deriveFont(15.0f));
					lblprojOfUser.setBounds(10,ypos,210,60);
					UserPageDetails.add(lblprojOfUser);
					
					JLabel lblprojPledgedByUser=new JLabel("Pledged : "+pbg.pledged);
					lblprojPledgedByUser.setFont(lblprojPledgedByUser.getFont().deriveFont(15.0f));
					lblprojPledgedByUser.setBounds(220,ypos, 350,60);
					UserPageDetails.add(lblprojPledgedByUser);
					
					JLabel lblprojGoal=new JLabel("Goal : "+pbg.goal);
					lblprojGoal.setFont(lblprojGoal.getFont().deriveFont(15.0f));
					lblprojGoal.setBounds(400,ypos, 500,60);
					UserPageDetails.add(lblprojGoal);
					
					ypos+=50;
				
				}

				UserPageDetails.setTitle("YOUR DETAILS");
				UserPageDetails.setVisible (true);
				UserPageDetails.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		
		String tempSubb=String.valueOf("Projects submitted : "+objDH.myProjCounter);
		JLabel lblProjSubmittedA=new JLabel(tempSubb);
		lblProjSubmittedA.setFont(lblProjSubmittedA.getFont().deriveFont(20.0f));
		lblProjSubmittedA.setBounds(350,350, 300,60);
		backGroundpnl.add(lblProjSubmittedA);
		
		
		JButton JbtnDetailsOfProjSub=new JButton("DETAILS");
		JbtnDetailsOfProjSub.setBounds(350,450, 150,30);
		backGroundpnl.add(JbtnDetailsOfProjSub);
		
		JbtnDetailsOfProjSub.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				JFrame UserPageDetails=new JFrame();
				UserPageDetails.setLayout(null);
				UserPageDetails.setBounds(100, 100, 500, 700);
				
				LinkedList<ProjByCatgegory> objListProjByCat = objDH.myProjects(strId);
				
				int ypos=50;
				for (ProjByCatgegory pbg : objListProjByCat) 						
				{
					String projNames=String.valueOf(pbg.project_name);
				
					JLabel lblprojOfUser=new JLabel(projNames);
					lblprojOfUser.setFont(lblprojOfUser.getFont().deriveFont(15.0f));
					lblprojOfUser.setBounds(10,ypos,250,60);
					UserPageDetails.add(lblprojOfUser);
					
					JLabel lblprojPledgedOfUser=new JLabel("Pledged : "+pbg.pledged);
					lblprojPledgedOfUser.setFont(lblprojPledgedOfUser.getFont().deriveFont(15.0f));
					lblprojPledgedOfUser.setBounds(260,ypos, 500,60);
					UserPageDetails.add(lblprojPledgedOfUser);
					
					ypos+=50;
				
				}
				UserPageDetails.setTitle("YOUR DETAILS");
				UserPageDetails.setVisible (true);
				//setSize(Toolkit.getDefaultToolkit().getScreenSize());
				UserPageDetails.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
	
		});
		
		JButton fund=new JButton("FUND A NEW PROJECT");
		fund.setBounds(800,300, 350,60);
		backGroundpnl.add(fund);
		
		fund.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						ExplorePage exp=new ExplorePage(strUnm);
					}
			
				});
		JButton JbtnNewProj=new JButton("BEGIN A CAMPAIGN");
		JbtnNewProj.setBounds(800,400, 350,60);
		backGroundpnl.add(JbtnNewProj);
		JbtnNewProj.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				NewProject newproj=new NewProject(strUnm);
			}
	
		});
		
		setTitle("USER PROFILE");
		setVisible (true);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
		

}
