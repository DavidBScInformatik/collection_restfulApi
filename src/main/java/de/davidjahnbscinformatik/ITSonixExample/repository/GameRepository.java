package de.davidjahnbscinformatik.ITSonixExample.repository;

import de.davidjahnbscinformatik.ITSonixExample.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {

}
