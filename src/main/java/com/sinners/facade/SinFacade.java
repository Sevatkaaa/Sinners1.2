package com.sinners.facade;

import com.sinners.converter.SinConverter;
import com.sinners.service.SinService;
import com.sinners.sin.SinData;
import com.sinners.sin.SinModel;
import com.sinners.user.UserModel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SinFacade {

    @Resource
    private SinService sinService;

    @Resource
    private SinConverter sinConverter;

    public List<SinData> getSins(String filter) {
        List<SinModel> sins;
        if (filter == null || filter.isEmpty()) {
            sins = sinService.getAllSins();
        } else {
            sins = sinService.getSinsByType(filter);
        }
        return sinConverter.convert(sins);
    }

    public void addSin(UserModel user, String sinType, Integer sinWeight, String sinDescription) {
        sinService.addSin(user, sinType, sinWeight, sinDescription);
    }
}
