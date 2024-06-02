package com.cntrlflow.server.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cntrlflow.server.model.ClusterDashboard;
import com.cntrlflow.server.repository.ClusterDashboardRepository;

@Service
public class ClusterDashboardService {
    
    @Autowired
	private ClusterDashboardRepository clusterDashboardRepository;
	
	public Iterable<ClusterDashboard> findClusterDashboardList() {
		return clusterDashboardRepository.findAll();
	}
	
	
	public Iterable<ClusterDashboard> findClusterDashboardListByClusterName(String clusterName) {
		return clusterDashboardRepository.findAllByClusterName(clusterName);
	}
	
	
	public Boolean addClusterDashboardList(List<ClusterDashboard> clusterDashboardList) {
		for(ClusterDashboard clusterDashboard : clusterDashboardList) {
			ClusterDashboard existingItem = clusterDashboardRepository.findByClusterNameAndClusterType(clusterDashboard.getClusterName(),clusterDashboard.getClusterType());
			if(existingItem != null) {
				clusterDashboard.setId(existingItem.getId());
				clusterDashboardRepository.save(clusterDashboard);
			} else {
				clusterDashboard.setId(UUID.randomUUID());
				clusterDashboardRepository.save(clusterDashboard);
			}
		}
		return true;
	}
	
	
	public Boolean deleteClusterDashboardList(List<ClusterDashboard> clusterDashboardList) {
		for(ClusterDashboard clusterDashboard : clusterDashboardList) {
            clusterDashboardRepository.delete(clusterDashboard);
        }
		return true;
	}
}