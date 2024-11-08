package com.jsp.studentapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.studentapp.dto.Teacher;

public interface TeacherRepo  extends JpaRepository<Teacher, Integer>{

}
