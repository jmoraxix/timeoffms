package com.timeoffms.web.dao;

import com.timeoffms.web.model.Overtime;
import com.timeoffms.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OvertimeRepository extends JpaRepository<Overtime, Long> {

	@Query("SELECT o FROM Overtime o WHERE o.user = :user")
	List<Overtime> findAllByUser(User user);

}
