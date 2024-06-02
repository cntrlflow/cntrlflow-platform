package com.cntrlflow.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cntrlflow.server.model.Alerts;
import com.cntrlflow.server.model.AuditLog;
import com.cntrlflow.server.repository.AuditLogRepository;

@Service
public class AuditLogService {
 
    @Autowired
    AuditLogRepository auditLogRepository;

    public Iterable<Alerts> findAuditLogsList() {
        return auditLogRepository.findAll();
    }

    public List<AuditLog> findAuditLogsListByClusterName(String clusterName) {
        return auditLogRepository.findAllByClusterName(clusterName);
    }
}