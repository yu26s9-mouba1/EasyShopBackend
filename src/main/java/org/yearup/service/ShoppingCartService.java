package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.models.CartItem;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.repository.ShoppingCartRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

@Service
public class ShoppingCartService
{
    // a shopping cart is built from cart rows plus a product lookup for each row
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductService productService;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productService = productService;
    }

    public ShoppingCart getByUserId(int userId)
    {
        // load the user's cart rows, look up each product, and build the ShoppingCart
        List<CartItem> cartItems = shoppingCartRepository.findByUserId(userId);

        ShoppingCart cart = new ShoppingCart();

        for (CartItem item : cartItems) {
            Product product = productService.getById(item.getProductId());
           ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
           shoppingCartItem.setProduct(product);
           shoppingCartItem.setQuantity(item.getQuantity());

           cart.add(shoppingCartItem);
        }
        return cart;
    }

    //Adds product to the cart
    public ShoppingCart addProduct(int userId, int productId) {
        CartItem item = shoppingCartRepository.findByUserIdAndProductId(userId, productId);

        if (item == null) {
            item = new CartItem();
            item.setUserId(userId);
            item.setProductId(productId);
            item.setQuantity(1);
        } else {
            item.setQuantity(item.getQuantity() + 1);
        }
        shoppingCartRepository.save(item);
        return getByUserId(userId);
    }


    // Updates an existing product in the cart
    public ShoppingCart updateProductQuantity(int userId, int productId, int quantity)
    {
        CartItem item = shoppingCartRepository.findByUserIdAndProductId(userId, productId);

        if (item != null) {
            item.setQuantity(quantity);
            shoppingCartRepository.save(item);
        }
        return getByUserId(userId);
    }

    //Clears the cart
    public ShoppingCart clearCart(int userId) {
        shoppingCartRepository.deleteByUserId(userId);
        return getByUserId(userId);

    }
}


