package exemple.consulfeignclient;

import exemple.consulfeignclient.entities.Client;
import exemple.consulfeignclient.entities.Voiture;
import exemple.consulfeignclient.repository.VoitureRepository;
import exemple.consulfeignclient.service.ClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableFeignClients
public class ConsulFeignClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulFeignClientApplication.class, args);
    }

    @Bean
    CommandLineRunner initializerBaseH2(VoitureRepository voitureRepository, ClientService clientService) {
        return args -> {
            Client c1 = clientService.getClient(1);
            Client c2 = clientService.getClient(2);

            System.out.println("****************");
            System.out.println("Client 1 - Id: " + c1.getId() + ", Nom: " + c1.getNom());
            System.out.println("Client 2 - Id: " + c2.getId() + ", Nom: " + c2.getNom());
            System.out.println("****************");

            voitureRepository.save(new Voiture(null, "BMW", "A55000", "Serie 7", c1.getId().intValue(), null));
            voitureRepository.save(new Voiture(null, "Porsche", "C63456", "Macan", c2.getId().intValue(), null));
            voitureRepository.save(new Voiture(null, "Audi", "B54444", "RS6", c1.getId().intValue(), null));

            voitureRepository.findAll().forEach(voiture -> {
                voiture.setClient(clientService.getClient(voiture.getId_client()));
                System.out.println("Voiture: " + voiture.getMarque() + ", Client: " + voiture.getClient().getNom());
            });
        };
    }

}

