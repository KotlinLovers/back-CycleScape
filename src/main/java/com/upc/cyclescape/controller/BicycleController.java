package com.upc.cyclescape.controller;

import com.upc.cyclescape.dto.BicycleDto;
import com.upc.cyclescape.dto.BicycleDtoResponse;
import com.upc.cyclescape.dto.UserDtoResponse;
import com.upc.cyclescape.model.Bicycle;
import com.upc.cyclescape.model.User;
import com.upc.cyclescape.service.BicycleService;
import com.upc.cyclescape.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/cyclescape/v1/bicycles")
public class BicycleController {
    @Autowired
    private UserService userService;

    private final BicycleService bicycleService;

    public BicycleController(BicycleService bicycleService) {
        this.bicycleService = bicycleService;
    }

    // URL: http://localhost:8080/api/cyclescape/v1/bicycles
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<BicycleDtoResponse>> getAllBicycles() {
        //print somethign
        List<Bicycle> bicycles = bicycleService.getAllBicycles();
        System.out.println("getAllBicycles");
        return new ResponseEntity<List<BicycleDtoResponse>>(bicycles.stream()
                .map(this::convertToDtoResponse)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/cyclescape/v1/bicycles/{bicycleId}
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/{bicycleId}")
    public ResponseEntity<BicycleDtoResponse> getBicycleById(@PathVariable(name = "bicycleId") Long bicycleId) {
        Bicycle bicycle = bicycleService.getBicycleById(bicycleId);
        return new ResponseEntity<>(convertToDtoResponse(bicycle), HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/cyclescape/v1/bicycles/available
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/available")
    public ResponseEntity<List<BicycleDto>> getAllAvailableBicycles(
            @RequestParam(name = "start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start_date,
            @RequestParam(name = "end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end_date
    ) {
        return new ResponseEntity<>(bicycleService.getAllAvailableBicycles(start_date, end_date).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }


    // URL: http://localhost:8080/api/cyclescapey/v1/bicycles/{userId}
    // Method: POST
    @Transactional
    @PostMapping("/{userId}")
    public ResponseEntity<Bicycle> createBicycleWithUserId(@PathVariable(name = "userId") Long userId, @RequestBody Bicycle bicycle) {
        return new ResponseEntity<Bicycle>(bicycleService.createBicycle(userId, bicycle), HttpStatus.CREATED);
    }

    // URL: http://localhost:8080/api/cyclescape/v1/bicycles/{bicycleId}
    // Method: PUT
    @Transactional
    @PutMapping("/{bicycleId}")
    public ResponseEntity<BicycleDto> updateBicycleByBicycleId(@PathVariable(name = "bicycleId") Long bicycleId, @RequestBody Bicycle bicycle) {

        return new ResponseEntity<BicycleDto>(convertToDto(bicycleService.updateBicycle(bicycleId, bicycle)), HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/cyclescape/v1/bicycles/{bicycleId}
    // Method: DELETE
    @Transactional
    @DeleteMapping("/{bicycleId}")
    public ResponseEntity<String> deleteBicycleByBicycleId(@PathVariable(name = "bicycleId") Long bicycleId) {
        bicycleService.deleteBicycle(bicycleId);
        return new ResponseEntity<String>("Bicicleta eliminada correctamente", HttpStatus.OK);
    }
    private BicycleDto convertToDto(Bicycle bicycle) {
        return BicycleDto.builder()
                .id((bicycle.getId()))
                .bicycleName(bicycle.getBicycleName())
                .bicycleDescription(bicycle.getBicycleDescription())
                .bicyclePrice(bicycle.getBicyclePrice())
                .bicycleSize(bicycle.getBicycleSize())
                .bicycleModel(bicycle.getBicycleModel())
                .imageData(bicycle.getImageData())
                .latitude(bicycle.getLatitudeData())
                .longitude(bicycle.getLongitudeData())
                .user(bicycle.getUser())
                .build();
    }

    private BicycleDtoResponse convertToDtoResponse(Bicycle bicycle) {
        return BicycleDtoResponse.builder()
                .id(bicycle.getId())
                .bicycleName(bicycle.getBicycleName())
                .bicycleDescription(bicycle.getBicycleDescription())
                .bicyclePrice(bicycle.getBicyclePrice())
                .bicycleSize(bicycle.getBicycleSize())
                .bicycleModel(bicycle.getBicycleModel())
                .imageData(bicycle.getImageData())
                .latitude(bicycle.getLatitudeData())
                .longitude(bicycle.getLongitudeData())
                .userDtoResponse(convertToDtoUser(bicycle.getUser()))
                .build();
    }
    private UserDtoResponse convertToDtoUser(User user) {
        return UserDtoResponse.builder()
                .id(user.getId())
                .userFirstName(user.getUserFirstName())
                .userLastName(user.getUserLastName())
                .userEmail(user.getUserEmail())
                .userPhone(user.getUserPhone())
                .userBirthDate(user.getUserBirthDate())
                .imageData(user.getImageData())
                .longitudeData(user.getLongitudeData())
                .latitudeData(user.getLatitudeData())
                .build();
    }
}