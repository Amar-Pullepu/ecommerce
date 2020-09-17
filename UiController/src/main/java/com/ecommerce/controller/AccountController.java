package com.ecommerce.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecommerce.service.user.UserRegisterImpl;

@Controller
@RequestMapping("/account")
public class AccountController {
	

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
	public ModelAndView postRegister(HttpServletRequest req, RedirectAttributes redirectAttributes) {
		
		String username = req.getParameter("username");
		String passwd = req.getParameter("password");
		String repasswd = req.getParameter("repassword");
		
		ModelAndView mv = new ModelAndView();
		
		if(passwd.equals(repasswd)) {
			try {
				userRegisterImpl.save(username, passwd);
				redirectAttributes.addFlashAttribute("message", "Account created, please login!");
				mv.setViewName("redirect:login");
			} catch (DataIntegrityViolationException e) {
				redirectAttributes.addFlashAttribute("message", "Error! Unable to create user, please use another username.");
				mv.setViewName("redirect:register");
			}
		}else {
			redirectAttributes.addFlashAttribute("message", "Password didn't match");
			mv.setViewName("redirect:register");
		}
		return mv;
	}
	
	@GetMapping(name = "login", value = "/login")
	public ModelAndView getLogin(HttpServletRequest req, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("title", "Login");
		mv.setViewName("login");
		
		return mv;
	}
	
	@GetMapping(name = "change_password", value = "/change-password")
	public ModelAndView getChangePasswd() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("title", "Password Change");
		mv.setViewName("changePasswd");
		return mv;
	}
	
	@PostMapping(name = "change_password", value = "/change-password")
	public ModelAndView postChangePasswd(HttpServletRequest req, HttpServletResponse res, RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView();
		
		String oldPasswd = req.getParameter("oldPasswd");
		String passwd = req.getParameter("password");
		String repasswd = req.getParameter("repassword");
		
		boolean success = false;
		if(passwd.equals(repasswd)) {
			success = userRegisterImpl.changePassword(req.getUserPrincipal().getName(), oldPasswd, repasswd);
			if(success)		redirectAttributes.addFlashAttribute("message", "Password changed successfully. Please login again!");
			else	redirectAttributes.addFlashAttribute("message", "Current password didn't match.");
		}
		else {
			redirectAttributes.addFlashAttribute("message", "\"new passwod\" and it's \"reenter\" didn't match.");
		}
		if(success) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
	              new SecurityContextLogoutHandler().logout(req, res, auth);
	        }
			mv.setViewName("redirect:/account/login");
		} else	mv.setViewName("redirect:change-password");
		return mv;
	}
	
	@GetMapping(name = "change_username", value = "/change-username")
	public ModelAndView getChangeUsername() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("title", "Username Change");
		mv.setViewName("changeUsername");
		return mv;
	}
	
	@PostMapping(name = "change_username", value = "/change-username")
	public ModelAndView postChangeUsername(HttpServletRequest req, HttpServletResponse res, RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView();
		String currUsername = req.getUserPrincipal().getName();
		String newUsername = req.getParameter("username");
		if(userRegisterImpl.changeUsername(currUsername, newUsername)) {
			redirectAttributes.addFlashAttribute("message", "Username changed succesfully, please login again!");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
	              new SecurityContextLogoutHandler().logout(req, res, auth);
	        }
			mv.setViewName("redirect:/account/login");
		}
		else {
			redirectAttributes.addFlashAttribute("message", "Error while changing username.");
			mv.setViewName("redirect:change-username");
		}
		return mv;
	}
	
	@GetMapping(name = "is_username_taken", value = "/is-username-taken")
	@ResponseBody
	public HashMap<String, String> isUsernameTaken(HttpServletRequest req){
		HashMap<String, String> response = new HashMap<>();
		String username = req.getParameter("username");
		if(userRegisterImpl.isUserPresent(username))	response.put("bool", "true");
		else	response.put("bool", "false");
		return response;
	}
}
