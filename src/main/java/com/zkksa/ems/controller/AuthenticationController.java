package com.zkksa.ems.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zkksa.ems.model.ERole;
import com.zkksa.ems.model.Role;
import com.zkksa.ems.model.Visitor;
import com.zkksa.ems.payload.request.LoginRequest;
import com.zkksa.ems.payload.request.SignupRequest;
import com.zkksa.ems.payload.response.JwtResponse;
import com.zkksa.ems.payload.response.MessageResponse;
import com.zkksa.ems.service.RoleService;
import com.zkksa.ems.security.jwt.JwtUtils;
import com.zkksa.ems.service.VisitorService;
import com.zkksa.ems.service.security.VisitorDetailsImplementation;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	@Autowired
	AuthenticationManager theAuthenticationManager;

	@Autowired
	VisitorService theVisitorService;

	@Autowired
	RoleService theRoleService;

	@Autowired
	PasswordEncoder theEncoder;

	@Autowired
	JwtUtils theJwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = theAuthenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = theJwtUtils.generateToken(authentication);

		VisitorDetailsImplementation theVisitorDetails = (VisitorDetailsImplementation) authentication.getPrincipal();

		List<String> roles = theVisitorDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, theVisitorDetails.getVisitorId(), theVisitorDetails.getUsername(),
				theVisitorDetails.getVisitorEmail(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest){

		if(theVisitorService.isUserNameExist(signupRequest.getUserName())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username already token!"));
		}
		if(theVisitorService.isEmailExist(signupRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		
		//creating new user's account 
		
		Visitor newUser = new Visitor(signupRequest.getFname()
									,signupRequest.getLname()
									,signupRequest.getUserName()
									,signupRequest.getEmail()
									,theEncoder.encode(signupRequest.getPassword())
									,signupRequest.getPhonenumber(),
									true);
		
		Set<String> strRoles = signupRequest.getRole();
		
		Set<Role> roles = new HashSet<>();
		
		if(strRoles == null ) {
			Role userRole = theRoleService.getRoleName(ERole.ROLE_VISITOR);
			
			roles.add(userRole);
		}else {
			
			strRoles.forEach(role ->{
				switch (role) {
				case "admin":
					Role adminRole = theRoleService.getRoleName(ERole.ROLE_ADMIN);
					roles.add(adminRole);
					break;
				case "host":
					Role modRole = theRoleService.getRoleName(ERole.ROLE_HOST);
					roles.add(modRole);
					break;
				default:
					Role userRole =theRoleService.getRoleName(ERole.ROLE_VISITOR);
					roles.add(userRole);
					break;
				}
			});
		}
		
		newUser.setRoles(roles);
		theVisitorService.saveVisitor(newUser);
		return ResponseEntity.ok(new MessageResponse("User successfully registered!"));
	}
}
