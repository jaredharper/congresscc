package com.none.pojo;

public class DebateResponse
{

	private String response, speaker;
	private Integer id;
	private Double anger, joy, fear, disgust, sadness;

	public String getResponse()
	{
		return response;
	}

	public void setResponse(String response)
	{
		this.response = response;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Double getAnger()
	{
		return anger;
	}

	public void setAnger(Double anger)
	{
		this.anger = anger;
	}

	public Double getJoy()
	{
		return joy;
	}

	public void setJoy(Double joy)
	{
		this.joy = joy;
	}

	public Double getFear()
	{
		return fear;
	}

	public void setFear(Double fear)
	{
		this.fear = fear;
	}

	public Double getDisgust()
	{
		return disgust;
	}

	public void setDisgust(Double disgust)
	{
		this.disgust = disgust;
	}

	public Double getSadness()
	{
		return sadness;
	}

	public void setSadness(Double sadness)
	{
		this.sadness = sadness;
	}

	public String getSpeaker()
	{
		return speaker;
	}

	public void setSpeaker(String speaker)
	{
		this.speaker = speaker;
	}

}
