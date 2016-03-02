package com.none.controller;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.none.mapper.VoteMapper;
import com.none.pojo.Legislator;
import com.none.pojo.LegislatorSummary;

/**
 * This class maps relative URLs to their
 * corresponding backend components and/or their
 * matching views (JSPs)
 * 
 * @author jthomas
 *
 */
@Controller
public class HomeController {
	
	@Autowired
	VoteMapper voteMap;
    
	/**
	 * Mapping and data preload for the home page view
	 * 
	 * @param model - Object to store preloaded data used by the JSP when rendering
	 * @param device - Spring object to determine mobile/desktop/etc
	 * 
	 * @return - String name of view to display
	 */
    @RequestMapping("/")
    public String home2(Model model, Device device)
    {
		List<String> s = voteMap.getStates();
		List<Legislator> r = voteMap.getReps("CA");
		model.addAttribute("states", s);    	
		model.addAttribute("reps",r);
		model.addAttribute("isMobile",device.isMobile());
		model.addAttribute("isTablet",device.isTablet());
		model.addAttribute("isNormal",device.isNormal());
    	return "home";
    }

    /**
     * Mapping and data preload for the legislator's detail view
     * 
     * @param id - GET/POST variable containing the legislator's id
     */
    @RequestMapping(value = "/leg")
    public String detailPage(@RequestParam String id, Model model)
    {
    	try
    	{
    		voteMap.updateCount(id);
    	}
    	catch (Exception ex)
    	{
    		Logger.getAnonymousLogger().log(Level.SEVERE, "Couldn't update count");
    	}
    	LegislatorSummary sum = voteMap.getSummary(id);
    	model.addAttribute("summary",sum);
    	model.addAttribute("id",id);
    	return "detail";
    }
    
    /**
     * Mapping and data preload for the "top 3" view
     * 
     */
    @RequestMapping(value = "/top")
    public String topPage(Model model)
    {
    	List<Legislator> success = voteMap.getTopThreeSuccess();
    	List<Legislator> rep = voteMap.getTopThreeRep();
    	List<Legislator> dem = voteMap.getTopThreeDem();
    	model.addAttribute("top",success);
    	model.addAttribute("rep",rep);
    	model.addAttribute("dem",dem);
    	return "top";
    }
    
    /**
     * Mapping and data preload for the 2016 Presidental candidate comparison view 
     * 
     */
    @RequestMapping(value = "/candidates")
    public String compare(Model model)
    {
		List<String> s = voteMap.getStates();
		List<Legislator> r = voteMap.getReps("CA");
		model.addAttribute("states", s);   	
    	model.addAttribute("reps",r);
    	return "candidate";
    }
    
    /**
     * Mapping for the "about this site" view
     * 
     */
    @RequestMapping(value = "/about")
    public String aboutPage()
    {
    	return "about";
    }
    
    /**
     * Mapping for the "contact page" view
     * 
     */
    @RequestMapping(value = "/contact")
    public String contactPage()
    {
    	return "contact";
    }
    
    /**
     * Mapping for the service endpoint that provides a list
     * of legislators by state
     * 
     * @param state - only show reps from this state
     *  
     * @return JSONized list of com.none.pojo.Legislator objects
     */
    @RequestMapping("/legislators")
    @ResponseBody
    public String legislators(@RequestParam String state)
    {
    	String response = new Gson().toJson(voteMap.getReps(state));
    	return response;
    }
    
    /**
     * Service endpoint that gets legislators most and least like the 
     * identified legislator
     * 
     * @param id - id of legislator to be compared
     * 
     * @return JSONized list of com.none.pojo.Legislator objects
     */
    @RequestMapping("/similarity")
    @ResponseBody
    public String similarity(@RequestParam String id)
    {
    	HashMap<String, List<Legislator>> m = new HashMap<>();
    	m.put("sim", voteMap.getSims(id));
    	m.put("dis", voteMap.getDims(id));
    	String response = new Gson().toJson(m);
    	return response;
    }
    
    /**
     * Service endpoint for the "presidential candidates of 2016" comparison
     * 
     * @param id - id of legislator to be compared
     * 
     * @return JSONized list of com.none.pojo.Legislator objects
     */
    @RequestMapping("/comparison")
    @ResponseBody
    public String candidates(@RequestParam String id)
    {
    	HashMap<String, List<Legislator>> m = new HashMap<>();
    	m.put("sim", voteMap.getCandidates(id));
    	String response = new Gson().toJson(m);
    	return response;
    }
    
    /**
     * Service endpoint for getting a single legislator's detailed information
     * 
     * @param id - id of legislator
     * 
     * @return JSONized representation of a com.none.pojo.Legislator object
     */
    @RequestMapping("/detail")
    @ResponseBody
    public String detail(@RequestParam String id)
    {    	
    	return new Gson().toJson(voteMap.getDetail(id));
    }
    
}