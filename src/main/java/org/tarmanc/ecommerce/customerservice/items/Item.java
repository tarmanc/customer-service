package org.tarmanc.ecommerce.customerservice.items;


import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;


@AllArgsConstructor
@Getter
@Setter
public class Item extends ItemBase {

    //    private UUID id;
//    private long itemID;
//    private String itemName;
//    private BigDecimal itemPrice;
    private String description;
    private long quantity;

    @Builder(builderMethodName = "itemDetailedBuilder")
    public Item(UUID id, long itemID, String itemName, BigDecimal itemPrice, String description, long quantity) {
        super(id, itemID, itemName, itemPrice);
        this.description = description;
        this.quantity = quantity;
    }

    public Item() {
    }
}