package it.cgmconsulting.folino.repository;

import it.cgmconsulting.folino.entity.Film;
import it.cgmconsulting.folino.entity.Inventory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Film> getFilmByInventoryId(Long filmId);

}
