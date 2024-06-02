package com.cntrlflow.server.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cntrlflow.server.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
	User findByUsername(String username);
}
