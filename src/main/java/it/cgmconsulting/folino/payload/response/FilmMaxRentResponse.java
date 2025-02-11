package it.cgmconsulting.folino.payload.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FilmMaxRentResponse {

    private Long filmId;
    private String title;
    private Long totalRents;
}
