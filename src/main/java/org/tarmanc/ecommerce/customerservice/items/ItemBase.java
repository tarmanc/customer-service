package org.tarmanc.ecommerce.customerservice.items;


import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemBase {

    private UUID id;
    private long itemID;
    private String itemName;
    private BigDecimal itemPrice;

}
