package com.ssafy.db.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.db.entity.Common;
import com.ssafy.db.entity.Common_detail;

public interface CommonDetailRepository extends JpaRepository<Common_detail, Long>{
	
	// 키워드 상세
	ArrayList<String> findAllByCommon(Common keycommon);
	
	Optional<Common_detail> findById(Long cid);

}
