package com.adviqo.app;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    
	List<Member> findByFirstName(String firstName);
    
	List<Member> findByLastName(String lastName);
    
	List<Member> findByDateOfBirth(Date dateofbirth);
	
	List<Member> findByZipCode(Long zipcode);
}
