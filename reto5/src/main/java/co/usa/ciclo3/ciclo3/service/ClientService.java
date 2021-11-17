package co.usa.ciclo3.ciclo3.service;


import co.usa.ciclo3.ciclo3.model.Category;
import co.usa.ciclo3.ciclo3.model.Client;
import co.usa.ciclo3.ciclo3.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll(){
        return clientRepository.getAll();
    }
    public Optional<Client> getClient(int id){
        return clientRepository.getClient(id);
    }
    public Client save(Client c){
        if (c.getIdClient()==null){
            return clientRepository.save(c);
        }else{
            Optional<Client> claux=clientRepository.getClient(c.getIdClient());
            if (claux.isEmpty()){
                return clientRepository.save(c);
            }else {
                return c;
            }
        }
    }

    //Metodo PUT
    public Client update(Client client){
        if (client.getIdClient()!=null){
            Optional<Client>cl=clientRepository.getClient(client.getIdClient());
            if (!cl.isEmpty()){
                if (client.getEmail()!=null){
                    cl.get().setEmail(client.getEmail());
                }
                if (client.getPassword()!=null){
                    cl.get().setPassword(client.getPassword());
                }
                if (client.getName()!=null){
                    cl.get().setName(client.getName());
                }
                if (client.getAge()!=null){
                    cl.get().setAge(client.getAge());
                }
                return clientRepository.save(cl.get());
            }

        }
        return client;
    }

    //Metodo DELETE
    public boolean deleteClient(int id){
        Optional<Client> cl=getClient(id);
        if (!cl.isEmpty()){
            clientRepository.delete(cl.get());
            return true;
        }
        return false;
    }
}
