package jpa.entitymodels;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "student")
public class Student {

	// id
	// NOT NULL, VARCHAR(50)
	@Id
	@Column(name = "email", nullable = false, length = 50)
	private String sEmail;

	// NOT NULL, VARCHAR(50)
	@Column(name = "name", nullable = false, length = 50)
	private String sName;

	// NOT NULL, VARCHAR(50)
	@Column(name = "password", nullable = false, length = 50)
	private String sPass;

	// entity association
	// student_course is the junction table
	// student_email & course_id are the PK columns
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "student_course", joinColumns = { @JoinColumn(name = "student_email") }, inverseJoinColumns = {
			@JoinColumn(name = "course_id") })
	private List<Course> sCourses;

	// empty constructor as per jpa specifications
	public Student() {
	}

	public Student(String sEmail, String sName, String sPass) {
		this.sEmail = sEmail;
		this.sName = sName;
		this.sPass = sPass;
		this.sCourses = new ArrayList<>();
	}

	public String getsEmail() {
		return sEmail;
	}

	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsPass() {
		return sPass;
	}

	public void setsPass(String sPass) {
		this.sPass = sPass;
	}

	public List<Course> getsCourses() {
		return sCourses;
	}

	public void setsCourses(List<Course> sCourses) {
		this.sCourses = sCourses;
	}

}
