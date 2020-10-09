package com.timeoffms.web.model.listeners;

import com.timeoffms.web.dao.TimeOffRequestRepository;
import com.timeoffms.web.model.User;
import com.timeoffms.web.service.OvertimeService;
import com.timeoffms.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PreUpdate;

@Component
public class UserJpaCallbacksListener {

	@Autowired
	private UserService userService;

	@Autowired
	private OvertimeService overtimeService;

	@Autowired
	private TimeOffRequestRepository timeOffRequestRepository;

	@PreUpdate
	void preUpdate(User user) {
		// TODO Fix vacation days calculation
//		if (userService.existsById(user.getId())) {
//			// Calculate extra vacation days from overtime
//			List<Overtime> overtimes = overtimeService.findAllByUser(user);
//			Double extraHours = 0.0;
//			for(Overtime overtime : overtimes){
//				if(overtime.getOvertimeType().getDefaultHours() != 0){
//					extraHours += overtime.getOvertimeType().getDefaultHours();
//				} else {
//					extraHours += overtime.getHours();
//				}
//			}
//			user.setOvertimeExtraDays(extraHours / 8);
//
//			// Calculate expended vacation days
//			List<TimeOffRequest> requests = timeOffRequestRepository.findAllApprovedByUser(user);
//			int expendedDays = 0;
//			for(TimeOffRequest request : requests){
//				expendedDays += request.getTotalDays();
//			}
//			user.setExpendedVacationDays(expendedDays);
//		}
	}

}