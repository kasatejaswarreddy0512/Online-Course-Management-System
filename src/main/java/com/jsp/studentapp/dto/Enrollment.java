package com.jsp.studentapp.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Enrollment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int enrollid;
	
	@ManyToOne
	@JoinColumn(name="studentid", nullable = false)
	private Student student;
	
	@ManyToOne
	@JoinColumn(name="batchid", nullable = false)
	private Batch batch;
	
	private String joinDate;
	
	private int fee;
	
	

	public int getEnrollid() {
		return enrollid;
	}

	public void setEnrollid(int enrollid) {
		this.enrollid = enrollid;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}
	
	
	
	
}
