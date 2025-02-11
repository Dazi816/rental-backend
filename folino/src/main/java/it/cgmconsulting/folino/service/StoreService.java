package it.cgmconsulting.folino.service;

import it.cgmconsulting.folino.entity.*;
import it.cgmconsulting.folino.exception.BadRequestException;
import it.cgmconsulting.folino.exception.ResourceNotFoundException;
import it.cgmconsulting.folino.payload.response.CustomerStoreResponse;
import it.cgmconsulting.folino.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.LocalDate;



@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final RentalRepository rentalRepository;

    public CustomerStoreResponse countCustomersByStore(String storeName) {
        Store store = storeRepository.findByStoreName(storeName);
        if(store==null){
            throw new ResourceNotFoundException("Store", "name", storeName);
        }
        return storeRepository.countCustomersByStore(store.getStoreId());
    }



    //service
    public long countRentalsInDateRangeByStore(long storeId, LocalDate start, LocalDate end) {
        LocalDateTime startDateTime = start.atStartOfDay();
        LocalDateTime endDateTime = end.atTime(23, 59, 59);
        if(startDateTime.isAfter(endDateTime)){
            throw new BadRequestException("Start date must be before end date");
        }
        return rentalRepository.countByStoreAndRentalDateBetween(storeId, startDateTime, endDateTime);
    }
}
