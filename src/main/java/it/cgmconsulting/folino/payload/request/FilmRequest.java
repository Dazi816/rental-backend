package it.cgmconsulting.folino.payload.request;

import it.cgmconsulting.folino.entity.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class FilmRequest {

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotBlank
    @Size(max = 255)
    private String description;

    @NotNull
    private Short releaseYear;

    @NotNull
    private Long languageId;

    @NotNull
    private Long genreId;

}
