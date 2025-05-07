package com.api.carrental.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carrental.model.User;

public interface AuthRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);

}
