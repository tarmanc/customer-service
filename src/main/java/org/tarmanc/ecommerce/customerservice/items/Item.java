package org.tarmanc.ecommerce.customerservice.items;


import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;


@AllArgsConstructor
@Getter
@Setter
public class Item extends ItemBase implements Serializable {

    private String description;
    private long quantity;

    @Builder(builderMethodName = "itemDetailedBuilder")
    public Item(UUID id, String itemName, BigDecimal itemPrice, String description, long quantity) {
        super(id, itemName, itemPrice);
        this.description = description;
        this.quantity = quantity;
    }

    public Item() {
    }
}