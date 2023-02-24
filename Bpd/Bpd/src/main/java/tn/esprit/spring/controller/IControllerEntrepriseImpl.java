package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.RequestMapping;
import tn.esprit.spring.entities.Direction;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.IBudgetService;

@Controller
@RequestMapping("/oauth")
public class IControllerEntrepriseImpl{

	@Autowired
	IEmployeService iemployeservice;
	@Autowired
	IEntrepriseService ientrepriseservice;
	@Autowired
	IBudgetService ibudgetservice;

	public int ajouterEntreprise(Entreprise ssiiConsulting) {
		ientrepriseservice.ajouterEntreprise(ssiiConsulting);
		return ssiiConsulting.getId();
	}
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		ientrepriseservice.affecterDirectionAEntreprise(depId, entrepriseId);
	}
	public void deleteEntrepriseById(int entrepriseId)
	{
		ientrepriseservice.deleteEntrepriseById(entrepriseId);
	}
	public Entreprise getEntrepriseById(int entrepriseId) {

		return ientrepriseservice.getEntrepriseById(1);
	}
	
	public int ajouterDepartement(Direction dep) {
		return ientrepriseservice.ajouterDirection(dep);
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		return ientrepriseservice.getAllDirectionsNamesByEntreprise(entrepriseId);
	}

	public void deleteDepartementById(int depId) {
		ientrepriseservice.deleteDirectionById(depId);

	}
}
