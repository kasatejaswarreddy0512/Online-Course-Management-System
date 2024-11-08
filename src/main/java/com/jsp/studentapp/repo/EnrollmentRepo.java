package com.jsp.studentapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.studentapp.dto.Enrollment;

public interface EnrollmentRepo extends JpaRepository<Enrollment, Integer>{

}
