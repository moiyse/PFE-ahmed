package tn.esprit.spring.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Direction;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.BudgetInitial;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Budget;
import tn.esprit.spring.entities.BudgetPK;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.repository.DirectionRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.BudgetInitialRepository;
import tn.esprit.spring.repository.BudgetRepository;

@Service
public class BudgetServiceImpl implements IBudgetService {
	

	@Autowired
	BudgetInitialRepository budgetinitialRepository;
	@Autowired
	DirectionRepository deptRepoistory;
	@Autowired
	BudgetRepository budgetRepository;
	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	BudgetInitialRepository budgetInitialRepository;
	
	public int ajouterBudgetInitial(BudgetInitial budgetinitial) {
		budgetinitialRepository.save(budgetinitial);
		return budgetinitial.getId();
	}
    
	public void affecterBudgetInitialADirection(int budgetinitialId, int depId) {
		BudgetInitial budgetinitial = budgetinitialRepository.findById(budgetinitialId).get();
		Direction dep = deptRepoistory.findById(depId).get();
		//budgetinitial.setDirection(dep);
		budgetinitialRepository.save(budgetinitial);
		
	}

	public void ajouterBudget(int idBudgetInitial, int idEmploye, Date dateDebut, Date dateFin) {
		BudgetPK budgetPK = new BudgetPK();
		budgetPK.setDateDebut(dateDebut);
		budgetPK.setDateFin(dateFin);
		budgetPK.setIdEmploye(idEmploye);
		budgetPK.setIdBudgetInitial(idBudgetInitial);
		
		Budget budget = new Budget();
		budget.setBudgetPK(budgetPK);
		budget.setValide(false); //par defaut non valide
		
		
	}

	
	public void validerBudget(int budgetinitialId, int employeId, Date dateDebut, Date dateFin, int validateurId) {
		System.out.println("In valider Budget");
		Employe validateur = employeRepository.findById(validateurId).get();
		BudgetInitial budgetInitial = budgetinitialRepository.findById(budgetinitialId).get();
		//verifier s'il est un chef de departement (interet des enum)
		if(!validateur.getRole().equals(Role.CHEF_DEPARTEMENT)){
			System.out.println("l'employe doit etre chef de direction pour valider une feuille de temps !");
			return;
		}
		//verifier s'il est le chef de departement de la mission en question
		boolean chefDeLaBudgetInitial = false;
		Direction dep=validateur.getDirection();

		/*if(dep.getId() == budgetInitial.getDirection().getId()){
			chefDeLaBudgetInitial = true;
		}*/

		if(!chefDeLaBudgetInitial){
			System.out.println("l'employe doit etre chef de departement de la mission en question");
			return;
		}

		BudgetPK budgetPK = new BudgetPK(budgetinitialId, employeId, dateDebut, dateFin);
		//Budget budget =budgetRepository.findByBudgetPK(budgetPK);
		//budget.setValide(true);
		
		//Comment Lire une date de la base de données
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		//System.out.println("dateDebut : " + dateFormat.format(budget.getBudgetPK().getDateDebut()));
		
	}

	
	public List<BudgetInitial> findAllBudgetInitialByEmployeJPQL(int employeId) {
		//return budgetRepository.findAllBudgetInitialByEmployeJPQL(employeId);
		return null;
	}

	
	public List<Employe> getAllEmployeByBudgetInitial(int budgetinitialId) {
		//return budgetRepository.getAllEmployeByBudgetInitial(budgetinitialId);
		return null;
	}
	
	
	
	public List<BudgetInitial> getBudgetInitial(){
		return (List<BudgetInitial>) budgetInitialRepository.findAll();
	}
	
	public BudgetInitial getBudgetInitialById(int BudgetInitialId) {
		return budgetInitialRepository.findById(BudgetInitialId).get();	
	}
	
	@Transactional
	public void deleteBudgetInitialById(int idbudgetInitial) {
		budgetInitialRepository.delete(budgetInitialRepository.findById(idbudgetInitial).get());
	}
}
