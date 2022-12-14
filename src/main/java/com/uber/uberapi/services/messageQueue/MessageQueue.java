package com.uber.uberapi.services.messageQueue;

public interface MessageQueue {
    void sendMessage( String topic, MQMessage message);

    MQMessage consumeMessage(String topic);
}
