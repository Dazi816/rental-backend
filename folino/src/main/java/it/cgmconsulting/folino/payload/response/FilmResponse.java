package it.cgmconsulting.folino.payload.response;

import it.cgmconsulting.folino.entity.Film;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmResponse {

    private Long filmId;
    private String title;
    private String description;
    private short releaseYear;
    private String languageName;

    public static FilmResponse fromEntityToDto(Film film) {
        return new FilmResponse(
                film.getFilmId(),
                film.getTitle(),
                film.getDescription(),
                film.getReleaseYear(),
                film.getLanguage().getLanguageName()
        );
    }

}
