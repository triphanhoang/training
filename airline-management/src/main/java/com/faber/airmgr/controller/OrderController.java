package com.faber.airmgr.controller;

import com.faber.airmgr.models.FlightOrderModel;
import com.faber.airmgr.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public ResponseEntity<String> doOrderNow(@RequestBody FlightOrderModel model) {
        try {
            orderService.doOrder(model);
            return ResponseEntity.ok("OK");
        } catch (RuntimeException ex) {
            return ResponseEntity.ok(ex.getMessage());
        }
    }
}
