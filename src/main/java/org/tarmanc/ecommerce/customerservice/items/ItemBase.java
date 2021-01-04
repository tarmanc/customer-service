package org.tarmanc.ecommerce.customerservice.items;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ItemBase {

    private final UUID id;
    private final long itemID;
    private String itemName;
    private BigDecimal itemPrice;

}
