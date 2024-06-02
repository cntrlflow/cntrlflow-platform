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
@Table(name = "cntrlflow_cluster_details")
@AllArgsConstructor
@NoArgsConstructor
public class ClusterDetails {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
	
	@NotBlank(message = "clusterName is blank")
	@Pattern(regexp="^[a-zA-Z0-9_-]*$", message="only alphanumeric and underscore/hyphen is allowed")
	private String clusterName;
	
	@NotBlank(message = "componentName is blank")
	@Pattern(regexp="^[a-zA-Z0-9_-]*$", message="only alphanumeric and underscore/hyphen is allowed")
	private String componentName;
	
	@NotBlank(message = "componentVersion is blank")
	@Pattern(regexp="^[a-zA-Z0-9_\\/.-]*$", message="only alphanumeric and underscore/hyphen/dot is allowed")
	private String componentVersion;
	
	@NotBlank(message = "homeLocation is blank")
	@Pattern(regexp="\\/[a-zA-Z0-9_\\/.-]*$", message="only alphanumeric and underscore/hyphen/slash is allowed")
	private String homeLocation;
	
	@NotBlank(message = "propertiesLocation is blank")
	@Pattern(regexp="\\/[a-zA-Z0-9_\\/.,-]*$", message="only alphanumeric and underscore/hyphen/slash is allowed")
	private String propertiesLocation;
	
	@NotBlank(message = "startLocation is blank")
	@Pattern(regexp="\\/[a-zA-Z0-9_\\/.-]*$", message="only alphanumeric and underscore/hyphen/slash is allowed")
	private String startLocation;
	
	@NotBlank(message = "stopLocation is blank")
	@Pattern(regexp="\\/[a-zA-Z0-9_\\/.-]*$", message="only alphanumeric and underscore/hyphen/slash is allowed")
	private String stopLocation;
	
	@NotBlank(message = "logLocation is blank")
	@Pattern(regexp="\\/[a-zA-Z0-9_\\/.-]*$", message="only alphanumeric and underscore/hyphen/slash/comma is allowed")
	private String logLocation;
	
	@NotBlank(message = "executeUser is blank")
	@Pattern(regexp="^[a-zA-Z0-9_-]*$", message="only alphanumeric and underscore/hyphen is allowed")
	private String executeUser;
	
	@NotNull(message = "startTimeout is blank")
	private int startTimeout;
	
	@NotNull(message = "startPriority is blank")
	private int startPriority;
}
