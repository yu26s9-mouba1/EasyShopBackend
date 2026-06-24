package org.yearup.service;
import org.yearup.models.Order;
import java.security.Principal;

import org.springframework.stereotype.Service;
import org.yearup.repository.OrderLineItemRepository;
import org.yearup.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderLineItemRepository orderLineItemRepository;
    private final ShoppingCartService shoppingCartService;
    private final ProfileService profileService;


    //Constructor
    public OrderService( OrderRepository orderRepository,
                         OrderLineItemRepository orderLineItemRepository,
                         ShoppingCartService shoppingCartService,
                         ProfileService profileService) {
        this.orderRepository = orderRepository;
        this.orderLineItemRepository = orderLineItemRepository;
        this.shoppingCartService = shoppingCartService;
        this.profileService = profileService;
    }

   // Checkout Method
    public Order checkout(int  userId) {
        return null;

    }
}
