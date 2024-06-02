package com.cntrlflow.server.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntrlflow.server.model.Plugins;
import com.cntrlflow.server.repository.PluginsRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PluginsService {

	@Autowired
	private PluginsRepository pluginsRepository;
	
	public boolean addPluginsList(List<Plugins> PluginsList) {
        log.info("[cntrlflow] addPluginsList");
        for(Plugins plugin : PluginsList) {
			Plugins existingItem = pluginsRepository.findByClusterNameAndPluginId(plugin.getClusterName(), plugin.getPluginId());
			if(existingItem != null) {
				plugin.setId(existingItem.getId());
				pluginsRepository.save(plugin);
			} else {
				plugin.setId(UUID.randomUUID());
				pluginsRepository.save(plugin);
			}
		}
		return true;
    }

    public boolean deletePluginsList(List<Plugins> pluginsList) {
        for(Plugins plugin : pluginsList) {
			pluginsRepository.delete(plugin);
		}
		return true;
    }

    public List<Plugins> findPluginListByClusterName(String clusterName) {
        return pluginsRepository.findAllByClusterName(clusterName);
    }

    public Iterable<Plugins> findPluginList() {
        return pluginsRepository.findAll();
    }
}
