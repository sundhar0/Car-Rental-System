package com.api.carrental.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.ReviewFeedbackRepository;
import com.api.carrental.Service.CarService;
import com.api.carrental.Service.CustomerService;
import com.api.carrental.Service.ReviewFeedbackService;
import com.api.carrental.model.Car;
import com.api.carrental.model.Customer;
import com.api.carrental.model.ReviewFeedback;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ReviewFeedbackServiceTest {

	@InjectMocks
    private ReviewFeedbackService reviewFeedbackService;

    @Mock
    private ReviewFeedbackRepository reviewFeedbackRepository;

    @Mock
    private CarService carService;

    @Mock
    private CustomerService customerService;

    private Customer customer;
    private Car car;
    private ReviewFeedback feedback;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setFullName("Test Customer");

        car = new Car();
        car.setId(101);
        car.setModel("Test Model");

        feedback = new ReviewFeedback();
        feedback.setFeedbackId(1);
        feedback.setRating(5);
        feedback.setReview("Excellent");
        feedback.setCar(car);
        feedback.setCustomer(customer);
    }

    @Test
    public void testGetByReview() throws InvalidIDException {
        when(customerService.getSingleCustomer(1L)).thenReturn(customer);
        when(reviewFeedbackRepository.findByCustomerId(1L)).thenReturn(Arrays.asList(feedback));

        List<ReviewFeedback> list = reviewFeedbackService.getByReview(1L);
        assertEquals(1, list.size());
        assertEquals("Excellent", list.get(0).getReview());
    }

    @Test
    public void testAddReview_Success() throws InvalidIDException {
        when(carService.getById(101)).thenReturn(car);
        when(customerService.getSingleCustomer(1L)).thenReturn(customer);
        when(reviewFeedbackRepository.save(any(ReviewFeedback.class))).thenReturn(feedback);

        Object savedFeedback = reviewFeedbackService.addReview(101, 1L, feedback);
        assertEquals(feedback, savedFeedback);
    }

    @Test
    public void testAddReview_InvalidCarId() throws InvalidIDException {
        when(carService.getById(999)).thenReturn(null);

        InvalidIDException exception = assertThrows(InvalidIDException.class, () -> {
            reviewFeedbackService.addReview(999, 1L, feedback);
        });

        assertEquals("Given Car id is Invalid...", exception.getMessage());
    }

    @Test
    public void testAddReview_InvalidCustomerId() throws InvalidIDException {
        when(carService.getById(101)).thenReturn(car);
        when(customerService.getSingleCustomer(99L)).thenReturn(null);

        InvalidIDException exception = assertThrows(InvalidIDException.class, () -> {
            reviewFeedbackService.addReview(101, 99L, feedback);
        });

        assertEquals("Given Customer Id is Invalid...", exception.getMessage());
    }

    @Test
    public void testGetByRating() {
        when(reviewFeedbackRepository.getByRating(5)).thenReturn(Arrays.asList(feedback));

        List<ReviewFeedback> list = reviewFeedbackService.getByRating(5);
        assertEquals(1, list.size());
        assertEquals(5, list.get(0).getRating());
    }
}
//package com.api.carrental.Service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.api.carrental.Exception.InvalidIDException;
//import com.api.carrental.Repository.ReviewFeedbackRepository;
//import com.api.carrental.model.Car;
//import com.api.carrental.model.Customer;
//import com.api.carrental.model.ReviewFeedback;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//public class ReviewFeedbackServiceTest {
//
//	@InjectMocks
//    private ReviewFeedbackService reviewFeedbackService;
//
//    @Mock
//    private ReviewFeedbackRepository reviewFeedbackRepository;
//
//    @Mock
//    private CarService carService;
//
//    @Mock
//    private CustomerService customerService;
//
//    private Customer customer;
//    private Car car;
//    private ReviewFeedback feedback;
//
//    @BeforeEach
//    public void setUp() {
//        customer = new Customer();
//        customer.setId(1L);
//        customer.setFullName("Test Customer");
//
//        car = new Car();
//        car.setCarId(101);
//        car.setModel("Test Model");
//
//        feedback = new ReviewFeedback();
//        feedback.setFeedbackId(1);
//        feedback.setRating(5);
//        feedback.setReview("Excellent");
//        feedback.setCar(car);
//        feedback.setCustomer(customer);
//    }
//
//    @Test
//    public void testGetByReview() throws InvalidIDException {
//        when(customerService.getSingleCustomer(1L)).thenReturn(customer);
//        when(reviewFeedbackRepository.findByCustomerId(1L)).thenReturn(Arrays.asList(feedback));
//
//        List<ReviewFeedback> list = reviewFeedbackService.getByReview(1L);
//        assertEquals(1, list.size());
//        assertEquals("Excellent", list.get(0).getReview());
//    }
//
//    @Test
//    public void testAddReview_Success() throws InvalidIDException {
//        when(carService.getById(101)).thenReturn(car);
//        when(customerService.getSingleCustomer(1L)).thenReturn(customer);
//        when(reviewFeedbackRepository.save(any(ReviewFeedback.class))).thenReturn(feedback);
//
//        Object savedFeedback = reviewFeedbackService.addReview(101, 1L, feedback);
//        assertEquals(feedback, savedFeedback);
//    }
//
//    @Test
//    public void testAddReview_InvalidCarId() throws InvalidIDException {
//        when(carService.getById(999)).thenReturn(null);
//
//        InvalidIDException exception = assertThrows(InvalidIDException.class, () -> {
//            reviewFeedbackService.addReview(999, 1L, feedback);
//        });
//
//        assertEquals("Given Car id is Invalid...", exception.getMessage());
//    }
//
//    @Test
//    public void testAddReview_InvalidCustomerId() throws InvalidIDException {
//        when(carService.getById(101)).thenReturn(car);
//        when(customerService.getSingleCustomer(99L)).thenReturn(null);
//
//        InvalidIDException exception = assertThrows(InvalidIDException.class, () -> {
//            reviewFeedbackService.addReview(101, 99L, feedback);
//        });
//
//        assertEquals("Given Customer Id is Invalid...", exception.getMessage());
//    }
//
//    @Test
//    public void testGetByRating() {
//        when(reviewFeedbackRepository.getByRating(5)).thenReturn(Arrays.asList(feedback));
//
//        List<ReviewFeedback> list = reviewFeedbackService.getByRating(5);
//        assertEquals(1, list.size());
//        assertEquals(5, list.get(0).getRating());
//    }
//}
