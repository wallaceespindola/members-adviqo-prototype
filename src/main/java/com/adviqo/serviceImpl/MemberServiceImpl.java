package com.adviqo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adviqo.app.Member;
import com.adviqo.app.MemberRepository;
import com.adviqo.exception.MemberNotFoundException;
import com.adviqo.service.MemberService;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberRepository memberRepository;
	
	@Override
	public List<Member> listMembers() {
		return memberRepository.findAll();
	}
	
	@Override
	public Member listMember(Long id) {

		Member m = memberRepository.findOne(id);
		
		if (m == null) {
			MemberNotFoundException ex = new MemberNotFoundException(id);
			throw ex;
		} else {
			return m;
		}
	}

	@Override
	public Member create(Member member) {
        Member result = memberRepository.save(member);
		return result;
	}

	@Override
	public Member update(Member member) {
		
        Member m = memberRepository.findOne(member.getId());
        
        m.setFirstName(member.getFirstName());
        m.setLastName(member.getLastName());
        m.setDateOfBirth(member.getDateOfBirth());
        m.setZipCode(member.getZipCode());
        
        Member result = memberRepository.save(m);
		return result;
	}

	@Override
	public void delete(Long id) {
		memberRepository.delete(id);
	}

}
