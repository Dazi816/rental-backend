package it.cgmconsulting.folino.service;

import it.cgmconsulting.folino.entity.Film;
import it.cgmconsulting.folino.entity.Genre;
import it.cgmconsulting.folino.entity.Language;
import it.cgmconsulting.folino.entity.Staff;
import it.cgmconsulting.folino.exception.BadRequestException;
import it.cgmconsulting.folino.exception.ResourceNotFoundException;
import it.cgmconsulting.folino.payload.request.FilmRequest;
import it.cgmconsulting.folino.payload.response.FilmResponse;
import it.cgmconsulting.folino.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;
    private final LanguageRepository languageRepository;
    private final GenreRepository genreRepository;
    private final StaffRepository staffRepository;

    @Transactional
    public FilmResponse updateFilm(Long filmId, FilmRequest request)
    {
        Film film = filmRepository.findById(filmId).
                orElseThrow(() -> new ResourceNotFoundException("Film", "id", filmId));

        Language language = languageRepository.findById(request.getLanguageId())
                .orElseThrow(() -> new ResourceNotFoundException("Language", "id", request.getLanguageId()));

        Genre genre = genreRepository.findById(request.getGenreId())
                .orElseThrow(() -> new ResourceNotFoundException("Genre", "id", request.getGenreId()));

        film.setTitle(request.getTitle());
        film.setDescription(request.getDescription());
        film.setReleaseYear(request.getReleaseYear());
        film.setLanguage(language);
        film.setGenre(genre);

        return FilmResponse.fromEntityToDto(film);


    }

    public List<FilmResponse> getFilmsByLanguage(long languageId) {
        List<FilmResponse> list = languageRepository.getList(languageId);
        return list;
    }

    public List<FilmResponse> findFilmsByActors(List<Long> actorsId) {
        Set<Long> actorsIdCleaned = new HashSet<>(actorsId);
        if(actorsIdCleaned.size()<=1){
            throw new BadRequestException("la lista deve contenere due o più ID");
        }
        if(!staffRepository.existsByStaffIdIn(actorsIdCleaned)){
            throw new BadRequestException("almeno uno degli ID non corrisponde a uno staff");
        }
        Set<Staff> staff= staffRepository.findByStaffIdIn(actorsIdCleaned);

        int size = staff.size();
        return filmRepository.getFilmsByActors(staff, size);
    }
}
