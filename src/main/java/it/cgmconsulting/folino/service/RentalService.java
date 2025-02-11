package it.cgmconsulting.folino.service;

import it.cgmconsulting.folino.entity.*;
import it.cgmconsulting.folino.exception.BadRequestException;
import it.cgmconsulting.folino.exception.ResourceNotFoundException;
import it.cgmconsulting.folino.payload.request.RentalRequest;
import it.cgmconsulting.folino.payload.response.FilmMaxRentResponse;
import it.cgmconsulting.folino.payload.response.FilmRentResponse;
import it.cgmconsulting.folino.payload.response.FilmRentableResponse;
import it.cgmconsulting.folino.repository.CustomerRepository;
import it.cgmconsulting.folino.repository.FilmRepository;
import it.cgmconsulting.folino.repository.InventoryRepository;
import it.cgmconsulting.folino.repository.RentalRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {
    
    private final RentalRepository rentalRepository;
    private final CustomerRepository customerRepository;
    private final InventoryRepository inventoryRepository;
    private final FilmRepository filmRepository;

    @Transactional
    public Void addUpdateRental(RentalRequest rentalRequest) {
        Customer customer = customerRepository.findById(rentalRequest.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", rentalRequest.getCustomerId()));
        //per grandi mole di dati si potrebbe fare ricerca per storeId(passandolo ne dto),
        // in seguito la copia specifica la si trova con rentalDate
        Inventory inventory = inventoryRepository.findById(rentalRequest.getInventoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Inventory", "id", rentalRequest.getInventoryId()));
        RentalId rentalId = new RentalId(customer, inventory, rentalRequest.getRentalDate());
        Rental rental = rentalRepository.findById(rentalId)
                .orElse(null);

        if(rental == null){
            Rental newRental = Rental.builder()
                    .rentalId(rentalId)
                    .rentalReturn(null)
                    .build();
            rentalRepository.save(newRental);
        }else{
            rental.setRentalReturn(LocalDateTime.now());
        }
        return null;
    }

    public List<FilmRentResponse> findAllFilmsRentByCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
        return rentalRepository.findAllFilmsRentByCustomer(customerId);
    }

    public List<FilmMaxRentResponse> findFilmMaxNumberOfRent() {
        return rentalRepository.findFilmsMaxNumberOfRent();
    }

    public List<FilmRentableResponse> findRentableFilms(String title) {

        Film film = filmRepository.findByTitle(title)
                .orElseThrow(() -> new BadRequestException("Not found a film with this title: " + title));
        return rentalRepository.findRentableFilms(title);
    }
}
