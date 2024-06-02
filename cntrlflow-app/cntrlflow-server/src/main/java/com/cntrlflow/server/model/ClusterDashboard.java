package com.cntrlflow.server.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "cntrlflow_cluster_dashboard")
@AllArgsConstructor
@NoArgsConstructor
public class ClusterDashboard {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
	
	@NotBlank(message = "clusterName is blank")
	@Pattern(regexp="^[a-zA-Z0-9_-]*$", message="only alphanumeric and underscore/hyphen is allowed")
	private String clusterName;
	
	@NotBlank(message = "clusterType is blank")
	@Pattern(regexp="^[a-zA-Z0-9_-]*$", message="only alphanumeric and underscore/hyphen is allowed")
	private String clusterType;
	
	@NotNull(message = "totalServers is required")
	private int totalServers;
	
	@NotNull(message = "totalComponents is required")
	private int totalComponents;
	
	@NotBlank(message = "clusterEmail is required")
	private String clusterEmail;
	
	private Boolean enableSystemMetrics = false;
}
