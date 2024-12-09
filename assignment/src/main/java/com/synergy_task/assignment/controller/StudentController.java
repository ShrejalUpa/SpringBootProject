package com.synergy_task.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.synergy_task.assignment.model.Student;
import com.synergy_task.assignment.serviceimpl.StudentServiceImpl;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @PostMapping("/addStudent")
    public Map<String, Object> addStudent(@RequestBody Student student) {
        
    	Map<String, Object> obj =  studentService.saveStudent(student);
    	
    	return obj;
        
    }

    @GetMapping("/getStudents")
    @ResponseBody
    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }
}
