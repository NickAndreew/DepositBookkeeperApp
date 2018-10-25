package test.playtech.PlayerDepositApp.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import test.playtech.PlayerDepositApp.instances.DepositEvent;
import test.playtech.PlayerDepositApp.instances.DepositsAggregation;
import test.playtech.PlayerDepositApp.db.repository.DepositRepository;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    SimpMessagingTemplate template;

    @Autowired
    DepositRepository depositRepository;

    private Gson gson = new Gson();

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

    @MessageMapping("/makeDeposit")
    @SendTo("/topic/deposit.event")
    public String messageFromClient(@Payload String message) throws Exception {
        System.out.println("messageFromClient method, message: "+message);

        try {
            DepositEvent depositEvent = gson.fromJson(message.toString(), DepositEvent.class);
            depositRepository.save(depositEvent);

            DepositsAggregation depositsAggregation = new DepositsAggregation();

            depositsAggregation.setType("new");
            depositsAggregation.setTotal(getTotal(depositRepository.findAll()));
            depositsAggregation.setDepositEvents(depositRepository.findAll());

            String json = gson.toJson(depositsAggregation);

            return json;

        } catch(JsonSyntaxException e) {
            return "JsonSyntaxException.. :( ";
        }
    }

    @MessageMapping("/getDepositsList")
    @SendTo("/topic/deposit.event")
    public String getDepositsList() throws Exception {
        DepositsAggregation depositsAggregation = getDepositAggregation();

        String json = gson.toJson(depositsAggregation);
        return json;
    }

    @MessageMapping("/getDeposits/player")
    @SendTo("/topic/deposit.event")
    private String getDepositListByPlayerId(@Payload String message){
        long playerId = 0;
        try {
            playerId = Long.parseLong(message);

        } catch (NumberFormatException e) {
            System.out.println("Error : " + e);
        }

        DepositsAggregation depositsAggregation = getDepositAggregationPerPlayer(playerId);

        String json = gson.toJson(depositsAggregation);

        return json;
    }

    private DepositsAggregation getDepositAggregation(){

        DepositsAggregation depositsAggregation = new DepositsAggregation();

        depositsAggregation.setType("all");
        depositsAggregation.setTotal(getTotal(depositRepository.findAll()));
        depositsAggregation.setDepositEvents(depositRepository.findAll());

        return depositsAggregation;
    }

    private DepositsAggregation getDepositAggregationPerPlayer(long playerId){


        DepositsAggregation depositsAggregation = new DepositsAggregation();

        depositsAggregation.setType("player");
        depositsAggregation.setTotal(getTotal(depositRepository.findByPlayerId(playerId)));
        depositsAggregation.setDepositEvents(depositRepository.findByPlayerId(playerId));

        return depositsAggregation;
    }

    public double getTotal(List<DepositEvent> list) {
        double total = 0;

        for (DepositEvent de : list){
            total = total + de.getDeposit();
        }

        return total;
    }
}
