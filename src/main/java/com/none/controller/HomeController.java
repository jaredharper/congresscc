package com.none.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.none.mapper.VoteMapper;

@Controller
public class HomeController {
	
	@Autowired
	VoteMapper voteMap;
	
    @RequestMapping("/home")
    @ResponseBody
    public String home() {
        return "Hello World!";
    }
    
    @RequestMapping("/bob")
    public String home2(Model model)
    {
		List<String> s = voteMap.getStates();
		model.addAttribute("states", s);    	
    	return "home";
    }
    
    @RequestMapping("/legislators")
    @ResponseBody
    public String legislators(@RequestParam String state)
    {
    	String response = new Gson().toJson(voteMap.getReps(state));
    	Logger.getAnonymousLogger().log(Level.INFO, response);
    	return response;
    }
    
}