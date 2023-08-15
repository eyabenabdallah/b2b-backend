package tn.taxi.services;

import java.util.Date;
import java.util.List;

import tn.taxi.entities.Course;

public interface CourseService {
	
	List<Course> retrieveAllCourses();
	Course addCourse(Course c, Long taxiId, String username);
	void deleteCourse (Long id);
	Course updateCourse (Course c);
	List<Course> getTodaysCourses();
	List<Course> retrieveCourseByUsername(String username);
	List<Course> retrieveCoursesByDate(Date startdate, Date endDate);
	Course retrieveCourseBynumCourse(Long numCourse);
	Course editCourse (Course c, Long taxiId, String username);
	List<Course> retrieveCourseByAgent(String agent);
	int countcourses(String username);
	double sumPrixcourses(String username);
	

}
