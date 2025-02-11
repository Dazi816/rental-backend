package it.cgmconsulting.folino.payload.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FilmRentableResponse {

    private String title;
    private String storeName;
    private Long numCopieTotali;
    private Long numCopieDisponibili;
}
