package com.ecommerce.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.ecommerce.service.user.UserRegisterImpl;

@Controller
@RequestMapping
public class Home {
	
	@Autowired
	private UserRegisterImpl userRegisterImpl;

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