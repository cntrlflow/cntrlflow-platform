package com.cntrlflow.server.model;

import java.util.List;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClusterServicesWithStatus {
	private UUID id;

	@NotBlank(message = "clusterName is blank")
	@Pattern(regexp="^[a-zA-Z0-9_-]*$", message="only alphanumeric and underscore/hyphen is allowed")
	private String clusterName;
	
	@NotBlank(message = "componentName is blank")
	@Pattern(regexp="^[a-zA-Z0-9_-]*$", message="only alphanumeric and underscore/hyphen is allowed")
	private String componentName;
	
	@NotBlank(message = "hostName is blank")
	@Pattern(regexp="^[a-zA-Z0-9_.-]*$", message="only alphanumeric and underscore/hyphen/dot is allowed")
	private String hostName;
	
	@NotBlank(message = "portNumber is blank")
	@Pattern(regexp="^[a-zA-Z0-9_.,-]*$", message="only alphanumeric and underscore/hyphen/dot is allowed")
	private String portNumber;
	
	private List<KeyValuePair> status;
	private boolean lockStatus = false;
}
