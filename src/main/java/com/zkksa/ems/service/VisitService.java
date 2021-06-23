package com.zkksa.ems.service;

import java.util.List;
import java.util.UUID;

import com.zkksa.ems.model.Visit;

public interface VisitService {

	List<Visit> getAllVisits();

	Visit getVisitById(UUID TheVisitId);

	void saveVisit(Visit theVisit);

	void deleteVisitById(UUID theVisitId);

	void sendMail(String emailDestination, UUID theVisitId);
}
