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
@Table(name = "language")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long languageId;

    @Column(length = 20, unique = true, nullable = false)
    private String languageName;

    @OneToMany(mappedBy = "language")
    private List<Film> films;

}
