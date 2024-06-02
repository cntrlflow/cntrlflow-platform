package com.cntrlflow.server.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import com.cntrlflow.server.model.Agents;

public interface AgentsRepository extends CrudRepository<Agents, UUID> {
	List<Agents> findAllByClusterName(String clusterName);
	Agents findByClusterNameAndHostName(String clusterName, String hostName);
}
