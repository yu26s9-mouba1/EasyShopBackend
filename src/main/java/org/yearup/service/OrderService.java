package org.yearup.service;
import org.yearup.models.*;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import org.yearup.models.Order;
import org.yearup.models.OrderLineItem;
import org.yearup.models.Profile;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.springframework.stereotype.Service;
import org.yearup.repository.OrderLineItemRepository;
import org.yearup.repository.OrderRepository;


@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderLineItemRepository orderLineItemRepository;
    private final ShoppingCartService shoppingCartService;
    private final ProfileService profileService;
    private final ProductService productService;


    //Constructor
    public OrderService(OrderRepository orderRepository,
                        OrderLineItemRepository orderLineItemRepository,
                        ShoppingCartService shoppingCartService,
                        ProfileService profileService,
                        ProductService productService) {
        this.orderRepository = orderRepository;
        this.orderLineItemRepository = orderLineItemRepository;
        this.shoppingCartService = shoppingCartService;
        this.profileService = profileService;
        this.productService = productService;
    }


    // Checkout Method
    public Order checkout(int  userId) {

        //Get Cart
        ShoppingCart cart = shoppingCartService.getByUserId(userId);

        //Get profile
        Profile profile = profileService.getUserProfile(userId);

        //Now, Create Order
        Order order = new Order();
        order.setUserId(userId);
        order.setDate(LocalDateTime.now());
        order.setAddress(profile.getAddress());
        order.setCity(profile.getCity());
        order.setState(profile.getState());
        order.setZip(profile.getZip());
        order.setShippingAmount(BigDecimal.ZERO);

        order = orderRepository.save(order);

        //Convert cart items
        for (ShoppingCartItem item : cart.getItems().values()) {

            OrderLineItem orderLineItem = new OrderLineItem();

            orderLineItem.setOrderId(order.getOrderId());
            orderLineItem.setProductId(item.getProductId());
            orderLineItem.setQuantity(item.getQuantity());
            orderLineItem.setSalesPrice(BigDecimal.valueOf(item.getProduct().getPrice()));
            orderLineItem.setDiscount(BigDecimal.ZERO);

            //Save order
            orderLineItemRepository.save(orderLineItem);
        }

        //Clear cart
        shoppingCartService.clearCart(userId);

        //Returns order
        return order;

    }
}
