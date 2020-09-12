package com.ecommerce.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecommerce.service.user.UserRegisterImpl;

@Controller
@RequestMapping("/account")
public class AccountControler {
	

	@Autowired
	private UserRegisterImpl userRegisterImpl;
	
	@GetMapping(name = "register", value = "/register")
	public ModelAndView getRegister() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("title", "Register");
		mv.setViewName("register");
		return mv;
	}

	@PostMapping(name = "register", value = "/register")
	public ModelAndView postRegister(HttpServletRequest req) {
		
		String username = req.getParameter("username");
		String passwd = req.getParameter("password");
		String repasswd = req.getParameter("repassword");
		
		ModelAndView mv = new ModelAndView();
		
		if(passwd.equals(repasswd)) {
			userRegisterImpl.save(username, passwd);
			mv.setViewName("redirect:/");
		}
		else	mv.setViewName("redirect:register");
		return mv;
	}
	
	@GetMapping(name = "login", value = "/login")
	public ModelAndView getLogin(HttpServletRequest req, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView();
		
		if(req.getUserPrincipal() == null) {
			mv.addObject("title", "Login");
			mv.setViewName("login");
		}
		else {
			redirectAttributes.addFlashAttribute("message", "Already logged in!");
			mv.setViewName("redirect:/");
		}
		return mv;
	}
}
