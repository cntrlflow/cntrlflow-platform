package com.cntrlflow.server.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "cntrlflow_cluster_services")
@AllArgsConstructor
@NoArgsConstructor
public class ClusterServices {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
	
	private boolean lockStatus = false;
}
