package de.davidjahnbscinformatik.ITSonixExample.repository;

import de.davidjahnbscinformatik.ITSonixExample.model.Collection;
import de.davidjahnbscinformatik.ITSonixExample.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
}
