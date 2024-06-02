package com.cntrlflow.server.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntrlflow.server.model.UserRole;
import com.cntrlflow.server.repository.UserRoleRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserRoleService {

    @Autowired
    UserRoleRepository userRoleRepository;

    public Iterable<UserRole> findUserRoleList() {
        return userRoleRepository.findAll();
    }

    public List<UserRole> findUserRoleListByClusterName(String clusterName) {
        return userRoleRepository.findAllByClusterName(clusterName);
    }

    public boolean addUserRoleList(List<UserRole> userRoleList) {
        log.info("[cntrlflow] addUserRoleList");
        for(UserRole userRole : userRoleList) {
			UserRole existingItem = userRoleRepository.findByClusterNameAndUserName(userRole.getClusterName(), userRole.getUserName());
			if(existingItem != null) {
				userRole.setId(existingItem.getId());
				userRoleRepository.save(userRole);
			} else {
				userRoleRepository.save(userRole);
			}
		}
		return true;
    }

    public boolean deleteUserRoleList(List<UserRole> userRoleList) {
        for(UserRole userRole : userRoleList) {
			userRoleRepository.delete(userRole);
		}
		return true;
    }
}