package com.none.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.none.mapper.VoteMapper;
import com.none.pojo.DebateResponse;
import com.none.pojo.Legislator;

/**
 * This class maps URLs to service endpoint logic
 * 
 * @author jthomas
 *
 */
@Controller
public class ServiceController
{

	@Autowired
	VoteMapper voteMap;

	/**
	 * Endpoint that provides a list of legislators by state
	 * 
	 * @param state - only show results from this state
	 * @param year - only show results for this year
	 * 
	 * @return JSONized list of com.none.pojo.Legislator objects
	 */
	@RequestMapping("/legislators/{year}/{state}")
	@ResponseBody
	public String legislators(@PathVariable String state, @PathVariable Integer year)
	{
		String response = new Gson().toJson(voteMap.getReps(state, year));
		return response;
	}

	/**
	 * Endpoint that gets legislators most and least like the identified
	 * legislator
	 * 
	 * @param id - id of legislator to be compared
	 * @param year - which year's data to use for the comparison
	 * 
	 * @return JSONized list of com.none.pojo.Legislator objects
	 */
	@RequestMapping("/similarity/{year}/{id}")
	@ResponseBody
	public String similarity(@PathVariable String id, @PathVariable Integer year)
	{
		HashMap<String, List<Legislator>> m = new HashMap<>();

		// The table where this data is stored contains pairs and their count.
		// i.e. Bob,Tom,123. We want Bob regardless of which column he's in.
		// TODO: refactor sql queries so this is one list
		List<Legislator> simsA = voteMap.getSims(id, year);
		List<Legislator> simsB = voteMap.getSimsReverse(id, year);
		simsA.addAll(simsB);
		Collections.sort(simsA);
		Collections.reverse(simsA);
		if (simsA.size() > 5)
			simsA = simsA.subList(0, 6);

		List<Legislator> dimsA = voteMap.getDims(id, year);
		List<Legislator> dimsB = voteMap.getDimsReverse(id, year);
		dimsA.addAll(dimsB);
		Collections.sort(dimsA);
		if (dimsA.size() > 5)
			dimsA = dimsA.subList(0, 6);

		m.put("sim", simsA);
		m.put("dis", dimsA);
		String response = new Gson().toJson(m);
		return response;
	}

	/**
	 * Endpoint for the "presidential candidates of 2016" comparison
	 * 
	 * @param id - id of legislator to be compared
	 * 
	 * @return JSONized list of com.none.pojo.Legislator objects
	 */
	@RequestMapping("/comparison/{year}/{id}")
	@ResponseBody
	public String candidates(@PathVariable String id, @PathVariable Integer year)
	{
		HashMap<String, List<Legislator>> m = new HashMap<>();
		m.put("sim", voteMap.getCandidates(id, year));
		String response = new Gson().toJson(m);
		return response;
	}

	/**
	 * Endpoint for getting a single legislator's bio
	 * 
	 * @param id - id of legislator
	 * 
	 * @return JSONized representation of a com.none.pojo.Legislator object
	 */
	@RequestMapping("/detail/{year}/{id}")
	@ResponseBody
	public String detail(@PathVariable String id, @PathVariable Integer year)
	{
		return new Gson().toJson(voteMap.getDetail(id, year));
	}

	/**
	 * Endpoint for states we have data on
	 * 
	 * @return JSONized list of states
	 */
	@RequestMapping("/states")
	@ResponseBody
	public String getStates()
	{
		return new Gson().toJson(voteMap.getStates());
	}

	/**
	 * Endpoint for years we have data on
	 * 
	 * @return JSONized list of valid years
	 */
	@RequestMapping("/years")
	@ResponseBody
	public String getYears()
	{
		return new Gson().toJson(voteMap.getYears());
	}

	/**
	 * Get voting record for the given year/legislator
	 * 
	 * @param id - target legislator
	 * @param year - target year
	 * 
	 * @return JSONized representation of a com.none.pojo.LegislatorSummary
	 *         object
	 */
	@RequestMapping("/summary/{year}/{id}")
	@ResponseBody
	public String getSummary(@PathVariable String id, @PathVariable Integer year)
	{
		return new Gson().toJson(voteMap.getSummary(id, year));
	}

	@RequestMapping("/top3/{year}")
	@ResponseBody
	public String getTopThree(@PathVariable Integer year)
	{
		HashMap<String, List<?>> tops = new HashMap<>();
		tops.put("success", voteMap.getTopThreeSuccess(year));
		tops.put("topGOP", voteMap.getTopThreeRep(year));
		tops.put("topDEM", voteMap.getTopThreeDem(year));
		return new Gson().toJson(tops);

	}
	
	@RequestMapping("/debate/{page}")
	@ResponseBody
	public String getDebatePage(@PathVariable Integer page)
	{
		int id = page * 1000;
		List<DebateResponse> responses = voteMap.getResponse(id, id+10);
		return new Gson().toJson(responses);
	}

}
