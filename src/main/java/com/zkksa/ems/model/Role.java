package com.zkksa.ems.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roleId;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole roleName;
	
	@ManyToMany(mappedBy = "roles")
	Set<Visitor> visitorRoles = new HashSet<>();  
	
	
	public Role() {
		
	}


	public Role(ERole roleName) {
		this.roleName = roleName;
	}

	

	public Integer getRoleId() {
		return roleId;
	}


	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}


	public ERole getRoleName() {
		return roleName;
	}


	public void setRoleName(ERole roleName) {
		this.roleName = roleName;
	}


	public Set<Visitor> getVisitorRoles() {
		return visitorRoles;
	}

	public void setVisitorRoles(Set<Visitor> VisitorRoles) {
		this.visitorRoles = VisitorRoles;
	}
}
