package com.zkksa.ems.service.security;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zkksa.ems.model.Visitor;

public class VisitorDetailsImplementation implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Long visitorId;

	private String visitorFirstName;

	private String VisitorLastName;

	private String userName;
	
	private String visitorEmail;
	
	@JsonIgnore
	private String password;
	
	private String visitorPhoneNumber;
	
	private boolean visitorEnabled; 
	
	private Collection<? extends GrantedAuthority> authorities;

	


	public VisitorDetailsImplementation(Long visitorId, String visitorFirstName, String visitorLastName,
			String userName, String visitorEmail, String password, String visitorPhoneNumber, boolean visitorEnabled,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.visitorId = visitorId;
		this.visitorFirstName = visitorFirstName;
		VisitorLastName = visitorLastName;
		this.userName = userName;
		this.visitorEmail = visitorEmail;
		this.password = password;
		this.visitorPhoneNumber = visitorPhoneNumber;
		this.visitorEnabled = visitorEnabled;
		this.authorities = authorities;
	}



	public static VisitorDetailsImplementation build(Visitor theVisitor) {
		List<GrantedAuthority> authorities = theVisitor.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
				.collect(Collectors.toList());
		
		return new VisitorDetailsImplementation(
				theVisitor.getVisitorId(),
				theVisitor.getVisitorFirstName(),
				theVisitor.getVisitorLastName(),
				theVisitor.getUserName(),
				theVisitor.getVisitorEmail(),
				theVisitor.getPassword(),
				theVisitor.getVisitorPhoneNumber(),
				theVisitor.isVisitorEnabled(),
				authorities);
	}
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}
	


	public Long getVisitorId() {
		return visitorId;
	}



	public String getVisitorFirstName() {
		return visitorFirstName;
	}



	public String getVisitorLastName() {
		return VisitorLastName;
	}



	public String getVisitorEmail() {
		return visitorEmail;
	}



	public String getVisitorPhoneNumber() {
		return visitorPhoneNumber;
	}



	public boolean isVisitorEnabled() {
		return visitorEnabled;
	}



	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public boolean equals(Object theObject) {
		if(this == theObject) {
			return true;
		}
		if (theObject ==null || getClass() != theObject.getClass()) {
			return false;
		}
		
		VisitorDetailsImplementation theVisitor = (VisitorDetailsImplementation) theObject;
		return Objects.equals(visitorId, theVisitor.visitorId);
	}

}
