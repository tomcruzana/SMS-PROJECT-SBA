/*
 * Filename: SMSRunner.java
* Author: Stefanski
* 02/25/2020 
 */
package jpa.mainrunner;

import static java.lang.System.out;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.service.CourseService;
import jpa.service.StudentService;

/**
 * 1
 * 
 * @author Harry
 *
 */
public class SMSRunner {

	private Scanner sin;
	private StringBuilder sb;

	private CourseService courseService;
	private StudentService studentService;
	private Student currentStudent;

	public SMSRunner() {
		sin = new Scanner(System.in);
		sb = new StringBuilder();
		courseService = new CourseService();
		studentService = new StudentService();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SMSRunner sms = new SMSRunner();
		// handling all possible errors
		try {
			sms.run();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("An error occured! Enter the correct index of the course.\nPlease try again ¯\\_(ツ)_/¯ " + e);
		} catch (InputMismatchException e) {
			System.out.println("An error occured! Enter a valid input. \nPlease try again ¯\\_(ツ)_/¯ " + e);
		} catch (Exception e) {
			System.out.println("An error occured! Please try again ¯\\_(ツ)_/¯ " + e);
		}
	}

	private void run() {
		// Login or quit
		switch (menu1()) {
		case 1:
			if (studentLogin()) {
				registerMenu();
			}
			break;
		case 2:
			out.println("Goodbye!");
			break;

		default:

		}
	}

	private int menu1() {
		sb.append("\n1.Student Login\n2. Quit Application\nPlease Enter Selection: ");
		out.print(sb.toString());
		sb.delete(0, sb.length());

		return sin.nextInt();
	}

	private boolean studentLogin() {
		boolean retValue = false;
		out.print("Enter your email address: ");
		String email = sin.next();
		out.print("Enter your password: ");
		String password = sin.next();

		Student students = studentService.getStudentByEmail(email);
		if (students != null) {
			currentStudent = students;
		}

		if (currentStudent != null && currentStudent.getsPass().equals(password)) {
			List<Course> courses = studentService.getStudentCourses(email);
			out.println("MyClasses");
			for (Course course : courses) {
				out.println(course);
			}
			retValue = true;
		} else {
			out.println("User Validation failed. GoodBye!");
		}
		return retValue;
	}

	private void registerMenu() {
		sb.append("\n1.Register a class\n2. Logout\nPlease Enter Selection: ");
		out.print(sb.toString());
		sb.delete(0, sb.length());

		switch (sin.nextInt()) {
		case 1:
			List<Course> allCourses = courseService.getAllCourses();
			List<Course> studentCourses = studentService.getStudentCourses(currentStudent.getsEmail());
			allCourses.removeAll(studentCourses);
			out.printf("%5s%15S%15s\n", "ID", "Course", "Instructor");
			for (Course course : allCourses) {
				out.println(course);
			}
			out.println();
			out.print("Enter Course Number: ");
			int number = sin.nextInt();
			Course newCourse = courseService.getAllCourses().get(number - 1); // get the index of the course

			if (newCourse != null) {
				studentService.registerStudentToCourse(currentStudent.getsEmail(), newCourse.getcId());
				Student temp = studentService.getStudentByEmail(currentStudent.getsEmail());

				StudentService scService = new StudentService();
				List<Course> sCourses = scService.getStudentCourses(temp.getsEmail());

				out.println("MyClasses");
				for (Course course : sCourses) {
					out.println(course);
				}
			}
			System.out.println("You have been signed out.");
			break;
		case 2:
		default:
			out.println("Goodbye!");
		}
	}
}
