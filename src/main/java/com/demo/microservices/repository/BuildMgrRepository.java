package com.demo.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.microservices.model.BuildMessage;

public interface BuildMgrRepository extends JpaRepository<BuildMessage, Long> {
	public BuildMessage findByDeveloper(String developer);
}
