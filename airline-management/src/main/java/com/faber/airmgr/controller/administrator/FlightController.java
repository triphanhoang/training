package com.faber.airmgr.controller.administrator;

import com.faber.airmgr.infrastructure.webservice.BaseController;
import com.faber.airmgr.models.AddFlightModel;
import com.faber.airmgr.models.FlightFilterModel;
import com.faber.airmgr.services.AirPortService;
import com.faber.airmgr.services.FlightService;
import org.omg.PortableServer.THREAD_POLICY_ID;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


@Controller("flightsManagement")
public class FlightController extends BaseController {

    private final FlightService flightService;
    private final AirPortService airPortService;

    public FlightController(FlightService flightService, AirPortService airPortService) {
        this.flightService = flightService;
        this.airPortService = airPortService;
    }

    @GetMapping("/admin/flights")
    public String index(Model model) {
        model.addAttribute("flights", this.flightService.findAll());
        return "admin/flight/index";
    }

    @GetMapping("/admin/flights/add")
    public ModelAndView add() {
        Map model = new HashMap<String, Object>() {{
            put("flight", new AddFlightModel());
            put("ports", airPortService.findAll());
            put("error", null);
        }};

        return new ModelAndView("admin/flight/add", model);
    }

    @PostMapping("/admin/flights/add")
    public ModelAndView doAdd(@ModelAttribute AddFlightModel model) {
        try {
            this.flightService.add(model);
            return new ModelAndView("redirect:/admin/flights");
        } catch (Exception ex) {
            Map data = new HashMap<String, Object>() {{
                put("flight", model);
                put("ports", airPortService.findAll());
                put("error", ex.getMessage());
            }};

            return new ModelAndView("admin/flight/add", data);
        }
    }
}
