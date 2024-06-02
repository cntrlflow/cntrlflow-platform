package com.cntrlflow.server.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntrlflow.server.model.ClusterDetails;
import com.cntrlflow.server.repository.ClusterDetailsRepository;

@Service
public class ClusterDetailsService {
    @Autowired
	ClusterDetailsRepository clusterDetailsRepository;
	
	
	public Iterable<ClusterDetails> findClusterDetailsList() {
		return clusterDetailsRepository.findAll();
	}
	
	
	public Iterable<ClusterDetails> findClusterDetailsListByClusterName(String clusterName) {
		return clusterDetailsRepository.findAllByClusterName(clusterName);
	}
	
	public Boolean addClusterDetailsList(List<ClusterDetails> clusterDetailsList) {
		for(ClusterDetails clusterDetails : clusterDetailsList) {
			ClusterDetails existingItem = clusterDetailsRepository.findByClusterNameAndComponentName(clusterDetails.getClusterName(), clusterDetails.getComponentName());
			
			if(existingItem != null) {
				clusterDetails.setId(existingItem.getId());
				clusterDetailsRepository.save(clusterDetails);
			} else {
				clusterDetails.setId(UUID.randomUUID());
				clusterDetailsRepository.save(clusterDetails);
			}
		}
	
		return true;
	}
	
	public ClusterDetails getClusterDetailsByClusterNameAndComponentName(String clusterName, String componentName) {
		return clusterDetailsRepository.findByClusterNameAndComponentName(clusterName, componentName);
	}
	
	public Boolean deleteClusterDetailsList(List<ClusterDetails> clusterDetailsList) {
        for(ClusterDetails clusterDetails : clusterDetailsList) {
            clusterDetailsRepository.delete(clusterDetails);
        }
		return true;
	}
}