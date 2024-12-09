package com.synergy_task.assignment.service;

import java.util.List;

import com.synergy_task.assignment.model.School;

public interface SchoolService {
	
	public void saveSchool(School school);

	List<School> getAllSchools();

}
