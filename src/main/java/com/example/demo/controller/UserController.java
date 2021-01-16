package com.example.demo.controller;


import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	
	
	   
	/***********************************************Gestion des users*************************************/
	@GetMapping("/users") 
	private String ListUsers(Model model,@RequestParam(defaultValue="") String name ,@RequestParam(defaultValue="name") String colonne, Authentication authentication) {
		model.addAttribute("users", userService.findByName(name,colonne)) ;	
		String emailauth= authentication.getName();
		User utilisateur= userRepository.findByEmail(emailauth);
		model.addAttribute("utilisateur",utilisateur);
		return"views/list";
		
	}
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model, Authentication authentication) {
	    User user = userRepository.findByIdUser(id);
	     
	    String emailauth= authentication.getName();
		User utilisateur= userRepository.findByEmail(emailauth);
		model.addAttribute("utilisateur",utilisateur);
        int roleid = userRepository.findRoleByUser(user.getId());
		Role roleactuel = roleRepository.findById(roleid);
		model.addAttribute("roleact",roleactuel);
	    model.addAttribute("user", user);
	    model.addAttribute("roles", userService.findAllrole()) ;
	    return "views/edit_user";
	}
	
	@PostMapping("/update/{id}")
	public String updateUser(@PathVariable("id") int id, @Valid User user, 
	  BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        user.setId(id);
	        model.addAttribute("roles", userService.findAllrole()) ;
	        return "views/edit_user";
	    }
	         
	    userService.saveUser(user);
	    model.addAttribute("users",userService.findAll());
	    model.addAttribute("roles", userService.findAllrole()) ;
	    return "redirect:/users";
	}
	@RequestMapping(value="/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String deleteuser(int id , Model model) {
		User user = userRepository.findByIdUser(id);
	    user.setStatus(0);
	    System.out.println(id);
	    userRepository.save(user);
	    model.addAttribute("users", userRepository.findAll());
	    return "redirect:/users";
	}
	@GetMapping("/Cancel") 
	private String CancelModif(Model model) {
		
		return "redirect:/users";
		
	}
	 @RequestMapping("/getOneUser")
	 @ResponseBody
	 public Optional<User> getOne(Integer id){
		 return userService.getOneUser(id);
	 }
	 @RequestMapping(value="/updateUser", method = {RequestMethod.PUT, RequestMethod.GET})
	 public String update(User user ) {
		 userService.saveUser(user);
		 return "redirect:/saisie" ;
	 }
	 @RequestMapping(value="/updateUseradmin", method = {RequestMethod.PUT, RequestMethod.GET})
	 public String updateadmin(User user ) {
		 userService.saveUser(user);
		 return "redirect:/admin" ;
	 }
	 @RequestMapping(value="/updateUservalidateur", method = {RequestMethod.PUT, RequestMethod.GET})
	 public String updatevalidateur(User user ) {
		 userService.saveUser(user);
		 return "redirect:/validateur" ;
	 }
	 @RequestMapping(value="/updateUservisiteur", method = {RequestMethod.PUT, RequestMethod.GET})
	 public String updatevisiteur(User user) {
		 userService.saveUser(user);
		 return "redirect:/home" ;
	 }
	 
/*****************************************************************************************************************************/
	
	
}
