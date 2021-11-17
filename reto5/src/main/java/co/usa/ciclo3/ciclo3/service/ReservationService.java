package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Cloud;
import co.usa.ciclo3.ciclo3.model.Reservation;
import co.usa.ciclo3.ciclo3.model.custom.CountClient;
import co.usa.ciclo3.ciclo3.model.custom.StatusAmount;
import co.usa.ciclo3.ciclo3.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    //Metodo GET
    public List<Reservation> getAll(){
        return reservationRepository.getAll();
    }
    //Metdo GET por ID
    public Optional<Reservation> getReservation(int id){
        return reservationRepository.getReservation(id);
    }
    //Metodo POST
    public Reservation save(Reservation r){
        if (r.getIdReservation()==null){
            return reservationRepository.save(r);
        }else{
            Optional<Reservation> reaux=reservationRepository.getReservation(r.getIdReservation());
            if (reaux.isEmpty()){
                return reservationRepository.save(r);
            }else {
                return r;
            }
        }
    }

    public List<CountClient> getTopClients(){
        return reservationRepository.getTopClients();
    }

    public StatusAmount getStatusReport(){
        List<Reservation> completed=reservationRepository.getReservationsByStatus("completed");
        List<Reservation> cancelled=reservationRepository.getReservationsByStatus("cancelled");

        StatusAmount statAmt=new StatusAmount(completed.size(),cancelled.size());
        return statAmt;
    }

    public List<Reservation> getReservationPeriod(String d1, String d2){
        //yyyy-MM-dd
        SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd");
        Date dateOne=new Date();
        Date dateTwo=new Date();

        try{
            dateOne=parser.parse(d1);
            dateTwo=parser.parse(d2);
        } catch (ParseException e){
            e.printStackTrace();
        }
        if(dateOne.before(dateTwo)){
            return reservationRepository.getReservationPeriod(dateOne,dateTwo);
        }else{
            return new ArrayList<>();
        }
    }
}
