package com.demo.microservices.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.microservices.controller.dto.BuildMessageDto;
import com.demo.microservices.controller.dto.HelloDto;
import com.demo.microservices.model.BuildMessage;
import com.demo.microservices.service.Action;
import com.demo.microservices.service.BuildMgrService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BuildMgrController {
	private final BuildMgrService buildMgrService;
	
	private String msgTemplate = "%s 님 반갑습니다.";
	private final AtomicLong vistorCounter = new AtomicLong();
	
	@GetMapping
	public String welcome() {
		return "Welcome IBM bootcamp";
	}
	@ApiOperation(value="Hello API 입니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name="name", value="이름", required=true, dataType="String", paramType="query", defaultValue="홍길동")
	})
	@GetMapping("/hello")
	public HelloDto getHelloMsg(@RequestParam(value="name") String name) {
		
		return new HelloDto(vistorCounter.incrementAndGet(), String.format(msgTemplate, name));
	}
	
	@PostMapping(value="/dummy", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Action saveJsonDummy() {
		Action action = Action.SUCCESS;
		return action;
	}
	
	@PostMapping(value="/dummy", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public Action saveFormDummy() {
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
