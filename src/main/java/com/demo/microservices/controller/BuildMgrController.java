package com.demo.microservices.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.microservices.controller.dto.BuildMessageDto;
import com.demo.microservices.model.BuildMessage;
import com.demo.microservices.service.Action;
import com.demo.microservices.service.BuildMgrService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BuildMgrController {
	private final BuildMgrService buildMgrService;
	
	@PostMapping(value="/dummy", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Action saveDummy() {
		Action action = Action.SUCCESS;
		return action;
	}
	
	@PostMapping(value="/builds", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Action saveAction(@RequestBody BuildMessageDto userDto) {
		Action action = buildMgrService.save(userDto.toEntity());
		return action;
	}
	
	@GetMapping("/builds")
	public List<BuildMessageDto> findAll() {
		List<BuildMessage> bldMgr = buildMgrService.findAll();
		List<BuildMessageDto> buildsMgr = new ArrayList<>();
		
		for (BuildMessage buildMessage : bldMgr) {
			buildsMgr.add(BuildMessageDto.builder()
					.buildMessage(buildMessage)
					.build());
		}
		return buildsMgr;
	}
	
	@GetMapping("/builds/{developer}")
	public BuildMessageDto findByDeveloper(@PathVariable String developer) {
		BuildMessage buildMessage = buildMgrService.findByDeveloper(developer);
		
		return BuildMessageDto.builder()
				.buildMessage(buildMessage)
				.build();
	}
}
