package com.demo.microservices.controller.dto;

import com.demo.microservices.model.BuildMessage;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BuildMessageDto {
	private String developer;
	
	private String action;
	
	private String message;
	
	@Builder
	public BuildMessageDto(BuildMessage buildMessage) {
		this.developer = buildMessage.getDeveloper();
		this.action = buildMessage.getAction();
		this.message = buildMessage.getMessage();
	}

	public BuildMessage toEntity() {
		return BuildMessage.builder()
				.developer(developer)
				.action(action)
				.message(message)
				.build();
	}
}
