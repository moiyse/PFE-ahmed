package tn.esprit.spring.controller;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Budget;
import tn.esprit.spring.entities.BudgetInitial;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;
import tn.esprit.spring.services.IBudgetService;

@Controller
@RequestMapping("/oauth")
public class IControllerBudgetImpl {

	@Autowired
	IEmployeService iemployeservice;
	@Autowired
	IEntrepriseService ientrepriseservice;
	@Autowired
	IBudgetService ibudgetservice;

	private String login; 
	private String password; 
	private Boolean loggedIn;

	private Employe authenticatedUser = null; 
	private String prenom; 
	private String nom; 
	private String email;
	private boolean actif;
	private Role role;  
	public Role[] getRoles() { return Role.values(); }

	private List<Employe> employes; 

	private Integer employeIdToBeUpdated; // getter et setter


	public String doLogin() {

		String navigateTo = "null";
		authenticatedUser=iemployeservice.authenticate(login, password);
		if (authenticatedUser != null && authenticatedUser.getRole() == Role.ADMINISTRATEUR) {
			navigateTo = "/pages/admin/welcome.xhtml?faces-redirect=true";
			loggedIn = true;
		}		

		else
		{
			System.out.println("fff");
			
			FacesMessage facesMessage =
					new FacesMessage("Login Failed: Please check your username/password and try again.");
			FacesContext.getCurrentInstance().addMessage("form:btn",facesMessage);
		}
		return navigateTo;	
	}

	public String doLogout()
	{
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	
	return "/login.xhtml?faces-redirect=true";
	}


	public String addEmploye() {

		if (authenticatedUser==null || !loggedIn) return "/login.xhtml?faces-redirect=true";

		//iemployeservice.addOrUpdateEmploye(new Employe(employeIdToBeUpdated, nom, prenom, email, password, actif, role, null, null, null));
		return "null"; 
	}  

	public String removeEmploye(int employeId) {
		String navigateTo = "null";
		if (authenticatedUser==null || !loggedIn) return "/login.xhtml?faces-redirect=true";

		iemployeservice.deleteEmployeById(employeId);
		return navigateTo; 
	} 

	public String displayEmploye(Employe empl) 
	{
		String navigateTo = "null";
		if (authenticatedUser==null || !loggedIn) return "/login.xhtml?faces-redirect=true";


		this.setPrenom(empl.getPrenom());
		this.setNom(empl.getNom());
		this.setActif(empl.isActif()); 
		this.setEmail(empl.getEmail());
		this.setRole(empl.getRole());
		this.setPassword(empl.getPassword());
		this.setEmployeIdToBeUpdated(empl.getId());

		return navigateTo; 

	} 

	public String updateEmploye() 
	{ 
		String navigateTo = "null";
		
		if (authenticatedUser==null || !loggedIn) return "/login.xhtml?faces-redirect=true";

		//iemployeservice.addOrUpdateEmploye(new Employe(employeIdToBeUpdated, nom, prenom, email, password, actif, role, null, null, null));

		return navigateTo; 

	} 


	// getters and setters 

	public IEmployeService getIEmployeService() {
		return iemployeservice;
	}

	public void setIEmployeService(IEmployeService iemployeservice) {
		this.iemployeservice = iemployeservice;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public List<Employe> getAllEmployes() {
		return iemployeservice.getAllEmployes();
	}

	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public int ajouterEmploye(Employe employe)
	{
		iemployeservice.addOrUpdateEmploye(employe);
		return employe.getId();
	}

	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		iemployeservice.mettreAjourEmailByEmployeId(email, employeId);

	}

	public void affecterEmployeADepartement(int employeId, int depId) {
		iemployeservice.affecterEmployeADirection(employeId, depId);

	}



	public void desaffecterEmployeDuDirection(int employeId, int depId)
	{
		iemployeservice.desaffecterEmployeDuDirection(employeId, depId);
	}

	public int ajouterContrat(Contrat contrat) {
		iemployeservice.ajouterContrat(contrat);
		return contrat.getReference();
	}

	public void affecterContratAEmploye(int contratId, int employeId)
	{
		iemployeservice.affecterContratAEmploye(contratId, employeId);
	}


	public String getEmployePrenomById(int employeId) {
		return iemployeservice.getEmployePrenomById(employeId);
	}

	public void deleteEmployeById(int employeId) {
		iemployeservice.deleteEmployeById(employeId);

	}
	public void deleteContratById(int contratId) {
		iemployeservice.deleteContratById(contratId);
	}

	public int getNombreEmployeJPQL() {

		return iemployeservice.getNombreEmployeJPQL();
	}

	public List<String> getAllEmployeNamesJPQL() {

		return iemployeservice.getAllEmployeNamesJPQL();
	}

	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return iemployeservice.getAllEmployeByEntreprise(entreprise);
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {	
		iemployeservice.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}

	public void deleteAllContratJPQL() {
		iemployeservice.deleteAllContratJPQL();

	}

	public float getSalaireByEmployeIdJPQL(int employeId) {
		return iemployeservice.getSalaireByEmployeIdJPQL(employeId);
	}


	public Double getSalaireMoyenByDirectionId(int directionId) {
		return iemployeservice.getSalaireMoyenByDirectionId(directionId);
	}

	public List<Budget> getBudgetsByBudgetInitialAndDate(Employe employe, BudgetInitial budgetInitial, Date dateDebut,
			Date dateFin) {
		return iemployeservice.getBudgetsByBudgetInitialAndDate(employe, budgetInitial, dateDebut, dateFin);
	}
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}




	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Employe> getEmployes() {
		employes = iemployeservice.getAllEmployes(); 
		return employes;
	}

	public void setEmployes(List<Employe> employes) {
		this.employes = employes;
	}

	public Integer getEmployeIdToBeUpdated() {
		return employeIdToBeUpdated;
	}

	public void setEmployeIdToBeUpdated(Integer employeIdToBeUpdated) {
		this.employeIdToBeUpdated = employeIdToBeUpdated;
	}

	public Employe getAuthenticatedUser() {
		return authenticatedUser;
	}

	public void setAuthenticatedUser(Employe authenticatedUser) {
		this.authenticatedUser = authenticatedUser;
	}

}