package it.cgmconsulting.folino.controller;

import it.cgmconsulting.folino.payload.request.FilmRequest;
import it.cgmconsulting.folino.payload.request.RentalRequest;
import it.cgmconsulting.folino.payload.response.*;
import it.cgmconsulting.folino.service.FilmService;
import it.cgmconsulting.folino.service.InventoryService;
import it.cgmconsulting.folino.service.RentalService;
import it.cgmconsulting.folino.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class Controller {

    private final FilmService filmService;
    private final InventoryService inventoryService;
    private final StoreService storeService;
    private final RentalService rentalService;

    //1
    @PutMapping("/update-film/{filmId}")
    public ResponseEntity<FilmResponse> updateFilm(@PathVariable long filmId, @Valid @RequestBody FilmRequest request){
        return ResponseEntity.ok(filmService.updateFilm(filmId, request));
    }

    //2
    @GetMapping("/find-films-by-language/{languageId}")
    public ResponseEntity<List<FilmResponse>> getFilmsByLanguage(@PathVariable long languageId){
        return ResponseEntity.ok(filmService.getFilmsByLanguage(languageId));
    }

    //3
    @PostMapping("/add-film-to-store/{storeId}/{filmId}")
    public ResponseEntity<Void> addFilmToStore(@PathVariable long storeId, @PathVariable long filmId){
        return new ResponseEntity<Void>(inventoryService.addFilmToStore(storeId, filmId), HttpStatus.CREATED);
    }

    //4
    @GetMapping("/count-customers-by-store/{storeName}")
    public ResponseEntity<CustomerStoreResponse> countCustomersByStore(@PathVariable String storeName){
        String formattedStoreName = storeName.replace("-", " "); //lower-upper checked
        return ResponseEntity.ok(storeService.countCustomersByStore(formattedStoreName));
    }

    //5
    @PutMapping("/add-update-rental")
    public ResponseEntity<Void> addUpdateRental(@Valid @RequestBody RentalRequest request){
        return ResponseEntity.ok(rentalService.addUpdateRental(request));
    }

    //6
    @GetMapping("/count-rentals-in-date-range-by-store/{storeId}")
    public ResponseEntity<Long> countRentalsInDateRangeByStore(@PathVariable Long storeId, @RequestParam LocalDate start, @RequestParam LocalDate end){
        return ResponseEntity.ok(storeService.countRentalsInDateRangeByStore(storeId, start, end));
    }

    //7
    @GetMapping("/find-all-films-rent-by-one-customer/{customerId}")
    public ResponseEntity<List<FilmRentResponse>> findAllFilmsRentByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(rentalService.findAllFilmsRentByCustomer(customerId));
    }

    //8
    @GetMapping("/find-film-with-max-number-of-rent")
    public ResponseEntity<List<FilmMaxRentResponse>> findFilmMaxNumberOfRent(){
        return ResponseEntity.ok(rentalService.findFilmMaxNumberOfRent());
    }

    //9
    @GetMapping("/find-films-by-actors")
    public ResponseEntity<List<FilmResponse>> findFilmsByActors(@RequestParam List<Long> actorsId){
        return ResponseEntity.ok(filmService.findFilmsByActors(actorsId));
    }

    //10
    @GetMapping("/find-rentable-films")
    public ResponseEntity<List<FilmRentableResponse>> findRentableFilms(@RequestParam String title) {
        return ResponseEntity.ok(rentalService.findRentableFilms(title));
    }
}
