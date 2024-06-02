package com.cntrlflow.server.model;

import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "cntrlflow_users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
	
	@NotBlank(message = "username is mandatory")
    @Size(min = 3, max = 50, message = "username must be between 3 and 50 characters")
    private String username;

    @Setter(AccessLevel.NONE)
    @NotBlank(message = "password is mandatory")
    @Size(min = 3, max = 50, message = "username must be between 3 and 50 characters")
    private String password;

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }
}
