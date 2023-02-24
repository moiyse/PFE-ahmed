package tn.esprit.spring.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.BudgetInitial;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.IBudgetService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RestControlBudget {

	@Autowired
	IEmployeService iemployeservice;
	@Autowired
	IEntrepriseService ientrepriseservice;
	@Autowired
	IBudgetService ibudgetservice;
	
	// http://localhost:8081/SpringMVC/servlet/ajouterBudgetInitial
	//{"id":4,"name":"mabudgetinitial", "description":"c ma budgetinitial"}
	@PostMapping("/ajouterBudgetInitial")
	@ResponseBody
	public int ajouterBudgetInitial(@RequestBody BudgetInitial budgetinitial) {
		ibudgetservice.ajouterBudgetInitial(budgetinitial);
		return budgetinitial.getId();
	}

	@GetMapping("/oauth/test")
	public String test() {
		return "Public Content.";
	}

	@GetMapping("/test")
	public String tests() {
		return "Test 2";
	}

	// http://localhost:8081/SpringMVC/servlet/affecterBudgetInitialADirection/4/4
	@PutMapping(value = "/affecterBudgetInitialADirection/{idBudgetInitial}/{iddept}") 
	public void affecterBudgetInitialADirection(@PathVariable("idBudgetInitial") int budgetinitialId, @PathVariable("iddept") int depId) {
		ibudgetservice.affecterBudgetInitialADirection(budgetinitialId, depId);

	}
	
	// http://localhost:8081/SpringMVC/servlet/ajouterBudget
    //{"idBudgetInitial":1,"employeId":2,"dateDebut":"2020-03-01","dateFin":"2021-03-01"}
	
	@PostMapping("/ajouterbudget/idBudgetinitial/idemp/dated/datef")
	@ResponseBody
	public void ajouterBudget(@PathVariable("idBudgetInitial") int idBudgetInitial, @PathVariable("idemp") int idEmploye, @PathVariable("dated") Date dateDebut,@PathVariable("datef") Date dateFin) {
		ibudgetservice.ajouterBudget(idBudgetInitial, idEmploye, dateDebut, dateFin);

	}

	// http://localhost:8081/SpringMVC/servlet/affecterBudgetADirection/4/4
	@PutMapping(value = "/validerBudget/{idBudgetInitial}/{iddept}") 
	public void validerBudget(int budgetinitialId, int employeId, Date dateDebut, Date dateFin, int validateurId) {
		ibudgetservice.validerBudget(budgetinitialId, employeId, dateDebut, dateFin, validateurId);

	}
	
	// URL : http://localhost:8081/SpringMVC/servlet/findAllBudgetInitialByEmployeJPQL/1
    @GetMapping(value = "findAllBudgetInitialByEmployeJPQL/{idemp}")
    @ResponseBody
	public List<BudgetInitial> findAllBudgetInitialByEmployeJPQL(@PathVariable("idemp") int employeId) {

		return ibudgetservice.findAllBudgetInitialByEmployeJPQL(employeId);
	}

    // URL : http://localhost:8081/SpringMVC/servlet/getAllEmployeByBudgetInitial/1
    @GetMapping(value = "getAllEmployeByBudgetInitial/{idBudgetInitial}")
    @ResponseBody
	public List<Employe> getAllEmployeByBudgetInitial(@PathVariable("idBudgetInitial") int budgetinitialId) {

		return ibudgetservice.getAllEmployeByBudgetInitial(budgetinitialId);
	}
	 // URL : http://localhost:8081/SpringMVC/servlet/getBudgetInitial
	@GetMapping(value = "/getBudgetInitial")
   @ResponseBody
	public List<BudgetInitial> getBudgetInitial() {
		
		return ibudgetservice.getBudgetInitial();
	}
    // http://localhost:8081/SpringMVC/servlet/getBudgetInitialById/1
    @GetMapping(value = "getBudgetInitialById/{idbudgetInitial}")
    @ResponseBody
	public BudgetInitial getBudgetInitialById(@PathVariable("idbudgetInitial") int budgetinitialId) {

		return ibudgetservice.getBudgetInitialById(budgetinitialId);
	}
    // URL : http://localhost:8081/SpringMVC/servlet/deleteBudgetInitialById/3
    @DeleteMapping("/deleteBudgetInitialById/{idbudgetInitial}") 
	@ResponseBody 
	public void deleteBudgetInitialById(@PathVariable("idbudgetInitial") int idbudgetInitial) {
		System.out.println("*********************************************************************");
		ibudgetservice.deleteBudgetInitialById(idbudgetInitial);

	}
}
