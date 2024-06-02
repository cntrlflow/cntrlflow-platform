package com.cntrlflow.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AgentRequest {
	private String jwtToken;
	private String hostName;
	private String payload;
}