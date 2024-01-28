package com.becoder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.becoder.entity.Employee;
import com.becoder.repository.EmpRepo;

import jakarta.servlet.http.HttpSession;

@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	private EmpRepo empRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public Employee saveEmp(Employee emp) {

		String password = passwordEncoder.encode(emp.getPassword());
		emp.setPassword(password);
		emp.setRole("ROLE_USER");
		Employee newemp = empRepo.save(emp);
		return newemp;
	}

	@Override
	public void removeSessionMessage() {
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();
		session.removeAttribute("msg");

	}

	@Override
	public boolean existEmailCheck(String email) {

		return empRepo.existsByEmail(email);
	}

}
