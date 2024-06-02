package com.cntrlflow.server.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Register {
	List<ClusterDashboard> clusterDashboardList;
	List<ClusterServices> clusterServicesList;
	List<ClusterDetails> clusterDetailsList;
	List<Agents> agentsList;
}