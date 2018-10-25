package test.playtech.PlayerDepositApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import test.playtech.PlayerDepositApp.repository.DepositRepository;
import test.playtech.PlayerDepositApp.services.KafkaProducerService;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("test.playtech")
public class PlayerDepositAppApplication {

	@Autowired
	DepositRepository repository;

	@Autowired
	private KafkaProducerService kafkaProducerService;

	public static void main(String[] args) {
		SpringApplication.run(PlayerDepositAppApplication.class, args);
	}
}
