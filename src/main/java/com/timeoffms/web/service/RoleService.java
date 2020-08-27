package com.timeoffms.web.service;

import com.timeoffms.web.dao.RoleRepository;
import com.timeoffms.web.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public List<Role> findAll(){
		return roleRepository.findAll();
	}

	public Role findRole(Long id){
		return roleRepository.findById(id).get();
	}

	public void saveRole(Role role){
		roleRepository.save(role);
	}

	public void deleteRole(Long id){
		roleRepository.deleteById(id);
	}
}
