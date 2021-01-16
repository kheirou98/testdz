package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	BCryptPasswordEncoder encoder ;
	@Autowired
	RoleRepository roleRepository ;
	@Autowired
	UserRepository userRepository ;

	
	@Override
	public void saveUser(User user) {
		 user.setPassword(encoder.encode(user.getPassword()));
         user.setStatus(1);
         Role userRole = roleRepository.findByRole(user.getRole()) ;
         user.setRoles(new HashSet <Role> (Arrays.asList(userRole)));
         userRepository.save(user);
        
         
	}
	@Override
	public void UpdateUser(User user) {
		String password= encoder.encode(user.getPassword());
		userRepository.UpdatePassword(password, user.getId());
	}
	@Override
	public List<User> findUserByRole(String role){
		Role role1 = roleRepository.findByRole(role);
		List<User> user = new ArrayList<User>();
		user = userRepository.findUserByRole(role1.getId());
		for (int i=0 ; i<user.size() ;  i++) {
		}
		return user;
	}
	
	@Override
	public boolean isUserAlreadyPresent(User user) {
	
		boolean isUserAlreadyExists = false;
		User existingUser = userRepository.findByEmail(user.getEmail());
		// If user is found in database, then then user already exists.
		if(existingUser != null){
			isUserAlreadyExists = true; 
		}
		return isUserAlreadyExists;
	}
	@Override
	public User getUser(String email) {
		return userRepository.findByEmail(email);
	};
	@Override
	public List<User> findAll(){ 
		return userRepository.findByStatus();
		
	};
	@Override
	public Optional<User> getOneUser(int id_user){
		return userRepository.findById(id_user);
	}
	@Override
	public List<User> findByName(String name, String colonne){
		
		List<User> users = new ArrayList<User>();
		if(colonne.contains("name")) {
		users = userRepository.findByNameLike("%"+name+"%");}
		if(colonne.contains("email")) {
			users = userRepository.findByEmailLike("%"+name+"%");
		}
		return users;
	}
	
	@Override
	public List<Role> findAllrole(){
		return roleRepository.findAll();
	}

	
	}
