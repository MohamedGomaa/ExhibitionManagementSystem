package com.zkksa.ems.service;

import java.util.List;

import com.zkksa.ems.model.Visitor;

public interface VisitorService {
	
	List<Visitor> getAllVisitors();
	
	Visitor getVisitorbyUserName(String userName);
	
	Visitor getByVisitorEmail(String theVisitorEmail);
	
	Visitor getVisitorById(Long TheVisitorId);
	
	void saveVisitor(Visitor theVisitor);
	
	void deleteVisitorById(Long theVisitorId);
	
	boolean isUserNameExist(String userName);
	
	boolean isEmailExist(String email);
}
