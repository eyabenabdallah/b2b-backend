package tn.taxi.repositories;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.taxi.entities.Course;
import tn.taxi.entities.Log;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	List<Course> findByDate(Date date);
	
	@Query("Select c from Course c Where c.user.idLog= :idLog")
	List<Course> findCoursesByTaxi(Long idLog);
	
	@Query("Select c from Course c Where c.date between :from and :to")
	List<Course> findCoursesByDate(@Param("from") Date from, @Param("to") Date to);
	
	@Query("Select c from Course c Where c.agent= :agent")
	List<Course> findCoursesByAgent(String agent);

	@Query("Select count(c) from Course c Where c.user.username= :username and c.date between :from and :to")
	int countCoursesThisMonth(@Param("username") String username, @Param("from") Date from, @Param("to") Date to);
	
	@Query("Select sum(c.prix) from Course c Where c.user.username= :username and c.date between :from and :to")
	double sumPrixCoursesThisMonth(@Param("username") String username, @Param("from") Date from, @Param("to") Date to);
}
