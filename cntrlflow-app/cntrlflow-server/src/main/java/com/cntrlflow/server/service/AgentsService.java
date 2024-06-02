package com.cntrlflow.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.cntrlflow.server.model.Agents;
import com.cntrlflow.server.model.AgentsWithStatus;
import com.cntrlflow.server.repository.AgentsRepository;
import com.cntrlflow.server.utility.RSocketUtil;
import com.cntrlflow.server.utility.SocketUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AgentsService {

    @Autowired
	private AgentsRepository agentsRepository;
    
    @Autowired
    private SocketUtil socketUtil;
    
    @Autowired
	private RSocketUtil rSocketUtil;
    
    @Autowired
	public AgentsService(RSocketUtil rSocketUtil) {
		this.rSocketUtil = rSocketUtil;
	}

    public boolean addAgentsList(List<Agents> agentsList) {
        log.info("[cntrlflow] addAgentsList");
        for(Agents agent : agentsList) {
			Agents existingItem = agentsRepository.findByClusterNameAndHostName(agent.getClusterName(), agent.getHostName());
			if(existingItem != null) {
				agent.setId(existingItem.getId());
				agentsRepository.save(agent);
			} else {
				agent.setId(UUID.randomUUID());
				agentsRepository.save(agent);
			}
		}
		return true;
    }

    public boolean deleteAgentsList(List<Agents> agentsList) {
        for(Agents agent : agentsList) {
			agentsRepository.delete(agent);
		}
		return true;
    }

    public List<Agents> findAgentListByClusterName(String clusterName) {
        return agentsRepository.findAllByClusterName(clusterName);
    }
    
    public Agents findAgentByClusterNameAndHostName(String clusterName, String hostName) {
    	log.info("[cntrlflow] clustername: " + clusterName + " | hostname: " + hostName);
        return agentsRepository.findByClusterNameAndHostName(clusterName, hostName);
    }

    public Iterable<Agents> findAgentList() {
        return agentsRepository.findAll();
    }

	public List<AgentsWithStatus> findAgentListByClusterNameWithStatus(String clusterName) {
		List<Agents> agentsList = agentsRepository.findAllByClusterName(clusterName);
		List<CompletableFuture<AgentsWithStatus>> futureList = new ArrayList<CompletableFuture<AgentsWithStatus>>();
		
		for(Agents agent : agentsList) {
			AgentsWithStatus agentsWithStatus = new AgentsWithStatus();
			agentsWithStatus.setId(agent.getId());
			agentsWithStatus.setClusterName(agent.getClusterName());
			agentsWithStatus.setHostName(agent.getHostName());
			agentsWithStatus.setPortNumber(agent.getPortNumber());
			agentsWithStatus.setStatus(socketUtil.getHostStatus(agent.getHostName(), Integer.parseInt(agent.getPortNumber())));
			futureList.add(getAgentLatency(agentsWithStatus));
		}
		
		List<AgentsWithStatus> agentsListWithStatus = futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
		
		return agentsListWithStatus;
	}
	
	@Async
	public CompletableFuture<AgentsWithStatus> getAgentLatency(AgentsWithStatus agentWithStatus) {
		if(agentWithStatus.isStatus()) {
			// Record the start time
	        long startTime = System.currentTimeMillis();
	        
			RSocketRequester requester = rSocketUtil.createSecureRequester(agentWithStatus.getHostName(), Integer.parseInt(agentWithStatus.getPortNumber()));
			String response = requester.route("rr.test").data("request response testing").retrieveMono(String.class).block();

			log.info("[cntrlflow] response --> " + response);
			rSocketUtil.close(requester);
			
			// Record the end time
	        long endTime = System.currentTimeMillis();

	        // Calculate the time taken
	        long elapsedTime = endTime - startTime;
	        
	        agentWithStatus.setLatencyTime(String.valueOf(elapsedTime));
		} else {
			agentWithStatus.setLatencyTime("");
		}

        return CompletableFuture.completedFuture(agentWithStatus);
	}
}
