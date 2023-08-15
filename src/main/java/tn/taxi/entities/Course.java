package tn.taxi.entities;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import jakarta.persistence.JoinColumn;


@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="Course")

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "numCourse")

public class Course implements Serializable {

	static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="numCourse")
	long numCourse;
	String depart;
	String destination;
	@Temporal(TemporalType.DATE)
	Date date;
	@Temporal(TemporalType.TIME)
	LocalTime heure;
	double prix;
	String agent;
	
	@ManyToOne
	Log user;
	@EqualsAndHashCode.Exclude
	@ManyToMany(fetch = FetchType.EAGER) 
	@JoinTable(
	    name = "course_client",
	    joinColumns = @JoinColumn(name = "course_id"),
	    inverseJoinColumns = @JoinColumn(name = "client_id")
	)
	@JsonBackReference
	Set<Client> clients;
	
	
}
