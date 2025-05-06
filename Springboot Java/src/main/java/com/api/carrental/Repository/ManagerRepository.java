package com.api.carrental.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carrental.model.Manager;



public interface ManagerRepository extends JpaRepository<Manager, Integer> {

}