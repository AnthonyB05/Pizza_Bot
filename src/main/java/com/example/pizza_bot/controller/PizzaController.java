package com.example.pizza_bot.controller;

import com.example.pizza_bot.DTO.Request.QueryRequestDTO;
import com.example.pizza_bot.DTO.Response.WebhookReponseDTO;
import com.example.pizza_bot.Service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/pizza")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/hello")
    public ResponseEntity<String> getHello() {
        return ResponseEntity.ok("hello");
    }

    @PostMapping("/request")
    public ResponseEntity<WebhookReponseDTO> postRequest(@RequestBody QueryRequestDTO queryRequestDTO) {

        //logger.info("queryRequestDTO : " + queryRequestDTO);
        // Check if queryRequest is correct
        if(queryRequestDTO.getQueryResult()==null || queryRequestDTO.getQueryResult().getQueryText()==null || queryRequestDTO.getQueryResult().getQueryText().equals("") || queryRequestDTO.getQueryResult().getParameters()==null ) {
            WebhookReponseDTO webhookReponseDTO = pizzaService.getbase(true);
            return ResponseEntity.ok(webhookReponseDTO);
        }

        return ResponseEntity.ok(pizzaService.getPizza(queryRequestDTO));
    }
}
