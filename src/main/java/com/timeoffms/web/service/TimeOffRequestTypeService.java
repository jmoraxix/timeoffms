//package com.timeoffms.web.other;
//
//import com.timeoffms.web.model.TimeOffRequestType;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TimeOffRequestTypeService {
//
//	@Autowired
//	private TimeOffRequestTypeRepository timeOffRequestTypeRepository;
//
//	public List<TimeOffRequestType> findAll(){
//		return timeOffRequestTypeRepository.findAll();
//	}
//
//	public TimeOffRequestType findTimeOffRequestType(String code){
//		return timeOffRequestTypeRepository.findByCode(code);
//	}
//
//	public void saveTimeOffRequestType(TimeOffRequestType TimeOffRequestType){
//		timeOffRequestTypeRepository.save(TimeOffRequestType);
//	}
//
//	public void deleteTimeOffRequestType(String code){
//		timeOffRequestTypeRepository.deleteByCode(code);
//	}
//}
