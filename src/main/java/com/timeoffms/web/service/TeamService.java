package com.timeoffms.web.service;

import com.timeoffms.web.dao.TeamRepository;
import com.timeoffms.web.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

	@Autowired
	private TeamRepository teamRepository;

	public List<Team> findAll(){
		return teamRepository.findAll();
	}

	public Team findTeam(Long id){
		return Optional.ofNullable(teamRepository.findById(id).get()).orElse(new Team());
	}
	public void saveTeam(Team team){
		teamRepository.save(team);
	}

	public void deleteTeam(Long id){
		teamRepository.deleteById(id);
	}
}
