package jpa.entitymodels;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

// this is StudentCourseEnrollment's compound keys!

public class CompoundKeys implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "student_email", nullable = false, length = 50)
	private String sEmail;

	@Id
	@Column(name = "course_id", nullable = false)
	private int cId;

	public CompoundKeys() {
		// empty
	}

	public CompoundKeys(String sEmail, int cId) {
		this.sEmail = sEmail;
		this.cId = cId;
	}

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
