package it.cgmconsulting.folino.repository;


import it.cgmconsulting.folino.payload.response.CustomerStoreResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import it.cgmconsulting.folino.entity.Store;
import org.springframework.data.jpa.repository.Query;


public interface StoreRepository extends JpaRepository<Store, Long> {


    Store findByStoreName(String storeName);

    //841 ms -> 20 ms
    //non c'è bisogno di optional in quanto dà 0 a numero (cfr. dto), store esiste già perché verificato prima con name
    @Query(value = "SELECT new it.cgmconsulting.folino.payload.response.CustomerStoreResponse(" +
            "i.store.storeName, " +
            "COUNT(DISTINCT r.rentalId.customer.id)) " +
            "FROM Rental r " +
            "JOIN r.rentalId.inventory i " +
            "WHERE i.store.id = :storeId")
    CustomerStoreResponse countCustomersByStore(Long storeId);
}

