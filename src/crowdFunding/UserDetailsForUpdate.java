package crowdFunding;

public class UserDetailsForUpdate 
{
	String strId,strUnm,strPwd,strEmail,strNewsLetter;
	int intProj_sup,intTot_amount,intCountry_id, intAge;
	
	public UserDetailsForUpdate(String strId,String strUnm,String strPwd,String strEmail,int intProj_sup,int intTot_amount,int intCountry_id,String strNewsLetter,int intAge)
	{
		this.strId=strId;
		this.strUnm=strUnm;
		this.strPwd=strPwd;
		this.strEmail=strEmail;
		this.intProj_sup=intProj_sup;
		this.intTot_amount=intTot_amount;
		this.intCountry_id=intCountry_id;
		this.strNewsLetter=strNewsLetter;
		this.intAge=intAge;
	}

	public String getStrId() {
		return strId;
	}

	public void setStrId(String strId) {
		this.strId = strId;
	}

	public String getStrUnm() {
		return strUnm;
	}

	public void setStrUnm(String strUnm) {
		this.strUnm = strUnm;
	}

	public String getStrPwd() {
		return strPwd;
	}

	public void setStrPwd(String strPwd) {
		this.strPwd = strPwd;
	}

	public String getStrEmail() {
		return strEmail;
	}

	public void setStrEmail(String strEmail) {
		this.strEmail = strEmail;
	}

	public String getStrNewsLetter() {
		return strNewsLetter;
	}

	public void setStrNewsLetter(String strNewsLetter) {
		this.strNewsLetter = strNewsLetter;
	}

	private int getIntProj_sup() {
		return intProj_sup;
	}

	private void setIntProj_sup(int intProj_sup) {
		this.intProj_sup = intProj_sup;
	}

	private int getIntTot_amount() {
		return intTot_amount;
	}

	private void setIntTot_amount(int intTot_amount) {
		this.intTot_amount = intTot_amount;
	}

	public int getIntCountry_id() {
		return intCountry_id;
	}

	public void setIntCountry_id(int intCountry_id) {
		this.intCountry_id = intCountry_id;
	}

	public int getIntAge() {
		return intAge;
	}

	public void setIntAge(int intAge) {
		this.intAge = intAge;
	}
	public void dispUserDetails()
	{
		System.out.println(strId + " "+strUnm+" "+strPwd+" "+strEmail+" "+intProj_sup+" "+intTot_amount+" "+intCountry_id+" "+strNewsLetter+" "+intAge);
	}

}
