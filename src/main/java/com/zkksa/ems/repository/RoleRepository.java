package com.zkksa.ems.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zkksa.ems.model.ERole;
import com.zkksa.ems.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByRoleName(ERole name );

}
