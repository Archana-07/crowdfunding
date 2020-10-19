package crowdFunding;

import java.sql.Date;

public class ProjByCatgegory
{
	String project_name,category,project_description,file_loc;
	int pledged,goal,project_id;
	 Date startDate, endDate;
	
	public ProjByCatgegory(int projId,String project_name,String category,String project_description,int pledged,int goal,Date startDate,Date endDate,String file_loc)
	{
		this.project_id=project_id;
		this.project_name=project_name;
		this.category=category;
		this.project_description=project_description;
		this.pledged=pledged;
		this.goal=goal;
		this.startDate=startDate;
		this.endDate=endDate;
		this.file_loc=file_loc;
	}

	public ProjByCatgegory(String project_name)
	{
		this.project_name=project_name;
	}
	public ProjByCatgegory(String project_name,int pledged,int goal)
	{
		this.project_name=project_name;
		this.pledged=pledged;
		this.goal=goal;
	}
	public ProjByCatgegory(int projectId, int pledged2, int goal2) 
	{
		this.project_id=project_id;
		this.pledged=pledged;
		this.goal=goal;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProject_description() {
		return project_description;
	}

	public void setProject_description(String project_description) {
		this.project_description = project_description;
	}

	public int getPledged() {
		return pledged;
	}

	public void setPledged(int pledged) {
		this.pledged = pledged;
	}
	public void projDetailsDisplay()
	{
		System.out.println(project_name+" "+project_description+" "+category+" "+pledged);
	}
	public void printAll()
	{
		
	}
	
}
