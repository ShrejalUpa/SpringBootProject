package com.synergy_task.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.synergy_task.assignment.model.Subject;
import com.synergy_task.assignment.serviceimpl.SubjectServiceImpl;

@RestController
public class SubjectController {

    @Autowired
    private SubjectServiceImpl subjectService;

    @PostMapping("/addSubject")
    public String addSubject(@RequestBody Subject subject) {
        subjectService.saveSubject(subject);
        return "Subject added successfully";
    }

    @GetMapping("/getSubjects")
    public List<Subject> getSubjects() {
        return subjectService.getAllSubjects();
    }
}
