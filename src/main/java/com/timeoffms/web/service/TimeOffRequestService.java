package com.timeoffms.web.service;

import com.timeoffms.web.dao.TimeOffRequestRepository;
import com.timeoffms.web.model.TimeOffRequest;
import com.timeoffms.web.model.User;
import com.timeoffms.web.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TimeOffRequestService {

	@Autowired
	private TimeOffRequestRepository timeOffRequestRepository;

	@Autowired
	private UserService userService;

	public List<TimeOffRequest> findAll(){
		return timeOffRequestRepository.findAll();
	}

	public List<TimeOffRequest> findAllFromCurrentUser(){
		return findAllByUser(Utils.getCurrentUser(userService));
	}

	public List<TimeOffRequest> findAllToApproveByCurrentUser(){
		return findAllByUser(Utils.getCurrentUser(userService));
	}
	public List<TimeOffRequest> findAllToApproveByUser(User user){
		return timeOffRequestRepository.findAllToApproveByUser(user.getId());
	}

	public List<TimeOffRequest> findAllByUser(User user){
		return Optional.ofNullable(timeOffRequestRepository.findAllByUser(user)).orElse(new ArrayList<>());
	}

	public List<TimeOffRequest> findAllActive(){
		return timeOffRequestRepository.findAllActive();
	}

	public List<TimeOffRequest> findAllActiveFromCurrentUser(){
		return findAllActiveFromUser(Utils.getCurrentUser(userService));
	}

	public List<TimeOffRequest> findAllActiveFromUser(User user){
		return Optional.ofNullable(timeOffRequestRepository.findAllActiveByUser(user)).orElse(new ArrayList<>());
	}

	public TimeOffRequest findById(Long id){
		return timeOffRequestRepository.findById(id).get();
	}

	public TimeOffRequest findIfExists(Long id){
		return id == null ? new TimeOffRequest() : findById(id);
	}

	public void save(TimeOffRequest timeOffRequest){
		timeOffRequestRepository.save(timeOffRequest);
	}

	public void delete(Long id){
		timeOffRequestRepository.deleteById(id);
	}
}
