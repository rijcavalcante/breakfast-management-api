package oi.github.rijcavalcante.breakfastmanagementapi.domain.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "participants")
@NamedQueries({
		@NamedQuery(name = Participant.QUERY_FIND_CPF_PARTICIPANT, query = "SELECT p FROM Participant p WHERE p.cpf LIKE :partCpf") })
public class Participant {
	/**
	 * Consulta o participante pelo cpf informado.
	 */
	public static final String QUERY_FIND_CPF_PARTICIPANT = "findParticipantForCPF";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 11)
	private String cpf;

	@Column(name = "fist_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "participant")
	private List<Item> itens;
}
