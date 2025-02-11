package it.cgmconsulting.folino.service;

import it.cgmconsulting.folino.entity.Film;
import it.cgmconsulting.folino.entity.Inventory;
import it.cgmconsulting.folino.entity.Store;
import it.cgmconsulting.folino.exception.ResourceNotFoundException;
import it.cgmconsulting.folino.repository.FilmRepository;
import it.cgmconsulting.folino.repository.LanguageRepository;
import it.cgmconsulting.folino.repository.StoreRepository;
import it.cgmconsulting.folino.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final StoreRepository storeRepository;
    private final FilmRepository filmRepository;
    private final InventoryRepository inventoryRepository;


    @Transactional
    public Void addFilmToStore(long storeId, long filmId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException("Store", "id", storeId));
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new ResourceNotFoundException("Film", "id", filmId));
        Inventory inventory = Inventory.builder()
                .store(store)
                .film(film)
                .build();

        inventoryRepository.save(inventory);

        return null;
    }
}
