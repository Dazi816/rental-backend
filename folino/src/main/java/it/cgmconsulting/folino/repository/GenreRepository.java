package it.cgmconsulting.folino.repository;

import it.cgmconsulting.folino.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
