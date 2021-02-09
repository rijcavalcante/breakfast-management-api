package oi.github.rijcavalcante.breakfastmanagementapi.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "itens")
//@NamedQueries({@NamedQuery(name = Item.QUERY_FIND_ALL_ITENS, query = "SELECT i from Item i")})
public class Item {
	
	//public static final String QUERY_FIND_ALL_ITENS = "findAllItem";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String description;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "participant_id")
	private Participant participant;
}
