package it.cgmconsulting.folino.repository;

import it.cgmconsulting.folino.entity.Rental;
import it.cgmconsulting.folino.entity.RentalId;
import it.cgmconsulting.folino.payload.response.FilmMaxRentResponse;
import it.cgmconsulting.folino.payload.response.FilmRentResponse;
import it.cgmconsulting.folino.payload.response.FilmRentableResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, RentalId> {

    @Query("SELECT COUNT(r) FROM Rental r " +
            "JOIN r.rentalId.inventory i " +
            "JOIN i.store s " +
            "WHERE s.storeId = :storeId " +
            "AND r.rentalId.rentalDate BETWEEN :start AND :end")
    long countByStoreAndRentalDateBetween(long storeId, LocalDateTime start, LocalDateTime end);

    @Query("SELECT new it.cgmconsulting.folino.payload.response.FilmRentResponse(" +
            "r.rentalId.inventory.film.filmId, r.rentalId.inventory.film.title, r.rentalId.inventory.store.storeName) " +
            "FROM Rental r " +
            "WHERE r.rentalId.customer.customerId = :customerId")
    List<FilmRentResponse> findAllFilmsRentByCustomer(Long customerId);

    //842 ms -> 57 ms
    @Query("SELECT new it.cgmconsulting.folino.payload.response.FilmMaxRentResponse(" +
            "r.rentalId.inventory.film.filmId, r.rentalId.inventory.film.title, COUNT(r)) " +
            "FROM Rental r " +
            "GROUP BY r.rentalId.inventory.film.filmId, r.rentalId.inventory.film.title " +
            "HAVING COUNT(r) = "+
            "    (SELECT COUNT(r2) " +
            "    FROM Rental r2 " +
            "    GROUP BY r2.rentalId.inventory.film.filmId " +
            "    ORDER BY COUNT(r2) DESC " +
            "    LIMIT 1)")
    List<FilmMaxRentResponse> findFilmsMaxNumberOfRent();

    // 0.95 -> 0.45
    @Query("SELECT new it.cgmconsulting.folino.payload.response.FilmRentableResponse(" +
            "i.film.title, " +
            "i.store.storeName, " +
            "COUNT(i), " +
            "(COUNT(i) - COUNT(r))) " +
            "FROM Inventory i " +
            "LEFT JOIN Rental r ON i.inventoryId = r.rentalId.inventory.inventoryId AND r.rentalReturn IS NULL " +
            "WHERE i.film.title = :title " +
            "GROUP BY i.film.title, i.store.storeName")
    List<FilmRentableResponse> findRentableFilms(String title);
}
