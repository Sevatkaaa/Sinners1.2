package com.sinners.repository;

import com.sinners.sin.SinModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SinRepository extends CrudRepository<SinModel, Long> {
    List<SinModel> findByType(String type);
}

