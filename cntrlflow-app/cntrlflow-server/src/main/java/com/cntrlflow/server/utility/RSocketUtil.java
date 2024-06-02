package com.cntrlflow.server.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.security.rsocket.metadata.UsernamePasswordMetadata;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;

import io.rsocket.metadata.WellKnownMimeType;
import io.rsocket.transport.netty.client.TcpClientTransport;
import reactor.netty.tcp.TcpClient;

@Component
public class RSocketUtil {
	@Value("${spring.security.user.name}")
	private String userName;

	@Value("${spring.security.user.password}")
	private String userPassword;
	
	@Value("${cntrlflow.client.ssl.trustStore}")
	private String trustStore;
	
	@Value("${cntrlflow.client.ssl.trustStorePassword}")
	private String trustStorePassword;
	
	private RSocketRequester.Builder builder;
	private RSocketStrategies rsocketStrategies;
	
	@Autowired
	public RSocketUtil(RSocketRequester.Builder builder, RSocketStrategies rsocketStrategies) {
		this.builder = builder;
		this.rsocketStrategies = rsocketStrategies;
	}
	
	public RSocketRequester createSecureRequester(String hostName, int portNumber) {
		System.setProperty(Constants.TRUSTSTORE_LOCATION,trustStore);
		System.setProperty(Constants.TRUSTSTORE_PASSWORD,trustStorePassword);
		
		TcpClient tcpClient = TcpClient.create().host(hostName).port(portNumber).secure();
		MimeType mimeType = MimeTypeUtils.parseMimeType(WellKnownMimeType.MESSAGE_RSOCKET_AUTHENTICATION.getString());
		
		return this.builder
				.setupMetadata(new UsernamePasswordMetadata(userName, userPassword), mimeType)
				.dataMimeType(MimeTypeUtils.APPLICATION_JSON)
				.rsocketStrategies(this.rsocketStrategies)
				.transport(TcpClientTransport.create(tcpClient));
	}
	
	public void close(RSocketRequester requester) {
		if(!requester.isDisposed()) {
			requester.dispose();
		}
	}
}
