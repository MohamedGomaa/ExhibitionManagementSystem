package com.zkksa.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zkksa.ems.model.Host;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {

}
