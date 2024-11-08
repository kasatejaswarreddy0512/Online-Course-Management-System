package com.jsp.studentapp.controller;

import java.util.List;
import java.util.Optional;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.jsp.studentapp.dto.Teacher;
import com.jsp.studentapp.helper.ResponseStructure;
import com.jsp.studentapp.repo.TeacherRepo;


@RestController
public class TeacherController {
	
	@Autowired
	TeacherRepo teacherRepo;
	
	
	@PostMapping("/saveTeacher")
	public ResponseStructure<Teacher> saveTeacher(@RequestBody Teacher teacher){
		
		teacherRepo.save(teacher);
		
		ResponseStructure<Teacher> rs=new ResponseStructure<Teacher>();
		rs.setStatuscode(HttpStatus.CREATED.value());
		rs.setData(teacher);
		rs.setMessage("Teacher Save Successfully....!");
		return rs;	
	}
	
	@GetMapping("getAllTeachers")
	public ResponseStructure<List<Teacher>> getAllTeachers(){
		List<Teacher> teachers=teacherRepo.findAll();
		ResponseStructure<List<Teacher>> rs= new ResponseStructure<List<Teacher>>();
		
		if(!teachers.isEmpty()) {
			rs.setStatuscode(HttpStatus.FOUND.value());
			rs.setData(teachers);
			rs.setMessage("All teachers Found.....!");
		}
		else {
			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("No Teacher Found....!");
		}
		return rs;
	}
	
	@GetMapping("/getTeacherById")
	public ResponseStructure<Teacher> getTeacherById(@RequestParam("id") int id){
		Optional<Teacher> o=teacherRepo.findById(id);
		
		if(o.isPresent()) {
			Teacher teacher=o.get();
			ResponseStructure<Teacher> rs=new ResponseStructure<Teacher>();
			rs.setStatuscode(HttpStatus.FOUND.value());
			rs.setData(teacher);
			rs.setMessage("Tecaher Found By Id.....!");
			return rs;
		}
		else {
			ResponseStructure<Teacher> rs=new ResponseStructure<Teacher>();
			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("Teacher Id Is Not Found.......!");
			return rs;
		}
		
	}
	
	@PutMapping("/updateTeacher")
	public ResponseStructure<Teacher> updateteacher(@RequestParam("id")int id,@RequestBody Teacher teacher){
		Optional<Teacher> o=teacherRepo.findById(id);
		if(o.isPresent()) {
			Teacher updateTeacher=o.get();
			updateTeacher.setTeacherName(teacher.getTeacherName());
			updateTeacher.setAddress(teacher.getAddress());
			updateTeacher.setPhoneNumber(teacher.getPhoneNumber());
			teacherRepo.save(updateTeacher);
			
			ResponseStructure<Teacher> rs=new ResponseStructure<Teacher>();
			rs.setStatuscode(HttpStatus.FOUND.value());
			rs.setData(updateTeacher);
			rs.setMessage("Data Update Successfullty.....!");
			return rs;	
		}
		else {
			ResponseStructure<Teacher> rs=new ResponseStructure<>();
			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("Data Not Found....>!");
			return rs;
			
		}
		
	}
	
	@DeleteMapping("deleteTeacherById")
	public ResponseStructure<Teacher> deleteTeacherById(@RequestParam("id") int id){
		teacherRepo.deleteById(id);
		
		ResponseStructure<Teacher> rs= new ResponseStructure<Teacher>();
		rs.setStatuscode(HttpStatus.OK.value());
		rs.setMessage("Data Delete Successfully");
		return rs;
	}
	
	
}
