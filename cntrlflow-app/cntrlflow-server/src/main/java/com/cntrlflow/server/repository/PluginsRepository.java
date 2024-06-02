package com.cntrlflow.server.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cntrlflow.server.model.Plugins;

@Repository
public interface PluginsRepository extends CrudRepository<Plugins, UUID> {
	List<Plugins> findAllByClusterName(String clusterName);
	Plugins findByClusterNameAndPluginId(String clusterName, String pluginId);
}
