package com.cntrlflow.server.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cntrlflow.server.model.ClusterDetails;

@Repository
public interface ClusterDetailsRepository extends CrudRepository<ClusterDetails, UUID> {
	Iterable<ClusterDetails> findAllByClusterName(String clusterName);
	ClusterDetails findByClusterNameAndComponentName(String clusterName, String componentName);
}
