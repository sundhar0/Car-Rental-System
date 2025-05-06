package com.api.carrental.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Config.JwtUtil;
import com.api.carrental.Exception.InvalidUserNameException;
import com.api.carrental.Service.AuthService;
import com.api.carrental.Service.MyUserService;
import com.api.carrental.dto.TokenDto;
import com.api.carrental.model.User;

@RestController
@RequestMapping("/api/userLogin")
//@CrossOrigin(origins = {"http://localhost:5173/"})
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@Autowired
	private MyUserService myUserService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/signup")
	public User addLogin(@RequestBody User user) throws InvalidUserNameException {
		return authService.add(user);
	}
	
	@PostMapping("/login")
	public UserDetails Login(Principal principal) {
		String username=principal.getName();
		return myUserService.loadUserByUsername(username);
		}
	
	@PostMapping("/token/generate")
	public TokenDto generateToken(@RequestBody User user1,TokenDto dto) {
		/*Step 1. Build authentication ref based on username,passord given*/
		Authentication auth = 
		new UsernamePasswordAuthenticationToken(user1.getUsername(),user1.getPassword());
	
		authenticationManager.authenticate(auth);
		
		/*Step 2: Generate the token since we know that credentials are correct */
		String token =  jwtUtil.generateToken(user1.getUsername()); 
		dto.setToken(token);
		dto.setUsername(user1.getUsername());
		dto.setExpiry(jwtUtil.extractExpiration(token).toString());
		return dto; 
	}
	@GetMapping("/userDetails")
	public UserDetails userDetails(Principal principal) {
		String username=principal.getName();
		return myUserService.loadUserByUsername(username);
	}
}
