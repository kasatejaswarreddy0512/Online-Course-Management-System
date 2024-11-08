package com.jsp.studentapp.controller;


import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.studentapp.dto.Batch;
import com.jsp.studentapp.dto.Course;
import com.jsp.studentapp.helper.ResponseStructure;
import com.jsp.studentapp.repo.BatchRepo;
import com.jsp.studentapp.repo.CourseRepo;

@RestController
public class BatchController {
	
	@Autowired
	BatchRepo batchRepo;
	
	@Autowired
	CourseRepo courseRepo;
	
	@PostMapping("/saveBatch")
    public ResponseStructure<Batch> saveBatch(@RequestBody Batch batch){
        batchRepo.save(batch);
        
        ResponseStructure<Batch> rs = new ResponseStructure<>();
        rs.setStatuscode(HttpStatus.CREATED.value());
        rs.setData(batch);
        rs.setMessage("Batch data saved successfully!");
        return rs;
    }
	
	@GetMapping("getAllBatches")
	public ResponseStructure<List<Batch>> getAllBatches(){
		List<Batch> batches=batchRepo.findAll();
		ResponseStructure<List<Batch>> rs= new ResponseStructure<List<Batch>>();
		
		if(!batches.isEmpty()){
			rs.setStatuscode(HttpStatus.FOUND.value());
			rs.setData(batches);
			rs.setMessage("All Batch Details are Displayed.....!");
		}
		else {
			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("Batch Data Not Found.....!");
		}
		return rs;
	}
	
	
	@GetMapping("/getBatchById")
	public ResponseStructure<Batch> getBatchById(@RequestParam("id") int id){
		Optional<Batch> o=batchRepo.findById(id);
		if(o.isPresent()) {
			Batch batch=o.get();
			ResponseStructure<Batch> rs= new ResponseStructure<Batch>();
			rs.setStatuscode(HttpStatus.FOUND.value());
			rs.setData(batch);
			rs.setMessage("Batch Data Display By Id.....!");
			return rs;
		}
		else {
			ResponseStructure<Batch> rs=new ResponseStructure<Batch>();
			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("Batch Data Not Found.....!");
			return rs;
		}
			
	}
	
	
	@PutMapping("/updateBatch")
	public ResponseStructure<Batch> updateBatch(@RequestParam("id") int id,@RequestParam("id") int courseid ,@RequestBody Batch batch){
		Optional<Batch> o=batchRepo.findById(id);
		if(o.isPresent()) {
			Batch updateBatch=o.get();
			updateBatch.setBatchName(batch.getBatchName());
			updateBatch.setStartDate(batch.getStartDate());
			
			Optional<Course> course = courseRepo.findById(courseid);
            if (course.isPresent()) {
                updateBatch.setCourse(course.get());
            } else {
                ResponseStructure<Batch> rs = new ResponseStructure<>();
                rs.setStatuscode(HttpStatus.NOT_FOUND.value());
                rs.setMessage("Course not found!");
                return rs;
            }
            
            batchRepo.save(updateBatch);
			
			
			ResponseStructure<Batch> rs= new ResponseStructure<Batch>();
			rs.setStatuscode(HttpStatus.OK.value());
			rs.setData(updateBatch);
			rs.setMessage("Batch Data Update Successfully.....!");
		return rs;
		}
		else {
			ResponseStructure<Batch> rs=new ResponseStructure<Batch>();
			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("Batch Data Not Found...!");
			return rs;
		}
		
	}
	
	
	@DeleteMapping("/deleteBathcById")
	public ResponseStructure<Batch> deleteBatchById(@RequestParam("id") int id){
		batchRepo.deleteById(id);
		
		ResponseStructure<Batch> rs=new ResponseStructure<>();
		rs.setStatuscode(HttpStatus.OK.value());
		rs.setMessage("Batch Data Deleted Successfully......!");
		return rs;
	}
	
	
	
}
