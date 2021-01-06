package org.tarmanc.ecommerce.customerservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.tarmanc.ecommerce.customerservice.items.Item;
import org.tarmanc.ecommerce.customerservice.items.ItemBase;

import java.math.BigDecimal;
import java.util.*;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EndPointTest {

    @Autowired
    ObjectMapper objectMapper;

    private final RestTemplate restTemplate = new RestTemplate();
    private final WireMockServer wireMockServer = new WireMockServer();
    private final UUID uuid = UUID.randomUUID();
    private Item item;
    private ItemBase itemBase;
    private ItemBase itemBase2;
    private final String url = "http://localhost:8080/api/v1/";


    @BeforeEach
    public void setup() {
        wireMockServer.start();
        this.itemBase = ItemBase.builder()
                .itemID(new Random().nextLong())
                .itemName("test1")
                .itemPrice(new BigDecimal("12.99"))
                .id(uuid)
                .build();
        this.itemBase2 = ItemBase.builder()
                .itemID(new Random().nextLong())
                .itemName("search")
                .itemPrice(new BigDecimal("12.99"))
                .id(UUID.randomUUID())
                .build();
        this.item = Item.itemDetailedBuilder()
                .itemID(new Random().nextLong())
                .id(uuid)
                .description("description")
                .quantity(20L)
                .itemName("testItem")
                .itemPrice(new BigDecimal("6.50"))
                .build();
    }

    @AfterEach
    public void afterEach() {
        wireMockServer.stop();
    }


    @Test
    @SuppressWarnings("all")
    void getAllAvailableItems() throws JsonProcessingException {
        ItemBase[] itemArray = {itemBase, itemBase2};

        stubFor(get(urlEqualTo("/api/v1/items"))
                .willReturn(aResponse()
                        .withStatus(202)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(objectMapper.writeValueAsString(itemArray)))
        );

        ResponseEntity<ItemBase[]> responseValue = restTemplate.getForEntity(url + "items", ItemBase[].class);

        ItemBase[] array = responseValue.getBody();

        assertEquals(array[0].getId(), itemBase.getId());
        assertEquals(array[1].getId(), itemBase2.getId());

    }

    @Test
    @SuppressWarnings("all")
    void getItemById() throws JsonProcessingException {

        stubFor(get(urlEqualTo("/api/v1/item/" + uuid.toString()))
                .willReturn(aResponse()
                        .withStatus(202)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(objectMapper.writeValueAsString(item))));

        Item responseValue = restTemplate.getForObject(url + "item/" + uuid.toString(), Item.class);
        assertEquals(responseValue.getId(), item.getId());
    }

    @Test
    void searchItems() throws JsonProcessingException {
        List<ItemBase> itemBaseList = new ArrayList<>();
        itemBaseList.add(this.itemBase2);
        itemBaseList.add(this.itemBase);
        stubFor(get(urlEqualTo("/api/v1/search?name=search"))
                .willReturn(aResponse()
                        .withStatus(202)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(objectMapper
                                .writeValueAsString(
                                        itemBaseList.stream()
                                                .map(ItemBase::getItemName)
                                                .filter(s -> s.equals("search"))
                                                .toArray(String[]::new)))
                )
        );

        List<String> returnValue = Arrays
                .asList(Objects.requireNonNull(restTemplate.getForObject(url + "search?name=search", String[].class)));
        assertEquals("search", returnValue.get(returnValue.indexOf("search")));
    }
}