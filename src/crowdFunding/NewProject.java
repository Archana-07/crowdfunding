package crowdFunding;

import java.awt.Color;

import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NewProject extends JFrame
{
	
	Container c=null;
	int x=0,noParticipant, projId,orgId,participantId,roleId,projTeamId,intUserAccount = 0,intParticipated_in=0;
	DbHandler objDH=new DbHandler();
	JTextField memTxt,titleTxt,GoalTxt,startTxt,endTxt,OrgTxtS;
	JTextField [] ParticipantTXT;
	JTextArea desTxt;
	JRadioButton jrbInd,jrbOption;
	JComboBox jcbCategory,OrgTxtCmb;JComboBox [] RoleCmb;
	ButtonGroup btn;
	JButton jbOK,btnCont,btnDone;
	java.sql.Date DtstrStartDt,DtstrEndDt;
	
	String strFilePath,strOrgName=null, strName,strRole;String strParticipantId;
	
	String strCategory[]={"Art","Comics","Craft","Dance","Design","Fashion","Film & Video","Food","Games","Journalism","Music","Photography","Publishing","Technology","Theater"};
	public NewProject(String strUnm)
	{

		c=getContentPane(); //top most visible layer returned
		c.setLayout(null);
		
		String strUserId=objDH.getUnm_IdFromUser_Account(strUnm);
		
		projId=objDH.getMaxId("id","project" );
		participantId=objDH.getMaxId("id","participant" );
		projTeamId=objDH.getMaxId("id","project_team" );
		orgId=objDH.getMaxId("id","organization" );
	
		
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
		
		JLabel lblTxt=new JLabel("Tell us something about your project ");
		Font obj =new Font("Segoe Script",Font.BOLD,60);
		lblTxt.setFont(obj);
		lblTxt.setBounds(30,100, 1300,70);
		backGroundpnl.add(lblTxt);
		
		//design=green panel
		JPanel g2pnl=new JPanel();
		g2pnl.setBackground(Color.GREEN);
		g2pnl.setBounds(0,170, 1500,7);
		backGroundpnl.add(g2pnl);
		
		JLabel lblCategory=new JLabel("Select a category ");
		lblCategory.setFont(lblCategory.getFont().deriveFont(22.0f));
		lblCategory.setBounds(30,220, 300,40);
		backGroundpnl.add(lblCategory);
		
		jcbCategory=new JComboBox(strCategory);
		jcbCategory.setBounds(30, 260,500, 40);
		jcbCategory.setBackground(Color.WHITE);
		jcbCategory.setFont(jcbCategory.getFont().deriveFont(20.0f));
		backGroundpnl.add(jcbCategory);
		
		//description of project
		JLabel lblDes=new JLabel("Describe your project");
		 lblDes.setFont(lblDes.getFont().deriveFont(22.0f));
		 lblDes.setBounds(30,320, 300,40);
		backGroundpnl.add( lblDes);
		
	    desTxt=new JTextArea();
		desTxt.setBounds(30, 360,500, 100);
		desTxt.setFont(desTxt.getFont().deriveFont(20.0f));
		desTxt.setBorder(BorderFactory.createLineBorder(Color.black));
		backGroundpnl.add(desTxt);
		
		//tittle of project
		//description of project
		JLabel lblTitle=new JLabel("Give a title to your project");
		lblTitle.setFont(lblTitle.getFont().deriveFont(22.0f));
		lblTitle.setBounds(30,470, 300,40);
		backGroundpnl.add( lblTitle);
		
		titleTxt=new JTextField();
		titleTxt.setBounds(30, 510,500, 40);
		titleTxt.setFont(titleTxt.getFont().deriveFont(20.0f));
		titleTxt.setBorder(BorderFactory.createLineBorder(Color.black));
		backGroundpnl.add(titleTxt);
		
		//Organization or individual
		
		JLabel lblOption=new JLabel("Organization or individual");
		lblOption.setFont(lblOption.getFont().deriveFont(22.0f));
		lblOption.setBounds(30,570, 300,40);
		backGroundpnl.add( lblOption);
		
		jrbOption=new JRadioButton("Organization");
		jrbOption.setBounds(30, 610, 200, 40);
		jrbOption.setBackground(Color.WHITE);
		jrbOption.setFont(jrbOption.getFont().deriveFont(18.0f));
		backGroundpnl.add( jrbOption);
		

		jrbInd=new JRadioButton("Individual");
		jrbInd.setBounds(250, 610, 200, 40);
		jrbInd.setFont(jrbInd.getFont().deriveFont(18.0f));
		jrbInd.setBackground(Color.WHITE);
		backGroundpnl.add( jrbInd);
		
		btn=new ButtonGroup();
		btn.add(jrbInd);
		btn.add(jrbOption);

		jrbOption.addActionListener(new ActionListener()
		{
				public void actionPerformed(ActionEvent arg0)
				{
					JFrame orgSetting=new JFrame();
					{
						orgSetting.setLayout(null);
						
						
						//background
						JPanel backGroundpnl=new JPanel();
						backGroundpnl.setLayout(null);
						backGroundpnl.setBackground(Color.WHITE);
						backGroundpnl.setBounds(0, 0, 1500, 1500);
						orgSetting.add(backGroundpnl);
						orgSetting.setResizable(true);
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
						
						
						//organization name
						JLabel lblOrg=new JLabel("Organization name");
						lblOrg.setFont(lblDes.getFont().deriveFont(22.0f));
						lblOrg.setBounds(30,170, 400,40);
						backGroundpnl.add( lblOrg);
						
					    OrgTxtCmb=new JComboBox();
					    OrgTxtCmb.setBounds(30, 210,400, 40);
					    OrgTxtCmb.setFont(OrgTxtCmb.getFont().deriveFont(20.0f));
					    OrgTxtCmb.setBorder(BorderFactory.createLineBorder(Color.black));
						backGroundpnl.add(OrgTxtCmb);
						
						Vector <String> vecCname =objDH.getAllOrganization();
						for(String str:vecCname)
						{
							OrgTxtCmb.addItem(str);
						}
						
						OrgTxtCmb.addItem("Other");
						
						
						//Specify organization name
						JLabel lblOrgSpecify=new JLabel("Please specify your organization name");
						lblOrgSpecify.setFont(lblDes.getFont().deriveFont(22.0f));
						lblOrgSpecify.setBounds(30,270, 400,40);
						backGroundpnl.add( lblOrgSpecify);
						
					    OrgTxtS=new JTextField();
					    OrgTxtS.setBounds(30, 310,400, 40);
					    OrgTxtS.setFont(OrgTxtS.getFont().deriveFont(20.0f));
					    OrgTxtS.setBorder(BorderFactory.createLineBorder(Color.black));
						backGroundpnl.add(OrgTxtS);
						
						lblOrgSpecify.setEnabled(false);
						OrgTxtS.setEnabled(false);
					
						{
							OrgTxtCmb.addActionListener(new ActionListener()
									{
								public void actionPerformed(ActionEvent arg0)
								{

									OrgTxtCmb=(JComboBox)arg0.getSource();
									Object selected=OrgTxtCmb.getSelectedItem();
									
									if(selected.toString().equals("Other"))
									{
										lblOrgSpecify.setEnabled(true);
										OrgTxtS.setEnabled(true);
										
										strOrgName=String.valueOf(OrgTxtS.getText());
									}
									else
									{
										lblOrgSpecify.setEnabled(false);
										OrgTxtS.setEnabled(false);
										strOrgName=String.valueOf(selected);
									}
								}
								});	
						
						//no of participants
						JLabel lblMem=new JLabel("How many team members are there?");
						lblMem.setFont(lblTitle.getFont().deriveFont(22.0f));
						lblMem.setBounds(30,360, 400,40);
						backGroundpnl.add( lblMem);
						
					    memTxt=new JTextField();
						memTxt.setBounds(30,400,400, 40);
						memTxt.setFont(titleTxt.getFont().deriveFont(20.0f));
						memTxt.setBorder(BorderFactory.createLineBorder(Color.black));
						backGroundpnl.add(memTxt);
						
						//continue button
						ImageIcon icnContinueBtn=new ImageIcon("images/button_continue.jpg");
						btnCont=new JButton(icnContinueBtn);
						btnCont.setBounds(30, 480, 400, 40);
						backGroundpnl.add(btnCont);
						
						//no of participants
						JLabel lblInfo=new JLabel("Please Note: A team having more than 25 members isn't allowed.");
						lblInfo.setBounds(30,550, 400,40);
						backGroundpnl.add( lblInfo);
						
						btnCont.addActionListener(new ActionListener()
						{
						
							  int yposition=100;
							  int xposition=490;
								public void actionPerformed(ActionEvent arg0)
								{
									orgId+=1;
									
									noParticipant=Integer.parseInt(memTxt.getText());
									
									System.out.println(noParticipant);
									ParticipantTXT=new JTextField[26];
									RoleCmb=new JComboBox[26];
			     					for(x=0;x<noParticipant;x++)
									{
			     						System.out.println(x);
			     						
										ParticipantTXT[x]=new JTextField("Participant "+(x+1));
										ParticipantTXT[x].setBounds(xposition,yposition, 250,40);
										backGroundpnl.add(ParticipantTXT[x]);
										//System.out.println(ParticipantTXT[x]);
										
									    RoleCmb[x]=new JComboBox();
									    RoleCmb[x].setBounds(xposition+260,yposition,160, 40);
									    backGroundpnl.add(RoleCmb[x]);
									    //System.out.println(RoleCmb[x]);

									    Vector <String> vecRlname =objDH.getRoles();
										for(String str1:vecRlname)
										{
											RoleCmb[x].addItem(str1);
										}
									    
										if(yposition==700)
										{
											
											yposition=100;
											xposition=920;
										}
										yposition+=50;
									}     			
			     					String strOrgId=String.valueOf(orgId);
			     					System.out.println("org name"+ strOrgName);
			     					
			     					objDH.insertOrg(strOrgId,strOrgName);
			     					System.out.println("org name"+ strOrgName);
										
										}
								});
						
						//done all  button
						ImageIcon icnDoneBtn=new ImageIcon("images/button_done.jpg");
						btnDone=new JButton(icnDoneBtn);
						btnDone.setBounds(30, 600, 400, 40);
						backGroundpnl.add(btnDone);
						
						btnDone.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent arg0)
							{
								orgSetting.dispose();
							}
							
						});
						
						orgSetting.setTitle("Organization Settings");
						orgSetting.setVisible (true);
						orgSetting.setBounds(7, 7, 1400, 1400);
						setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					}
					
				}
				}
	});
		//goal decided
		JLabel lblGoal=new JLabel("Project Goal");
		lblGoal.setFont(lblGoal.getFont().deriveFont(22.0f));
		lblGoal.setBounds(650,220, 300,40);
		backGroundpnl.add( lblGoal);
		
		GoalTxt=new JTextField();
		GoalTxt.setBounds(650, 260,500, 40);
		GoalTxt.setFont(GoalTxt.getFont().deriveFont(20.0f));
		GoalTxt.setBorder(BorderFactory.createLineBorder(Color.black));
		backGroundpnl.add(GoalTxt);
		
		//start date 
		JLabel lblStartDt=new JLabel("Start date of Campaign");
		lblStartDt.setFont(lblTitle.getFont().deriveFont(22.0f));
		lblStartDt.setBounds(650,310, 300,40);
		backGroundpnl.add( lblStartDt);
		
		startTxt=new JTextField("dd-mm-yyyy");
		startTxt.setBounds(650, 355,500, 40);
		startTxt.setFont(startTxt.getFont().deriveFont(20.0f));
		startTxt.setBorder(BorderFactory.createLineBorder(Color.black));
		backGroundpnl.add(startTxt);
		
		JLabel lblEndDt=new JLabel("End date of Campaign");
		lblEndDt.setFont(lblTitle.getFont().deriveFont(22.0f));
		lblEndDt.setBounds(650,405, 300,40);
		backGroundpnl.add( lblEndDt);
		
		
		endTxt=new JTextField("dd-mm-yyyy");
		endTxt.setBounds(650, 450,500, 40);
		endTxt.setFont(endTxt.getFont().deriveFont(20.0f));
		endTxt.setBorder(BorderFactory.createLineBorder(Color.black));
		backGroundpnl.add(endTxt);
		
		//uploading documents
		JLabel lblUploadTitle=new JLabel("Upload project document file");
		lblUploadTitle.setFont(lblUploadTitle.getFont().deriveFont(22.0f));
		lblUploadTitle.setBounds(650,510, 400,40);
		backGroundpnl.add( lblUploadTitle);
		
		//select file button
		JButton btnselect=new JButton("Upload document");
		btnselect.setBounds(650,560, 500, 40);
		backGroundpnl.add(btnselect);
		
		btnselect.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				BufferedReader in;
				JFileChooser chooser =new JFileChooser();
				int rval=chooser.showOpenDialog(null);
			
				try
				{
					File fileSelected=chooser.getSelectedFile();
					
					strFilePath=fileSelected.getAbsolutePath();
					JOptionPane.showMessageDialog(null, strFilePath);
					String strFileName=fileSelected.getName();
					File imgUploaded=new File(strFileName);
				
					FileInputStream fis=new FileInputStream(fileSelected);
					byte rawData[]=new byte[(int)fileSelected.length()];
				
					fis.read(rawData);
					
					fis.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		});	
		
	 	projId+=1;
		//continue button
		ImageIcon icnBtn=new ImageIcon("images/button_continue.jpg");
		JButton btnContinue=new JButton(icnBtn);
		btnContinue.setBounds(730, 650, 250, 40);
		backGroundpnl.add(btnContinue);
		
		btnContinue.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				dispose();
				
				java.util.Date Dtutildt1;
				try 
				{
					SimpleDateFormat dtft=new SimpleDateFormat("dd-MM-yyyy");
					Dtutildt1 = dtft.parse(startTxt.getText());
				    DtstrStartDt=new java.sql.Date(Dtutildt1.getTime());
			 		System.out.println(DtstrStartDt);
			 		

			 		java.util.Date utildt = dtft.parse(endTxt.getText());
			 	    DtstrEndDt=new java.sql.Date(utildt.getTime());
			 		System.out.println(DtstrEndDt);
			 		
			   
			 		
			 		//JOptionPane.showMessageDialog(null,"text from txtbox is "+ startTxt.getText()+" icsd dt start date is "+ DtstrStartDt);
					
					String strId=String.valueOf(projId);
				    String strProjName=titleTxt.getText();
				    //int intUserAccount=Integer.parseInt(strUserId);
					int intUserAccount=Integer.parseInt(strUserId);////////////////////////////////////////////////
					int intGoal= Integer.parseInt(GoalTxt.getText());
					int intInvestors=0;
					int intStatus=1;
					String strCategory=(String) jcbCategory.getSelectedItem();
					String strDesc=desTxt.getText();
					int intPledged=0;
					String strLoc=strFilePath;
					
					objDH.insertProject(strId,strProjName,intUserAccount,orgId,DtstrStartDt,DtstrEndDt,intGoal,intInvestors,intStatus,strCategory,strDesc,intPledged,strLoc);
					objDH.insertProj_team(projTeamId,projId,noParticipant);
					
					for(x=0;x<noParticipant;x++)
					{
						///////inserting into participant
						
						participantId+=1;
						projTeamId+=1;
						strParticipantId=String.valueOf(participantId);
						intUserAccount=Integer.parseInt(strUserId);
						
						strName=String.valueOf(ParticipantTXT[x].getText());
						Object objRole=RoleCmb[x].getSelectedItem();
						strRole=String.valueOf(objRole);
						
						roleId=Integer.parseInt(objDH.getRole_IdFromRoleByRole(strRole));
						objDH.insertParticipant(strParticipantId,strName,intUserAccount,intParticipated_in,projTeamId,roleId,orgId);
			       		}
					
				} 
				catch (ParseException e1) 
				{
					e1.printStackTrace();
				}
				dispose();
			}
			
		});	
		setTitle("NEW PROJECT");
		setVisible (true);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public String[] getAllCountries() 
	{
		String[] countries=new String[Locale.getISOCountries().length];
		String[] locales=Locale.getISOCountries();
		for(int i=0;i<locales.length;i++)
		{
			Locale locObj=new Locale("",locales[i]);
			countries[i]=locObj.getDisplayCountry();
		}
		return countries;
	}
}
