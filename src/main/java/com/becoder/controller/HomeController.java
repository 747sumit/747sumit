package com.becoder.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.becoder.entity.Employee;
import com.becoder.repository.EmpRepo;
import com.becoder.service.EmpService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private EmpService empService;

	@GetMapping("/index")
	public String index() {
		return "index";
	}

	@Autowired
	private EmpRepo empRepo;

	@ModelAttribute
	public void commonUser(Principal p, Model m) {
		if (p != null) {
			String email = p.getName();
			Employee emp = empRepo.findByEmail(email);

			m.addAttribute("emp", emp);
		}
	}

	@GetMapping("/signin")
	public String signin() {
		return "login";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@PostMapping("/registerEmp")
	public String saveEmp(@ModelAttribute Employee emp, HttpSession session) {

		boolean f = empService.existEmailCheck(emp.getEmail());
		if (f) {
			session.setAttribute("msg", "User Already exists");
		}

		else {
			Employee e = empService.saveEmp(emp);
			if (e != null) {
				session.setAttribute("msg", "Registeration Successful");
			} else {
				session.setAttribute("msg", "Registeration Failed");
			}

		}
		return "redirect:/register";
	}

}
