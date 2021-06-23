package com.zkksa.ems.service.implementation;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkksa.ems.model.Host;
import com.zkksa.ems.repository.HostRepository;
import com.zkksa.ems.service.HostService;

@Service
@Transactional
public class HostServiceImplementation implements HostService {
	
	@Autowired
	private HostRepository theHostrepository;

	@Override
	public List<Host> getAllHosts() {
		return theHostrepository.findAll();
	}

	@Override
	public Host getHostById(Long TheHostId) {
		Optional<Host> theOptionalHost = theHostrepository.findById(TheHostId);
		Host theHost = null;
		if(theOptionalHost.isPresent()) {
			theHost = theOptionalHost.get();
			return theHost;
		}else {
			throw new RuntimeException("The host is not found ..!");
		}
	}

	@Override
	public void saveHost(Host theHost) {
		theHostrepository.save(theHost);
	}

	@Override
	public void deleteHostById(Long theHostId) {
		theHostrepository.deleteById(theHostId);
	}

}
