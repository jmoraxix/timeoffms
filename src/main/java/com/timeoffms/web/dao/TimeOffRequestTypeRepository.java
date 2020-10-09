//package com.timeoffms.web.other;
//
//import com.timeoffms.web.model.TimeOffRequestStatus;
//import com.timeoffms.web.model.TimeOffRequestType;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import javax.transaction.Transactional;
//
//@Repository
//public interface TimeOffRequestTypeRepository extends JpaRepository<TimeOffRequestType, Long> {
//
//	TimeOffRequestType findByCode(String code);
//
//	@Transactional
//	void deleteByCode(String code);
//}
