package com.sinners.controller;

import com.sinners.facade.SinFacade;
import com.sinners.sin.SinData;
import com.sinners.user.UserData;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SinController extends BaseController {

    @Resource
    private SinFacade sinFacade;

    @RequestMapping(value = "/sins", method = RequestMethod.GET)
    public List<SinData> getSins(@RequestParam(required = false) String filter) {
        return sinFacade.getSins(filter);
    }

    @RequestMapping(value = "/sins", method = RequestMethod.POST)
    public void addSin(@AuthenticationPrincipal UserData user,
                       @RequestParam String sinType,
                       @RequestParam Integer sinWeight,
                       @RequestParam String sinDescription) {
        sinFacade.addSin(user.getUsername(), sinType, sinWeight, sinDescription);
    }

    @RequestMapping(value = "sins/current", method = RequestMethod.GET)
    public List<SinData> getCurrentUserSins(@AuthenticationPrincipal UserData user) {
        return sinFacade.getSinsForUser(user.getUsername());
    }

    @RequestMapping(value = "sins/user/{name}", method = RequestMethod.GET)
    public List<SinData> getSinsForUser(@PathVariable String name) {
        return sinFacade.getSinsForUser(name);
    }
}
