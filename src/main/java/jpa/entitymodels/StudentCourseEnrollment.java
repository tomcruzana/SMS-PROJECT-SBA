package jpa.entitymodels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

// junction table for student and course entities
// since we are dealing with multiple PKs, we use @IdClass
// to create compound keys
@Entity
@Table(name = "student_course")
@IdClass(CompoundKeys.class)
public class StudentCourseEnrollment {

	@Id
	@Column(name = "student_email", nullable = false, length = 50)
	private String sEmail;

	@Id
	@Column(name = "course_id", nullable = false)
	private int cId;

	public String getsEmail() {
		return sEmail;
	}

	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

}
