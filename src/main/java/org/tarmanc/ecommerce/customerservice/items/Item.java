package org.tarmanc.ecommerce.customerservice.items;

import lombok.*;

import java.util.UUID;


public class Item extends ItemBase{

    private String description;
    private long quantity;

    public Item(UUID id, long itemID) {
        super(id, itemID);
    }
}
