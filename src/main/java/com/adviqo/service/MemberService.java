package com.adviqo.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.adviqo.app.Member;
import com.adviqo.exception.MemberNotFoundException;

public interface MemberService {
	
	List<Member> listMembers();
	
	public Member listMember(Long id) throws MemberNotFoundException;
	
	@Transactional
	public Member create(Member member);
	
	@Transactional
	public Member update(Member member);
	
	@Transactional
	public void delete(Long id);

}
