package edu.kh.comm.member.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.kh.comm.member.model.dao.MemberDAO;
import edu.kh.comm.member.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	private Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	//=====================================================================================
	
	
	@Override
	public Member login(Member inputMember) {
		
		logger.debug(inputMember.getMemberPw() + " / " + bcrypt.encode(inputMember.getMemberPw()));
		
		Member loginMember = dao.login(inputMember);
		
		if(loginMember != null) {
			if(bcrypt.matches(inputMember.getMemberPw(), loginMember.getMemberPw())) {
				loginMember.setMemberPw(null);
			} else {
				loginMember = null;
			}
		} 
		
		return loginMember;
	}

	
}