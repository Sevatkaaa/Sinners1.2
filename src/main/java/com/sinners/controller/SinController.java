package com.sinners.controller;

import com.sinners.facade.SinFacade;
import com.sinners.sin.SinData;
import com.sinners.user.UserModel;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SinController {

    @Resource
    private SinFacade sinFacade;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public List<SinData> getSins(@RequestParam(required = false) String filter) {
        return sinFacade.getSins(filter);
    }

    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public void addSin(@AuthenticationPrincipal UserModel user,
                       @RequestParam String sinType,
                       @RequestParam Integer sinWeight,
                       @RequestParam String sinDescription) {
        sinFacade.addSin(user, sinType, sinWeight, sinDescription);
    }
}
