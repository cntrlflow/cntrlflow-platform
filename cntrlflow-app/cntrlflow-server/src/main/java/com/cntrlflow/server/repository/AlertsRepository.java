package com.cntrlflow.server.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cntrlflow.server.model.Alerts;

@Repository
public interface AlertsRepository extends CrudRepository<Alerts, UUID>{
	List<Alerts> findAllByClusterName(String clusterName);
	Alerts findByClusterNameAndAlertName(String clusterName, String alertName);
}