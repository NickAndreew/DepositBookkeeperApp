package test.playtech.PlayerDepositApp.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService{

    @Autowired
    SimpMessagingTemplate template;

    private Gson gson = new Gson();

    @KafkaListener(topics="${kafka.topic}")
    public void consume(@Payload String message) {
        if(isJson(message)) {
            template.convertAndSend("/topic/deposit.event", message);
        }
    }

    public boolean isJson(String str) {
        try {
            @SuppressWarnings("unused")

            String d = gson.toJson(str);
        } catch(Exception e) {
            return false;
        }

        return true;
    }
}