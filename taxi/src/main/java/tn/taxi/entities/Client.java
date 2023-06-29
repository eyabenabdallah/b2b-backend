package tn.taxi.entities;

import java.io.Serializable;
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel .PRIVATE)
@Table(name="Client")

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "idClient")

public class Client implements Serializable {

	static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idClient")
	long idClient;
	String nom;
	String prenom;
	long tel;
	String activite;
	

	@ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER) 
	@JoinColumn(name = "societe_id") 
	private Societe societe;
	
	@EqualsAndHashCode.Exclude
	@ManyToMany(mappedBy = "clients",fetch = FetchType.EAGER)
	@JsonBackReference
	private Set<Course> courses;
	

}
