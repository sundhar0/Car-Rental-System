//package com.api.carrental.serviceTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import java.util.Arrays;
//import java.util.List;
//
//import com.api.carrental.Exception.InvalidIDException;
//import com.api.carrental.Repository.ReviewFeedbackRepository;
//import com.api.carrental.Service.CarService;
//import com.api.carrental.Service.CustomerService;
//import com.api.carrental.Service.ReviewFeedbackService;
//import com.api.carrental.model.Car;
//import com.api.carrental.model.Customer;
//import com.api.carrental.model.ReviewFeedback;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//@ExtendWith(MockitoExtension.class)
//public class ReviewFeedbackServiceTest {
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
//    @InjectMocks
//    private ReviewFeedbackService reviewFeedbackService;
//
//    @BeforeEach
//    public void setUp() {
//        customer = new Customer();
//        customer.setId(1);
//        customer.setFullName("Test Customer");
//
//        car = new Car();
//        car.setId(101);
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
//        when(customerService.getSingleCustomer(1)).thenReturn(customer);
//        when(reviewFeedbackRepository.findByCustomerId(1)).thenReturn(Arrays.asList(feedback));
//
//        List<ReviewFeedback> list = reviewFeedbackService.getByReview(1);
//        assertEquals(1, list.size());
//        assertEquals("Excellent", list.get(0).getReview());
//    }
//
//    @Test
//    public void testAddReview_Success() throws InvalidIDException {
//        when(carService.getById(101)).thenReturn(car);
//        when(customerService.getSingleCustomer(1)).thenReturn(customer);
//        when(reviewFeedbackRepository.save(any(ReviewFeedback.class))).thenReturn(feedback);
//
//        Object savedFeedback = reviewFeedbackService.addReview(101, 1, feedback);
//        assertEquals(feedback, savedFeedback);
//    }
//
//    @Test
//    void testGetByRating() {
//        int rating = 4;
//        ReviewFeedback feedback = new ReviewFeedback();
//        feedback.setRating(rating);
//
//<<<<<<< HEAD
//        when(reviewFeedbackRepository.getByRating(rating)).thenReturn(List.of(feedback));
//
//        List<ReviewFeedback> result = reviewFeedbackService.getByRating(rating);
//        assertEquals(1, result.size());
//        assertEquals(rating, result.get(0).getRating());
//        verify(reviewFeedbackRepository).getByRating(rating);
//=======
//        InvalidIDException exception = assertThrows(InvalidIDException.class, () -> {
//            reviewFeedbackService.addReview(999, 1, feedback);
//        });
//
//        assertEquals("Given Car id is Invalid...", exception.getMessage());
//    }
//
//    @Test
//    public void testAddReview_InvalidCustomerId() throws InvalidIDException {
//        when(carService.getById(101)).thenReturn(car);
//        when(customerService.getSingleCustomer(99)).thenReturn(null);
//
//        InvalidIDException exception = assertThrows(InvalidIDException.class, () -> {
//            reviewFeedbackService.addReview(101, 99, feedback);
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
//>>>>>>> 01639c93b624410e2ee14f03a79a72910d06db74
//    }
//}
