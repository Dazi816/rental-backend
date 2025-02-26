package it.cgmconsulting.folino.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerStoreResponse {
    private String storeName;
    private long totalCustomers;

}
