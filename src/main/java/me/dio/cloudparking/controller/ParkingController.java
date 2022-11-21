package me.dio.cloudparking.controller;

import me.dio.cloudparking.controller.dto.ParkingCreateDTO;
import me.dio.cloudparking.controller.dto.ParkingDTO;
import me.dio.cloudparking.controller.mapper.ParkingMapper;
import me.dio.cloudparking.model.Parking;
import me.dio.cloudparking.service.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {


    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping
    public ResponseEntity<List<ParkingDTO>> findAll (){
        List<Parking> parkingList = parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/")
    public String teste () {
        return "Quem eh o maior time do Rio?";
    }
    @GetMapping("/Vasco")
    public String vasco () {
        return "Nem a Pau. Chegou nem perto.";
    }
    @GetMapping("/Flamengo")
    public String flamengo () {
        return "Acerto em cheio. Maior do rio é o Flamengo.";
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDTO> findById (@PathVariable String id){
        Parking parking = parkingService.findById(id);
        ParkingDTO result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<ParkingDTO> create (@RequestBody ParkingCreateDTO dto){
        var parkingCreate = parkingMapper.toParkingCreate(dto);
        Parking parking = parkingService.create(parkingCreate);
        ParkingDTO result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
