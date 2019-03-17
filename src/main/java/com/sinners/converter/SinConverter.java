package com.sinners.converter;

import com.sinners.sin.SinData;
import com.sinners.sin.SinModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SinConverter {

    public SinData convert(SinModel sinModel) {
        SinData sinData = new SinData();

        sinData.setDescription(sinModel.getDescription());
        sinData.setType(sinModel.getType());
        sinData.setWeight(sinModel.getWeight());
        sinData.setAuthor(sinModel.getAuthorName());

        return sinData;
    }

    public List<SinData> convert(List<SinModel> sinModels) {
        List<SinData> sins = new ArrayList<>();

        sinModels.forEach(sin -> sins.add(convert(sin)));

        return sins;
    }

}
