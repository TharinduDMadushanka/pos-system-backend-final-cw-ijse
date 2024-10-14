package lk.ijse.pos.controller;

import lk.ijse.pos.dto.ItemDto;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.ItemCategory;
import lk.ijse.pos.repository.ItemCategoryRepository;
import lk.ijse.pos.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemCategoryRepository itemCategoryRepository; // To fetch categories

    // Endpoint to create a new item
    @PostMapping("/create")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Item newItem = itemService.createItem(item);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

    // Endpoint to update an existing item
    @PutMapping("/update/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item item) {
        Item updatedItem = itemService.updateItem(id, item);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    // Endpoint to delete an item by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Endpoint to get all items
    @GetMapping("/all")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    // Endpoint to get all categories for dropdown
    @GetMapping("/categories")
    public ResponseEntity<List<ItemCategory>> getAllCategories() {
        List<ItemCategory> categories = itemCategoryRepository.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}