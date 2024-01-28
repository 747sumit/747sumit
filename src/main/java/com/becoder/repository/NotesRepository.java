package com.becoder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.becoder.entity.Employee;
import com.becoder.entity.Notes;

public interface NotesRepository extends JpaRepository<Notes, Integer> {

	public List<Notes> findByEmployee(Employee emp);
}
