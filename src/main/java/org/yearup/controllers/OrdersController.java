package org.yearup.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.yearup.models.User;
import org.yearup.service.OrderService;
import org.yearup.service.UserService;
import org.yearup.models.Order;

import java.security.Principal;

@RestController
@RequestMapping("/orders")
@PreAuthorize("isAuthenticated()")
@CrossOrigin
public class OrdersController {

    private final OrderService orderService;
    private final UserService userService;

    //Contructor
    public OrdersController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }


    //CheckOut Method endpoint
    @PostMapping
    public ResponseEntity<Order> checkout(Principal principal) {
        String username = principal.getName();
        User user = userService.getByUserName(username);

        Order order = orderService.checkout(user.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
