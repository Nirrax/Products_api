package com.sa.product_api.producer.service;

import com.sa.product_api.producer.dto.ProducerDTO;
import com.sa.product_api.producer.dto.ProducerResponse;
import com.sa.product_api.producer.exception.ProducerNotFoundException;
import com.sa.product_api.producer.model.Producer;
import com.sa.product_api.producer.repository.ProducerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProducerServiceImpl implements ProducerService {
    private final ProducerRepository producerRepository;

    public ProducerServiceImpl(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Override
    public List<ProducerResponse> getProducers() {
        return this.producerRepository.findAll()
                .stream()
                .map(ProducerResponse::from)
                .toList();
    }

    @Override
    public ProducerResponse getProducerById(Long id) {
        Producer producer =  this.producerRepository.findById(id)
                .orElseThrow(() -> new ProducerNotFoundException("Producer not found"));
        return ProducerResponse.from(producer);
    }

    @Override
    public void deleteProducerById(Long id) {
        this.producerRepository.findById(id).ifPresent(this.producerRepository::delete);
    }

    @Override
    public ProducerResponse createProducer(ProducerDTO producerDTO) {
        Producer producer = this.producerRepository.save(new Producer(producerDTO.name()));
        return ProducerResponse.from(producer);
    }

    @Override
    public ProducerResponse updateProducer(Long id, ProducerDTO producerDTO) {
        Producer producer = this.producerRepository.findById(id).orElseThrow(() -> new ProducerNotFoundException("Producer not found"));
        producer.setName(producerDTO.name());
        return ProducerResponse.from(this.producerRepository.save(producer));
    }
}
