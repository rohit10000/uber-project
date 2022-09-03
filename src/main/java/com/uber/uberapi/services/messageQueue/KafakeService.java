package com.uber.uberapi.services.messageQueue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.springframework.stereotype.Service;

@Service
public class KafakeService implements MessageQueue {

    // fake in-memory kafka
    // not thread safe

    private final Map<String, Queue<MQMessage>> topics = new HashMap<>();


    @Override
    public MQMessage consumeMessage(String topic) {
        MQMessage message = topics.getOrDefault(topic, new LinkedList<>()).poll();
        System.out.printf("Kafake consuming from %s: %s", topic, message.toString());

        return message;
    }

    @Override
    public void sendMessage(String topic, MQMessage message) {
        
        System.out.printf("Kafake: appended to %s: %s", topic, message.toString());
        topics.putIfAbsent(topic, new LinkedList<>());
        topics.get(topic).add(message);
        
    }

}