package tn.banque.metier;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.banque.dao.CompteRepository;
import tn.banque.dao.OperationRepository;
import tn.banque.entities.Compte;
import tn.banque.entities.CompteCourant;
import tn.banque.entities.Operation;
import tn.banque.entities.Retrait;
import tn.banque.entities.Versement;
@Service
@Transactional
public class BanqueMetierImpl implements IBanqueMetier {
	@Autowired
	private CompteRepository compteRepository; 
	@Autowired
	private OperationRepository operationRepository;
	@Override
	public Compte consulterCompte(String codeCpte) {
		Compte cp=compteRepository.findById(codeCpte).get();
		if(cp==null)
			throw new RuntimeException("Compte inexistant !!");
		return cp;
	}

	@Override
	public void verser(String codeCpte, double montant) {
		Compte cp=consulterCompte(codeCpte);
		Versement v=new Versement(new Date(),montant,cp);
		operationRepository.save(v);
		cp.setSolde(cp.getSolde()+montant);
		compteRepository.save(cp);
		
	}

	@Override
	public void retirer(String codeCpte, double montant) {
		Compte cp=consulterCompte(codeCpte);
		double faciliteCaisse=0;
		if (cp instanceof CompteCourant)
			faciliteCaisse=((CompteCourant) cp).getDecouvert();
		if(cp.getSolde()+faciliteCaisse<montant)
			throw new RuntimeException("Solde insuffisant");
		Retrait r=new Retrait(new Date(),montant,cp);
		operationRepository.save(r);
		cp.setSolde(cp.getSolde()-montant);
		compteRepository.save(cp);
		
		
	}

	@Override
	public void virement(String codeCpte1, String codeCpte2, double montant) {
		retirer(codeCpte1, montant);
		verser(codeCpte2, montant);
		
	}

	@Override
	public Page<Operation> listOperation(String codeCpte, int page, int size) {
		return operationRepository.listOperation(codeCpte, new QPageRequest(page, size));
	}

}
