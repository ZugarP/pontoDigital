package com.system.pontoDigital.hours;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDate;



public interface IHoursRepository extends JpaRepository<HoursModel, Long>{

    List<HoursModel> findByIdUser(Long idUser);
    List<HoursModel> findByIdUserAndDate(Long idUser, LocalDate date);
    
}
