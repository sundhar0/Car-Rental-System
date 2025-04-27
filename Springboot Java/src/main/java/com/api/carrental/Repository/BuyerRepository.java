package com.api.carrental.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carrental.model.Buyer;

public interface BuyerRepository extends JpaRepository<Buyer, Integer> {

	//List<Car> findByCarYear(String year);

	//List<Buyer> getByCustomerId(int cId);

}
