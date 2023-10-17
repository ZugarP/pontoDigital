package com.system.pontoDigital.hours;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;





@RestController
@RequestMapping("/hours")
public class HoursController {

    @Autowired
    private IHoursRepository hoursRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody HoursModel hoursModel, HttpServletRequest request){

        var idUser = request.getAttribute("idUser");
        hoursModel.setIdUser((Long)idUser);

        var currentDate = LocalDate.now();
        var currentHour = LocalTime.now();

        
                
        var hoursList = this.hoursRepository.findByIdUserAndDate((Long)idUser, (LocalDate)currentDate);

        System.out.println(hoursList);
        // verifica se tem algum dia preenchido
        if(hoursList == null){
            // verificando se o dia é o mesmo do de hoje
            hoursModel.setDate(currentDate);
            if(currentDate.equals( hoursModel.getDate())){
                // se o dia for hj
              hoursModel.setEntry(currentHour);
                
            }else{
                // se o dia nao for hj
                return ResponseEntity.status(400).body("So poder inserir dados sorbe o dia atual ");
            }
              
                var hour = this.hoursRepository.save(hoursModel);
                return ResponseEntity.status(200).body(hour);


        }else{
           return ResponseEntity.status(400).body("So poder inserir dados sorbe o dia atual ");
        }
            

    }


    @GetMapping("/")
    public List<HoursModel> list(HttpServletRequest request){
        var idUser = request.getAttribute("idUser");
        var hours = this.hoursRepository.findByIdUser((Long)idUser);
        return hours;
    }
    

}
