package com.sa.product_api.producer.repository;

import com.sa.product_api.producer.model.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
}
