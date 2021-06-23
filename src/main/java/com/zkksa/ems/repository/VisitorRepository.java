package com.zkksa.ems.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zkksa.ems.model.Visitor;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {

	Optional<Visitor> findByVisitorEmail(String theVisitorEmail);

	Optional<Visitor> findByUserName(String userName);

	Boolean existsByUserName(String userName);

	Boolean existsByVisitorEmail(String email);

}
