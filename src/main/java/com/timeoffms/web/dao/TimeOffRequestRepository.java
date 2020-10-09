package com.timeoffms.web.dao;

import com.timeoffms.web.model.TimeOffRequest;
import com.timeoffms.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeOffRequestRepository extends JpaRepository<TimeOffRequest, Long> {

	@Query("SELECT r from TimeOffRequest r " +
			"WHERE r.requestedBy = :user AND r.timeOffRequestStatus = com.timeoffms.web.model.TimeOffRequestStatus.PENDING_APPROVAL " +
			"ORDER BY r.requestedDate")
	List<TimeOffRequest> findAllActiveByUser(User user);

	@Query("SELECT r from TimeOffRequest r " +
			"WHERE r.requestedBy = :user AND r.timeOffRequestStatus = com.timeoffms.web.model.TimeOffRequestStatus.APPROVED " +
			"ORDER BY r.requestedDate")
	List<TimeOffRequest> findAllApprovedByUser(User user);

	@Query("SELECT r FROM TimeOffRequest r " +
			"WHERE r.timeOffRequestStatus = com.timeoffms.web.model.TimeOffRequestStatus.PENDING_APPROVAL " +
			"ORDER BY r.requestedDate")
	List<TimeOffRequest> findAllActive();

	@Query("SELECT r FROM TimeOffRequest r WHERE r.requestedBy = :user ORDER BY r.requestedDate ")
	List<TimeOffRequest> findAllByUser(User user);

	@Query(value = "SELECT r.* FROM time_off_request r WHERE r.requested_by_id IN ( " +
						"SELECT a.employee_id FROM approver a WHERE a.approver_id = :userId " +
						"UNION " +
						"SELECT u.id FROM user u " +
							"JOIN user_teams ut ON ut.user_id = u.id " +
							"JOIN team t ON t.id = ut.team_id " +
							"JOIN default_approver d ON d.team_id = t.id " +
						"WHERE d.approver_id = :userId " +
					"ORDER BY r.requested_date DESC ",
			nativeQuery = true)
	List<TimeOffRequest> findAllToApproveByUser(@Param("userId") Long userId);
}
