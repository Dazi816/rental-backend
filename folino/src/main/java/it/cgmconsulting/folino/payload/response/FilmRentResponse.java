package it.cgmconsulting.folino.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class FilmRentResponse{

        private Long filmId;
        private String title;
        private String storeName;

}
