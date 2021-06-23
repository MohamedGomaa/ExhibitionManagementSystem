package com.zkksa.ems.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Visitor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long visitorId;
	
	@NotNull
	@NotEmpty(message= "Visitor must have a name!")
	private String visitorFirstName;
	
	@NotNull
	@NotEmpty(message= "Visitor must have a name!")
	private String VisitorLastName;
	
	@NotNull
	@NotEmpty(message = "Visitor must have username")
	private String userName;
	
	@Email
	private String visitorEmail;
	
	@NotNull
	@NotEmpty(message= "You have to enter a Password!")
	private String password;
	
	@Size(min=5, max=14)
	private String visitorPhoneNumber;
	
	@NotNull
	private boolean visitorEnabled;
	
	@OneToMany(mappedBy = "theVisitor")
	private List<Visit> visitorVisits;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles",
				joinColumns =@JoinColumn(name = "visitor_id"),
				inverseJoinColumns =@JoinColumn(name =  "role_id"))
	private Set<Role> roles = new HashSet<>(); 
	
	public Visitor() {
		
	}

	public Visitor(String visitorFirstName, String visitorLastName,String userName,  String visitorEmail, String visitorPassword,
			String visitorPhoneNumber, boolean visitorEnabled) {
		this.visitorFirstName = visitorFirstName;
		this.VisitorLastName = visitorLastName;
		this.userName = userName;
		this.visitorEmail = visitorEmail;
		this.password = visitorPassword;
		this.visitorPhoneNumber = visitorPhoneNumber;
		this.visitorEnabled = visitorEnabled;
	}

	public Long getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(Long visitorId) {
		this.visitorId = visitorId;
	}

	public String getVisitorFirstName() {
		return visitorFirstName;
	}

	public void setVisitorFirstName(String visitorFirstName) {
		this.visitorFirstName = visitorFirstName;
	}

	public String getVisitorLastName() {
		return VisitorLastName;
	}

	public void setVisitorLastName(String visitorLastName) {
		VisitorLastName = visitorLastName;
	}
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVisitorEmail() {
		return visitorEmail;
	}

	public void setVisitorEmail(String visitorEmail) {
		this.visitorEmail = visitorEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setVisitorPhoneNumber(String visitorPhoneNumber) {
		this.visitorPhoneNumber = visitorPhoneNumber;
	}

	public boolean isVisitorEnabled() {
		return visitorEnabled;
	}

	public void setVisitorEnabled(boolean visitorEnabled) {
		this.visitorEnabled = visitorEnabled;
	}

	public List<Visit> getVisitorVisits() {
		return visitorVisits;
	}

	public void setVisitorVisits(List<Visit> visitorVisits) {
		this.visitorVisits = visitorVisits;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getVisitorPhoneNumber() {
		return visitorPhoneNumber;
	}
	
	
	
	
}
