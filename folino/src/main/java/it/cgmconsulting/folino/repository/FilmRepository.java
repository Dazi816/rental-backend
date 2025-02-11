package it.cgmconsulting.folino.repository;

import it.cgmconsulting.folino.entity.Film;
import it.cgmconsulting.folino.entity.Staff;
import it.cgmconsulting.folino.payload.response.FilmResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FilmRepository extends JpaRepository<Film, Long> {


    @Query("SELECT new it.cgmconsulting.folino.payload.response.FilmResponse(f.filmId, f.title, f.description, f.releaseYear, f.language.languageName) " +
            "FROM Film f " +
            "JOIN FilmStaff fs ON f.filmId = fs.filmStaffId.film.filmId " +
            "JOIN fs.filmStaffId.staff s " +
            "WHERE s IN :staff " +
            "GROUP BY f " +
            "HAVING COUNT(s) = :size")
    List<FilmResponse> getFilmsByActors(Set<Staff> staff, int size);

    Optional<Film>findByTitle(String title);
}
