package com.cntrlflow.server.utility;

import java.io.IOException;
import java.net.Socket;

import org.springframework.stereotype.Component;

@Component
public class SocketUtil {

	public boolean getHostStatus(String hostname, int port) {
		Socket socket = null;
		try {
			socket = new Socket(hostname, port);
			socket.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}