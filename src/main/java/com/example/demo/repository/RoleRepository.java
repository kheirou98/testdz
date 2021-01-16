package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	public Role findByRole(String role);
	public Role findById(int id);
	public List<Role> findAll();
	

}