package com.cntrlflow.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntrlflow.server.model.Alerts;
import com.cntrlflow.server.repository.AlertsRepository;

@Service
public class AlertsService {
    
    @Autowired
	AlertsRepository alertsRepository;

    public Iterable<Alerts> findAllAlertList() {
		return alertsRepository.findAll();
	}

    public List<Alerts> findAlertsListByClusterName(String clusterName) {
        return alertsRepository.findAllByClusterName(clusterName);
    }

    public Boolean deleteAlertsList(List<Alerts> alertList) {
        for(Alerts alert : alertList) {
            alertsRepository.delete(alert);
        }
		return true;
	}

    public Boolean addAlertsList(List<Alerts> alertList) {
		for(Alerts alert : alertList) {
			Alerts existingItem = alertsRepository.findByClusterNameAndAlertName(alert.getClusterName(), alert.getAlertName());
			if(existingItem != null) {
				alert.setId(existingItem.getId());
				alertsRepository.save(alert);
			} else {
				alertsRepository.save(alert);
			}
		}
		return true;
	}
}