package exemple.consulfeignclient.service;

import exemple.consulfeignclient.entities.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ClientService")
public interface ClientService {
    @GetMapping(path="/client/{id}")
    public Client getClient(@PathVariable("id") int id);
}
