package cn.entity;

import java.io.Serializable;

import javax.sql.rowset.serial.SerialArray;

public class student implements Serializable {
	private String studentName;
	private String studentAge;
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentAge() {
		return studentAge;
	}
	public void setStudentAge(String studentAge) {
		this.studentAge = studentAge;
	}
	/*@Override
	public String toString() {
		return "student [studentName=" + studentName + ", studentAge=" + studentAge + "]";
	}*/
}
