package com.none.controller;

import java.util.Collections;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.none.mapper.VoteMapper;
import com.none.pojo.Legislator;
import com.none.pojo.LegislatorSummary;

/**
 * This class maps URLs to their backend components & views
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
    public String homePage(Model model, Device device)
    {
		List<String> s = voteMap.getStates();
		List<String> y = voteMap.getYears();
		List<Legislator> r = voteMap.getReps("CA",2015);
		model.addAttribute("states", s);    	
		model.addAttribute("years", y);
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
    @RequestMapping(value = "/leg/{year}/{id}", method = RequestMethod.GET)
    public String detailPage(@PathVariable String id, @PathVariable Integer year, Model model)
    {
    	try
    	{
    		voteMap.updateCount(id);
    	}
    	catch (Exception ex)
    	{
    		Logger.getAnonymousLogger().log(Level.SEVERE, "Couldn't update count");
    	}
    	LegislatorSummary sum = voteMap.getSummary(id,year);
    	Legislator detail = voteMap.getDetail(id,year);
		List<String> s = voteMap.getStates();
		List<Legislator> r = voteMap.getReps("CA",year);
		model.addAttribute("states", s);    	
		model.addAttribute("year", year);
		model.addAttribute("reps",r);    	
    	model.addAttribute("summary",sum);
    	model.addAttribute("id",id);
    	model.addAttribute("detail",detail);
    	return "detail";
    }
    
    /**
     * Mapping and data preload for the "top 3" views
     * 
     * If no year is provided, use 2015 as a default
     * 
     */
    @RequestMapping(value = "/top")
    public String topPageCurrent(Model model)
    {
    	return topPage(2015, model);
    }
    
    /**
     * Mapping and data preload for the "top 3" views
     * 
     */
    @RequestMapping(value = "/top/{year}")
    public String topPage(@PathVariable Integer year, Model model)
    {
    	List<Legislator> success = voteMap.getTopThreeSuccess(year);
    	List<Legislator> rep = voteMap.getTopThreeRep(year);
    	List<Legislator> dem = voteMap.getTopThreeDem(year);
		List<String> y = voteMap.getYears();
		model.addAttribute("years",y);
    	model.addAttribute("year",year);
    	model.addAttribute("top",success);
    	model.addAttribute("rep",rep);
    	model.addAttribute("dem",dem);
    	return "top";
    }
    
    /**
     * Mapping and data preload for the 2016 Presidental candidate comparison view
     * 
     *  If no year is provided, use 2015 as a default
     * 
     */
    @RequestMapping(value = "/candidates")
    public String compareCurrent(Model model)
    {
    	return compare(2015,model);
    }

    /**
     * Mapping and data preload for the 2016 Presidental candidate comparison view 
     * 
     */
    @RequestMapping(value = "/candidates/{year}")
    public String compare(@PathVariable Integer year, Model model)
    {
		List<String> s = voteMap.getStates();
		List<Legislator> r = voteMap.getReps("CA",year);
		List<String> y = voteMap.getYears();
		model.addAttribute("years", y);
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
        
}