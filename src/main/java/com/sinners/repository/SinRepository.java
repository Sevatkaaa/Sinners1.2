package com.sinners.repository;

import com.sinners.sin.SinModel;
import com.sinners.user.UserModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SinRepository extends CrudRepository<SinModel, Long> {
    List<SinModel> findByTypeContaining(String type);

    List<SinModel> findByAuthor(UserModel author);
}

