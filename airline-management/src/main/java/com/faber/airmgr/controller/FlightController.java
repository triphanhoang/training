package com.faber.airmgr.controller;

import com.faber.airmgr.infrastructure.webservice.BaseController;
import com.faber.airmgr.models.FlightFilterModel;
import com.faber.airmgr.services.AirPortService;
import com.faber.airmgr.services.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class FlightController extends BaseController {

    private final AirPortService airPortService;
    private final FlightService flightService;

    public FlightController(AirPortService airPortService, FlightService flightService) {
        this.airPortService = airPortService;
        this.flightService = flightService;
    }

    private Map baseViewModel(FlightFilterModel filter) {
        return new HashMap<String, Object>() {
            {
                put("results", null);
                put("ports", airPortService.findAll());
                put("filter", filter == null ? new FlightFilterModel() : filter);
            }
        };
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView filter() {
        return new ModelAndView("flight/index", baseViewModel(null));
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.POST)
    public ModelAndView doFilter(@ModelAttribute FlightFilterModel model) {
        Map data = this.baseViewModel(model);
        data.put("results", flightService.filter(model));
        return new ModelAndView("flight/index", data);
    }
}
