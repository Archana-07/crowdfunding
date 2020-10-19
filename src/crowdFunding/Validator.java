package crowdFunding;

import java.awt.Container;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Validator 
{
// checking the validation of numerical fields
	public static int numberValidation(String strNumber)
	{
		try
		{
			
			Integer.parseInt(strNumber);
			return 1;
		}
		catch(NumberFormatException nfe)
		{
			int dialougeRes=JOptionPane.showConfirmDialog(null,"Please enter valid amount","TRY AGAIN", JOptionPane.OK_CANCEL_OPTION);
			return dialougeRes;
		}
		
	}
	
	public static int stringValidation(String strString)
	{
		Pattern number=Pattern.compile("^[a-zA-Z]+$");
		Matcher matcher=number.matcher(strString);
		if(matcher.find())
		{
			return 1;
		}
		else
		{ 
			int dialougeRes=JOptionPane.showConfirmDialog(null,"Please enter valid name","TRY AGAIN", JOptionPane.OK_CANCEL_OPTION);
			return dialougeRes;
		}
		
	}
	public static int isTextEnteredValidation(String strString,String strString1,String strString2,String strString3)
	{
		
		if(strString.equals("")||strString.equals(null)||strString1.equals("")||strString1.equals(null)||strString2.equals("")||strString2.equals(null)||strString3.equals("")||strString3.equals(null))
		{
			int dialougeRes=JOptionPane.showConfirmDialog(null,"Please fill in missing details","TRY AGAIN", JOptionPane.OK_CANCEL_OPTION);
			return dialougeRes;
		}
		else
		{
			return 1;
		}
	}
	
	public static boolean isValidEmailValidation(String strStringEmail)
	{
		String email_regex="[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		boolean b=strStringEmail.matches(email_regex);
		
		if(b==false)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	 
	
}
