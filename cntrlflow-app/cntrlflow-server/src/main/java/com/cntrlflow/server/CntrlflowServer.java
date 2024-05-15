package com.cntrlflow.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class CntrlflowServer {
	public static void main(String[] args) {
		log.info("[cntrlflow] starting cntrlflow server");
		SpringApplication.run(CntrlflowServer.class, args);
	}
}