package com.jsp.studentapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.studentapp.dto.Batch;

public interface BatchRepo extends JpaRepository<Batch, Integer>{

}
