package it.cgmconsulting.folino.repository;

import it.cgmconsulting.folino.entity.Language;
import it.cgmconsulting.folino.payload.response.FilmResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface LanguageRepository extends JpaRepository<Language, Long> {


    //prima chiamata 691 ms -> successive 15 ms, funziona meglio per tanti film
    @Query(value="SELECT new it.cgmconsulting.folino.payload.response.FilmResponse(" +
            "f.filmId, " +
            "f.title, " +
            "f.description, " +
            "f.releaseYear, " +
            "f.language.languageName" +
            ") FROM Language l " +
            "INNER JOIN l.films f " +
            "WHERE l.languageId = :languageId " +
            "ORDER BY f.title")
    List<FilmResponse> getList(long languageId);

    //prima chiamata 866 ms
//    @Query(value="SELECT new it.cgmconsulting.folino.payload.response.FilmResponse(" +
//            "f.filmId, " +
//            "f.title, " +
//            "f.description, " +
//            "f.releaseYear, " +
//            "f.language.languageName" +
//            ") FROM Film f " +
//            "WHERE f.language.languageId = :languageId")
//    List<FilmResponse> getList(long languageId);
}
