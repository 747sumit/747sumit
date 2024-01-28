package com.becoder.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.becoder.entity.Employee;
import com.becoder.repository.EmpRepo;

@Controller
@RequestMapping("/admin/")
public class AdminController {

	@GetMapping("/index")
	public String index() {
		return "index";
	}

	@GetMapping("/profile")
	public String profile() {
		return "admin_profile";
	}

	@GetMapping("/home")
	public String home() {
		return "home";
	}

	@GetMapping("/addNotes")
	public String addNotes() {
		return "add_Notes";
	}

	@GetMapping("/viewNotes")
	public String viewNotes() {
		return "view_Notes";
	}

	@GetMapping("/editNotes")
	public String editNotes() {
		return "edit_Notes";
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
}
