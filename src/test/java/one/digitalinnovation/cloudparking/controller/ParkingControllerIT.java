package one.digitalinnovation.cloudparking.controller;

import io.restassured.RestAssured;
import one.digitalinnovation.cloudparking.dto.ParkingCreateDTO;
import one.digitalinnovation.cloudparking.mapper.ParkingMapper;
import one.digitalinnovation.cloudparking.repository.ParkingRepository;
import one.digitalinnovation.cloudparking.service.ParkingService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.parameters.P;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerIT {

    @LocalServerPort
    private int randomPort;
    @Autowired ParkingRepository parkingRepository;
    @Autowired ParkingMapper parkingMapper;

    @BeforeEach
    public void setupTest(){
        RestAssured.port = randomPort;

    }

    @Test
    void whenFindAllThenCheckResult() {
        ParkingCreateDTO createDTO = new ParkingCreateDTO();
        createDTO.setColor("AMARELO");
        createDTO.setLicense("dms-1111");
        createDTO.setModel("Brasilia");
        createDTO.setState("SP");

        ParkingService parkingService = new ParkingService(parkingMapper, parkingRepository);
        ParkingController parkingController = new ParkingController(parkingService, parkingMapper);
        parkingController.create(createDTO);

        RestAssured.given()
                .auth()
                .basic("user","123")
                .when()
                .get("/api/parking/v1")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("license[0]", Matchers.equalTo("dms-1111"));
//                .extract().response().body().prettyPrint();
    }

    @Test
    void create() {
        ParkingCreateDTO createDTO = new ParkingCreateDTO();
        createDTO.setColor("AMARELO");
        createDTO.setLicense("hrw-2222");
        createDTO.setModel("Brasilia");
        createDTO.setState("SP");

        RestAssured.given()
                .auth()
                .basic("user","123")
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createDTO)
                .post("/api/parking/v1/create")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("license", Matchers.equalTo("hrw-2222"))
                .body("color", Matchers.equalTo("AMARELO"));
    }
}