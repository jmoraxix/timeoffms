package com.timeoffms.web.service;

import com.timeoffms.web.dao.OvertimeRepository;
import com.timeoffms.web.model.Overtime;
import com.timeoffms.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OvertimeService {

	@Autowired
	private OvertimeRepository overtimeRepository;

	public List<Overtime> findAll(){
		return overtimeRepository.findAll();
	}

	public List<Overtime> findAllByUser(User user){
		return overtimeRepository.findAllByUser(user);
	}

	public Overtime findById(Long id){
		return overtimeRepository.findById(id).get();
	}

	public void save(Overtime overtime){
		overtimeRepository.save(overtime);
	}

	public void delete(Long id){
		overtimeRepository.deleteById(id);
	}
}
