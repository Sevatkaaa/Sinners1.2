package com.sinners.service;

import com.sinners.repository.SinRepository;
import com.sinners.sin.SinModel;
import com.sinners.user.UserModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class SinService {

    @Resource
    private SinRepository sinRepository;

    public List<SinModel> getAllSins() {
        Iterable<SinModel> sins = sinRepository.findAll();
        List<SinModel> sinModels = new ArrayList<>();

        Iterator<SinModel> iterator = sins.iterator();
        while (iterator.hasNext()) {
            sinModels.add(iterator.next());
        }

        return sinModels;
    }

    public List<SinModel> getSinsByType(String type) {
        return sinRepository.findByTypeContaining(type);
    }

    public void addSin(UserModel user, String sinType, Integer sinWeight, String sinDescription) {
        SinModel sin = new SinModel(sinType, sinWeight, sinDescription, user);

        sinRepository.save(sin);
    }
}
