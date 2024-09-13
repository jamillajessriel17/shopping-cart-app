package com.jamilje.shopping_cart.mapper;

import com.jamilje.shopping_cart.entity.Cart;
import com.jamilje.shopping_cart.entity.CartItem;
import com.jamilje.shopping_cart.entity.Item;

public class CartItemBuilder {
    public static CartItem buildCartItem(Cart cart, Item item, Integer quantity) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setItem(item);
        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice();
        return cartItem;
    }
}
