package com.example.shoppinglist.controllers;

import com.example.shoppinglist.persist.ShoppingItem;
import com.example.shoppinglist.persist.ShoppingItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ShoppingController {

    private final ShoppingItemRepository repository;

    @Autowired
    public ShoppingController(ShoppingItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("items", repository.findAll());
        model.addAttribute("item", new ShoppingItem());
        return "index";
    }

    @PostMapping("/")
    public String newShoppingItem(ShoppingItem item) {
        repository.save(item);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteShoppingItem(@PathVariable("id") long id) {
        repository.deleteById(id);
        return "redirect:/";
    }
}
