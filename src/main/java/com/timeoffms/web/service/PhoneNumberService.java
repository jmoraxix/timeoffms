package com.timeoffms.web.service;

import com.timeoffms.web.dao.PhoneNumberRepository;
import com.timeoffms.web.model.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneNumberService {

	@Autowired
	private PhoneNumberRepository phoneNumberRepository;

	@Autowired
	private UserService userService;

	public List<PhoneNumber> findAll(){
		return phoneNumberRepository.findAll();
	}

	public List<PhoneNumber> findByUserId(Long userId){
		return phoneNumberRepository.findAllByUserId(userId);
	}

	public PhoneNumber findPhoneNumber(Long id){
		return phoneNumberRepository.findById(id).get();
	}

	public void savePhoneNumber(PhoneNumber phoneNumber){
		phoneNumberRepository.save(phoneNumber);
	}

	public void deletePhoneNumber(Long id){
		phoneNumberRepository.deleteById(id);
	}
}
