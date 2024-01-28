package com.becoder.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.becoder.entity.Employee;
import com.becoder.entity.Notes;
import com.becoder.repository.EmpRepo;
import com.becoder.service.NotesService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/")
public class UserController {

	@Autowired
	private NotesService notesService;

	@GetMapping("/index")
	public String index() {
		return "index";
	}

	@GetMapping("/profile")
	public String profile() {
		return "profile";
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
	public String viewNotes(Model m, Principal p) {
		Employee emp = commonUser(p, m);
		List<Notes> notes = notesService.getNotesByUser(emp);
		m.addAttribute("notesList", notes);
		return "view_Notes";
	}

	@GetMapping("/editNotes/{id}")
	public String editNotes(@PathVariable int id, Model m) {
		Notes notes = notesService.getNotesById(id);
		m.addAttribute("n", notes);
		return "edit_Notes";
	}

	@PostMapping("/saveNotes")
	public String saveNotes(@ModelAttribute Notes notes, HttpSession session, Principal p, Model m) {
		notes.setDate(LocalDate.now());
		notes.setEmp(commonUser(p, m));
		Notes saveNotes = notesService.saveNotes(notes);
		if (saveNotes != null) {
			session.setAttribute("msg", "Note Saved Successfully");
		} else {
			session.setAttribute("msg", "Note Not Saved");
		}
		return "redirect:/user/addNotes";
	}

	@PostMapping("/updateNotes")
	public String updateNotes(@ModelAttribute Notes notes, HttpSession session, Principal p, Model m) {
		notes.setDate(LocalDate.now());
		notes.setEmp(commonUser(p, m));
		Notes saveNotes = notesService.saveNotes(notes);
		if (saveNotes != null) {
			session.setAttribute("msg", "Note Updated Successfully");
		} else {
			session.setAttribute("msg", "Note Not Updated");
		}
		return "redirect:/user/viewNotes";
	}

	@GetMapping("/deleteNotes/{id}")
	public String deleteNotes(@PathVariable int id, HttpSession session) {
		boolean f = notesService.deleteNotes(id);
		if (f) {
			session.setAttribute("msg", "Note Updated Successfully");
		} else {
			session.setAttribute("msg", "Note Not Updated");
		}
		return "redirect:/user/viewNotes";
	}

	@Autowired
	private EmpRepo empRepo;

	@ModelAttribute
	public Employee commonUser(Principal p, Model m) {

		String email = p.getName();
		Employee emp = empRepo.findByEmail(email);

		m.addAttribute("emp", emp);
		return emp;

	}

}
