package com.synergy_task.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synergy_task.assignment.model.School;

public interface SchoolRepository extends JpaRepository<School, Long> {
}
