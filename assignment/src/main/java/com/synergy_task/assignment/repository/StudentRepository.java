package com.synergy_task.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synergy_task.assignment.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
