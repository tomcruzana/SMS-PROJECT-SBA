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
		List<Course> courseList;
		Session session = HibernateUtil.getSessionFactory();
		Transaction transaction = session.beginTransaction();

		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("from Course");
		courseList = query.list();

		transaction.commit();
		HibernateUtil.shutdown();
		return courseList;
	}

}
