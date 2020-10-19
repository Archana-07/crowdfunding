package crowdFunding;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ExplorePage extends JFrame
{
	Container c=null;
	ProjByCatgegory[] pbg1;
	static int intProjStatushisId;
	static int projStatusId = 0;
	JFrame catgry;
	static DbHandler objDH=new DbHandler();
	static java.util.Date Dtutildt1;
	static java.sql.Date currentDate;
	
	public ExplorePage(String strUnm)
	{
		c=getContentPane(); //top most visible layer returned
		c.setLayout(null);
		intProjStatushisId=objDH.getMaxId("id","proj_status_history");
		
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
		
		
        String strLbl[]={"Collections","Projects we love","Nearly funded","Just Launched","Categories","Art","Comics","Craft","Dance","Design","Fashion","Film/Video","Food","Games","Journalism","Music","Photography","Publishing","Technology","Theater"};
		int yposLbl=50, yposLbl2=50,yposLbl3=150;
		 int xposLbl=550,xposLbl2=760;
		
		//
		for(int i=0;i<20;i++)
		{
			JLabel lblObj=new JLabel(strLbl[i]);
			backGroundpnl.add(lblObj);
			lblObj.setFont(lblObj.getFont().deriveFont(30.0f));
			lblObj.setBounds(20, yposLbl, 400, 30);
			if(i==0)
			{ 
				
				lblObj.setBounds(20, yposLbl, 600, 200);
				Font obj =new Font("Segoe Script",Font.BOLD,60);
				lblObj.setFont(obj);
				yposLbl+=100;
			}
			if(i==4)
			{
				lblObj.setBounds(xposLbl, yposLbl2, 500, 200);
				Font obj =new Font("Segoe Script",Font.BOLD,60);
				lblObj.setFont(obj);
				yposLbl2+=100;
			}
			 if(i>4 && i<15)
			 {
				 yposLbl2+=50;
				 lblObj.setBounds(xposLbl, yposLbl2, 200, 30);
				
			 }
			 yposLbl+=60;
			 
			 if(i>12)
			 {
				 yposLbl3+=50;
				 lblObj.setBounds(xposLbl2, yposLbl3, 300, 30);
				
			 }
			if(i>0 && i<4 ||i>4) 
			 {	
				 
				 lblObj.addMouseListener(new MouseAdapter()  
				{  
				    public void mouseEntered(MouseEvent e)  
				    { 
				    	lblObj.setForeground(Color.GRAY);
				    }  
				    public void mouseExited(MouseEvent e)  
				    { 
				    	lblObj.setForeground(Color.BLACK);
				    } 
				    public void mouseClicked(MouseEvent e)
				    {
				    	
				    	System.out.println(lblObj.getText());
						String categorySelected=lblObj.getText();
						try {
							AllprojDetailsByCategory(categorySelected,strUnm);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				    	
//				    	
				    }});
			}

		}
		
		//seperator panel
		JPanel seppnl=new JPanel();
		seppnl.setBackground(Color.BLACK);
		seppnl.setBounds(500,100, 2,470);
		backGroundpnl.add(seppnl);
		
		//design=green panel
		JPanel gBTMpnl=new JPanel();
		gBTMpnl.setBackground(Color.GREEN);
		gBTMpnl.setBounds(0,591, 1500,7);
		backGroundpnl.add(gBTMpnl);
		
		//Bottom most Panel
		JPanel BTMpnl=new JPanel();
		BTMpnl.setLayout(null);
		BTMpnl.setBackground(Color.BLACK);
		BTMpnl.setBounds(0, 600, 1500, 200);
		backGroundpnl.add(BTMpnl);
		
		JLabel quoteLbl=new JLabel("Bring your creativity to life with Kickstarter...");
		Font obj =new Font("Segoe Script",Font.BOLD,50);
		quoteLbl.setFont(obj);
		quoteLbl.setForeground(Color.WHITE);
		quoteLbl.setBounds(30,20,1300, 50);
		BTMpnl.add(quoteLbl);
		
		
		setTitle("EXPLORE");
		setVisible (true);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public static void AllprojDetailsByCategory(String categorySelected,String strUnm) throws ParseException
	{
		
		JFrame artFrm=new JFrame();
    	artFrm.setBounds(10,10,1000, 1000);
		artFrm.setLayout(null);
		
		JPanel backGroundpnl=new JPanel();
		backGroundpnl.setLayout(null);
		backGroundpnl.setBackground(Color.WHITE);
		backGroundpnl.setBounds(0, 0, 1500, 1500);
		artFrm.add(backGroundpnl);
		
	    //////panel=topmost
		
		JPanel tpnl=new JPanel();
		tpnl.setBackground(Color.BLACK);
		tpnl.setBounds(0, 0, 1500, 60);
		backGroundpnl.add(tpnl);
		//inner contents
		
		ImageIcon icnLogo=new ImageIcon("images/KickStarter-logo.jpg");
		JLabel lblicnLogo=new JLabel(icnLogo);
		lblicnLogo.setBounds(300, 0, 650, 60);
		tpnl.add(lblicnLogo);	
		
		//design=green panel
		JPanel gpnl=new JPanel();
		gpnl.setBackground(Color.GREEN);
		gpnl.setBounds(0,62, 1500,7);
		backGroundpnl.add(gpnl);
		
		
		//all projects
		JLabel lbl=new JLabel("ALL PROJECTS IN THIS CATEGORY");
		lbl.setFont(lbl.getFont().deriveFont(22.0f));
		lbl.setBounds(30,120, 400,40);
		backGroundpnl.add( lbl);
		JPanel [] pnlBox; JLabel [] lblproj_name,lblproj_des,lblproj_pledge,lblproj_Sdate,lblproj_Edate;
		int j;
		LinkedList<ProjByCatgegory> objListProjByCat = objDH.getAllProjectsByCatISD(categorySelected);

		int ypos=190,xpos=40;
		//lbl1 =new JLabel[10];
		pnlBox=new JPanel[20];
		
		 j=0;
		for (ProjByCatgegory pbg : objListProjByCat) 						
		{ 
			//ProjByCatgegory pbg=objListProjByCat.get(j);
			pnlBox[j]=new JPanel();
			pnlBox[j].setLayout(null);
			pnlBox[j].setBounds(xpos,ypos,250,200);
			
			lblproj_name=new JLabel[20];
			lblproj_name[j]=new JLabel(pbg.project_name);
			lblproj_name[j].setFont(lblproj_name[j].getFont().deriveFont(20.0f));
			lblproj_name[j].setBounds(10, 10, 320, 40);
			pnlBox[j].add(lblproj_name[j]);
			
			lblproj_des=new JLabel[20];
			lblproj_des[j]=new JLabel(pbg.project_description);
			lblproj_des[j].setBounds(10, 70, 240, 20);
			pnlBox[j].add(lblproj_des[j]);
			
			lblproj_Sdate=new JLabel[20];
			lblproj_Sdate[j]=new JLabel(String.valueOf("Start date : "+pbg.startDate));
			lblproj_Sdate[j].setBounds(10, 100, 240, 20);
			pnlBox[j].add(lblproj_Sdate[j]);
			
			lblproj_Edate=new JLabel[20];
			lblproj_Edate[j]=new JLabel(String.valueOf("End date : "+pbg.endDate));
			lblproj_Edate[j].setBounds(10, 120, 240, 16);
			pnlBox[j].add(lblproj_Edate[j]);
			
			
			lblproj_pledge=new JLabel[20];
			lblproj_pledge[j]=new JLabel(String.valueOf("GOAL : "+pbg.goal+"        PLEDGED : "+pbg.pledged));
			lblproj_pledge[j].setBounds(10, 140, 200, 15);
			pnlBox[j].add(lblproj_pledge[j]);
			
			String timeStamp= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
			SimpleDateFormat dtft=new SimpleDateFormat("dd-MM-yyyy");
			Dtutildt1 = dtft.parse(timeStamp);
			currentDate=new java.sql.Date(Dtutildt1.getTime());
			System.out.println(currentDate);
			
			intProjStatushisId+=1;
			
			int duration=daysBetween(pbg.startDate,pbg.endDate);
			java.util.Date d=addDays(pbg.startDate,duration);
			
			if(pbg.pledged>=pbg.goal)
			{
				projStatusId=3;//"successfully funded";
			}
			else if(pbg.pledged<pbg.goal && d.after(pbg.endDate))
			{
				projStatusId=4;//"Unsuccessful";
			}
			else if(pbg.goal-pbg.pledged<=3000)
			{
				projStatusId=2;//"Nearly funded";
			}
			else if(pbg.pledged<pbg.goal && d.before(pbg.endDate))
			{
				projStatusId=5;//"Campaign in progress";
			}
			else if(pbg.startDate.after(currentDate))
			{
				projStatusId=6;//"Not started";
			
			objDH.insetIntoProjStausHistory(intProjStatushisId,pbg.project_id,projStatusId,currentDate);
			}
		    
			JButton JbtnSeeAll=new JButton("SEE MORE");
			JbtnSeeAll.setBounds(10,160, 120,20);
			pnlBox[j].add(JbtnSeeAll);
			
			JButton JbtnFund=new JButton("BACK THIS");
			JbtnFund.setBounds(130,160, 110,20);
			pnlBox[j].add(JbtnFund);
			
			if(strUnm.equals("null"))
			{
				JbtnFund.setEnabled(false);
			}
			else
			{
				JbtnFund.setEnabled(true);
			}
			
			JbtnSeeAll.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					File file=new File(pbg.file_loc);
					JOptionPane.showMessageDialog(null, file.toString());
					Desktop desktop=Desktop.getDesktop();
					try
					{
						desktop.open(file);
					}
					catch(IOException e)
					{
						JOptionPane.showMessageDialog(null, "OOPS ! SOMETHING WENT WRONG");
					}
				}
				});
			
			JbtnFund.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					ProjByCatgegory pbg1=objDH.getDetailsOfaProject(pbg.project_name);
					FundProject fund=new FundProject(strUnm,pbg1.project_id);
				}
				});
						
			xpos+=280;
			if(xpos>900)
			{
				ypos+=300;
				xpos=40;
			}			
			pnlBox[j].setBorder(BorderFactory.createLineBorder(Color.black));
			pnlBox[j].setVisible(true);
			//catgry.add(lbl1[j]);
			backGroundpnl.add(pnlBox[j]);
			
			artFrm.setVisible(true);
			j++;
			}
	}
	public static int daysBetween(java.util.Date javaDateStartDate, java.util.Date javaDateEndDate)
	 {
		 return (int)( (javaDateEndDate.getTime() - javaDateStartDate.getTime()) / (1000 * 60 * 60 * 24));
	 }
	
	 public static java.util.Date addDays(java.util.Date d, int days)
	    {
	        d.setTime(d.getTime() + days * 1000 * 60 * 60 * 24);
	        return d;
	    }
}

