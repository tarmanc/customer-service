package org.tarmanc.ecommerce.customerservice.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.tarmanc.ecommerce.customerservice.config.JMSConfig;
import org.tarmanc.ecommerce.customerservice.items.Item;
import org.tarmanc.ecommerce.customerservice.items.ItemBase;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    @SuppressWarnings("unchecked")
    public List<ItemBase> getAllItems() {
        Message message = jmsTemplate.sendAndReceive(JMSConfig.ALL_QUEUE, session -> session.createTextMessage("getAll"));
        if (message != null) {
            try {
                return objectMapper.readValue(message.getBody(String.class), List.class);
            } catch (JMSException | JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Item getItemById(UUID id) {
        Message message = jmsTemplate.sendAndReceive(JMSConfig.DETAILS_QUEUE,
                session -> session.createTextMessage(id.toString()));
        if (message != null) {
            try {
                return objectMapper.readValue(message.getBody(String.class), Item.class);
            } catch (JMSException | JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<ItemBase> searchItems(String name) {
        Message message = jmsTemplate.sendAndReceive(JMSConfig.SEARCH_QUEUE, session -> session.createTextMessage(name));
        if (message != null) {
            try {
                System.out.println(message.getBody(String.class));
                return objectMapper.readValue(message.getBody(String.class), List.class);
            } catch (JMSException | JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
