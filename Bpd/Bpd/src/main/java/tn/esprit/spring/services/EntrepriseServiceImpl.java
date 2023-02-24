package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import tn.esprit.spring.entities.Direction;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DirectionRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DirectionRepository deptRepoistory;
	
	public int ajouterEntreprise(Entreprise entreprise) {
		entrepriseRepoistory.save(entreprise);
		return entreprise.getId();
	}

	public int ajouterDirection(Direction dep) {
		deptRepoistory.save(dep);
		return dep.getId();
	}
	
	public void affecterDirectionAEntreprise(int depId, int entrepriseId) {
		//Le bout Master de cette relation N:1 est departement  
				//donc il faut rajouter l'entreprise a departement 
				// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
				//Rappel : la classe qui contient mappedBy represente le bout Slave
				//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
				Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
				Direction depManagedEntity = deptRepoistory.findById(depId).get();
				
				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				deptRepoistory.save(depManagedEntity);
		
	}
	
	public List<String> getAllDirectionsNamesByEntreprise(int entrepriseId) {
		Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
		List<String> depNames = new ArrayList<>();
		for(Direction dep : entrepriseManagedEntity.getDirections()){
			depNames.add(dep.getName());
		}
		
		return depNames;
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		entrepriseRepoistory.delete(entrepriseRepoistory.findById(entrepriseId).get());
	}

	public void deleteDirectionById(int depId) {
		Direction direction=deptRepoistory.findById(depId).orElse(null);

		deptRepoistory.delete(direction);
	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		return entrepriseRepoistory.findById(entrepriseId).get();	
	}

	@Override
	public List<Entreprise> getAllEntreprise() {
		return (List<Entreprise>) entrepriseRepoistory.findAll();
	}

	@Override
	public List<Direction> getAllDirections() {
		return (List<Direction>) deptRepoistory.findAll();
	}

	@Override
	public void ajouterEtAffecterDirectionAEntreprise(Direction direction,int idEntreprise) {
		Entreprise entreprise=entrepriseRepoistory.findById(idEntreprise).orElse(null);
		direction.setEntreprise(entreprise);
		deptRepoistory.save(direction);
	}

	@Override
	public Direction getDirectionByid(int id) {
		return deptRepoistory.findById(id).orElse(null);
	}

}
