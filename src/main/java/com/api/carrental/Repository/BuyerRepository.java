package com.api.carrental.Repository;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carrental.model.Buyer;

public interface BuyerRepository extends JpaRepository<Buyer, Integer> {

	//List<Buyer> getByCustomerId(int cId);

}
