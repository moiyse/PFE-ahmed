package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.BudgetInitial;
import tn.esprit.spring.entities.Budget;
import tn.esprit.spring.entities.BudgetPK;
@Repository
public interface BudgetRepository extends CrudRepository<Budget, BudgetPK>{

	/*@Query("select DISTINCT m from BudgetInitial m join m.budgets t join t.employe e where e.id=:employeId")
public List<BudgetInitial> findAllBudgetInitialByEmployeJPQL(@Param("employeId")int employeId);
	
	@Query("select DISTINCT e from Employe e "
				+ "join e.budgets t "
				+ "join t.budgetinitial m "
				+ "where m.id=:biId")
	public List<Employe> getAllEmployeByBudgetInitial(@Param("biId")int budgetinitialId);

	
	@Query("Select t from Budget t "
				+ "where t.budgetinitial=:bi and "
				+ "t.employe=:emp and "
				+ "t.budgetPK.dateDebut>=:dateD and "
				+ "t.budgetPK.dateFin<=:dateF")
	public List<Budget> getBudgetsByBudgetInitialAndDate(@Param("emp")Employe employe, @Param("bi")BudgetInitial budgetinitial, @Param("dateD")Date dateDebut,@Param("dateF")Date dateFin);
public Budget findByBudgetPK(BudgetPK budgetPK);*/
}
