package com.zkksa.ems.service;

import java.util.List;

import com.zkksa.ems.model.Host;

public interface HostService {

	List<Host> getAllHosts();
		
	Host getHostById(Long TheHostId);
	
	void saveHost(Host theHost);
	
	void deleteHostById(Long theHostId);
}
