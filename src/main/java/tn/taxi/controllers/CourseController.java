package tn.taxi.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.taxi.entities.Course;
import tn.taxi.entities.Log;
import tn.taxi.services.CourseService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/course")
public class CourseController {

	@Autowired
	CourseService courseService;
	
	@GetMapping("/today")
    public List<Course> getTodaysCourses() {
        return courseService.getTodaysCourses();
    }
	
	@PostMapping("add-course/{logId}/{username}")
	public ResponseEntity<Course> addClientsToCourse(@RequestBody Course course, @PathVariable("logId") Long logId, @PathVariable("username") String username) {
	    Course addedCourse = courseService.addCourse(course, logId, username);
	    return ResponseEntity.ok().body(addedCourse);
	}
	
	@GetMapping("/retrieve-course/{login}")
	@ResponseBody
	public List<Course> retrieveCourseByUser(@PathVariable ("login") String login) {
	return courseService.retrieveCourseByUsername(login);
	}
	
	@PutMapping("/modify-course")
	@ResponseBody
	public Course modifyCourse(@RequestBody Course course) {
	return courseService.updateCourse(course);
	}
	
	@GetMapping("/retrieve-course-date/{startdate}/{enddate}")
	@ResponseBody
	public List<Course> retrieveCourseByUser(@PathVariable ("startdate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startdate, @PathVariable ("enddate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date enddate) {
	return courseService.retrieveCoursesByDate(startdate, enddate);
	}
	
	@GetMapping("/get-course/{numCourse}")
	@ResponseBody
	public Course retrieveCourseByNumCourse(@PathVariable ("numCourse") Long numcourse) {
	return courseService.retrieveCourseBynumCourse(numcourse);
	}
	
	@PutMapping("/update-course/{logId}/{username}")
	@ResponseBody
	public Course updateCourse(@RequestBody Course course, @PathVariable("logId") Long logId, @PathVariable("username") String username) {
	return courseService.editCourse(course, logId, username);
	}
	
	@DeleteMapping("/remove-course/{course-id}")
	@ResponseBody
	public void removeCourse(@PathVariable("course-id") Long courseId) {
	courseService.deleteCourse(courseId);
	}
	
	@GetMapping("/get-courseAgent/{agent}")
	@ResponseBody
	public List<Course> retrieveCourseByAgent(@PathVariable ("agent") String agent) {
	return courseService.retrieveCourseByAgent(agent);
	}
	
	@GetMapping("/count-courses/{login}")
	@ResponseBody
	public int countCoursesThisMonth(@PathVariable ("login") String login) {
	return courseService.countcourses(login);
	}
	
	@GetMapping("/prix-courses/{login}")
	@ResponseBody
	public double sumPrixCoursesThisMonth(@PathVariable ("login") String login) {
	return courseService.sumPrixcourses(login);
	}
}
