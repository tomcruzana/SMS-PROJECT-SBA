package jpa.entitymodels;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course {

	// NOT NULL
	@Id
	@Column(name = "id", nullable = false)
	private int cId;

	// NOT NULL, VARCHAR(50)
	@Column(name = "name", nullable = false, length = 50)
	private String cName;

	// NOT NULL, VARCHAR(50)
	@Column(name = "instructor", nullable = false, length = 50)
	private String cInstructorName;

	// set the owning entity to Student
	// and use its sCourses property
	@ManyToMany(mappedBy = "sCourses")
	List<Student> students;

	// empty constructor as per jpa specifications
	public Course() {
	}

	public Course(int cId, String cName, String cInstructorName) {
		this.cId = cId;
		this.cName = cName;
		this.cInstructorName = cInstructorName;
		this.students = new ArrayList<>();
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcInstructorName() {
		return cInstructorName;
	}

	public void setcInstructorName(String cInstructorName) {
		this.cInstructorName = cInstructorName;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Course [cId=" + cId + ", cName=" + cName + ", cInstructorName=" + cInstructorName + ", students="
				+ students + "]";
	}

}
