package com.jsp.studentapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.studentapp.dto.Student;

public interface StudentRepo extends JpaRepository<Student, Integer>{

}
