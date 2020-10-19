package crowdFunding;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.JOptionPane;
import oracle.jdbc.pool.OracleDataSource;


public class DbHandler 
{
	String strUnm,strUserAccount,id,user_account,strPwdofUser;
	public int stat=0,myProjCounter;
	int projCounter=0;
	

	///////////TO ESTABLISH THE CONNECTION//////////////////
	public Connection getDBConnection()
	{

		Connection con=null;
		OracleDataSource ods;
		try
		{
			ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
			con=ods.getConnection("ArchanaKumari", "Crowdfunding");
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
		
		
	}
	
	///////////////CHECHING THE VALIDITY OF USER///////////////////
	public boolean isValidUser(String strUnm, String strPwd)
	{
		//establish connection
		Connection con=getDBConnection();
		boolean res=false;
		
		////specify objective
		try 
		{
			PreparedStatement stmt=con.prepareStatement("select * from user_account where username=? and password=?");
			stmt.setString(1, strUnm);
			stmt.setString(2, strPwd);
			ResultSet rset=stmt.executeQuery();
			if(rset.next())
			{
				res=true;
			}
			else
			{
				res=false;
			}
			con.close();
		} 
		catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		return res;
	}

    ////////////////PROVIDE NEWSLETTER//////////////////////////
	public void provideNewsletter()
	{
		
		JOptionPane.showMessageDialog(null, "You will recive all our latest updates through Kickstarter newsletter.");
		
	}

	
	///////////////GET MAX ID OF THE TABLES////////////////////
	public int getMaxId(String strColumn,String strTblName)
	{
		int id=0;
		Connection con=getDBConnection();
		try 
		{
			PreparedStatement stmt=con.prepareStatement("select max("+strColumn+") as id from "+strTblName);
			ResultSet rset=stmt.executeQuery();
			
			if(rset.next())
			{
				id=rset.getInt("id");
			}
			else
			{
				id=0;
			}
			con.close();
			rset.close();
		} 
		catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		return id;
	}
	
	

	/*
	ID                 NOT NULL NUMBER(38)    
	USERNAME           NOT NULL VARCHAR2(128) 
	PASSWORD           NOT NULL VARCHAR2(255) 
	EMAIL                       VARCHAR2(128) 
	PROJECTS_SUPPORTED          NUMBER(38)    
	TOTAL_AMOUNT                NUMBER(12,2)  
	COUNTRY_ID                  NUMBER(38)    
	PROVIDE_NEWSLETTER          VARCHAR2(5)   
	AGE                         NUMBER(38)
        */
	
	
	////////////////INSERT INTO USER_ACCOUNT/////////////////////////////
	public void insertUserAccount(String strId,String strUnm,String strPwd,String strEmail,int intProj_sup,int intTot_amount,int intCountry_id,String strNewsLetter,int intAge)
	{
		//establish connection
		Connection con=getDBConnection();
		
		//specify objective
		try 
		{
			PreparedStatement stmt=con.prepareCall("insert into user_account values(?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, strId);
			stmt.setString(2, strUnm);
			stmt.setString(3, strPwd);
			stmt.setString(4, strEmail);
			stmt.setInt(5,intProj_sup);
			stmt.setInt(6,intTot_amount);
			stmt.setInt(7, intCountry_id);
			stmt.setString(8, strNewsLetter);
			stmt.setInt(9, intAge);
			
			stmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Now you are register user to Kickstarter");
			con.close();
		}
		catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		catch(NumberFormatException e1)
		{
			e1.printStackTrace(); 
		}
	}
	
	
	////////////////////FETCHS THE USERNAME OF THE USER///////////////////////
	public Vector<String> getUnmFromUserAccount() 
	{
		

		Vector <String> vecUname=new Vector<String>();
		//establish connection
		Connection con=getDBConnection();
		
		//specify objective
		try 
		{
			PreparedStatement stmt=con.prepareStatement("select username from user_account");

			ResultSet rset=stmt.executeQuery();
			while(rset.next())
			{
				String strUname=rset.getString("username");
				vecUname.add(strUname);
			}
			con.close();
			rset.close();
		}
		catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		return vecUname;
	}

///////////////////////////////////////////////////////////////////////////////

	/*
	ID                 NOT NULL NUMBER(38)    
	USERNAME           NOT NULL VARCHAR2(128) 
	PASSWORD           NOT NULL VARCHAR2(255) 
	EMAIL                       VARCHAR2(128) 
	PROJECTS_SUPPORTED          NUMBER(38)    
	TOTAL_AMOUNT                NUMBER(12,2)  
	COUNTRY_ID                  NUMBER(38)    
	PROVIDE_NEWSLETTER          VARCHAR2(5)   
	AGE                         NUMBER(38)
        */
	////////////////////////////////////FETCHS ALL USER DETAILS///////////////////////
	
	

	
	public UserDetailsForUpdate getAllUserDetails(String strId)
	{
		UserDetailsForUpdate user=null;
		
		Connection con=getDBConnection();
		try 
		{
			PreparedStatement stmt=con.prepareStatement("select username, password, email,projects_supported,total_amount, country_id,provide_newsletter,age from user_account where id=?");
		   
			stmt.setString(1, strId);

			ResultSet rset=stmt.executeQuery();

			if(rset.next())
			{

				String strUnm,strPwd,strEmail,strNewsLetter;
				int intProj_sup = 0,intTot_amount = 0,intCountry_id = 0, intAge = 0;
				
				strUnm=rset.getString("username");
				strPwd=rset.getString("password");
				strEmail=rset.getString("email");
				strNewsLetter=rset.getString("provide_newsletter");
				intProj_sup=rset.getInt("projects_supported");
				intTot_amount=rset.getInt("total_amount");
				intCountry_id=rset.getInt("country_id");
				intAge=rset.getInt("age");
				
				user=new UserDetailsForUpdate(strId,strUnm, strPwd, strEmail,intProj_sup,intTot_amount, intCountry_id,strNewsLetter,intAge);

			System.out.println(strId+" "+ strUnm+" "+strPwd+" "+strEmail+" "+intProj_sup+" "+intTot_amount+" "+intCountry_id+" "+strNewsLetter+" "+intAge );
			System.out.println("class user details"+ user);
			}
			con.close();
			rset.close();
		} 
		catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		return user;
	}
	/*create table user_account(
	        id int primary key,
	        username varchar(128) unique not null,
	        password varchar(255) not null,
	        email varchar(128),
	        projects_supported int,
	        total_amount decimal(12,2),
	        country_id int references country(id)
	               
        alter table user_account add provide_newsletter varchar2(5);
        alter table user_account add age int;
	        );
	        */
	
	
	//////////////////UPDATE USER ACCOUNT///////////////////////////////////
	
	public void UpdateUserAccount(String strUserId,String strUnm, String strPwd, String strEmail,int intProj_sup,int intTot_amount,int intCountry_id,String strNewsletter,int intAge)
	{
		//establish connection
				Connection con=getDBConnection();
				
				//specify objective
				try 
				{
					System.out.println(intProj_sup+" "+intTot_amount);
					PreparedStatement stmt=con.prepareStatement("update user_account set username=?,password=?,email=?,projects_supported=?,total_amount=?,country_id=?,provide_newsletter=?,age=? where id=?");
					stmt.setString(1, strUserId);
					stmt.setString(2, strUnm);
					stmt.setString(3, strPwd);
					stmt.setString(4, strEmail);
					stmt.setInt(5,intProj_sup);
					stmt.setInt(6,intTot_amount);
					stmt.setInt(7, intCountry_id);
					stmt.setString(8, strNewsletter);
					stmt.setInt(9, intAge);
					
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Your details are updated");
					stmt.close();
					con.close();
				}
				catch (SQLException e)
				{
					
					e.printStackTrace();
				}
		
	}
	
	
	///////////////////////////UPDATE PASSWORD BY EMAIL ADDRESS/////////////////////////////////
	public void UpdatePwdOfUserAccountByEmail(String strUserId,String strEmail,String strPwd)
	{
		//establish connection
				Connection con=getDBConnection();
				
				//specify objective
				try 
				{
					PreparedStatement stmt=con.prepareStatement("update user_account set password=? where id=? and email=?");
					stmt.setString(2,strUserId);
					stmt.setString(3,strEmail);
					stmt.setString(1,strPwd);
				
					stmt.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Your details are updated");
					stmt.close();
					con.close();
					
				}
				catch (SQLException e)
				{
					
					e.printStackTrace();
				}
		
	}
	//////////////////////////UPDATE PROFILE OF USER BY ID///////////////////////////////////

	public void UpdateProfileOfUserAccountById(String strUserId, String strEmail, String strName, String strAge) 
	{

		//establish connection
				Connection con=getDBConnection();
				
				//specify objective
				try 
				{
					
					PreparedStatement stmt=con.prepareStatement("update user_account set email=?, username=?, age=? where id=?");
					stmt.setString(4,strUserId);
					stmt.setString(1,strEmail);
					stmt.setString(2,strName);
					stmt.setString(3,strAge);
				
					stmt.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Your details are updated");
					stmt.close();
					con.close();
				}
				catch (SQLException e)
				{
					
					e.printStackTrace();
				}
		
	}

	//////////////////////////GETS ID OF USER FROM USER_ACCOUNT//////////////////////////////(used if FundPoject class)
	public String getUnm_IdFromUser_Account(String strUnm) 
	{
		String strid = null;

		//establish connection
		Connection con=getDBConnection();
		//specify objective
		try 
		{
			PreparedStatement stmt=con.prepareStatement("select id from user_account where username=?");
			stmt.setString(1, strUnm);
			
			ResultSet rset=stmt.executeQuery();
			if(rset.next())
			{
			strid=String.valueOf(rset.getInt("id"));
			}
			con.close();
		}
		catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		return strid;
		
	}
	
	public String getUnm_nameFromUser_Account(String strId) 
	{
		String strUnm = null;

		//establish connection
		Connection con=getDBConnection();
		//specify objective
		try 
		{
			PreparedStatement stmt=con.prepareStatement("select username from user_account where id=?");
			stmt.setString(1, strId);
			
			ResultSet rset=stmt.executeQuery();
			if(rset.next())
			{
			strUnm=String.valueOf(rset.getString("username"));
			}
			con.close();
		}
		catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		return strUnm;
		
	}
	//////////////////////////////GETS PASSWORD FROM USERAccount//////////////////////////
	public String getPasswordFromUser_AccountById(String strId) 
	{
		strPwdofUser = null;

		//establish connection
		Connection con=getDBConnection();
		//specify objective
		try 
		{
			PreparedStatement stmt=con.prepareStatement("select password from user_account where id=?");
			stmt.setString(1, strId);
			
			ResultSet rset=stmt.executeQuery();
			if(rset.next())
			{
				strPwdofUser=String.valueOf(rset.getString("password"));
			}
			System.out.println(strPwdofUser+"lllllkkkk");
			stmt.close();
			con.close();
		}
		catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		return strPwdofUser;
		
	}
	
	/*
	 ID                  NOT NULL NUMBER(38)     
	 PROJECT_NAME                 VARCHAR2(255)  
	USER_ACCOUNT                 NUMBER(38)     
	ORGANIZATION_ID              NUMBER(38)     
	START_DATE                   DATE           
	END_DATE                     DATE           
	GOAL                         NUMBER(12,2)   
	INVESTORS                    NUMBER(38)     
	PROJ_STATUS_ID               NUMBER(38)     
	CATEGORY                     VARCHAR2(64)   
	PROJECT_DESCRIPTION          VARCHAR2(2000) 
	PLEDGED                      NUMBER(12,2)   
	FILELOCATION                 VARCHAR2(1000)
	 */

	
	
	////////////////////////////INSERT INTO PROJECT///////////////////////////////
	public void insertProject(String strId,String strProjName,int intUserAccount,int intorgId,Date strStartDt,Date strEndDt,int intGoal,
			int intInvestors,int intStatus,String strCategory,String strDesc,int intPledged,String strLoc)
	{
		//establish connection
		Connection con=getDBConnection();
		
		//specify objective
		try 
		{
			PreparedStatement stmt=con.prepareCall("insert into project values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, strId);
			stmt.setString(2, strProjName);
			stmt.setInt(3, intUserAccount);
			stmt.setInt(4, intorgId);
			stmt.setDate(5, strStartDt);
			stmt.setDate(6, strEndDt);
			stmt.setInt(7,intGoal);
			stmt.setInt(8,intInvestors);
			stmt.setInt(9, intStatus);
			stmt.setString(10, strCategory);
			stmt.setString(11, strDesc);
			stmt.setInt(12, intPledged);
			stmt.setString(13, strLoc);
			
			stmt.executeUpdate();

			
			con.close();
		}
		catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Your project has now been recorded on Kickstarter");
	}


	//"select max("+strColumn+") as id from "+strTblName
	
	///////////////////////GETS ALL PROJECT DETAILS OF A SPECIFIC CATEGORY//////////////////////////
		public LinkedList<ProjByCatgegory> getAllProjectsByCatISD(String strCategory)
		{
			ProjByCatgegory pbg=null;
			
			LinkedList<ProjByCatgegory> objListProjByCat=new LinkedList<>();
				Connection con=getDBConnection();
				try 
				{
					PreparedStatement stmt=con.prepareStatement("select id, project_name, category, project_description, pledged, goal ,start_date ,end_date,filelocation from project where category=?");
				   
					stmt.setString(1, strCategory);
					
					ResultSet rset=stmt.executeQuery();
					
						while(rset.next())
						{
							String projectName,Category,projectDescription,File_loc;
							Date StartDt,EndDt;
							int Pledged,Goal,projectId;
							
							projectId=rset.getInt("id");
							projectName=rset.getString("project_name");
							Category=rset.getString("category");
							projectDescription=rset.getString("project_description");
							Pledged=rset.getInt("pledged");
							Goal=rset.getInt("goal");
							StartDt = rset.getDate("start_date");
							EndDt=rset.getDate("end_date");
							File_loc=rset.getString("filelocation");
							pbg=new ProjByCatgegory(projectId,projectName,Category,projectDescription,Pledged,Goal,StartDt,EndDt,File_loc);
						System.out.println(projectId+" "+projectName+" "+Category+" "+projectDescription+" "+Pledged+" "+Goal+" "+StartDt+" "+EndDt+" "+File_loc);
						stat++;
						objListProjByCat.add(pbg);
						
						}
						
						rset.close();
						con.close();
				} 
				
				catch (SQLException e)
				{
					
					e.printStackTrace();
				}
				return objListProjByCat;
			}
		
		///////////////////////get the details of a single project////////////
		public ProjByCatgegory getDetailsOfaProject(String strprojName)
		{
			ProjByCatgegory pbg1=null;
			
			Connection con=getDBConnection();
			try 
			{
				PreparedStatement stmt=con.prepareStatement("select id, project_description, pledged, goal ,start_date ,end_date,filelocation from project where project_name=?");
			   
				stmt.setString(1,strprojName);

				ResultSet rset=stmt.executeQuery();

				if(rset.next())
				{
					String projectName,Category,projectDescription,File_loc;
					Date StartDt,EndDt;
					int Pledged,Goal,projectId;
					
					projectId=rset.getInt("id");
					projectDescription=rset.getString("project_description");
					Pledged=rset.getInt("pledged");
					Goal=rset.getInt("goal");
					
					pbg1=new ProjByCatgegory(projectId,Pledged,Goal);

	
				}
				con.close();
				rset.close();
			} 
			catch (SQLException e)
			{
				
				e.printStackTrace();
			}
			return pbg1;
		}
//////////////projects supported ie backed by user//////////////////////////////////
		public LinkedList<ProjByCatgegory> getAllProjectsByUserIdSuppByUser(String strUserId)
		{
			ProjByCatgegory pbg=null;
			
			LinkedList<ProjByCatgegory> objListProjByCat=new LinkedList<>();
				Connection con=getDBConnection();
				try 
				{
					PreparedStatement stmt=con.prepareStatement("select project_name,pledged, goal from project where user_account=?");
				   
					stmt.setString(1, strUserId);
					
					ResultSet rset=stmt.executeQuery();
					
						while(rset.next())
						{
							String projectName;
							int Pledged,Goal;
							
							projectName=rset.getString("project_name");
							Pledged=rset.getInt("pledged");
							Goal=rset.getInt("goal");
							pbg=new ProjByCatgegory(projectName,Pledged,Goal);
						
						objListProjByCat.add(pbg);
						
						}
						rset.close();
						con.close();
				} 
				
				catch (SQLException e)
				{
					
					e.printStackTrace();
				}
				return objListProjByCat;
			}
		
		
	
	////PROJECTS OF A USER subbmitted by uder///////////////////////////////////////////////////
		public LinkedList<ProjByCatgegory> myProjects(String strUserAccount)
		{
			ProjByCatgegory pbg=null;
			
			LinkedList<ProjByCatgegory> objListProjByCat=new LinkedList<>();

			//establish connection
			Connection con=getDBConnection();
			boolean res=false;
			
			
			//specify objective
			try 
			{
				PreparedStatement stmt=con.prepareStatement("select project_name from project where user_account=?");
				stmt.setString(1, strUserAccount);
				ResultSet rset=stmt.executeQuery();
				while(rset.next())
				{
					String strProjName;
					strProjName=rset.getString("project_name");
					myProjCounter++;
					pbg=new ProjByCatgegory(strProjName);
					
					objListProjByCat.add(pbg);
				}
				rset.close();
				con.close();
			}
			catch (SQLException e)
			{
				
				e.printStackTrace();
			}
			return objListProjByCat;
		}
/*
 * 
----------------- -------- ------------- 
ID                NOT NULL NUMBER(38)    
ORGANIZATION_NAME          VARCHAR2(200) 
*/

	/////////////////////////INSERT INTO ORGANIZATION////////////////////////////////////
	public void insertOrg(String strId,String strOrgName)
	{
		//establish connection
		Connection con=getDBConnection();
		
		//specify objective
		try 
		{
			PreparedStatement stmt=con.prepareCall("insert into organization values(?,?)");
			stmt.setString(1, strId);
			stmt.setString(2, strOrgName);
			
			stmt.executeUpdate();

			
			con.close();
		}
		catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Your organization has been recorded on Kickstarter");
	}
/*
create table participant(
        id int primary key,
        name varchar(64),
        user_account int null references user_account(id),
        participated_in int,
        project_team references project_team(id),
        role_id int references role(id),
        organization_id int references organization(id) 
        );*/
	
	
	
//	Name            Null     Type         
//	--------------- -------- ------------ 
//	ID              NOT NULL NUMBER(38)   
//	NAME                     VARCHAR2(64) 
//	USER_ACCOUNT             NUMBER(38)   
//	PARTICIPATED_IN          NUMBER(38)   
//	PROJECT_TEAM             NUMBER(38)   
//	ROLE_ID                  NUMBER(38)   
//	ORGANIZATION_ID          NUMBER(38)   

	
////////////////INSERT INTO PARTICIPANT TABLE///////////////////////
	public void insertParticipant(String strParticipantId,String strName,int intUserAccount,int intParticipated_in,int projectTeamId,int roleId,int org_id)
	{
		//establish connection
		Connection con=getDBConnection();
		
		//specify objective
		try 
		{
			PreparedStatement stmt=con.prepareStatement("insert into participant values (?,?,?,?,?,?,?)");
			stmt.setString(1, strParticipantId);
			stmt.setString(2, strName);
			stmt.setInt(3, intUserAccount);
			stmt.setInt(4, intParticipated_in);
			stmt.setInt(5, projectTeamId);
			stmt.setInt(6, roleId);
			stmt.setInt(7,org_id);
			String str="insert into participant values ("+strParticipantId+","+strName+","+intUserAccount+","+intParticipated_in+","+projectTeamId+","+roleId+","+org_id+")";
			System.out.println(str);
			
			System.out.println("iiiicccssddd    "+strParticipantId+"  "+strName+"  "+intUserAccount+"  "+intParticipated_in+"  "+"  "+projectTeamId+"  "+roleId+"  "+org_id);
			System.out.println("orgnaization id is"+org_id);
			stmt.executeUpdate();
			
			

			
			con.close();
		}
		catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "All paticipants has now been recorded on Kickstarter");
	}
	
	///////////////////////////GET ALL COUNTRY NAMES//////////////////////////
	public Vector <String> getAllCountries()
	{
		Vector <String> vecCname=new Vector<String>();
		//establish connection
		Connection con=getDBConnection();
		
		//specify objective
		try 
		{
			PreparedStatement stmt=con.prepareStatement("select country from country");

			ResultSet rset=stmt.executeQuery();
			while(rset.next())
			{
				String strCname=rset.getString("country");
				vecCname.add(strCname);
			}
			con.close();
			rset.close();
		}
		catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		return vecCname;
	}


	//////////////////////////GETS THE ORG ID FROM ORG////////////////////////
	public String getOrg_IdFromOrg(String strOrg) 
	{
		String strid = null;

		//establish connection
		Connection con=getDBConnection();
		//specify objective
		try 
		{
			PreparedStatement stmt=con.prepareStatement("select id from organization where organization_name=?");
			stmt.setString(1, strOrg);
			
			ResultSet rset=stmt.executeQuery();
			if(rset.next())
			{
			strid=String.valueOf(rset.getInt("id"));
			}
			con.close();
		}
		catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		return strid;
		
	}

/////////////////////////////////GETS ALL ORGANIZATION NAMES////////////////////
	public Vector<String> getAllOrganization() 
	{
		Vector <String> vecOname=new Vector<String>();
		//establish connection
		Connection con=getDBConnection();
		
		//specify objective
		try 
		{
			PreparedStatement stmt=con.prepareStatement("select distinct organization_name from organization");

			ResultSet rset=stmt.executeQuery();
			while(rset.next())
			{
				String stroname=rset.getString("organization_name");
				vecOname.add(stroname);
			}
			con.close();
			rset.close();
		}
		catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		return vecOname;
	}


/////////////////////////////////GET ALL ROLES FROM ROLE///////////////////////////
	public Vector<String> getRoles() 
	{
		Vector <String> vecRname=new Vector<String>();
		//establish connection
		Connection con=getDBConnection();
		
		//specify objective
		try 
		{
			PreparedStatement stmt=con.prepareStatement("select role_name from role");

			ResultSet rset=stmt.executeQuery();
			while(rset.next())
			{
				String strrname=rset.getString("role_name");
				vecRname.add(strrname);
			}
			con.close();
			rset.close();
		}
		catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		System.out.println(vecRname);
		return vecRname;
	}

/////////////////////////////gets role id of a role///////////////////////
	public String getRole_IdFromRoleByRole(String strRole) 
	{
		String strRoleid = null;

		//establish connection
		Connection con=getDBConnection();
		//specify objective
		try 
		{
			PreparedStatement stmt=con.prepareStatement("select id from role where role_name=?");
			stmt.setString(1, strRole);
			
			ResultSet rset=stmt.executeQuery();
			if(rset.next())
			{
			strRoleid=String.valueOf(rset.getInt("id"));
			}
			con.close();
		}
		catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		return strRoleid;
		
	}
	
//	Name          Null     Type          
//	------------- -------- ------------- 
//	ID            NOT NULL NUMBER(38)    
//	CARDNO                 NUMBER(20)    
//	NAME                   VARCHAR2(100) 
//	EXPIRYDT               DATE          
//	SECURITY_CODE          NUMBER(20)    
//	USER_ID                NUMBER(38) 
	
	public void insertIntoCardDetailsByUserId(String strId,long longCardNo,String strCardName,Date DtCardExp,long longCardSecCode,String strUserId)
	{
		//establish connection
		Connection con=getDBConnection();
		
		//specify objective
		try 
		{
			PreparedStatement stmt=con.prepareCall("insert into card_details values(?,?,?,?,?,?)");
			stmt.setString(1, strId);
			stmt.setLong(2, longCardNo);
			stmt.setString(3, strCardName);
			stmt.setDate(4, DtCardExp);
			stmt.setLong(5,longCardSecCode);
			stmt.setString(6, strUserId);
			
			stmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Your card is saved to Kickstarter");
			con.close();
		}
		catch (SQLException e)
		{
			
			e.printStackTrace();
		}
	}

	public void insertProj_team(int intprojTeamId, int intprojId, int intnoParticipant) 
	{
		//establish connection
	
		Connection con=getDBConnection();
		
		//specify objective
		try 
		{
			PreparedStatement stmt=con.prepareCall("insert into project_team values(?,?,?)");
			
			stmt.setInt(1,intprojTeamId);
			stmt.setInt(2,intprojId);
			stmt.setInt(3,intnoParticipant);
			
			
			stmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Your team is saved to Kickstarter");
			con.close();
		}
		catch (SQLException e)
		{
			
			e.printStackTrace();
		
		}
	}
//
//Name         Null     Type         
//------------ -------- ------------ 
//ID           NOT NULL NUMBER(38)   
//PROJECT_ID            NUMBER(38)   
//USER_ACCOUNT          NUMBER(38)   
//TS                    TIMESTAMP(6) 
//PLEDGED               NUMBER(12,2) 

	public void insertIntoProjInvestorByUserId(int intProjInvestorId, int projectId, String strUserId,Date dtstrTimeStampDt, int totAmt) 
	{
		//establish connection		
			Connection con=getDBConnection();			
			//specify objective
			try 
			{
				PreparedStatement stmt=con.prepareCall("insert into project_investor values(?,?,?,?,?)");
				
				stmt.setInt(1,intProjInvestorId);
				stmt.setInt(2,projectId);
				stmt.setString(3,strUserId);
				stmt.setDate(4,dtstrTimeStampDt);
				stmt.setInt(5,totAmt);
				
				stmt.executeUpdate();

				JOptionPane.showMessageDialog(null, "Your contibution is recorded");
				con.close();
			}
			catch (SQLException e)
			{
				
				e.printStackTrace();
			
			}
	}

	public void insetIntoProjStausHistory(int intProjStatushisId, int project_id, int projStatusId,java.util.Date currentDate) 
	{
		  Connection con=getDBConnection();			
		//specify objective
		 try 
		  {
			PreparedStatement stmt=con.prepareCall("insert into proj_status_history values(?,?,?,?)");
			
			stmt.setInt(1,intProjStatushisId);
			stmt.setInt(2,project_id);
			stmt.setInt(3,projStatusId);
			stmt.setDate(4,(Date) currentDate);;
			
			stmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Your contibution is recorded");
			con.close();
		}
		catch (SQLException e)
		{
			
			e.printStackTrace();
		
		}
		
	}

}


