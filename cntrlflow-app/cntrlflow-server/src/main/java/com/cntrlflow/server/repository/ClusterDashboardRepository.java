package com.cntrlflow.server.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cntrlflow.server.model.ClusterDashboard;

@Repository
public interface ClusterDashboardRepository extends CrudRepository<ClusterDashboard, UUID>{
	Iterable<ClusterDashboard> findAllByClusterName(String clusterName);
    ClusterDashboard findByClusterNameAndClusterType(String clusterName, String clusterType);
}
