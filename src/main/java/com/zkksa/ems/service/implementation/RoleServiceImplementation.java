package com.zkksa.ems.service.implementation;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkksa.ems.model.ERole;
import com.zkksa.ems.model.Role;
import com.zkksa.ems.repository.RoleRepository;
import com.zkksa.ems.service.RoleService;

@Service
@Transactional
public class RoleServiceImplementation implements RoleService {

	@Autowired
	RoleRepository theRoleRepository;
	
	@Override
	public Role getRoleName(ERole name) {
		Optional<Role> theOptionalRole = theRoleRepository.findByRoleName(name);
		Role theRole = null;
		
		if(theOptionalRole.isPresent()) {
			theRole = theOptionalRole.get();
			return theRole;
		}else {
			throw new RuntimeException("The Role is not exist ..!");
		}
	}

}
