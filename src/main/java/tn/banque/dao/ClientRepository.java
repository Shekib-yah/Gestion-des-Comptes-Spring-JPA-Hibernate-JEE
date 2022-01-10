package tn.banque.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.banque.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
