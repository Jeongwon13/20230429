package edu.kh.comm.member.controller;

 

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import edu.kh.comm.member.model.service.MemberService;
import edu.kh.comm.member.model.service.MemberServiceImpl;
import edu.kh.comm.member.model.vo.Member;
@Controller
@RequestMapping("/member")
@SessionAttributes({"loginMember"})

public class MemberController {

	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	 
	@Autowired
	private MemberService service;
	
	@PostMapping("/login")
	public String login( @ModelAttribute Member inputMember 
						, Model model
						, RedirectAttributes ra
						, HttpServletResponse resp
						, HttpServletRequest req
						, @RequestParam(value="saveId", required = false) String saveId) {
							
		logger.info("로그인 기능 수행됨~");
		
		Member loginMember = service.login(inputMember);
		
		if(loginMember != null) {
			model.addAttribute("loginMember", loginMember);
			
			Cookie cookie = new Cookie("saveId", loginMember.getMemberEmail());
			
			if(saveId != null) {
				cookie.setMaxAge(60 * 60 * 24 * 365);
				
			} else {
				cookie.setMaxAge(0);
			}
			
			cookie.setPath(req.getContextPath());
			
			resp.addCookie(cookie);
		
		} else {
			ra.addFlashAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		
		return "redirect:/";
		
		
	 
	}

}
