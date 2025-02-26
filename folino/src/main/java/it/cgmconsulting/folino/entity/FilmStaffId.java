package it.cgmconsulting.folino.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FilmStaffId implements Serializable {

    @ManyToOne
    @JoinColumn(nullable = false, name="film_id")
    private Film film;

    @ManyToOne
    @JoinColumn(nullable = false, name="staff_id")
    private Staff staff;

    @ManyToOne
    @JoinColumn(nullable = false, name = "role_id")
    private Role role;
}


