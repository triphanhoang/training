package com.faber.airmgr.controller.administrator;

import com.faber.airmgr.infrastructure.webservice.BaseController;
import com.faber.airmgr.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("ordersManagement")
public class OrderController extends BaseController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/admin/orders")
    public String index(Model model) {
        model.addAttribute("orders", this.orderService.findAll());
        return "admin/order/index";
    }
}
