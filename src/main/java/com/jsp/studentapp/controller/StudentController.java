package com.jsp.studentapp.controller;

import java.rmi.StubNotFoundException;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
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

import com.jsp.studentapp.dto.Student;
import com.jsp.studentapp.helper.ResponseStructure;
import com.jsp.studentapp.repo.StudentRepo;

@RestController
public class StudentController {
	
	@Autowired
	StudentRepo studentRepo;
	
	@PostMapping("/saveStudent")
	public ResponseStructure<Student> saveStudent(@RequestBody Student student){
		studentRepo.save(student);
		
		ResponseStructure<Student> rs=new ResponseStructure<Student>();
		rs.setStatuscode(HttpStatus.CREATED.value());
		rs.setData(student);
		rs.setMessage("Student Save Successfully....!");
		return rs;
		
	}
	
	@GetMapping("/getAllStudends")
	public ResponseStructure<List<Student>> getAllStudents(){
		List<Student> students=studentRepo.findAll();
		ResponseStructure<List<Student>> rs=new ResponseStructure<>();
		
		if(!students.isEmpty()) {
			rs.setStatuscode(HttpStatus.FOUND.value());
			rs.setData(students);
			rs.setMessage("Get ALL Students Data");
		}
		else {
			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("No Students Found");
		}
		return rs;
	}
	
	@GetMapping("/getStudentById")
	public ResponseStructure<Student> getStudentById(@RequestParam("id") int id) throws StubNotFoundException{
		Optional<Student> o=studentRepo.findById(id);
		
		if(o.isPresent()) {
			Student student=o.get();
			ResponseStructure<Student> rs=new ResponseStructure<>();
			rs.setStatuscode(HttpStatus.FOUND.value());
			rs.setData(student);
			rs.setMessage("Student Data Found");
			return rs;
		}
		else {
			ResponseStructure<Student> rs=new ResponseStructure<Student>();
			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("Student Data Not Found...!");
			return rs;
//			throw new StubNotFoundException("Student Not Found");
		}
	}
	
	@PutMapping("/udpateStudent")
	public ResponseStructure<Student> updateStudent(@RequestParam("id") int id, @RequestBody Student student){
		Optional<Student> o=studentRepo.findById(id);
		if(o.isPresent()) {
			Student updateStudent=o.get();
			updateStudent.setStudentName(student.getStudentName());
			updateStudent.setAddress(student.getAddress());
			updateStudent.setPhoneNumber(student.getPhoneNumber());
			
			studentRepo.save(updateStudent);
			
			ResponseStructure<Student> rs=new ResponseStructure<Student>();
			rs.setStatuscode(HttpStatus.ACCEPTED.value());
			rs.setData(updateStudent);
			rs.setMessage("Data Successfully Updated.....!");
			return rs;
		}
		else {
			ResponseStructure<Student> rs=new ResponseStructure<Student>();
			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("Student ID Not Found....!");
			return rs;
		}
		
	}
	
	@DeleteMapping("/deleteStudentById")
	public ResponseStructure<Student> deleteStudentById(@RequestParam int id){
		studentRepo.deleteById(id);
		
		ResponseStructure<Student> rs=new ResponseStructure<Student>();
		rs.setStatuscode(HttpStatus.OK.value());
		rs.setMessage(" Data deleted Successfully......!");
		return rs;
	}
	
}
