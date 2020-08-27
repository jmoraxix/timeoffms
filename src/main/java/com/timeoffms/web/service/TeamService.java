package com.timeoffms.web.service;

import com.timeoffms.web.dao.TeamRepository;
import com.timeoffms.web.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

	@Autowired
	private TeamRepository teamRepository;

	public List<Team> findAll(){
		return teamRepository.findAll();
	}

	public Team findTeam(Long id){
		return teamRepository.findById(id).get();
	}

	public Team findIfExists(Long id){
		return id == null ? new Team() : findTeam(id);
	}

	public void saveTeam(Team team){
		teamRepository.save(team);
	}

	public void deleteTeam(Long id){
		teamRepository.deleteById(id);
	}
}
