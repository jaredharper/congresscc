package com.none.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController
{

	@ExceptionHandler(value = {Exception.class, RuntimeException.class})
	public String err(Model model)
	{	
		model.addAttribute("message","I don't know what you did, but now you're here.  You should try doing whatever you did again and see if that works.  If it doesn't, consider doing something else.");
		return "err";
	}
	
	@ExceptionHandler(value = {NumberFormatException.class})
	public String nfe(Model model)
	{		
		model.addAttribute("message","Having a giggle with sql injection vulnerabilities are we.  No yuo.");
		return "err";
	}
}
