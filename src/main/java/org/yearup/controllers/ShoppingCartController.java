package org.yearup.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.models.CartItem;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.models.User;
import org.yearup.repository.ShoppingCartRepository;
import org.yearup.service.ShoppingCartService;
import org.yearup.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/cart")
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class ShoppingCartController {

    private final ShoppingCartRepository shoppingCartRepository;
    // a shopping cart controller depends on the service layer
    private ShoppingCartService shoppingCartService;
    private UserService userService;


    //Constructor Injection
    public ShoppingCartController(ShoppingCartService shoppingCartService, UserService userService, ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.shoppingCartRepository = shoppingCartRepository;
    }



    @GetMapping
    public ShoppingCart getCart(Principal principal) {

        // get the currently logged in username
        String userName = principal.getName();
        // find database user by username
        User user = userService.getByUserName(userName);
        int userId = user.getId();

        // get all items in the cart and return the cart
        return shoppingCartService.getByUserId(userId);
    }

    // Adds a product to the cart
    @PostMapping("/products/{productId}")
    public ResponseEntity<ShoppingCart> addProductToCart(@PathVariable int productId, Principal principal) {
        String userName = principal.getName();
        User user = userService.getByUserName(userName);
        int userId = user.getId();

        ShoppingCart cart = shoppingCartService.addProduct(userId, productId);

        // Returns the updated cart with status 201 Created
        return ResponseEntity.ok(cart);
    }



    // Updates an existing product in the cart
    @PutMapping("/products/{productId}")
    public ResponseEntity<ShoppingCart> updateProductToCart(@PathVariable int productId, @RequestBody ShoppingCartItem item, Principal principal) {
        String userName = principal.getName();
        User user = userService.getByUserName(userName);
        int userId = user.getId();

        ShoppingCart cart = shoppingCartService.updateProductQuantity(userId, productId, item.getQuantity());

        //return the cart (200 OK)
        return ResponseEntity.ok(cart);
    }
    


    // Clears all products from the current users cart
    @DeleteMapping
    public ResponseEntity<ShoppingCart> clearCart(Principal principal) {
        String userName = principal.getName();
        User user = userService.getByUserName(userName);
        int userId = user.getId();

        ShoppingCart cart = shoppingCartService.clearCart(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(cart);
    }

}
