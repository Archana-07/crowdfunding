package crowdFunding;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class AccountPage  extends JFrame
{
	Container c=null;
	DbHandler objDH=new DbHandler();
	int i,intCardId;
	JPanel accountPnl,editPnl,payPnl;
	JMenuBar menuLbl;
	JMenu jmAccount,jmEdit,jmPay;
	JMenuItem jmiAccount,jmiEdit,jmiPay;
	String strUnm;
	JTextField emailTxt,nameTxt,ageTxt,currentPwd,newPwd,CardSecCodeTxt,CardNoTxt,CardNameTxt,CardExpTxt;
	java.sql.Date DtstrExpDt;
	
		public AccountPage(String StrId)
		{
			c=getContentPane(); //top most visible layer returned
			c.setLayout(null);
			 intCardId=objDH.getMaxId("id","card_details");
			 strUnm=objDH.getUnm_nameFromUser_Account(StrId);
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
			
//			String strTopPnlLbl[]={"Explore","Start a project","For you","Search","Logout"};
//			int[] xposLbl={10,110,300,1150,1250};
//			for(int i=0;i<5;i++)
//			{
//				JLabel lblTxt=new JLabel(strTopPnlLbl[i]);
//				lblTxt.setFont(lblTxt.getFont().deriveFont(20.0f));
//				lblTxt.setForeground(Color.WHITE);
//				lblTxt.setBounds(xposLbl[i], 15, 200,40);
//				tpnl.add(lblTxt);
//			}
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
//			    	case "For you":
//			    		break;
//			    	case " ":
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
			
			///////2nd Panel -contains label "settings" and menubar
			JPanel menuPnl=new JPanel();
			menuPnl.setLayout(null);
			menuPnl.setBorder(BorderFactory.createLineBorder(Color.black));
			menuPnl.setBackground(Color.WHITE);
			menuPnl.setBounds(0,71, 1500,200);
			backGroundpnl.add(menuPnl);
			
			//3rd panel
			JPanel menu2Pnl=new JPanel();
			menu2Pnl.setLayout(null);
			menu2Pnl.setBorder(BorderFactory.createLineBorder(Color.black));
			menu2Pnl.setBackground(Color.WHITE);
			menu2Pnl.setBounds(0,271, 1500,500);
			backGroundpnl.add(menu2Pnl);
			
			//inner contents
			JLabel lblTxtSetting=new JLabel("Settings");
			Font obj =new Font("Segoe Script",Font.BOLD,60);
			lblTxtSetting.setFont(obj);
			lblTxtSetting.setBounds(30,30, 500,70);
			menuPnl.add(lblTxtSetting);
			
			//menubar
			menuLbl=new JMenuBar();
			menuLbl.setBounds(20, 150,550, 50);
			menuPnl.add(menuLbl);
			
			//account menu
			jmAccount=new JMenu("Account   ");
			jmAccount.setBounds(30, 60, 100, 40);
			jmAccount.setFont(jmAccount.getFont().deriveFont(25.0f));
			menuLbl.add(jmAccount);
			
			//edit profile
			jmEdit=new JMenu("Edit profile   ");
			jmEdit.setBounds(120, 60, 100, 40);
			jmEdit.setFont(jmEdit.getFont().deriveFont(25.0f));
			menuLbl.add(jmEdit);
			
			//payment options
			
			jmPay=new JMenu("Payment methods    ");
			jmPay.setBounds(170, 60, 100, 40);
			jmPay.setFont(jmPay.getFont().deriveFont(25.0f));
			menuLbl.add(jmPay);
			
			jmiAccount=new JMenuItem("Account                      ");
			jmiEdit=new JMenuItem("Edit                              ");
			jmiPay=new JMenuItem("Pay                                 ");
			
			jmAccount.add(jmiAccount);
			jmEdit.add(jmiEdit);
			jmPay.add(jmiPay);
			

			accountPnl=new JPanel();
			accountPnl.setLayout(null);
			accountPnl.setBounds(10, 10, 600, 400);
			accountPnl.setBorder(BorderFactory.createLineBorder(Color.black));
			menu2Pnl.add(accountPnl);
			
			editPnl=new JPanel();
			editPnl.setLayout(null);
			editPnl.setBounds(10, 10, 600, 450);
			//editPnl.setBorder(BorderFactory.createLineBorder(Color.black));
			menu2Pnl.add(editPnl);

			payPnl=new JPanel();
			payPnl.setLayout(null);
			payPnl.setBounds(10, 10, 600, 400);
			payPnl.setBorder(BorderFactory.createLineBorder(Color.black));
			menu2Pnl.add(payPnl);
			
//			accountPnl.setEnabled(false);
//			editPnl.setEnabled(false);
//			payPnl.setEnabled(false);
			
			
			jmiAccount.addActionListener(new ActionListener()
		{
				public void actionPerformed(ActionEvent arg0)
				{

					
					//inside panel
					String strPnlLbl[]={"Email"," Change password","Current password","New password"};
					int[] yposLbl={30,130,200,290};
					for(i=0;i<4;i++)
					{
						
						JLabel lblTxt=new JLabel(strPnlLbl[i]);
						lblTxt.setFont(lblTxt.getFont().deriveFont(25.0f));
						lblTxt.setForeground(Color.BLACK);
						lblTxt.setBounds(20,yposLbl[i], 520,40);
						Font obj =new Font("Segoe Script",Font.BOLD,30);
						lblTxt.setFont(obj);
						
						if(i==1)
						{
							lblTxt.setOpaque(true);
							lblTxt.setBackground(Color.BLACK);
							lblTxt.setBounds(20,yposLbl[i], 350,40);
							lblTxt.setForeground(Color.WHITE);
							
						}
						accountPnl.add(lblTxt);
					}
						emailTxt=new JTextField();
						emailTxt.setBounds(20, 80,520, 40);
						emailTxt.setFont(emailTxt.getFont().deriveFont(20.0f));
						accountPnl.add(emailTxt);
						
						
						currentPwd=new JTextField();
						currentPwd.setBounds(20, 240,520, 40);
						currentPwd.setFont(currentPwd.getFont().deriveFont(20.0f));
						accountPnl.add(currentPwd);
						
						newPwd=new JTextField();
						newPwd.setBounds(20, 330,520, 40);
						newPwd.setFont(newPwd.getFont().deriveFont(20.0f));
						accountPnl.add(newPwd);
						
						JButton btnOk=new JButton(" DONE ");
						btnOk.setBounds(450, 5,130, 25);
						accountPnl.add(btnOk);
						
						btnOk.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent arg0)
							{
								
								String StrEmail=String.valueOf(emailTxt.getText());
								String StrCurPwd=String.valueOf(currentPwd.getText());
								String StrNewPwd=String.valueOf(newPwd.getText());
								String strNull = "null";
								
								//the fields should not be null. 
								int intNotNullfields=Validator.isTextEnteredValidation(StrEmail,StrCurPwd,StrNewPwd,strNull);

								if(intNotNullfields!=1 )
								{
									JOptionPane.showMessageDialog(null, "It seems that any field is missing");
								}
								else
								{
									
									String oldPwd=objDH.getPasswordFromUser_AccountById(StrId);
									System.out.println("current pass  "+StrCurPwd+"to be chaned  "+oldPwd);
									//email validation
									if(Validator.isValidEmailValidation(StrEmail)==true && oldPwd.equals(StrCurPwd))
									{
									objDH.UpdatePwdOfUserAccountByEmail(StrId,StrEmail,StrNewPwd);
									}
									else
									{
										JOptionPane.showMessageDialog(null, "Entered password to be changed doesn't exist");
									}
								}
							}
						});
						
						repaint();
						accountPnl.setVisible(true);	
				}
			});
			jmiEdit.addActionListener(new ActionListener()
			{
				
					public void actionPerformed(ActionEvent arg0)
					{
					accountPnl.setVisible(false);

					
					//inside panel
					String strPnlLbl[]={"Email"," Name","Age"};
					int[] yposLbl={30,130,230,320};
					for(i=0;i<3;i++)
					{
						
						JLabel lblTxt=new JLabel(strPnlLbl[i]);
						lblTxt.setFont(lblTxt.getFont().deriveFont(25.0f));
						lblTxt.setForeground(Color.BLACK);
						lblTxt.setBounds(20,yposLbl[i], 520,40);
	
						editPnl.add(lblTxt);
					}	
						
						
						emailTxt=new JTextField();
						emailTxt.setBounds(20, 80,520, 40);
						emailTxt.setFont(emailTxt.getFont().deriveFont(20.0f));
						editPnl.add(emailTxt);
						
						nameTxt=new JTextField();
						nameTxt.setBounds(20, 180,520, 40);
						nameTxt.setFont(nameTxt.getFont().deriveFont(20.0f));
						editPnl.add(nameTxt);
						
						ageTxt=new JTextField();
						ageTxt.setBounds(20, 270,520, 40);
						ageTxt.setFont(ageTxt.getFont().deriveFont(20.0f));
						editPnl.add(ageTxt);
						JButton btnOk=new JButton(" DONE ");
						btnOk.setBounds(450, 5,130, 25);
						editPnl.add(btnOk);
						btnOk.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent arg0)
							{
								String StrEmail=String.valueOf(emailTxt.getText());
								String StrName=String.valueOf(nameTxt.getText());
								String StrAge=String.valueOf(ageTxt.getText());
								String strNull = "null";
								
								int intNotNullfields=Validator.isTextEnteredValidation(StrEmail,StrName,StrAge,strNull);

								if(intNotNullfields!=1 )
								{
									JOptionPane.showMessageDialog(null, "It seems that any field is missing");
								}
								else
								{
									if(Validator.isValidEmailValidation(StrEmail)==true && Validator.stringValidation(StrName)==1 && Validator.numberValidation(StrAge)==1)
									{

										System.out.println(StrId+" "+StrEmail+" "+StrName+" "+StrAge);
										objDH.UpdateProfileOfUserAccountById(StrId,StrEmail,StrName,StrAge);
									}
									else
									{
										JOptionPane.showMessageDialog(null, "Enter a valid value in provided fields");
									}
									
								}
								
							}
						});
						
						repaint();
						editPnl.setVisible(true);
				}
					
			});
			
			
			
//			//payment options
            jmiPay.addActionListener(new ActionListener()
		{
				public void actionPerformed(ActionEvent arg0)
				{
					editPnl.setVisible(false);
					accountPnl.setVisible(false);
					
					
					JLabel lblpayTxt=new JLabel("<html><h1>Manage Payement Options</h1></br></br><p>Any payment method you save to"
							+ " Kickstarter will be listed here for your convinience.</br>To save a card for future pledges, click 'ADD A NEW CARD'.</p></html>");
					lblpayTxt.setFont(lblpayTxt.getFont().deriveFont(20.0f));
					lblpayTxt.setBounds(20,10, 520,200);
					payPnl.add(lblpayTxt);
					
					JButton btnAddCard=new JButton(" ADD A NEW CARD ");
					btnAddCard.setBounds(150,300,200, 30);
					payPnl.add(btnAddCard);

					//handling the event of adding a new card
					btnAddCard.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent arg0)
						{
							//background
							JFrame cardDetailsFrm=new JFrame("Card Details");
							cardDetailsFrm.setLayout(null);
							cardDetailsFrm.setBounds(600, 130, 450, 550);
							cardDetailsFrm.setVisible(true);
						
							String strFrmLbl[]={"Card number"," Name","Expiration","Security code"};
							int[] yposLbl={20,100,180,260};
							for(i=0;i<4;i++)
							{
								
								JLabel lblTxt=new JLabel(strFrmLbl[i]);
								lblTxt.setForeground(Color.BLACK);
								lblTxt.setBounds(20,yposLbl[i], 400,40);
								cardDetailsFrm.add(lblTxt);
							}		
						CardNoTxt=new JTextField();
						CardNoTxt.setBounds(20, 60,400, 40);
						cardDetailsFrm.add(CardNoTxt);
						
						CardNameTxt=new JTextField();
						CardNameTxt.setBounds(20, 140,400, 40);
						cardDetailsFrm.add(CardNameTxt);
						
						CardExpTxt=new JTextField();
						CardExpTxt.setBounds(20, 220,400, 40);
						cardDetailsFrm.add(CardExpTxt);
						
						CardSecCodeTxt=new JTextField();
						CardSecCodeTxt.setBounds(20, 300,400, 40);
						cardDetailsFrm.add(CardSecCodeTxt);
						
						JButton btnSaveCard=new JButton(" SAVE MY CARD ");
						btnSaveCard.setBounds(150,400,150, 30);
						cardDetailsFrm.add(btnSaveCard);
						
						btnSaveCard.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent arg0)
							{
								
								java.util.Date Dtutildt1;
								SimpleDateFormat dtft=new SimpleDateFormat("dd-MM-yyyy");
							

								long longCardSecCode=Long.parseLong(CardSecCodeTxt.getText());
								String temp=String.valueOf(CardNoTxt.getText());
								long longCardNo=Long.parseLong(temp);
								String strCardName=String.valueOf(CardNameTxt.getText());
								
								try 
								{
									Dtutildt1 = dtft.parse(CardExpTxt.getText());
								    DtstrExpDt=new java.sql.Date(Dtutildt1.getTime());
								    
									String strDtValid=String.valueOf(DtstrExpDt);
									String strCardnoValid=String.valueOf(longCardNo);
									String strCardSecValid=String.valueOf(longCardSecCode);
									
									//NOT NULL VALIDATION
									int intNotNullfields=Validator.isTextEnteredValidation(strCardName,strDtValid,strCardnoValid,strCardSecValid);

									if(intNotNullfields!=1 )
									{
										JOptionPane.showMessageDialog(null, "It seems that any field is missing");
									}
									else
									{
										intCardId++;
									 		
									 		String strCardId=String.valueOf(intCardId);
									 		JOptionPane.showMessageDialog(null,"text from txtbox is icsd dt start date is "+ DtstrExpDt);
											objDH.insertIntoCardDetailsByUserId( strCardId, longCardNo,strCardName,DtstrExpDt,longCardSecCode,StrId);
									}
									
								}
								
								catch (ParseException e1)
								{
									e1.printStackTrace();
								}
								catch (NumberFormatException e2)
								{
									e2.printStackTrace();
								}
							}
						});
					}
			});
					repaint();
					payPnl.setVisible(true);
				}
			});
			setTitle("MY ACCOUNT");
			setVisible (true);
			setSize(Toolkit.getDefaultToolkit().getScreenSize());
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}

}

