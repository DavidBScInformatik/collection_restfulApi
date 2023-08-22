package de.davidjahnbscinformatik.ITSonixExample.repository;

import de.davidjahnbscinformatik.ITSonixExample.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformRepository extends JpaRepository<Platform, Integer> {
}
