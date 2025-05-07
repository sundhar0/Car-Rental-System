package com.api.carrental.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.ReviewFeedbackRepository;
import com.api.carrental.Service.CarService;
import com.api.carrental.Service.CustomerService;
import com.api.carrental.Service.ReviewFeedbackService;
import com.api.carrental.model.Car;
import com.api.carrental.model.Customer;
import com.api.carrental.model.ReviewFeedback;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReviewFeedbackServiceTest {

    @Mock
    private ReviewFeedbackRepository reviewFeedbackRepository;

    @Mock
    private CarService carService;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private ReviewFeedbackService reviewFeedbackService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetByReview() throws InvalidIDException {
        int customerId = 1;
        ReviewFeedback feedback1 = new ReviewFeedback();
        ReviewFeedback feedback2 = new ReviewFeedback();
        List<ReviewFeedback> feedbackList = Arrays.asList(feedback1, feedback2);

        Customer customer = new Customer();
        when(customerService.getSingleCustomer(customerId)).thenReturn(customer);
        when(reviewFeedbackRepository.findByCustomerId(customerId)).thenReturn(feedbackList);

        List<ReviewFeedback> result = reviewFeedbackService.getByReview(customerId);
        assertEquals(2, result.size());
        verify(customerService).getSingleCustomer(customerId);
        verify(reviewFeedbackRepository).findByCustomerId(customerId);
    }

    @Test
    void testAddReview() throws InvalidIDException {
        int carId = 1;
        int customerId = 2;

        Car car = new Car();
        Customer customer = new Customer();
        ReviewFeedback reviewFeedback = new ReviewFeedback();

        when(carService.getById(carId)).thenReturn(car);
        when(customerService.getSingleCustomer(customerId)).thenReturn(customer);
        when(reviewFeedbackRepository.save(any(ReviewFeedback.class))).thenAnswer(i -> i.getArgument(0));

        ReviewFeedback savedFeedback = (ReviewFeedback) reviewFeedbackService.addReview(carId, customerId, reviewFeedback);
        assertEquals(car, savedFeedback.getCar());
        assertEquals(customer, savedFeedback.getCustomer());

        verify(carService).getById(carId);
        verify(customerService).getSingleCustomer(customerId);
        verify(reviewFeedbackRepository).save(reviewFeedback);
    }

    @Test
    void testGetByRating() {
        int rating = 4;
        ReviewFeedback feedback = new ReviewFeedback();
        feedback.setRating(rating);

        when(reviewFeedbackRepository.getByRating(rating)).thenReturn(List.of(feedback));

        List<ReviewFeedback> result = reviewFeedbackService.getByRating(rating);
        assertEquals(1, result.size());
        assertEquals(rating, result.get(0).getRating());
        verify(reviewFeedbackRepository).getByRating(rating);
    }
}
