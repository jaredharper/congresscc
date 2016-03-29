package com.none.pojo;

/**
 * POJO for a Legislator.  This contains their
 * id, name, party and state as well as a "count"
 * value for various votes/tallies/etc.
 * 
 * @author jthomas
 *
 */
public class Legislator implements Comparable<Legislator>
{
	private String id,name,count,party,state;

	public String getParty()
	{
		return party;
	}

	public void setParty(String party)
	{
		this.party = party;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getCount()
	{
		return count;
	}

	public void setCount(String count)
	{
		this.count = count;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public int compareTo(Legislator o)
	{
		int left = Integer.parseInt(this.count);
		int right = Integer.parseInt(o.getCount());
		
		if (left > right)
			return 1;
		else if (left < right)
			return -1;
		else
			return 0;
	}
	
}
