package com.cntrlflow.server.utility;

import java.util.Base64;
import org.springframework.stereotype.Component;

@Component
public class Base64Util {

	public String encodeString(String input) {
		byte[] encodedBytes = Base64.getEncoder().encode(input.getBytes());
		return new String(encodedBytes);
	}

	public String decodeString(String input) {
		byte[] decodedBytes = Base64.getDecoder().decode(input);
		return new String(decodedBytes);
	}
}
