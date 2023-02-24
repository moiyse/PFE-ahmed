package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Direction implements Serializable {

	private static final long serialVersionUID = -357738161698377833L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	//@JsonManagedReference 
	@JsonIgnore
	@OneToMany(mappedBy="direction")
	private List<Employe> employes;
	
	@OneToOne(cascade = CascadeType.ALL)
	private BudgetInitial budgetInitials;
	@OneToOne(cascade = CascadeType.ALL)
	private BudgetRevise budgetRevise;

	@ManyToOne
	private Entreprise entreprise;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employe> getEmployes() {
		return employes;
	}

	public void setEmployes(List<Employe> employes) {
		this.employes = employes;
	}

	public BudgetInitial getBudgetInitials() {
		return budgetInitials;
	}

	public void setBudgetInitials(BudgetInitial budgetInitials) {
		this.budgetInitials = budgetInitials;
	}

	public BudgetRevise getBudgetRevise() {
		return budgetRevise;
	}

	public void setBudgetRevise(BudgetRevise budgetRevise) {
		this.budgetRevise = budgetRevise;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	

	
	

}
