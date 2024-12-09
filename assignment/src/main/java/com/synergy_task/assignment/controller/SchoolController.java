package com.synergy_task.assignment.controller;

import com.synergy_task.assignment.model.School;
import com.synergy_task.assignment.repository.SchoolRepository;
import com.synergy_task.assignment.service.SchoolService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")

public class SchoolController {
	
	@Autowired
	private SchoolService schoolService;

    @Autowired
    private SchoolRepository schoolRepository;
    
    @PostMapping("/addSchool")
    @ResponseBody
    public String addSchool(@RequestBody School school) {
        System.out.println("Received School Data: " + school);
        schoolService.saveSchool(school);
        return "School details added successfully";
    }


    @GetMapping
    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public School getSchoolById(@PathVariable Long id) {
        return schoolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("School not found with ID: " + id));
    }

    @PutMapping("/{id}")
    public School updateSchool(@PathVariable Long id, @RequestBody School schoolDetails) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("School not found with ID: " + id));

        school.setSchoolName(schoolDetails.getSchoolName());
        school.setState(schoolDetails.getState());
        school.setCity(schoolDetails.getCity());
        school.setAddress(schoolDetails.getAddress());
        school.setLandmark(schoolDetails.getLandmark());
        school.setPincode(schoolDetails.getPincode());

        return schoolRepository.save(school);
    }

    @DeleteMapping("/{id}")
    public String deleteSchool(@PathVariable Long id) {
        schoolRepository.deleteById(id);
        return "School with ID: " + id + " has been deleted.";
    }

}
