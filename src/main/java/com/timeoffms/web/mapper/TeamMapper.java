package com.timeoffms.web.mapper;

import com.timeoffms.web.dto.TeamDto;
import com.timeoffms.web.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TeamMapper {

	TeamMapper MAPPER = Mappers.getMapper(TeamMapper.class);

	TeamDto teamToTeamDto(Team team);
	Team teamDtoToTeam(TeamDto teamDto);
}
