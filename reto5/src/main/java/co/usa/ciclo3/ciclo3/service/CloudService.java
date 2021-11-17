package co.usa.ciclo3.ciclo3.service;


import co.usa.ciclo3.ciclo3.model.Client;
import co.usa.ciclo3.ciclo3.model.Cloud;
import co.usa.ciclo3.ciclo3.repository.CloudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CloudService {

    @Autowired
    private CloudRepository cloudRepository;

    //Metodo GET
    public List<Cloud> getAll(){
        return cloudRepository.getAll();
    }
    //Metodo GET por ID
    public Optional<Cloud> getCloud(int id){
        return cloudRepository.getCloud(id);
    }
    //Metodo POST
    public Cloud save(Cloud c){
        if (c.getId()==null){
            return cloudRepository.save(c);
        }else{
            Optional<Cloud> caux=cloudRepository.getCloud(c.getId());
            if (caux.isEmpty()){
                return cloudRepository.save(c);
            }else{
                return c;
            }
        }
    }
    //Metodo PUT
    public Cloud update(Cloud cloud){
        if (cloud.getId()!=null){
            Optional<Cloud>c=cloudRepository.getCloud(cloud.getId());
            if (!c.isEmpty()){
                if (cloud.getName()!=null){
                    c.get().setName(cloud.getName());
                }
                if (cloud.getBrand()!=null){
                    c.get().setBrand(cloud.getBrand());
                }
                if (cloud.getName()!=null){
                    c.get().setYear(cloud.getYear());
                }
                if (cloud.getDescription()!=null){
                    c.get().setDescription(cloud.getDescription());
                }
                return cloudRepository.save(c.get());
            }
        }
        return cloud;
    }
    //Metodo DELETE
    public boolean deleteCloud(int id){
        Optional<Cloud> c=getCloud(id);
        if (!c.isEmpty()){
            cloudRepository.delete(c.get());
            return true;
        }
        return false;
    }
}
