package it.cgmconsulting.folino.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long filmId;

    @Column(length = 100, nullable = false)
    private String title;
    @Column(length = 1000, nullable = false)
    private String description;

    @Column(nullable = false)
    private short releaseYear;

    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;


}
