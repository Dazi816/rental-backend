package it.cgmconsulting.folino.payload.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter @Builder
public class RentalRequest {

    @NotNull
    private Long customerId;

    @NotNull
    private Long inventoryId;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime rentalDate;

}


