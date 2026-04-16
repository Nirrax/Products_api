package com.sa.product_api.producer.controller;

import com.sa.product_api.producer.dto.ProducerDTO;
import com.sa.product_api.producer.model.Producer;
import com.sa.product_api.producer.service.ProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/producers")
public class ProducerController {
    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping("")
    public ResponseEntity<List<Producer>> findAll() {
        return ResponseEntity.ok().body(this.producerService.getProducers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producer> findOne(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.producerService.getProducerById(id));
    }

    @PostMapping("")
    public ResponseEntity<Producer> create(@RequestBody ProducerDTO producerDTO) {
        return ResponseEntity.ok().body(this.producerService.createProducer(producerDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Producer> update(@PathVariable Long id, @RequestBody ProducerDTO producerDTO) {
        return ResponseEntity.ok().body(this.producerService.updateProducer(id, producerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        this.producerService.deleteProducerById(id);
        return ResponseEntity.noContent().build();
    }
}
