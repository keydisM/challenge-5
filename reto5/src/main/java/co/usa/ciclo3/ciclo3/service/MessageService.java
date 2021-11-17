package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Message;
import co.usa.ciclo3.ciclo3.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    //Metodo GET
    public List<Message> getAll(){return messageRepository.getAll();}
    //Metodo GET por ID
    public Optional<Message> getMessage(int id){
        return messageRepository.getMessage(id);
    }
    //Metodo POST
    public Message save(Message m){
        if (m.getIdMessage()==null){
            return messageRepository.save(m);
        }else{
            Optional<Message> meaux=messageRepository.getMessage(m.getIdMessage());
            if (meaux.isEmpty()){
                return messageRepository.save(m);
            }else {
                return m;
            }
        }
    }
}
