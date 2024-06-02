package com.cntrlflow.server.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "cntrlflow_user_role")
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
	
	@NotBlank(message = "clusterName is blank")
	@Pattern(regexp="^[a-zA-Z0-9_-]*$", message="only alphanumeric and underscore/hyphen is allowed")
	private String clusterName;

    @NotBlank(message = "username is mandatory")
    @Size(min = 3, max = 50, message = "username must be between 3 and 50 characters")
    private String userName;

    private String role;
}
