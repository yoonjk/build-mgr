package com.demo.microservices.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.microservices.model.BuildMessage;
import com.demo.microservices.repository.BuildMgrRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor   
@Service
public class BuildMgrService {
	private final BuildMgrRepository buildMgrRepository;
	public List<BuildMessage> findAll() {
		return buildMgrRepository.findAll();
	}
	
	public BuildMessage findByDeveloper(String username) {
		return buildMgrRepository.findByDeveloper(username);
	}
	
	public Action save(BuildMessage buildMessage) {
		BuildMessage bldMessage = buildMgrRepository.save(buildMessage);
		return bldMessage != null ? Action.SUCCESS : Action.FAIL;
	}
}
