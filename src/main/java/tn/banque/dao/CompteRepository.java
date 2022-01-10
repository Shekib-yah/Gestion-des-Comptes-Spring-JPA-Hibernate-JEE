package tn.banque.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.banque.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, String> {

}
