package it.cgmconsulting.folino.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "film_staff")
public class FilmStaff {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private FilmStaffId filmStaffId;
}
