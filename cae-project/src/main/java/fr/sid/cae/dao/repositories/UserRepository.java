package fr.sid.cae.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.sid.cae.domain.User;

public interface UserRepository extends JpaRepository<User, String> {}
