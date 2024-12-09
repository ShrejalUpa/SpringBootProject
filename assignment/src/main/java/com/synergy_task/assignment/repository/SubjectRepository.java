package com.synergy_task.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synergy_task.assignment.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
