package org.tarmanc.ecommerce.customerservice.items;


import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemBase implements Serializable {

    private UUID id;
    private String itemName;
    private BigDecimal itemPrice;

}
