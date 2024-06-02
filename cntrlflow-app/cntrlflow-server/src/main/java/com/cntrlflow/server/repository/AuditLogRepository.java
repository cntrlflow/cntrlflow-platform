package com.cntrlflow.server.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cntrlflow.server.model.Alerts;
import com.cntrlflow.server.model.AuditLog;

@Repository
public interface AuditLogRepository extends CrudRepository<Alerts, UUID>{
	List<AuditLog> findAllByClusterName(String clusterName);
}
