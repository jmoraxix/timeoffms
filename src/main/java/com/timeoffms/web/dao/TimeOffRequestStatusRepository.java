//package com.timeoffms.web.other;
//
//import com.timeoffms.web.model.Country;
//import com.timeoffms.web.model.TimeOffRequestStatus;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import javax.transaction.Transactional;
//
//@Repository
//public interface TimeOffRequestStatusRepository extends JpaRepository<TimeOffRequestStatus, Long> {
//
//	TimeOffRequestStatus findByCode(String code);
//
//	@Transactional
//	void deleteByCode(String code);
//}
