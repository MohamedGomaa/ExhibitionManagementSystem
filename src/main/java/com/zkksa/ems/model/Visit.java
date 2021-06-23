package com.zkksa.ems.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class Visit {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name= "uuid2", strategy="uuid2")
	@Column(columnDefinition="UUID")
	private UUID visitCode;
	
	@NotNull
	@NotEmpty(message = "You must enter your visit reason" )
	private String visitReason;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date visitCreatedAt;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date visitStartTime;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date visitExitTime;
	
	@NotNull
	private String visitEnterDoorCode;
	
	@NotNull
	private String visitExitDoorCode;
	
	@NotNull
	private boolean visitStatues;
	
	@ManyToOne
	@JoinColumn(name = "hostId")
	private Host theHost;
	

	@ManyToOne
	@JoinColumn(name = "visitorId")
	private Visitor theVisitor;
	
	public Visit() {
		
	}

	public Visit(String visitReason, Date visitCreatedAt,
			Date visitStartTime, Date visitExitTime,
			String visitEnterDoorCode, String visitExitDoorCode,
			boolean visitStatues) {
		this.visitReason = visitReason;
		this.visitCreatedAt = visitCreatedAt;
		this.visitStartTime = visitStartTime;
		this.visitExitTime = visitExitTime;
		this.visitEnterDoorCode = visitEnterDoorCode;
		this.visitExitDoorCode = visitExitDoorCode;
		this.visitStatues = visitStatues;
	}

	public UUID getVisitCode() {
		return visitCode;
	}

	public void setVisitCode(UUID visitorCode) {
		this.visitCode = visitorCode;
	}

	public String getVisitReason() {
		return visitReason;
	}

	public void setVisitReason(String visitReason) {
		this.visitReason = visitReason;
	}

	public Date getVisitCreatedAt() {
		return visitCreatedAt;
	}

	public void setVisitCreatedAt(Date visitCreatedAt) {
		this.visitCreatedAt = visitCreatedAt;
	}

	public Date getVisitStartTime() {
		return visitStartTime;
	}

	public void setVisitStartTime(Date visitStartTime) {
		this.visitStartTime = visitStartTime;
	}

	public Date getVisitExitTime() {
		return visitExitTime;
	}

	public void setVisitExitTime(Date visitExitTime) {
		this.visitExitTime = visitExitTime;
	}

	public String getVisitEnterDoorCode() {
		return visitEnterDoorCode;
	}

	public void setVisitEnterDoorCode(String visitEnterDoorCode) {
		this.visitEnterDoorCode = visitEnterDoorCode;
	}

	public String getVisitExitDoorCode() {
		return visitExitDoorCode;
	}

	public void setVisitExitDoorCode(String visitExitDoorCode) {
		this.visitExitDoorCode = visitExitDoorCode;
	}

	public boolean isVisitStatues() {
		return visitStatues;
	}

	public void setVisitStatues(boolean visitStatues) {
		this.visitStatues = visitStatues;
	}

	public Host getTheHost() {
		return theHost;
	}

	public void setTheHost(Host theHost) {
		this.theHost = theHost;
	}

	public Visitor getTheVisitor() {
		return theVisitor;
	}

	public void setTheVisitor(Visitor theVisitor) {
		this.theVisitor = theVisitor;
	}
	
	
	
	
}
