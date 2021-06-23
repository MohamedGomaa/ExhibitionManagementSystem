package com.zkksa.ems.service.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zkksa.ems.model.Visitor;
import com.zkksa.ems.service.VisitorService;

@Service
public class VisitorDetailsServiceImplementation implements UserDetailsService {
	
	@Autowired
	private VisitorService theVisitorService;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Visitor theVisitor = theVisitorService.getVisitorbyUserName(username);
		return VisitorDetailsImplementation.build(theVisitor);
	}
	
	

}
