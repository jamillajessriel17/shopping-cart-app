package com.jamilje.shopping_cart.controller;

import com.jamilje.shopping_cart.entity.Item;
import com.jamilje.shopping_cart.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/save")
    public ResponseEntity<String> saveItem(@RequestBody Item item) {
        Item savedItem = itemService.saveItem(item);
        String message = String.format("%s was saved.", savedItem.getName());
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    @GetMapping("")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> savedItem = itemService.getAllItems();
        return new ResponseEntity<>(savedItem, HttpStatus.OK);
    }
}
