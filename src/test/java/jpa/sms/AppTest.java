package jpa.sms;

import static org.junit.Assert.assertTrue;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

/**
 * Unit test for simple App.
 */
public class AppTest {
	/**
	 * Rigorous Test :-)
	 */

	@BeforeAll
	static void init() {
		assertTrue(true);
		System.out.println("init test");
	}

	@Test
	public void shouldAnswerWithTrue() {
		assertTrue(true);
	}

	@Test
	public void shouldAnswerWithFalse() {
		assertTrue(false);
	}

	@Test
	public void sessionTest() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		System.out.println("created test");

		t.commit();
		factory.close();
		session.close();
		
		assertTrue(true);
	}

}
