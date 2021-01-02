package com.adviqo.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.adviqo.app.Member;
import com.adviqo.exception.MemberNotFoundException;
import com.adviqo.service.MemberService;

@RestController
@RequestMapping("/")
public class MemberRestController {

	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/members", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<Member> listMembers() {
		
		return memberService.listMembers();
	}
	
	@RequestMapping(value = "/members/{id}", method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Member listMember(@PathVariable Long id) throws MemberNotFoundException {
		
		return memberService.listMember(id);
	}
	
	@RequestMapping(value = "/members/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		
		memberService.delete(id);
		
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

    @RequestMapping(value = "/members", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Member member, UriComponentsBuilder ucBuilder) throws ParseException {
    	
        System.out.println("Creating member [" + member.getFirstName() + ", " + member.getLastName() + ", " + member.getDateOfBirth() + ", " + member.getZipCode() + "]");
        
        Member result = memberService.create(member);
 
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ucBuilder.path("/members/{id}").buildAndExpand(result.getId()).toUri());
        return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
    }
    

    @RequestMapping(value = "/members/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Long memberId, @RequestBody Member member, UriComponentsBuilder ucBuilder) throws ParseException {
    	
        System.out.println("Updating member [" + memberId + ", " + member.getFirstName() + ", " + member.getLastName() + ", " + member.getDateOfBirth() + ", " + member.getZipCode() + "]");
        
        member.setId(memberId);
        Member result = memberService.update(member);
        
 
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ucBuilder.path("/members/{id}").buildAndExpand(result.getId()).toUri());
        return new ResponseEntity<Void>(httpHeaders, HttpStatus.OK);
    }

}
