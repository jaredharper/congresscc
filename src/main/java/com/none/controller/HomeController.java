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

@Controller
public class HomeController {
	
	@Autowired
	VoteMapper voteMap;
	
    @RequestMapping("/home")
    @ResponseBody
    public String home() 
    {
        return "Hello World!";
    }
    
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
    
    @RequestMapping("/legislators")
    @ResponseBody
    public String legislators(@RequestParam String state)
    {
    	String response = new Gson().toJson(voteMap.getReps(state));
    	return response;
    }
    
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
    
    @RequestMapping("/detail")
    @ResponseBody
    public String detail(@RequestParam String id)
    {
    	return new Gson().toJson(voteMap.getDetail(id));
    }
    
    @RequestMapping(value = "/leg")
    public String detailPage(@RequestParam String id, Model model)
    {
    	model.addAttribute("id",id);
    	return "detail";
    }
    
}