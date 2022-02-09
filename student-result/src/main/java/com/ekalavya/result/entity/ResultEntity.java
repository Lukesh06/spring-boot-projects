package com.ekalavya.result.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RESULT")
public class ResultEntity implements Serializable{

private static final long serialVersionUID = 2215500574128076707L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RESULT_ID")
	private int resultId;
	
	@Column(name = "STUDENT_ID")
	private int studentId;
	
	@Column(name = "TOTAL_MARKS")
	private int totalMarks;
	
	@Column(name = "MARKS_OBTAINED")
	private int marksObtained;
	
	@Column(name = "PERCENTAGE")
	private BigDecimal percentage;
	
	@Column(name = "DIVISION")
	private int division;
	
	@Column(name = "YEAR_OF_EXAM")
	private int yearofExam;

	public int getResultId() {
		return resultId;
	}

	public void setResultId(int resultId) {
		this.resultId = resultId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

	public int getMarksObtained() {
		return marksObtained;
	}

	public void setMarksObtained(int marksObtained) {
		this.marksObtained = marksObtained;
	}

	public BigDecimal getPercentage() {
		return percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

	public int getDivision() {
		return division;
	}

	public void setDivision(int division) {
		this.division = division;
	}

	public int getYearofExam() {
		return yearofExam;
	}

	public void setYearofExam(int yearofExam) {
		this.yearofExam = yearofExam;
	}
	
	
	
}
