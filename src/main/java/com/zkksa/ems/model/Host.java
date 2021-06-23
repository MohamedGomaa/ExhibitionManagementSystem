package com.zkksa.ems.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Host {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long hostId;
	
	@NotNull
	@NotEmpty(message = "You have enter the place!")
	private String hostPlace;
	
	@NotNull
	private boolean hostEnabled;
	
	@OneToMany(mappedBy = "theHost")
	private List<Visit> hostVisits;
	
	public Host() {
		
	}

	public Host(String hostPlace,boolean hostEnabled) {
		this.hostPlace = hostPlace;
		this.hostEnabled = hostEnabled;
	}

	public Long getHostId() {
		return hostId;
	}

	public void setHostId(Long hostId) {
		this.hostId = hostId;
	}

	public String getHostPlace() {
		return hostPlace;
	}

	public void setHostPlace(String hostPlace) {
		this.hostPlace = hostPlace;
	}

	public boolean isHostEnabled() {
		return hostEnabled;
	}

	public void setHostEnabled(boolean hostEnabled) {
		this.hostEnabled = hostEnabled;
	}

	public List<Visit> getHostVisits() {
		return hostVisits;
	}

	public void setHostVisits(List<Visit> hostVisits) {
		this.hostVisits = hostVisits;
	}

	
	
}
