package com.example.testSpring.controller;

import com.example.testSpring.model.Il;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/il")

public class Controller {

    public static List<Il> iller;
    public Controller() {
        Il il1 = new Il("36", "Qebele");
        Il il2 = new Il("90", "Baki");

         iller = Arrays.asList(il1, il2);
    }



    @GetMapping
    public ResponseEntity<List<Il>> getIller() {


        return new ResponseEntity<>(iller,HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Il> getIl(@PathVariable String id){
Il result = iller.stream().filter(il->il.getId().equals(id)).findFirst().orElseThrow(()-> new RuntimeException());
return new ResponseEntity<>(result,HttpStatus.OK);
    }

}
