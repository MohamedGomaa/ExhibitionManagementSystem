package com.zkksa.ems.controller;


import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zkksa.ems.model.Visit;
import com.zkksa.ems.service.HostService;
import com.zkksa.ems.service.VisitService;
import com.zkksa.ems.service.VisitorService;

@RestController
@RequestMapping("/api/visit")
public class VisitController {

	@Autowired
	private VisitService theVisitService;
	
	@Autowired
	private HostService theHostService;
	
	@Autowired
	private VisitorService theVisitorService;
	
	
	@PostMapping("/create")
	public void createVisit(@RequestBody Visit theVisit) {
		
		UUID uuid = UUID.randomUUID();
		theVisit.setVisitCode(uuid);
		theVisit.setTheVisitor(theVisitorService.getVisitorById(2L));
		theVisit.setTheHost(theHostService.getHostById(1L));
		theVisit.setVisitCreatedAt(new Date());
		
		theVisitService.sendMail(theVisit.getTheVisitor().getVisitorEmail(), theVisit.getVisitCode());
		
		theVisitService.saveVisit(theVisit);
	}
}
