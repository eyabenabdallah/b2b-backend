package tn.taxi.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.taxi.entities.Client;
import tn.taxi.entities.Course;
import tn.taxi.entities.Log;
import tn.taxi.repositories.ClientRepository;
import tn.taxi.repositories.CourseRepository;
import tn.taxi.repositories.LogRepository;
import tn.taxi.repositories.TaxiRepository;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	CourseRepository courseRepository;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	LogService logService;
	@Autowired
	TaxiRepository taxiRepository;
	@Autowired
	LogRepository logRepository;
	
	
	@Override
	public Course addCourse(Course c, Long taxiId, String username) {
		Log log = logRepository.findLogByTaxi(taxiId);
		c.setUser(log);
	
		Set<Client> clients = c.getClients();
	    for (Client client : clients) {
	        if (client.getCourses() == null) {
	            client.setCourses(new HashSet<>()); // Initialize courses set if null
	        }
	        client.getCourses().add(c);
	        clientRepository.save(client);
	    }
		c.setPrix(0);
		c.setAgent(username);
		courseRepository.save(c);
		return c;
		
	}
	
	/*private Set<Client> saveClient(Course c){
		Set<Client> clients=c.getClients();
		for(Client client:clients)
		{
			client.getCourses().add(c);
			clientRepository.save(client);
		}
		return clients;
	}*/

	@Override
	public void deleteCourse(Long id) {
		courseRepository.deleteById(id);	
	}

	@Override
	public Course updateCourse(Course c) {
		Course existingCourse = courseRepository.findById(c.getNumCourse()).orElse(null);
	    Set<Client> existingClients = existingCourse.getClients();
	    
	    // Preserve the existing clients
	    c.setClients(existingClients);
	    
	    
	    // Save the updated course
	    Course updatedCourse = courseRepository.save(c);
	    
	    // Update the association between the course and clients
	    Set<Client> updatedClients = existingCourse.getClients();
	    for (Client client : updatedClients) {
	        if (client.getCourses() == null) {
	            client.setCourses(new HashSet<>());
	        }
	        client.getCourses().add(updatedCourse);
	        clientRepository.save(client);
	    }
	    
	    return updatedCourse;
	}
	
	@Override
	public List<Course> getTodaysCourses() {
        LocalDate today = LocalDate.now();
        Date date = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return courseRepository.findByDate(date);
    }
	
	@Override
	public List<Course> retrieveCourseByUsername(String username) {
		Long idUser=logService.findLogByLogUsername(username).getIdLog();
		return  courseRepository.findCoursesByTaxi(idUser);
	}

	@Override
	public List<Course> retrieveAllCourses() {
		return courseRepository.findAll();
	}

	@Override
	public List<Course> retrieveCoursesByDate(Date startdate, Date endDate) {
		return courseRepository.findCoursesByDate(startdate, endDate);
	}

	@Override
	public Course retrieveCourseBynumCourse(Long numCourse) {
		Course course = courseRepository.findById(numCourse).orElse(null);
		return course;
	}

	@Override
	public Course editCourse(Course c, Long taxiId, String username) {
		Course existingCourse = courseRepository.findById(c.getNumCourse()).orElse(null);
	    Set<Client> existingClients = existingCourse.getClients();
	    c.setClients(existingClients);
	    Log log = logRepository.findLogByTaxi(taxiId);
		c.setUser(log);
		c.setAgent(username);
	    Course updatedCourse = courseRepository.save(c);
	    
	    // Update the association between the course and clients
	    Set<Client> updatedClients = existingCourse.getClients();
	    for (Client client : updatedClients) {
	        if (client.getCourses() == null) {
	            client.setCourses(new HashSet<>());
	        }
	        client.getCourses().add(updatedCourse);
	        clientRepository.save(client);
	    }
	    
	    return updatedCourse;
	}

	@Override
	public List<Course> retrieveCourseByAgent(String agent) {
		return courseRepository.findCoursesByAgent(agent);
	}

	@Override
	public int countcourses(String username) {
		LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate endOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
        Date start = Date.from(startOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(endOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return courseRepository.countCoursesThisMonth(username, start, end);
	}

	@Override
	public double sumPrixcourses(String username) {
		LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate endOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
        Date start = Date.from(startOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(endOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return courseRepository.sumPrixCoursesThisMonth(username, start, end);
	}



}
