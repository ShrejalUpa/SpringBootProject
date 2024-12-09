package com.synergy_task.assignment.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergy_task.assignment.model.Student;
import com.synergy_task.assignment.repository.StudentRepository;
import com.synergy_task.assignment.service.StudentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    public Map<String, Object> saveStudent(Student student) {
        studentRepository.save(student);
        
        Map<String, Object> map=new HashMap<>();
        map.put("success", true);
        map.put("error", false);
        map.put("message", "Successfully stored!");
        return map;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
