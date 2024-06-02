package com.cntrlflow.server.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cntrlflow.server.model.ClusterServices;

@Repository
public interface ClusterServicesRepository extends CrudRepository<ClusterServices, UUID> {
	Iterable<ClusterServices> findAllByClusterName(String clusterName);
	ClusterServices findByClusterNameAndComponentNameAndHostName(String clusterName, String componentName, String hostName);
}
