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
@Table(name = "cntrlflow_plugins")
@AllArgsConstructor
@NoArgsConstructor
public class Plugins {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
	
	@NotBlank(message = "clusterName is blank")
	@Pattern(regexp="^[a-zA-Z0-9_-]*$", message="only alphanumeric and underscore/hyphen is allowed")
	private String clusterName;
	
	@NotBlank(message = "pluginId is blank")
	@Pattern(regexp="^[a-zA-Z0-9_-]*$", message="only alphanumeric and underscore/hyphen is allowed")
	private String pluginId;
	
	@NotBlank(message = "pluginName is blank")
	@Pattern(regexp="^[a-zA-Z0-9_-]*$", message="only alphanumeric and underscore/hyphen is allowed")
	private String pluginName;
	
	@NotBlank(message = "clusterType is blank")
	@Pattern(regexp="^[a-zA-Z0-9_-]*$", message="only alphanumeric and underscore/hyphen is allowed")
	private String clusterType;
	
	@NotBlank(message = "version is blank")
	private String version;
	
	@NotBlank(message = "shortDescription is blank")
	private String shortDescription;
	
	private boolean pluginStatus = false;
}
