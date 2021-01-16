package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Controller
public class AuthenticationController {
	
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login"); // resources/template/login.html
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(Authentication authentication) {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		String emailauth= authentication.getName();
		User utilisateur= userRepository.findByEmail(emailauth);
		modelAndView.addObject("utilisateur",utilisateur);
		modelAndView.addObject("user", user); 
		modelAndView.addObject("roles", userService.findAllrole()) ;
		modelAndView.setViewName("register"); // resources/template/register.html
		return modelAndView;
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(Authentication authentication) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home"); // resources/template/home.html
		String emailauth= authentication.getName();
		User utilisateur= userRepository.findByEmail(emailauth);
		modelAndView.addObject("user",utilisateur);
		int roleid = userRepository.findRoleByUser(utilisateur.getId());
		Role roleactuel = roleRepository.findById(roleid);
		modelAndView.addObject("roleact",roleactuel);
		modelAndView.addObject("roles", userService.findAllrole()) ;

		return modelAndView;
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminHome(Authentication authentication ) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin");// resources/template/admin.html
		String emailauth= authentication.getName();
		User user= userRepository.findByEmail(emailauth);
		modelAndView.addObject("user",user);
		int roleid = userRepository.findRoleByUser(user.getId());
		Role roleactuel = roleRepository.findById(roleid);
		modelAndView.addObject("roleact",roleactuel);
		modelAndView.addObject("roles", userService.findAllrole()) ;
		return modelAndView;
	}
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView SaisieHome(Authentication authentication) {
		ModelAndView modelAndView = new ModelAndView();
		String emailauth= authentication.getName();
		User utilisateur= userRepository.findByEmail(emailauth);
		int roleid = userRepository.findRoleByUser(utilisateur.getId());
		Role roleactuel = roleRepository.findById(roleid);
		modelAndView.addObject("roleact",roleactuel);
		modelAndView.addObject("user",utilisateur);
		modelAndView.addObject("roles", userService.findAllrole()) ;
		modelAndView.setViewName("new"); // resources/template/new.html
		return modelAndView;
	}
	@RequestMapping(value = "/superuser", method = RequestMethod.GET)
	public ModelAndView ValidateurHome(Authentication authentication) {
		ModelAndView modelAndView = new ModelAndView();
		String emailauth= authentication.getName();
		User utilisateur= userRepository.findByEmail(emailauth);	
		modelAndView.addObject("user",utilisateur);
		int roleid = userRepository.findRoleByUser(utilisateur.getId());
		Role roleactuel = roleRepository.findById(roleid);
		modelAndView.addObject("roleact",roleactuel);
		modelAndView.addObject("roles", userService.findAllrole()) ;
		modelAndView.setViewName("superuser"); // resources/template/superuser.html
		return modelAndView;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerUser (@Valid User user, BindingResult bindingResult,ModelMap modelMap,Authentication authentication) {
		ModelAndView modelAndView = new ModelAndView();
		//check for the validation
		if(bindingResult.hasErrors()) {
			modelAndView.addObject("successMessage", "Please Correct the error in form!");
			modelMap.addAttribute("bindingResult", bindingResult);
			modelAndView.addObject("roles", userService.findAllrole()) ;
			String emailauth= authentication.getName();
			User utilisateur= userRepository.findByEmail(emailauth);
			modelAndView.addObject("utilisateur",utilisateur);
			modelAndView.setViewName("register");
			return modelAndView ;
			
		}
		// we will save the user if no error
		else if(userService.isUserAlreadyPresent(user)){
			modelAndView.addObject("successMessage", "User already exist!");
		}
		else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User is register succeful!");
		}
		String emailauth= authentication.getName();
		User utilisateur= userRepository.findByEmail(emailauth);
		modelAndView.addObject("utilisateur",utilisateur);
		modelAndView.addObject("user",new User());
		modelAndView.addObject("roles", userService.findAllrole());
		modelAndView.setViewName("register");
		return modelAndView ;
		
	}
}