package org.tarmanc.ecommerce.customerservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tarmanc.ecommerce.customerservice.service.CustomerService;
import org.tarmanc.ecommerce.customerservice.items.Item;
import org.tarmanc.ecommerce.customerservice.items.ItemBase;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class ItemController {

    private final CustomerService customerService;

    @GetMapping("/items")
    public ResponseEntity<List<ItemBase>> getAllAvailableItems() {
        System.out.println("Items Working");
        return new ResponseEntity<>(customerService.getAllItems(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable() UUID id) {
        System.out.println("ID Working");
        return new ResponseEntity<>(customerService.getItemById(id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ItemBase>> searchItems(@RequestParam String name) {
        System.out.println("Search Working");
        return new ResponseEntity<>(customerService.searchItems(name), HttpStatus.ACCEPTED);
    }

}
