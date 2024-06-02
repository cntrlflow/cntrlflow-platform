package com.cntrlflow.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntrlflow.server.model.ClusterDetails;
import com.cntrlflow.server.model.ClusterServices;
import com.cntrlflow.server.model.ClusterServicesWithDetails;
import com.cntrlflow.server.model.ClusterServicesWithStatus;
import com.cntrlflow.server.model.KeyValuePair;
import com.cntrlflow.server.repository.ClusterDetailsRepository;
import com.cntrlflow.server.repository.ClusterServicesRepository;
import com.cntrlflow.server.utility.SocketUtil;

@Service
public class ClusterServicesService {

	@Autowired
	private SocketUtil socketUtil;

	@Autowired
	private ClusterServicesRepository clusterServicesRepository;
	
	@Autowired
	private ClusterDetailsRepository clusterDetailsRepository;

	public Iterable<ClusterServices> findAllClusterServices() {
		return clusterServicesRepository.findAll();
	}

	public Iterable<ClusterServices> findAllClusterServicesByClusterName(String clusterName) {
		return clusterServicesRepository.findAllByClusterName(clusterName);
	}

	public Boolean deleteClusterServices(List<ClusterServices> clusterServicesList) {
		for (ClusterServices clusterServices : clusterServicesList) {
			clusterServicesRepository.delete(clusterServices);
		}
		return true;
	}

	public Boolean addClusterServices(List<ClusterServices> clusterServicesList) {
		for (ClusterServices clusterServices : clusterServicesList) {
			ClusterServices existingItem = clusterServicesRepository.findByClusterNameAndComponentNameAndHostName(
					clusterServices.getClusterName(), clusterServices.getComponentName(),
					clusterServices.getHostName());
			if (existingItem != null) {
				clusterServices.setId(existingItem.getId());
				clusterServicesRepository.save(clusterServices);
			} else {
				clusterServices.setId(UUID.randomUUID());
				clusterServicesRepository.save(clusterServices);
			}
		}
		return true;
	}

	public List<ClusterServicesWithStatus> findAllClusterServicesWithStatus() {

		List<ClusterServicesWithStatus> clusterServicesWithStatusList = new ArrayList<ClusterServicesWithStatus>();

		Iterable<ClusterServices> clusterServicesList = clusterServicesRepository.findAll();

		for (ClusterServices clusterServices : clusterServicesList) {
			ClusterServicesWithStatus clusterServicesWithStatus = new ClusterServicesWithStatus();
			clusterServicesWithStatus.setId(clusterServices.getId());
			clusterServicesWithStatus.setClusterName(clusterServices.getClusterName());
			clusterServicesWithStatus.setComponentName(clusterServices.getComponentName());
			clusterServicesWithStatus.setHostName(clusterServices.getHostName());
			clusterServicesWithStatus.setLockStatus(clusterServices.isLockStatus());
			clusterServicesWithStatus.setPortNumber(clusterServices.getPortNumber());

			String[] portList = clusterServices.getPortNumber().split(",");
			List<KeyValuePair> statusList = new ArrayList<KeyValuePair>();
			for (String port : portList) {
				Boolean status = socketUtil.getHostStatus(clusterServices.getHostName(),Integer.parseInt(port));
				KeyValuePair keyValuePair = new KeyValuePair();
				keyValuePair.setKey(port);
				keyValuePair.setValue(status);
				statusList.add(keyValuePair);
			}
			clusterServicesWithStatus.setStatus(statusList);
			clusterServicesWithStatusList.add(clusterServicesWithStatus);
		}

		return clusterServicesWithStatusList;
	}

	public List<ClusterServicesWithStatus> findAllClusterServicesByClusterNameWithStatus(String clusterName) {
		List<ClusterServicesWithStatus> clusterServicesWithStatusList = new ArrayList<ClusterServicesWithStatus>();

		Iterable<ClusterServices> clusterServicesList = clusterServicesRepository.findAllByClusterName(clusterName);

		for (ClusterServices clusterServices : clusterServicesList) {
			ClusterServicesWithStatus clusterServicesWithStatus = new ClusterServicesWithStatus();
			clusterServicesWithStatus.setId(clusterServices.getId());
			clusterServicesWithStatus.setClusterName(clusterServices.getClusterName());
			clusterServicesWithStatus.setComponentName(clusterServices.getComponentName());
			clusterServicesWithStatus.setHostName(clusterServices.getHostName());
			clusterServicesWithStatus.setLockStatus(clusterServices.isLockStatus());
			clusterServicesWithStatus.setPortNumber(clusterServices.getPortNumber());

			String[] portList = clusterServices.getPortNumber().split(",");
			List<KeyValuePair> statusList = new ArrayList<KeyValuePair>();
			for (String port : portList) {
				Boolean status = socketUtil.getHostStatus(clusterServices.getHostName(),Integer.parseInt(port));
				KeyValuePair keyValuePair = new KeyValuePair();
				keyValuePair.setKey(port);
				keyValuePair.setValue(status);
				statusList.add(keyValuePair);
			}
			clusterServicesWithStatus.setStatus(statusList);
			clusterServicesWithStatusList.add(clusterServicesWithStatus);
		}

		return clusterServicesWithStatusList;
	}

	public List<ClusterServicesWithDetails> findAllClusterServicesAndClusterDetailsByClusterName(String clusterName) {
		Iterable<ClusterServices> clusterServicesList = clusterServicesRepository.findAllByClusterName(clusterName);
		List<ClusterServicesWithDetails> clusterServicesWithDetailsList = new ArrayList<ClusterServicesWithDetails>();
		
		for(ClusterServices clusterServices : clusterServicesList) {
			ClusterDetails clusterDetails = clusterDetailsRepository.findByClusterNameAndComponentName(clusterName, clusterServices.getComponentName());
			ClusterServicesWithDetails clusterServicesWithDetails = new ClusterServicesWithDetails();
			
			clusterServicesWithDetails.setClusterName(clusterName);
			clusterServicesWithDetails.setComponentName(clusterServices.getComponentName());
			clusterServicesWithDetails.setComponentVersion(clusterDetails.getComponentVersion());
			clusterServicesWithDetails.setExecuteUser(clusterDetails.getExecuteUser());
			clusterServicesWithDetails.setHomeLocation(clusterDetails.getHomeLocation());
			clusterServicesWithDetails.setHostName(clusterServices.getHostName());
			clusterServicesWithDetails.setLockStatus(clusterServices.isLockStatus());
			clusterServicesWithDetails.setLogLocation(clusterDetails.getLogLocation());
			clusterServicesWithDetails.setPortNumber(clusterServices.getPortNumber());
			clusterServicesWithDetails.setPropertiesLocation(clusterDetails.getPropertiesLocation());
			clusterServicesWithDetails.setStartLocation(clusterDetails.getStartLocation());
			clusterServicesWithDetails.setStartPriority(clusterDetails.getStartPriority());
			clusterServicesWithDetails.setStartTimeout(clusterDetails.getStartTimeout());
			clusterServicesWithDetails.setStopLocation(clusterDetails.getStopLocation());
			
			clusterServicesWithDetailsList.add(clusterServicesWithDetails);
		}
		return clusterServicesWithDetailsList;
	}
}