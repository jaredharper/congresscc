package com.none.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.none.mapper.VoteMapper;
import com.none.pojo.LegislatorSummary;

@Controller
public class SaraController
{
	
	
	@Autowired
	VoteMapper voteMap;
	
	@RequestMapping("/states")
	@ResponseBody
	public String getStates()
	{
    	return new Gson().toJson(voteMap.getStates());
	}

	@RequestMapping("/years")
	@ResponseBody
	public String getYears()
	{
    	return new Gson().toJson(voteMap.getYears());
	}
	
	@RequestMapping("/summary/{id}")
	@ResponseBody
	public String getSummary(@PathVariable String id, @PathVariable Integer year)
	{
		return new Gson().toJson(voteMap.getSummary(id,year));
	}	
	
	@RequestMapping("/top3")
	@ResponseBody
	public String getTopThree(@PathVariable Integer year)
	{
		HashMap<String, List<?>> tops = new HashMap<>();
		tops.put("success", voteMap.getTopThreeSuccess(year));
		tops.put("topGOP", voteMap.getTopThreeRep(year));
		tops.put("topDEM", voteMap.getTopThreeDem(year));
		return new Gson().toJson(tops);

	}
	
}
