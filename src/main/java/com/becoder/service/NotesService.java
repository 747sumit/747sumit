package com.becoder.service;

import java.util.List;

import com.becoder.entity.Employee;
import com.becoder.entity.Notes;

public interface NotesService {

	public Notes saveNotes(Notes notes);

	public Notes getNotesById(int id);

	public List<Notes> getNotesByUser(Employee emp);

	public Notes updateNotes(Notes notes);

	public boolean deleteNotes(int id);

}
