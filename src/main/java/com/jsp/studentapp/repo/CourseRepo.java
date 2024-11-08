package com.jsp.studentapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.studentapp.dto.Course;

public interface CourseRepo extends JpaRepository<Course, Integer>{

}
