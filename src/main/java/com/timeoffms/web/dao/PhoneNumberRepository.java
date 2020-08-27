package com.timeoffms.web.dao;

import com.timeoffms.web.model.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {

	List<PhoneNumber> findAllByUserId(Long id);
}
