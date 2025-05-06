package com.api.carrental.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carrental.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{

	Optional<User> findByUsername(String username);

	Optional<User> findByUserId(Integer userId);


	//Optional<User> findByFullname(String fullname);

}
