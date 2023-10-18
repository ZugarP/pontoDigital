package com.system.pontoDigital.hours;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import jakarta.servlet.http.HttpServletRequest;





@RestController
@RequestMapping("/hours")
public class HoursController {

    @Autowired
    private IHoursRepository hoursRepository;

    @PostMapping("/entrada")
    public ResponseEntity createEntry(@RequestBody HoursModel hoursModel, HttpServletRequest request){

        var idUser = request.getAttribute("idUser");
        hoursModel.setIdUser((Long)idUser);

        var currentDate = LocalDate.now();
        var currentHour = LocalTime.now();

        
        // variavel para buscar no banco de dados o usuario e o dia atual
        var hoursList = this.hoursRepository.findByIdUserAndDate((Long)idUser, (LocalDate)currentDate);

        // verifica se tem algum dia preenchido
        if(hoursList.isEmpty()){        
            hoursModel.setDate(currentDate);
            // verificando se o dia é o mesmo do de hoje
            if(currentDate.equals( hoursModel.getDate())){
                hoursModel.setEntry(currentHour);             
            }else{
                return ResponseEntity.status(400).body("So poder inserir dados sorbe o dia atual ");
            }
              
            var hour = this.hoursRepository.save(hoursModel);
            return ResponseEntity.status(200).body(hour);

        }else{
            System.out.println("lista de horas not empty");
           return ResponseEntity.status(400).body("O dia atual ja está registrado");
        }
    }

    @PutMapping("/entradaAlmoco/{id}")
    public ResponseEntity createLunch(@RequestBody HoursModel hoursModel, HttpServletRequest request, @PathVariable Long id){

        var currentHour = LocalTime.now();
        var currentDay = LocalDate.now();
        
        var hoursById = this.hoursRepository.findById(id).orElse(hoursModel);
        var idUser = request.getAttribute("idUser");

        hoursById.setLunch(currentHour);

        if(currentDay.equals(hoursById.getDate())){
            System.out.println("dia igual");

            if(idUser.equals(hoursById.getIdUser())){
            System.out.println("voce tem permissao");
            
            }else{
                return ResponseEntity.status(400).body("Voce não tem permissao para alterar o dia dos outros");
            }
        }else{
            return ResponseEntity.status(400).body("Não é possivel alterar dias passados");
        }    

        var hoursUpdated = this.hoursRepository.save(hoursById);
        return ResponseEntity.ok().body(hoursUpdated);     

    }

    @PutMapping("/saidaAlmoco/{id}")
    public ResponseEntity createLunchReturn(@RequestBody HoursModel hoursModel, HttpServletRequest request, @PathVariable Long id){

        var currentHour = LocalTime.now();
        var currentDay = LocalDate.now();
        
        var hoursById = this.hoursRepository.findById(id).orElse(hoursModel);
        var idUser = request.getAttribute("idUser");

        hoursById.setLunchReturn(currentHour);

        if(currentDay.equals(hoursById.getDate())){
            System.out.println("dia igual");

            if(idUser.equals(hoursById.getIdUser())){
            System.out.println("voce tem permissao");
            
            }else{
                return ResponseEntity.status(400).body("Voce não tem permissao para alterar o dia dos outros");
            }
        }else{
            return ResponseEntity.status(400).body("Não é possivel alterar dias passados");
        }    

        var hoursUpdated = this.hoursRepository.save(hoursById);
        return ResponseEntity.ok().body(hoursUpdated);
        
    }

    @PutMapping("/saida/{id}")
    public ResponseEntity createExit(@RequestBody HoursModel hoursModel, HttpServletRequest request, @PathVariable Long id){

        var currentHour = LocalTime.now();
        var currentDay = LocalDate.now();
        
        var hoursById = this.hoursRepository.findById(id).orElse(hoursModel);
        var idUser = request.getAttribute("idUser");

        hoursById.setExit(currentHour);

        if(currentDay.equals(hoursById.getDate())){
            System.out.println("dia igual");

            if(idUser.equals(hoursById.getIdUser())){
            System.out.println("voce tem permissao");
            
            }else{
                return ResponseEntity.status(400).body("Voce não tem permissao para alterar o dia dos outros");
            }
        }else{
            return ResponseEntity.status(400).body("Não é possivel alterar dias passados");
        }    

        var hoursUpdated = this.hoursRepository.save(hoursById);
        return ResponseEntity.ok().body(hoursUpdated);
        

    }

    @GetMapping("/All")
    public List<HoursModel> listAll(HttpServletRequest request, @PathVariable String userType){

        if(userType.equals("admin")){
            var hours = this.hoursRepository.findAll();

            return hours;

        }else{
            return null;
        }


    }

    @GetMapping("/byUserId")
    public List<HoursModel> listByUserId(HttpServletRequest request){
        var idUser = request.getAttribute("idUser");
        var hours = this.hoursRepository.findByIdUser((Long)idUser);
        return hours;
    }
    @GetMapping("/byMonth")
    public List<HoursModel> listByMonth(HttpServletRequest request){
        var idUser = request.getAttribute("idUser");
        var hours = this.hoursRepository.findByIdUser((Long)idUser);

        var date = LocalDate.now().getMonthValue();
        var year = LocalDate.now().getYear();

        var dataFiltrada = hours.stream().filter(
            e -> e.getDate().getMonthValue() == date
            &&
            e.getDate().getYear() == year)
            .collect(Collectors.toList());


        return dataFiltrada;
    }
    
    

}
