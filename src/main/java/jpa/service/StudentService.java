package jpa.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.utils.HibernateUtil;

public class StudentService implements StudentDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getAllStudents() {
		List<Student> studentList;
		Session session = HibernateUtil.getSessionFactory();
		Transaction transaction = session.beginTransaction();

		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("from Student");
		studentList = query.list();

		transaction.commit();
		HibernateUtil.shutdown();
		return studentList;
	}

	@Override
	public Student getStudentByEmail(String sEmail) {

		return null;
	}

	@Override
	public boolean validateStudent(String sEmail, String sPassword) {

		return false;
	}

	@Override
	public void registerStudentToCourse(String sEmail, int cId) {

	}

	@Override
	public List<Course> getStudentCourses(String sEmail) {

		return null;
	}

}
