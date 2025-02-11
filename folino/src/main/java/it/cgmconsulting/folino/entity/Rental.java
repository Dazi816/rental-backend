package it.cgmconsulting.folino.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "rental")
public class Rental {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private RentalId rentalId;

    @Column(nullable = true)
    private LocalDateTime rentalReturn;

}
