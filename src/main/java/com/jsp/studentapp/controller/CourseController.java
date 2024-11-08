package com.jsp.studentapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.studentapp.dto.Course;
import com.jsp.studentapp.helper.ResponseStructure;
import com.jsp.studentapp.repo.CourseRepo;

@RestController
public class CourseController {
	
	@Autowired
	CourseRepo courseRepo;
	
	@PostMapping("/saveCourse")
	public ResponseStructure<Course> saveCourse(@RequestBody Course course){
		courseRepo.save(course);
		
		ResponseStructure<Course> rs= new ResponseStructure<Course>();
		rs.setStatuscode(HttpStatus.CREATED.value());
		rs.setData(course);
		rs.setMessage("Course Data Saved Successfully....!");
		return rs;
		
	}
	
	@GetMapping("/getAllCourses")
	public ResponseStructure<List<Course>> getAllCourses(){
		List<Course> course=courseRepo.findAll();
		ResponseStructure<List<Course>> rs= new ResponseStructure<List<Course>>();
		
		if(!course.isEmpty()) {
			rs.setStatuscode(HttpStatus.FOUND.value());
			rs.setData(course);
			rs.setMessage("Fatch All Courses Data Successfully....!");
		}
		else {
			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("coures Data Not Found.....!");
			
		}
		return rs;
	
	}
	
	@GetMapping("getCourseById")
	public ResponseStructure<Course> getCourseById(@RequestParam("id") int id){
		Optional<Course> o=courseRepo.findById(id);
		
		if(o.isPresent()) {
			Course course=o.get();
			
			ResponseStructure<Course> rs= new ResponseStructure<Course>();
			rs.setStatuscode(HttpStatus.FOUND.value());
			rs.setData(course);
			rs.setMessage("Course FInd By ID Successfully....!");
			return rs;
		}
		else {
			ResponseStructure<Course> rs= new ResponseStructure<Course>();
			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("Course ID Not Found");
			return rs;
		}
	}
	
	
	
	
	
	
	
	
}
