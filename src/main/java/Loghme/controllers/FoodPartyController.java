package Loghme.controllers;

import Loghme.entities.FoodPartyTimer;
import Loghme.entities.PartyFood;
import Loghme.services.FoodPartyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/party")
public class FoodPartyController {

//    @GetMapping
//    public ArrayList<PartyFood> getParty() throws SQLException {
//        return FoodPartyService.getFoodPartyFoods();
//    }

//    @GetMapping("/time")
//    public FoodPartyTimer getTime() {
//        return FoodPartyService.getFoodPartyTimer();
//    }
}