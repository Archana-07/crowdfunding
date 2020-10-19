package crowdFunding;

import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FundProject extends JFrame
{
	Container c=null;
	DbHandler objDH=new DbHandler();
	String strUserId,tsProjInvestor;
	JTextField CreditTxt,amtTxt,nameTxt,expTxt;
	int intProj_sup,intTot_amount,projId,intProjInvestorId,totAmt;
	static java.util.Date Dtutildt1;
	java.sql.Date DtstrTimeStampDt;
	
     public FundProject(String strUnm,int projectId)
     {
 			c=getContentPane(); //top most visible layer returned
 			c.setLayout(null);
 			intProjInvestorId=objDH.getMaxId("id","project_investor");
 			
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
		
			ImageIcon icnLogo=new ImageIcon("images/KickStarter-logo.jpg");
			JLabel lblicnLogo=new JLabel(icnLogo);
			lblicnLogo.setBounds(300, 0, 650, 60);
			tpnl.add(lblicnLogo);	
			
			//design=green panel
			JPanel gpnl=new JPanel();
			gpnl.setBackground(Color.GREEN);
			gpnl.setBounds(0,62, 1500,7);
			backGroundpnl.add(gpnl);
			
			JLabel amtFunded=new JLabel("Enter amount to be pledged");
			amtFunded.setBounds(20, 120,350,40);
			backGroundpnl.add(amtFunded);
			amtFunded.setFont(amtFunded.getFont().deriveFont(22.0f));
			
			amtTxt=new JTextField();
			amtTxt.setBounds(20, 170,350, 40);
			amtTxt.setFont(amtTxt.getFont().deriveFont(20.0f));
			backGroundpnl.add(amtTxt);
			
			//////////validation of entered number ie it should be numbers only//////////////

			

			//continue button
			ImageIcon icnDoneBtn=new ImageIcon("images/button_done.jpg");
			JButton btnDone=new JButton(icnDoneBtn);
			btnDone.setBounds(20, 260, 350, 40);
			backGroundpnl.add(btnDone);
			
			btnDone.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{

					String amountSupp=String.valueOf(amtTxt.getText());
					int temp=Validator.numberValidation(amountSupp);
					if(temp==1)
					{
					
					JPanel cardDetails=new JPanel();
					cardDetails.setBounds(600, 120, 500, 550);
					backGroundpnl.add(cardDetails);
					
					JLabel cardNo=new JLabel("Card Number");
					cardNo.setBounds(10, 50,400,40);
					cardDetails.add(cardNo);
					cardNo.setFont(cardNo.getFont().deriveFont(22.0f));
					
					CreditTxt=new JTextField();
					CreditTxt.setBounds(10, 100,400, 40);
					CreditTxt.setFont(CreditTxt.getFont().deriveFont(20.0f));
					cardDetails.add(CreditTxt);
					
					JLabel cardName=new JLabel("Name");
					cardName.setBounds(10, 160,400,40);
					cardDetails.add(cardName);
					cardName.setFont(cardName.getFont().deriveFont(22.0f));
					
					nameTxt=new JTextField();
					nameTxt.setBounds(10, 210,400, 40);
					nameTxt.setFont(nameTxt.getFont().deriveFont(20.0f));
					cardDetails.add(nameTxt);
					JLabel cardExp=new JLabel("Expiry Date");
					cardExp.setBounds(10, 280,400,40);
					cardDetails.add(cardExp);
					cardExp.setFont(cardExp.getFont().deriveFont(22.0f));
					
					expTxt=new JTextField();
					expTxt.setBounds(10, 330,400, 40);
					expTxt.setFont(expTxt.getFont().deriveFont(20.0f));
					cardDetails.add(expTxt);
					
					//continue button
					ImageIcon icnDoneBtn=new ImageIcon("images/button_done.jpg");
					JButton btnDone=new JButton(icnDoneBtn);
					btnDone.setBounds(10, 400, 400, 40);
					cardDetails.add(btnDone);
					
					btnDone.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent arg0)
						{
							String CardHoldname=String.valueOf(nameTxt.getText());
							int temp=Validator.stringValidation(CardHoldname);
							if(temp==1)
							{
							totAmt=Integer.parseInt(amtTxt.getText());
							strUserId=objDH.getUnm_IdFromUser_Account(strUnm);							
							proj_Supported(totAmt);
							
							UserDetailsForUpdate userDetails=objDH.getAllUserDetails(strUserId);
							userDetails.dispUserDetails();
							
							String strUnm=userDetails.getStrUnm();
							String strPwd=userDetails.getStrPwd();
							String strEmail=userDetails.getStrEmail();
							int intCountry_id =userDetails.getIntCountry_id();
							int intAge=userDetails.getIntAge();
							String strNewsletter=userDetails.getStrNewsLetter();
							
							objDH.UpdateUserAccount(strUserId, strUnm, strPwd, strEmail,intProj_sup,intTot_amount, intCountry_id, strNewsletter, intAge);
							
						    //////updating the user table////////////////////
							UserDetailsForUpdate userDetails1=objDH.getAllUserDetails(strUserId);
							userDetails1.dispUserDetails();
						     dispose();
						     JFrame frm=new JFrame();
						     frm.setBounds(50, 50, 600, 600);
						     
						     intProjInvestorId+=1;
						       
						       SimpleDateFormat dtft=new SimpleDateFormat("dd-MM-yyyy");
								
						     
						     String tsProjInvestor= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
						        try {
								Dtutildt1 = dtft.parse(tsProjInvestor);
							    DtstrTimeStampDt=new java.sql.Date(Dtutildt1.getTime());
						 		System.out.println(DtstrTimeStampDt);
						 		objDH.insertIntoProjInvestorByUserId(intProjInvestorId,projectId,strUserId,DtstrTimeStampDt,totAmt);
						        } 
						        catch (ParseException e)
						        {
									e.printStackTrace();
								}
						        JLabel msgLbl=new JLabel("You have successfully funded this project.");
						        msgLbl.setBounds(20, 200,500,40);
						        frm.add(msgLbl);
								msgLbl.setFont(msgLbl.getFont().deriveFont(22.0f));
								
								JButton newproj=new JButton("Fund Another Project");
								newproj.setBounds(20, 270, 200, 40);
								frm.add(newproj);
								
								newproj.addActionListener(new ActionListener()
								{
									public void actionPerformed(ActionEvent arg0)
									{
										dispose();
										ExplorePage ep= new ExplorePage(strUnm);
									}
									
								});
								
								JButton home=new JButton("Go To My Homepage");
								home.setBounds(350, 270, 200, 40);
								frm.add(home);
								
								home.addActionListener(new ActionListener()
								{
									public void actionPerformed(ActionEvent arg0)
									{
										dispose();
										UserProfilePage ufrm=new UserProfilePage(strUnm);
									}
									
								});
						     
						     frm.setTitle("CONGRATULATIONS");
						     frm.setLayout(null);
						     frm.setVisible(true);
						}
							else
								{
									
								}
							}
						});
					
					cardDetails.setLayout(null);
					cardDetails.setVisible(true);
					repaint();
				}
					else
					{
						JOptionPane.showMessageDialog(null, "ENTER A VALUE");
					}				
				}});						
 			setTitle("FUND A PROJECT");
			setVisible (true);
			setSize(Toolkit.getDefaultToolkit().getScreenSize());
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
     }
 	public void proj_Supported(int totAmt)
	{
		intProj_sup=intProj_sup+1;
		intTot_amount=intTot_amount+totAmt;

	}
}
