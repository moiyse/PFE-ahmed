package tn.esprit.spring.services;

import java.util.Date;
import java.util.List;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.BudgetInitial;



public interface IBudgetService {
	
	public int ajouterBudgetInitial(BudgetInitial budgetinitial);
	public void affecterBudgetInitialADirection(int budgetinitialId, int depId);
	public void ajouterBudget(int idBudgetInitial, int idEmploye, Date dateDebut, Date dateFin);
	public void validerBudget(int budgetinitialId, int employeId, Date dateDebut, Date dateFin, int validateurId);
	public List<BudgetInitial> findAllBudgetInitialByEmployeJPQL(int employeId);
	public List<Employe> getAllEmployeByBudgetInitial(int budgetinitialId);
	
	public List<BudgetInitial> getBudgetInitial();
	public BudgetInitial getBudgetInitialById(int budgetInitialId);
	public void deleteBudgetInitialById(int idbudgetInitial);
}
