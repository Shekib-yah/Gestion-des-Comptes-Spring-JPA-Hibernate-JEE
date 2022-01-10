package tn.banque;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import tn.banque.dao.ClientRepository;
import tn.banque.dao.CompteRepository;
import tn.banque.dao.OperationRepository;
import tn.banque.entities.Client;
import tn.banque.entities.Compte;
import tn.banque.entities.CompteCourant;
import tn.banque.entities.CompteEpargne;
import tn.banque.entities.Retrait;
import tn.banque.entities.Versement;
import tn.banque.metier.IBanqueMetier;

@SpringBootApplication
public class GestionDuBanqueApplication implements CommandLineRunner  {
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private IBanqueMetier banqueMetier;
	public static void main(String[] args) {
		SpringApplication.run(GestionDuBanqueApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		/*Client c1=clientRepository.save(new Client("Chakib", "chakib.yahyaoui@esprit.tn"));
		Client c2=clientRepository.save(new Client("Ahmed", "ahmed.yahyaoui@esprit.tn"));
		Client c3=clientRepository.save(new Client("Rached", "rached.yahyaoui@esprit.tn"));
		Compte cp1=compteRepository.save(new CompteCourant("c1", new Date(), 9000, c1, 6000));
		Compte cp2=compteRepository.save(new CompteEpargne("c2", new Date(), 18000, c2, 7.5));
		operationRepository.save(new Versement(new Date(), 9050, cp1));
		operationRepository.save(new Versement(new Date(), 1850, cp1));
		operationRepository.save(new Versement(new Date(), 7860, cp1));
		operationRepository.save(new Retrait(new Date(), 9050, cp1));
		operationRepository.save(new Versement(new Date(), 2350, cp2));
		operationRepository.save(new Versement(new Date(), 8750, cp2));
		operationRepository.save(new Versement(new Date(), 850, cp2));
		operationRepository.save(new Retrait(new Date(), 1450, cp2));
		banqueMetier.verser("c1", 1111111);*/
		
	}

}
