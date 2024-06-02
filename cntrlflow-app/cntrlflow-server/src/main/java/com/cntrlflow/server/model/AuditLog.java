package com.cntrlflow.server.model;

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
import org.springframework.data.annotation.CreatedDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "cntrlflow_audit_logs")
@AllArgsConstructor
@NoArgsConstructor
public class AuditLog {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
	
	@NotBlank(message = "clusterName is blank")
	@Pattern(regexp="^[a-zA-Z0-9_-]*$", message="only alphanumeric and underscore/hyphen is allowed")
	private String clusterName;

    @NotBlank(message = "userName is mandatory")
    @Size(min = 3, max = 50, message = "username must be between 3 and 50 characters")
    private String userName;

    @NotBlank(message = "eventName is blank")
	@Pattern(regexp="^[a-zA-Z0-9_-]*$", message="only alphanumeric and underscore/hyphen is allowed")
	private String eventName;

    private String properties;

    @CreatedDate
    private LocalDateTime insertTimestamp;
}
