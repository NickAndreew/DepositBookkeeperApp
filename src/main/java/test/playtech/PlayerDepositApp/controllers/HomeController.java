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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import test.playtech.PlayerDepositApp.instances.DepositEvent;
import test.playtech.PlayerDepositApp.repository.DepositRepository;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    SimpMessagingTemplate template;

    @Autowired
    DepositRepository depositRepository;

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

    @MessageMapping("/makeDeposit")
    @SendTo("/topic/deposit.event")
    public String messageFromClient(@Payload String message) throws Exception {
        System.out.println("messageFromClient method, message: "+message);

        try {
            Gson gson = new Gson();
            DepositEvent depositEvent = gson.fromJson(message.toString(), DepositEvent.class);
            depositRepository.save(depositEvent);

            return Double.toString(depositEvent.getDeposit());
        } catch(JsonSyntaxException e) {
            return "JsonSyntaxException.. :( ";
        }
    }

    @MessageMapping("/getDepositsList")
    @SendTo("/topic/deposit.event")
    public String messageGetDeposits() throws Exception {
        System.out.println("Deposits List Request..");

        List<DepositEvent> list = depositRepository.findAll();

        return list.toString();
    }

    @RequestMapping(value="/getDeposits/{playerId}")
    private List<DepositEvent> getDepositListByPlayerId(@PathVariable("playerId") long playerId){
        List<DepositEvent> list = depositRepository.findByPlayerId(playerId);

        return list;
    }

    private double getDepositAggregation(){
        double total = 0;

        for (DepositEvent de : depositRepository.findAll()){
            total = total + de.getDeposit();
        }

        return total;
    }

    private double getDepositAggregationPerPlayer(long playerId){
        double total = 0;

        for(DepositEvent de : depositRepository.findByPlayerId(playerId) ){
            total = total + de.getDeposit();
        }

        return total;
    }

    public boolean isNumeric(String str) {
        try {
            @SuppressWarnings("unused")
            double d = Double.parseDouble(str);
        } catch(NumberFormatException nfe){
            return false;
        }

        return true;
    }

}
