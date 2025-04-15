package com.api.carrental.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carrental.model.ReviewFeedback;

public interface ReviewFeedbackRepository extends JpaRepository<ReviewFeedback, Integer> {


	List<ReviewFeedback> getByRating(int rating);

	List<ReviewFeedback> findByCustomerId(Long cId);

}
