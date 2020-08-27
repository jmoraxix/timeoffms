package com.timeoffms.web.mapper;

import com.timeoffms.web.dto.UserDto;
import com.timeoffms.web.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

	UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

	UserDto userToUserDto(User user);
	User userDtoToUser(UserDto userDto);
}
