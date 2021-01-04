package org.tarmanc.ecommerce.customerservice.items;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class Item extends ItemBase {

    private String description;
    private long quantity;

    @Builder(builderMethodName = "itemBaseBuilder")
    public Item(UUID id, long itemID, BigDecimal price, String itemName, String description, long quantity) {
        super(id, itemID, itemName, price);
        this.description = description;
        this.quantity = quantity;
    }
}
