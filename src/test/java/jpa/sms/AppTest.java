package jpa.sms;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.entitymodels.StudentCourseEnrollment;
import jpa.utils.HibernateUtil;

public class AppTest {
	/**
	 * ¯\_(ツ)_/¯ it works on my machine,. All test cases start with a failing test.
	 */

	@BeforeAll
	static void init() {
		System.out.println(">>> LOG: init test ¯\\_(ツ)_/¯ <<<");
	}

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
	public void registerStudentToCourseTest() {
		// fail();
		Session session = HibernateUtil.getSessionFactory();
		Transaction transaction = session.beginTransaction();

		String studentEmail = "john_smith@email.com";
		int courseId = 1;

		// enroll student john smith to engineering course
		StudentCourseEnrollment sce = new StudentCourseEnrollment();
		sce.setcId(courseId);
		sce.setsEmail(studentEmail);

		session.saveOrUpdate(sce);
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

	/* DQL Tests */
	@Test
	public void getAllStudentsTest() {
		// fail();
		Session session = HibernateUtil.getSessionFactory();
		Transaction transaction = session.beginTransaction();

		@SuppressWarnings("unchecked")
		Query<Student> q = session.createQuery("from Student");
		List<Student> studentList = q.list();

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
		List<Student> list = q.list();

		transaction.commit();
		HibernateUtil.shutdown();
		assertTrue((list.size() > 0));

	}

	@Test
	public void getStudentCoursesTest() {
		// fail();
		Session session = HibernateUtil.getSessionFactory();
		Transaction transaction = session.beginTransaction();

		String sEmail = "cjaulme9@bing.com";
		String hql = "from StudentCourseEnrollment sc where sc.sEmail = :sEmail";

		@SuppressWarnings("unchecked")
		Query<Student> q = session.createQuery(hql);
		q.setParameter("sEmail", sEmail);
		List<Student> list = q.list();

		transaction.commit();
		HibernateUtil.shutdown();
		assertTrue((list.size() > 0));

	}

	// TODO: FIX JUNIT 5!!!

}
