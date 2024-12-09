package com.synergy_task.assignment.serviceimpl;

import com.synergy_task.assignment.model.Subject;
import com.synergy_task.assignment.repository.SubjectRepository;
import com.synergy_task.assignment.service.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService{

    @Autowired
    private SubjectRepository subjectRepository;

    public void saveSubject(Subject subject) {
        subjectRepository.save(subject); // Save the subject
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll(); // Retrieve all subjects
    }
}
