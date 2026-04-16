package com.sa.product_api.producer.service;

import com.sa.product_api.producer.dto.ProducerDTO;
import com.sa.product_api.producer.dto.ProducerResponse;
import com.sa.product_api.producer.model.Producer;
import com.sa.product_api.producer.repository.ProducerRepository;

import java.util.List;

public interface ProducerService {
    List<ProducerResponse> getProducers();
    ProducerResponse getProducerById(Long id);
    void deleteProducerById(Long id);
    ProducerResponse createProducer(ProducerDTO producerDTO);
    ProducerResponse updateProducer(Long id, ProducerDTO producerDTO);

}
