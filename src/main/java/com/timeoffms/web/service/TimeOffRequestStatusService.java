//package com.timeoffms.web.other;
//
//import com.timeoffms.web.model.TimeOffRequestStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TimeOffRequestStatusService {
//
//	@Autowired
//	private TimeOffRequestStatusRepository timeOffRequestStatusRepository;
//
//	public List<TimeOffRequestStatus> findAll(){
//		return timeOffRequestStatusRepository.findAll();
//	}
//
//	public TimeOffRequestStatus findTimeOffRequestStatus(String code){
//		return timeOffRequestStatusRepository.findByCode(code);
//	}
//
//	public void saveTimeOffRequestStatus(TimeOffRequestStatus TimeOffRequestStatus){
//		timeOffRequestStatusRepository.save(TimeOffRequestStatus);
//	}
//
//	public void deleteTimeOffRequestStatus(String code){
//		timeOffRequestStatusRepository.deleteByCode(code);
//	}
//}
