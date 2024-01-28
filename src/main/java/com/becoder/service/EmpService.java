package com.becoder.service;

import com.becoder.entity.Employee;

public interface EmpService {
	public Employee saveEmp(Employee emp);

	public void removeSessionMessage();

	boolean existEmailCheck(String email);
}
