package tn.taxi.entities;

import java.io.Serializable;
import java.util.Set;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "idLog")
@Table(name="Log", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"})
})
public class Log implements Serializable {
	
	static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idLog")
	long idLog;
	String password;
	String username;
	Boolean active=true;
	@Enumerated(EnumType.STRING)
	Role role;
	long tel;
	String nom;
	String prenom;
	
	@OneToOne(mappedBy = "log",cascade = {CascadeType.ALL})
	Taxi taxi ;
	@OneToMany (mappedBy = "user")
	@JsonIgnore
	Set<Course> courses;

	
	
	

}
