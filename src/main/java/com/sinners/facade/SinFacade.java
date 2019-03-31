package com.sinners.facade;

import com.sinners.converter.SinConverter;
import com.sinners.service.SinService;
import com.sinners.service.UserService;
import com.sinners.sin.SinData;
import com.sinners.sin.SinModel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SinFacade {

    @Resource
    private SinService sinService;

    @Resource
    private UserService userService;

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

    public void addSin(String username, String sinType, Integer sinWeight, String sinDescription) {
        sinService.addSin(userService.getUserByName(username), sinType, sinWeight, sinDescription);
    }

    public List<SinData> getSinsForUser(String username) {
        return sinConverter.convert(sinService.getSinsForUser(userService.getUserByName(username)));
    }
}
