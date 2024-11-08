package com.jsp.studentapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.studentapp.dto.Batch;
import com.jsp.studentapp.dto.Enrollment;
import com.jsp.studentapp.dto.Student;
import com.jsp.studentapp.helper.ResponseStructure;
import com.jsp.studentapp.repo.BatchRepo;
import com.jsp.studentapp.repo.EnrollmentRepo;
import com.jsp.studentapp.repo.StudentRepo;

@RestController
public class EnrollmentController {

	@Autowired
	EnrollmentRepo enrollmentRepo;
	
	@Autowired
	StudentRepo studentRepo;
	
	@Autowired
	BatchRepo batchRepo;
	
	
	@PostMapping("/saveEnrollment")
	public ResponseStructure<Enrollment> saveEnrollment(@RequestBody Enrollment enrollment){
		enrollmentRepo.save(enrollment);
		
		ResponseStructure<Enrollment> rs=new ResponseStructure<Enrollment>();
		rs.setStatuscode(HttpStatus.CREATED.value());
		rs.setData(enrollment);
		rs.setMessage("Data Saved Successfully.....!");
		return rs;
		
	}
	
	@GetMapping("/getAllEnrollments")
	public ResponseStructure<List<Enrollment>> getEnrollments(){
		List<Enrollment> enrollment=enrollmentRepo.findAll();
		ResponseStructure<List<Enrollment>> rs= new ResponseStructure<>();
		
		if(!enrollment.isEmpty()) {
			rs.setStatuscode(HttpStatus.FOUND.value());
			rs.setData(enrollment);
			rs.setMessage("Fetch all Details successfully.....!");
		}
		else {
			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("Details are Not Found....!");
		}
		return rs;
	}
	
	
	
	@GetMapping("getEnrollmentById")
	public ResponseStructure<Enrollment> getEnrolmentById(@RequestParam("id") int id){
		Optional<Enrollment>  o=enrollmentRepo.findById(id);
		
		if(o.isPresent()) {
			Enrollment enrollment=o.get();
			
			ResponseStructure<Enrollment> rs= new ResponseStructure<Enrollment>();
			rs.setStatuscode(HttpStatus.FOUND.value());
			rs.setData(enrollment);
			rs.setMessage("Data Found By Id.....!");
			return rs;
		}
		else {
			ResponseStructure<Enrollment> rs= new ResponseStructure<Enrollment>();
			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("Data Not Found By Id.....!");
			return rs;
		}
	}
	
	
	@PutMapping("/updateEnrollment")
    public ResponseStructure<Enrollment> updateEnrollment(@RequestParam("enrollid") int id,
                                                          @RequestParam("studentid") int studentid,
                                                          @RequestParam("batchid") int batchid,
                                                          @RequestBody Enrollment enrollment) {
        
        Optional<Enrollment> optionalEnrollment = enrollmentRepo.findById(id);
        
        if (optionalEnrollment.isPresent()) {
            Enrollment updateEnrollment = optionalEnrollment.get();
            
            Optional<Student> student = studentRepo.findById(studentid);
            if (student.isPresent()) {
                updateEnrollment.setStudent(student.get());
            } else {
                ResponseStructure<Enrollment> rs = new ResponseStructure<>();
                rs.setStatuscode(HttpStatus.NOT_FOUND.value());
                rs.setMessage("Student not found!");
                return rs;
            }
            
            Optional<Batch> batch = batchRepo.findById(batchid);
            if (batch.isPresent()) {
                updateEnrollment.setBatch(batch.get());
            } else {
                ResponseStructure<Enrollment> rs = new ResponseStructure<>();
                rs.setStatuscode(HttpStatus.NOT_FOUND.value());
                rs.setMessage("Batch not found!");
                return rs;
            }
            
            updateEnrollment.setJoinDate(enrollment.getJoinDate());
            updateEnrollment.setFee(enrollment.getFee());
            
            enrollmentRepo.save(updateEnrollment);
            
            ResponseStructure<Enrollment> rs = new ResponseStructure<>();
            rs.setStatuscode(HttpStatus.OK.value());
            rs.setData(updateEnrollment);
            rs.setMessage("Enrollment data updated successfully!");
            return rs;
        } else {
            ResponseStructure<Enrollment> rs = new ResponseStructure<>();
            rs.setStatuscode(HttpStatus.NOT_FOUND.value());
            rs.setMessage("Enrollment not found!");
            return rs;
        }
    }
	
	
	@DeleteMapping("/deleteById")
	public ResponseStructure<Enrollment> deleteById(@RequestParam("id")int id){
		enrollmentRepo.deleteById(id);
		
		ResponseStructure<Enrollment> rs=new ResponseStructure<>();
		rs.setStatuscode(HttpStatus.OK.value());
		rs.setMessage("Enrollment Data Deleted Successfully......!");
		return rs;
	}
	
	
	
}
