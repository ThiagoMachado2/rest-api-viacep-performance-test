package com.example.viaceptest.controller;

import com.example.viaceptest.model.ViaCepResponse;
import com.example.viaceptest.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/cep")
public class CepController {

    @Autowired
    private ViaCepService viaCepService;

    @GetMapping("/{cep}")
    public Mono<ResponseEntity<ViaCepResponse>> getCepInfo(@PathVariable String cep) {
        return viaCepService.getCepInfo(cep)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

}



