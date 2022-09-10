package jpa.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;
import jpa.utils.HibernateUtil;

public class CourseService implements CourseDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getAllCourses() {

		Session session = HibernateUtil.getSessionFactory();
		Transaction transaction = session.beginTransaction();

		Query<Course> query = session.createQuery("from Course");
		List<Course> courseList;
		courseList = query.list();

		transaction.commit();
		HibernateUtil.shutdown();

		return courseList;
	}

}
