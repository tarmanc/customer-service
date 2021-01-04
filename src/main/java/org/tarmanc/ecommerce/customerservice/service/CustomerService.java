package org.tarmanc.ecommerce.customerservice.service;


import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.tarmanc.ecommerce.customerservice.config.JMSConfig;
import org.tarmanc.ecommerce.customerservice.items.Item;
import org.tarmanc.ecommerce.customerservice.items.ItemBase;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final JmsTemplate jmsTemplate;

    @SuppressWarnings("unchecked")
    public List<ItemBase> getAllItems() {

        Message message = jmsTemplate.sendAndReceive(JMSConfig.ALL_QUEUE, session -> session.createTextMessage(""));
        if (message != null) {
            try {
                System.out.println(message.getBody(String.class));
                return message.getBody(List.class);
            } catch (JMSException e) {
                e.printStackTrace();

            }
        }
        return null;
    }

    public Item getItemById(long id) {

        Message message = jmsTemplate.sendAndReceive(JMSConfig.DETAILS_QUEUE,
                session -> session.createTextMessage(String.valueOf(id)));

        if (message != null) {
            try {
                System.out.println(message.getBody(String.class));
                return message.getBody(Item.class);
            } catch (JMSException e) {
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
                return message.getBody(List.class);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
