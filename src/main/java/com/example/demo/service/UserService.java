package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import com.example.demo.model.Role;
import com.example.demo.model.User;

public interface UserService {

public void saveUser (User user) ;
	
	public boolean isUserAlreadyPresent(User user) ;
	public User getUser (String email);
	public Optional<User> getOneUser(int id_user);
	public void UpdateUser(User user);
	public List<User> findByName(String name,String colonne);
	public List<User> findAll();
	public List<Role> findAllrole();
	public List<User> findUserByRole(String role);
}
