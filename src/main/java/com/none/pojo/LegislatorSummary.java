package com.none.pojo;

/**
 * POJO for a Legislator's vote summary.  It contains
 * their id, success (0.0-1.0), and the number of times
 * they voted with dem/gop/bi majorities.
 * 
 * @author jthomas
 *
 */
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
