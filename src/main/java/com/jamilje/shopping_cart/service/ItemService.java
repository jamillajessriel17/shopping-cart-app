package com.jamilje.shopping_cart.service;

import com.jamilje.shopping_cart.entity.Item;
import com.jamilje.shopping_cart.exception.NotFoundException;
import com.jamilje.shopping_cart.exception.UnableToSaveException;
import com.jamilje.shopping_cart.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public Item findById(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(() -> new NotFoundException("Item not found."));
    }

    public Item saveItem(Item item) {
        try {
            return itemRepository.save(item);
        } catch (Exception e) {
            throw new UnableToSaveException();
        }
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}
