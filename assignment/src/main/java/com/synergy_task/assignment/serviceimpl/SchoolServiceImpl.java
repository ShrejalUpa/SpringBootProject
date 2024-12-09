package com.synergy_task.assignment.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.synergy_task.assignment.model.School;
import com.synergy_task.assignment.repository.SchoolRepository;
import com.synergy_task.assignment.service.SchoolService;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    public void saveSchool(School school) {
        schoolRepository.save(school);
    }
    
    @Override
    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

}
