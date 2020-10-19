package crowdFunding;

import java.awt.Color;

import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;

public class WelcomePage extends JFrame
{
	
	Container c=null;
	JLabel imglblSlider;
	Timer timer;
	int x=0;
	
	
	//list of images and categories to be displayed
	String [] imgList={"images/slide1.jpg","images/slide2.jpg","images/slide3.jpg","images/slide5.jpg","images/slide6.jpg","images/slide7.jpg","images/slide8.jpeg","images/slide8.png","images/slide9.png","images/slide10.jpg","images/slide11.jpg"};
	String strCategory[]={"Art","Comics","Craft","Dance","Design","Fashion","Film & Video","Food","Games","Journalism","Music","Photography","Publishing","Technology","Theater"};
	public WelcomePage()
	{

		c=getContentPane(); //top most visible layer returned
		c.setLayout(null);
		
		//background
		ImageIcon icn=new ImageIcon("images/cfund.v1.png");
		JLabel lblbg=new JLabel(icn);
		lblbg.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		c.add(lblbg);

		
		//scrollpane
		JScrollPane scroll = new JScrollPane( lblbg );
		setContentPane( scroll );
		scroll.setVerticalScrollBarPolicy(scroll.VERTICAL_SCROLLBAR_ALWAYS);
		
		//////panel=topmost
		JPanel tpnl=new JPanel();
		tpnl.setLayout(null);
		tpnl.setBackground(Color.BLACK);
		tpnl.setBounds(0, 0, 1500, 60);
		lblbg.add(tpnl);
		//inner contents
		
		String strTopPnlLbl[]={"Explore","Start a project","About us","  ","Login","Register"};
		int[] xposLbl={10,110,300,900,1050,1180};
		for(int i=0;i<6;i++)
		{
			JLabel lblTxt=new JLabel(strTopPnlLbl[i]);
			lblTxt.setFont(lblTxt.getFont().deriveFont(20.0f));
			lblTxt.setForeground(Color.WHITE);
			lblTxt.setBounds(xposLbl[i], 15, 140,40);
			tpnl.add(lblTxt);
			
			//helping user to identify location of mouse
			
			//mouselistener helps to handle any event on the click of mouse
			lblTxt.addMouseListener(new MouseAdapter()  
			{  
			    public void mouseEntered(MouseEvent e)  
			    { 
			    	lblTxt.setForeground(Color.GRAY);
			    }  
			    public void mouseExited(MouseEvent e)  
			    { 
			    	lblTxt.setForeground(Color.WHITE);
			    } 
			    public void mouseClicked(MouseEvent e)
			    {
			    	switch(lblTxt.getText())
			    	{
			    	case "Explore":
			    		String strUnm="null";
			    		ExplorePage expPage=new ExplorePage(strUnm);
			    		break;
			    	case "Start a project":
			    		LoginPage loginPage=new LoginPage();
			    		break;
			    	case "About us":
			    		AboutUs ab=new AboutUs();
			    		break;
			    	case "Search":
			    		
			    	case "Login":
			    	     loginPage=new LoginPage();
			    		break;
			    	case "Register":
			    		RegisterPage regPage=new RegisterPage();
			    		break;
			    	
			    	}
			    }
			});
		}
		
		ImageIcon icnLogo=new ImageIcon("images/KickStarter-logo.jpg");
		JLabel lblicnLogo=new JLabel(icnLogo);
		lblicnLogo.setBounds(300, 0, 650, 60);
		tpnl.add(lblicnLogo);
		
		
		////panel=picslider
		JPanel picPnl=new JPanel();
		picPnl.setBounds(20, 90, 900, 500);
		picPnl.setBackground(Color.BLACK);
		lblbg.add(picPnl);	
		
		//image slider -using timer
		imglblSlider=new JLabel();
		imglblSlider.setBounds(20, 0, 900, 600);
		
		setImageSize(10);
		timer=new Timer(1000,new ActionListener()
		{
					public void actionPerformed(ActionEvent arg0)
					{

						setImageSize(x);
						
						if(x>imgList.length)
						{
							x=0;
						}
						x=x+1;
						
						}
			
		});
		picPnl.add(imglblSlider);
		timer.start();
		setSize(600,500);
		
//		side panel-showing categories of projects
		JPanel PnlCategory=new JPanel();
		PnlCategory.setLayout(null);
		PnlCategory.setBounds(980, 90, 300, 500);
		PnlCategory.setBorder(BorderFactory.createLineBorder(Color.black));
		lblbg.add(PnlCategory);
		
		//inner contents
		
		int yposLbl=20;
		for(int i=0;i<15;i++)
		{
			JLabel lblCategory=new JLabel(strCategory[i]);
			lblCategory.setFont(lblCategory.getFont().deriveFont(20.0f));
			lblCategory.setBounds(40,yposLbl, 200,40);
			PnlCategory.add(lblCategory);
			yposLbl+=30;
			
			//helping user to identify location of mouse
			lblCategory.addMouseListener(new MouseAdapter()  
			{  
			    public void mouseEntered(MouseEvent e)  
			    { 
			    	lblCategory.setForeground(Color.GRAY);
			    }  
			    public void mouseExited(MouseEvent e)  
			    { 
			    	lblCategory.setForeground(Color.BLACK);
			    } 
			    public void mouseClicked(MouseEvent e)
			    {
			    	String strUnm="null";
			    	String categorySelected=lblCategory.getText();
			    	try {
						ExplorePage.AllprojDetailsByCategory(categorySelected,strUnm);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    }
			});
		}
	
		setTitle("WELCOME");
		setVisible (true);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		}	
	
	public void setImageSize(int i)
	{ 
		ImageIcon icon=new ImageIcon(imgList[i]);
		Image image=icon.getImage();
		Image newImg=image.getScaledInstance(900, 600, Image.SCALE_SMOOTH);
		ImageIcon icnImg=new ImageIcon(newImg);
		imglblSlider.setIcon(icnImg);
	
		
	}
}
