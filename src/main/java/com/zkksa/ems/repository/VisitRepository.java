package com.zkksa.ems.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zkksa.ems.model.Visit;

@Repository
public interface VisitRepository extends JpaRepository<Visit, UUID> {

}
