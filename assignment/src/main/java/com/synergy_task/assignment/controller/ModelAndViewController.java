package com.synergy_task.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.synergy_task.assignment.model.School;
import com.synergy_task.assignment.service.SchoolService;

@Controller
public class ModelAndViewController {

	@Autowired
	private SchoolService schoolService;

	@GetMapping("/subject-details")
	public String subjectDetails() {
		return "subjectDetails";
	}

	@GetMapping("/school-details")
	public String schoolDetails() {
		return "schoolDetails";
	}

	@GetMapping("/student-details")
	public String studentDetails() {
		return "studentDetails";
	}

	
	@GetMapping("/getSchools")
	@ResponseBody
	public List<School> getSchools() {
		return schoolService.getAllSchools();
	}
}
