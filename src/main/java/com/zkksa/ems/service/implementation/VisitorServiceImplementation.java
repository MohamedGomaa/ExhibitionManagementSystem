package com.zkksa.ems.service.implementation;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkksa.ems.model.Visitor;
import com.zkksa.ems.repository.VisitorRepository;
import com.zkksa.ems.service.VisitorService;

@Service
@Transactional
public class VisitorServiceImplementation implements VisitorService {

	@Autowired
	private VisitorRepository theVisitorRepository;
	
	@Override
	public List<Visitor> getAllVisitors() {
		return theVisitorRepository.findAll();
	}

	@Override
	public Visitor getByVisitorEmail(String theVisitorEmail) {
		
		Optional<Visitor> theOptionalVisitor = theVisitorRepository.findByVisitorEmail(theVisitorEmail);
		Visitor theVisitor = null;
		
		if(theOptionalVisitor.isPresent()) {
			theVisitor = theOptionalVisitor.get();
			return theVisitor;
		}else {
			throw new RuntimeException("The visitor is not exist ..!");
		}
		
	}

	@Override
	public Visitor getVisitorById(Long TheVisitorId) {
		Optional<Visitor> theOptionalVisitor = theVisitorRepository.findById(TheVisitorId);
		Visitor theVisitor = null;
		
		if(theOptionalVisitor.isPresent()) {
			theVisitor = theOptionalVisitor.get();
			return theVisitor;
		}else {
			throw new RuntimeException("The visitor is not exist ..!");
		}
	}

	@Override
	public void saveVisitor(Visitor theVisitor) {
		theVisitorRepository.save(theVisitor);
	}

	@Override
	public void deleteVisitorById(Long theVisitorId) {
		theVisitorRepository.deleteById(theVisitorId);
	}

	@Override
	public Visitor getVisitorbyUserName(String userName) {
		Optional<Visitor> theOptionalVisitor = theVisitorRepository.findByUserName(userName);
		Visitor theVisitor = null;
		
		if(theOptionalVisitor.isPresent()) {
			theVisitor = theOptionalVisitor.get();
			return theVisitor;
		}else {
			throw new RuntimeException("The visitor is not exist ..!");
		}	}

	@Override
	public boolean isUserNameExist(String userName) {
		return theVisitorRepository.existsByUserName(userName);
	}

	@Override
	public boolean isEmailExist(String email) {
		return theVisitorRepository.existsByVisitorEmail(email);
	}

}
