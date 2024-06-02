package com.cntrlflow.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AgentResponse {
	private String statusCode;
	private String message;
	private String content;
}