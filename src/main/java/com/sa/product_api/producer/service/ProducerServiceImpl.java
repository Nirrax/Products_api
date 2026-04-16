package com.sa.product_api.producer.service;

import com.sa.product_api.producer.dto.ProducerDTO;
import com.sa.product_api.producer.exception.ProducerNotFoundException;
import com.sa.product_api.producer.model.Producer;
import com.sa.product_api.producer.repository.ProducerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducerServiceImpl implements ProducerService {
    private final ProducerRepository producerRepository;

    public ProducerServiceImpl(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Override
    public List<Producer> getProducers() {
        return this.producerRepository.findAll();
    }

    @Override
    public Producer getProducerById(Long id) {
        return this.producerRepository.findById(id).orElseThrow(() -> new ProducerNotFoundException("Producer not found"));
    }

    @Override
    public void deleteProducerById(Long id) {
        this.producerRepository.findById(id).ifPresent(this.producerRepository::delete);
    }

    @Override
    public Producer createProducer(ProducerDTO producerDTO) {
        return this.producerRepository.save(new Producer(producerDTO.name()));
    }
}
