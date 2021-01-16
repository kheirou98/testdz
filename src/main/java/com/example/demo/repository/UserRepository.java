package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	public User findByEmail(String email) ;
	@Query(value ="select * from auth_user e where e.auth_user_id= ?1",nativeQuery=true)
	public User findByIdUser(int id) ;
	@Query(value ="select * from auth_user e where e.username= ?1",nativeQuery=true)
	public User findByName(String name);
	@Transactional
	@Modifying
	@Query(value ="UPDATE auth_user SET password= :password WHERE auth_user_id= :id", nativeQuery=true)
	public void UpdatePassword(@Param("password")String password,@Param("id")int id);
	@Query(value ="select * from auth_user e where e.username= ?1",nativeQuery=true)
	public User findByLastName(String lastName);
	@Query(value ="select * from auth_user e where e.status=1 and e.username LIKE %?1%",nativeQuery=true)
	public List<User> findByNameLike(String name);
	@Query(value ="select * from auth_user e where e.status=1 and e.email LIKE %?1%",nativeQuery=true)
	public List<User> findByEmailLike(String name);
	@Query(value ="select * from auth_user e where e.username= ?1",nativeQuery=true)
	public User findByNom(String lastName);
	public List<User> findAll();
	 @Query(value ="select * from auth_user e where e.status=1",nativeQuery=true)
	 List<User> findByStatus () ;
	 @Query(value ="SELECT u.* FROM auth_user as u , auth_user_role as r WHERE u.auth_user_id = r.auth_user_id and r.auth_role_id= ?1",nativeQuery=true)
	 public List<User> findUserByRole (int role) ;
	 @Query(value ="SELECT u.auth_role_id FROM auth_user_role u WHERE u.auth_user_id= ?1",nativeQuery=true)
	 public int findRoleByUser(int user) ;
	 
	
}
