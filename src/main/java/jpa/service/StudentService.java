package jpa.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.entitymodels.StudentCourseEnrollment;
import jpa.utils.HibernateUtil;

public class StudentService implements StudentDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getAllStudents() {
		Session session = HibernateUtil.getSessionFactory();
		Transaction transaction = session.beginTransaction();

		Query<Student> q = session.createQuery("from Student");
		List<Student> studentList = q.list();

		transaction.commit();
		HibernateUtil.shutdown();
		return studentList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Student getStudentByEmail(String sEmail) {
		Session session = HibernateUtil.getSessionFactory();
		Transaction transaction = session.beginTransaction();

		String hql = "from Student s where s.sEmail = :sEmail";
		Query<Student> q = session.createQuery(hql);
		q.setParameter("sEmail", sEmail);
		List<Student> studentList = q.list();
		Student student = studentList.get(0);

		transaction.commit();
		HibernateUtil.shutdown();
		return student;
	}

	@Override
	public boolean validateStudent(String sEmail, String sPassword) {

		return false;
	}

	@Override
	public void registerStudentToCourse(String sEmail, int cId) {
		Session session = HibernateUtil.getSessionFactory();
		Transaction transaction = session.beginTransaction();

		StudentCourseEnrollment sce = new StudentCourseEnrollment();
		sce.setcId(cId);
		sce.setsEmail(sEmail);

		session.saveOrUpdate(sce);
		transaction.commit();
		HibernateUtil.shutdown();
	}

	@Override
	public List<Course> getStudentCourses(String sEmail) {
		Session session = HibernateUtil.getSessionFactory();
		Transaction transaction = session.beginTransaction();

		String hql = "from StudentCourseEnrollment sc where sc.sEmail = :sEmail";

		@SuppressWarnings("unchecked")
		Query<Student> q = session.createQuery(hql);
		q.setParameter("sEmail", sEmail);
		List<Student> list = q.list();

		transaction.commit();
		HibernateUtil.shutdown();

		// todo: must return course
		return null;
	}

}
