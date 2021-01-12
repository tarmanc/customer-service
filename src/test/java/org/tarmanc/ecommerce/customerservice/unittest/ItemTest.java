package org.tarmanc.ecommerce.customerservice.unittest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.tarmanc.ecommerce.customerservice.items.Item;
import org.tarmanc.ecommerce.customerservice.items.ItemBase;

import java.math.BigDecimal;
import java.util.UUID;

import static org.jgroups.util.Util.assertEquals;
import static org.jgroups.util.Util.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class ItemTest {


    @Test
    public void itemBuilder() {
        ItemBase itemBase = ItemBase.builder()
                .itemName("test")
                .itemPrice(new BigDecimal("12.99"))
                .id(UUID.randomUUID())
                .build();

        System.out.println(itemBase);
        assertEquals(itemBase.getItemPrice().toString(), "12.99");
        assertNotNull(itemBase);

        Item item = Item.itemDetailedBuilder()
                .id(UUID.randomUUID())
                .description("description")
                .quantity(20L)
                .itemName("testItem")
                .itemPrice(new BigDecimal("6.50"))
                .build();

        assertEquals(item.getItemPrice().toString(), "6.50");
        assertNotNull(item.getDescription());
        assertNotEquals(item.getQuantity(), 0);
        assertNotNull(item);
    }


}
