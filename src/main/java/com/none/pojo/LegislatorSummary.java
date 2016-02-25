package com.none.pojo;

public class LegislatorSummary
{
	String id;
	Double success;
	Integer demvotes,repvotes,bivote;
	
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public Double getSuccess()
	{
		return success;
	}
	public void setSuccess(Double success)
	{
		this.success = success;
	}
	public Integer getDemvotes()
	{
		return demvotes;
	}
	public void setDemvotes(Integer dem)
	{
		this.demvotes = dem;
	}
	public Integer getRepvotes()
	{
		return repvotes;
	}
	public void setRepvotes(Integer rep)
	{
		this.repvotes = rep;
	}
	public Integer getBivote()
	{
		return bivote;
	}
	public void setBivote(Integer bi)
	{
		this.bivote = bi;
	}
}
