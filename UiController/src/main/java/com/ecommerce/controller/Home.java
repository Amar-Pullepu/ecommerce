package com.ecommerce.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class Home {

	@GetMapping(name = "home", value = "/")
	public ModelAndView root(Principal principal) {
		ModelAndView mv = new ModelAndView();
		if(principal != null)
		mv.addObject("name", principal.getName());
		mv.addObject("title", "Home");
		mv.setViewName("home");
		return mv;
	}
	
}