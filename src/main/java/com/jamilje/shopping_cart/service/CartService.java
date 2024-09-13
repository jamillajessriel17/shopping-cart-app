package com.jamilje.shopping_cart.service;

import com.jamilje.shopping_cart.entity.Cart;
import com.jamilje.shopping_cart.entity.CartItem;
import com.jamilje.shopping_cart.entity.Item;
import com.jamilje.shopping_cart.exception.InsufficientStockException;
import com.jamilje.shopping_cart.exception.NotFoundException;
import com.jamilje.shopping_cart.mapper.CartItemBuilder;
import com.jamilje.shopping_cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ItemService itemService;

    public Cart findCartById(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new NotFoundException("Cart not found"));
    }

    public Cart addItemToCart(Long cartId, Long itemId, Integer quantity) {
        Cart cart = findCartById(cartId);
        Item item = itemService.findById(itemId);
        List<CartItem> cartItemList = cart.getCartItemList();

        CartItem existingCartItem = getCartItemByItem(item, cartItemList);

        if (existingCartItem == null) {
            CartItem cartItem = CartItemBuilder.buildCartItem(cart, item, quantity);
            throwExceptionIfInsufficientStock(item, cartItem);
            cartItemList.add(cartItem);
        } else {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
            throwExceptionIfInsufficientStock(item, existingCartItem);
            existingCartItem.setTotalPrice();
        }

        cart.setCartItemList(cartItemList);
        cartRepository.save(cart);
        return cart;
    }

    private void throwExceptionIfInsufficientStock(Item item, CartItem existingCartItem) {
        if (!(existingCartItem.getQuantity() <= item.getStockQuantity())) {
            throw new InsufficientStockException();
        }
    }

    @Transactional
    public Cart deleteItemFromCart(Long cartId, Long itemId) {
        Cart cart = findCartById(cartId);
        List<CartItem> cartItemList = cart.getCartItemList();
        CartItem cartItemToBeDeleted = findCartItemByItemId(cartItemList, itemId);

        cartItemList.remove(cartItemToBeDeleted);
        cart.setCartItemList(cartItemList);

        return cartRepository.save(cart);
    }

    @Transactional
    public Cart deleteAllItemsFromCart(Long cartId) {
        Cart cart = findCartById(cartId);
        cart.getCartItemList().clear();
        return cartRepository.save(cart);
    }

    private CartItem findCartItemByItemId(List<CartItem> cartItemSet, Long itemId) {
        for (CartItem cartItem : cartItemSet) {
            if (Objects.equals(cartItem.getItem().getItemId(), itemId)) {
                return cartItem;
            }
        }
        throw new NotFoundException("Unable to find item in cart.");
    }

    private CartItem getCartItemByItem(Item item, List<CartItem> cartItemList) {
        return cartItemList.stream()
                .filter(cartItem -> Objects.equals(cartItem.getItem().getItemId(), item.getItemId()))
                .findFirst()
                .orElse(null);
    }
}
