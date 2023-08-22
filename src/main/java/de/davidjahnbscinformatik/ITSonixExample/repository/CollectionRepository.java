package de.davidjahnbscinformatik.ITSonixExample.repository;

import de.davidjahnbscinformatik.ITSonixExample.model.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository extends JpaRepository<Collection, Integer> {
}
