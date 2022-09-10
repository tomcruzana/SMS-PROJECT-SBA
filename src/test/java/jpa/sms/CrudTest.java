package jpa.sms;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.entitymodels.StudentCourseEnrollment;
import jpa.utils.HibernateUtil;

class CrudTest {
	/**
	 * ¯\_(ツ)_/¯ it works on my machine,. All test cases start with a failing test.
	 */

	/*
	 * 
	 * 
	 * 
	 * ======================================== DDL Tests
	 * ========================================
	 */

	@Test
	public void createStudentTable() {
		// fail();
		Session session = HibernateUtil.getSessionFactory();
		Transaction transaction = session.beginTransaction();
		Student s = new Student("john_smith@email.com", "John Smith", "!@wyz19&^456");
		session.save(s);
		transaction.commit();
		HibernateUtil.shutdown();
	}

	@Test
	public void createCourseTable() {
		// fail();
		Session session = HibernateUtil.getSessionFactory();
		Transaction transaction = session.beginTransaction();
		Course c = new Course(100, "Engineering", "Ricardo Millo");
		session.save(c);
		transaction.commit();
		HibernateUtil.shutdown();
	}

	@Test
	public void deleteAStudentTest() {
		// fail();
		Session session = HibernateUtil.getSessionFactory();
		Transaction transaction = session.beginTransaction();

		// delete a persistent object
		Student s1 = session.get(Student.class, "aiannitti7@is.gd");
		if (s1 != null) {
			session.delete(s1);
			System.out.println("Student deleted");
		}
		transaction.commit();
		HibernateUtil.shutdown();
	}

	@Test
	public void deleteACourseTest() {
		// fail();
		Session session = HibernateUtil.getSessionFactory();
		Transaction transaction = session.beginTransaction();

		// delete a persistent object
		// delete English course
		// note we can't delete a course if a student is currently enrolled
		Course c1 = session.get(Course.class, 100);
		if (c1 != null) {
			session.delete(c1);
			System.out.println("Course deleted");
		}
		transaction.commit();
		HibernateUtil.shutdown();
	}

	/*
	 * 
	 * 
	 * 
	 * ======================================== DQL Tests
	 * ======================================== Students
	 */
	@Test
	public void getAllStudentsTest() {
		// fail();
		Session session = HibernateUtil.getSessionFactory();
		Transaction transaction = session.beginTransaction();

		@SuppressWarnings("unchecked")
		Query<Student> q = session.createQuery("from Student");
		List<Student> studentList = q.list();

		studentList.forEach(e -> {
			System.out.printf("%s, %s %n", e.getsEmail(), e.getsName());
		});

		transaction.commit();
		HibernateUtil.shutdown();
		assertTrue((studentList.size() > 0));
	}

	@Test
	public void getStudentByEmailTest() {
		// fail();
		Session session = HibernateUtil.getSessionFactory();
		Transaction transaction = session.beginTransaction();

		String sEmail = "cjaulme9@bing.com";
		String hql = "from Student s where s.sEmail = :sEmail";

		@SuppressWarnings("unchecked")
		Query<Student> q = session.createQuery(hql);
		q.setParameter("sEmail", sEmail);
		List<Student> studentList = q.list();

		studentList.forEach(e -> {
			System.out.printf("%s, %s %n", e.getsEmail(), e.getsName());
		});

		transaction.commit();
		HibernateUtil.shutdown();
		assertTrue((studentList.get(0).getsEmail().equalsIgnoreCase(sEmail)));

	}

	@Test
	public void validateStudentTest() {
		// fail();
		Session session = HibernateUtil.getSessionFactory();
		Transaction transaction = session.beginTransaction();

		String sEmail = "ljiroudek8@sitemeter.com";
		String sPassword = "bXRoLUP";
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

		assertTrue(isValidated);

	}

	@Test
	public void registerStudentToCourseTest() {
		// fail();
		Session session = HibernateUtil.getSessionFactory();
		Transaction transaction = session.beginTransaction();

		String studentEmail = "cjaulme9@bing.com";
		int courseId = 4;

		// enroll student john smith to engineering course
		StudentCourseEnrollment sce = new StudentCourseEnrollment();
		sce.setcId(courseId);
		sce.setsEmail(studentEmail);

		session.saveOrUpdate(sce);
		transaction.commit();
		HibernateUtil.shutdown();
	}

	@Test
	public void getStudentCoursesTest() {
		// fail();
		Session session = HibernateUtil.getSessionFactory();
		Transaction transaction = session.beginTransaction();

		String sEmail = "john_smith@email.com";
		String hql = "select c.cId, c.cName, c.cInstructorName "
				+ "FROM Course c JOIN StudentCourseEnrollment sc ON c.cId = sc.cId "
				+ "JOIN Student s ON s.sEmail = sc.sEmail " + "WHERE s.sEmail = :sEmail";

		@SuppressWarnings("unchecked")
		Query<Object[]> q = session.createQuery(hql);
		q.setParameter("sEmail", sEmail);

		List<Object[]> list = q.list();

		List<Course> cList = new ArrayList<>();

		for (Object[] result : q.list()) {
			int cid = (Integer) result[0];
			String name = (String) result[1];
			String instructor = (String) result[2];
			cList.add(new Course(cid, name, instructor));
		}

		cList.forEach(e -> {
			System.out.printf("%d %s %s%n", e.getcId(), e.getcName(), e.getcInstructorName());
		});

		transaction.commit();
		HibernateUtil.shutdown();
		assertTrue((list.size() > 0));

	}

	/*
	 * 
	 * 
	 * 
	 * ======================================== DQL Tests
	 * ======================================== Course
	 */
	@Test
	public void getAllCoursesTest() {
		// fail();
		Session session = HibernateUtil.getSessionFactory();
		Transaction transaction = session.beginTransaction();

		@SuppressWarnings("unchecked")
		Query<Course> query = session.createQuery("from Course");
		List<Course> courseList;
		courseList = query.list();

		courseList.forEach(e -> {
			System.out.printf("%s, %s, %s%n", e.getcId(), e.getcName(), e.getcInstructorName());
		});

		transaction.commit();
		HibernateUtil.shutdown();
		assertTrue((courseList.size() > 0));
	}
}
