package jpa.service;

import java.util.ArrayList;
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
		Session session = HibernateUtil.getSessionFactory();
		Transaction transaction = session.beginTransaction();

		String hql = "from Student s where s.sEmail = :sEmail AND s.sPass = :sPass";

		@SuppressWarnings("unchecked")
		Query<Student> q = session.createQuery(hql);
		q.setParameter("sEmail", sEmail);
		q.setParameter("sPass", sPassword);
		List<Student> studentList = q.list();

		boolean isValidated = (studentList.get(0).getsEmail().equalsIgnoreCase(sEmail)
				&& studentList.get(0).getsPass().equals(sPassword)) ? true : false;
		transaction.commit();
		HibernateUtil.shutdown();
		return isValidated;
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

		String hql = "select c.cId, c.cName, c.cInstructorName "
				+ "FROM Course c JOIN StudentCourseEnrollment sc ON c.cId = sc.cId "
				+ "JOIN Student s ON s.sEmail = sc.sEmail " + "WHERE s.sEmail = :sEmail";

		@SuppressWarnings("unchecked")
		Query<Object[]> q = session.createQuery(hql);
		q.setParameter("sEmail", sEmail);

		List<Object[]> list = q.list();
		List<Course> cList = new ArrayList<>();

		// this will convert the object[] to course[]
		for (Object[] result : q.list()) {
			int cid = (Integer) result[0];
			String name = (String) result[1];
			String instructor = (String) result[2];
			cList.add(new Course(cid, name, instructor));
		}

		transaction.commit();
		HibernateUtil.shutdown();

		return cList;
	}

}
