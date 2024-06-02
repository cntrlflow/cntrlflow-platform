package com.cntrlflow.server.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cntrlflow.server.model.UserRole;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, UUID> {
    List<UserRole> findAllByClusterName(String clusterName);
	UserRole findByClusterNameAndUserName(String clusterName, String userName);
}
