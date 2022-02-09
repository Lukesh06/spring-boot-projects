package com.ekalavya.result.dto;

import java.math.BigDecimal;

public class ResultDto {

	private int resultId;

	private int studentId;

	private int totalMarks;

	private int marksObtained;

	private BigDecimal percentage;

	private int division;

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
