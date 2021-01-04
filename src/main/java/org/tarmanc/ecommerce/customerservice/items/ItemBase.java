package org.tarmanc.ecommerce.customerservice.items;


import lombok.*;

import java.math.BigInteger;
import java.util.UUID;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ItemBase {

    private final UUID id;
    private final long itemID;
    private String itemName;
    private BigInteger itemPrice;
}
