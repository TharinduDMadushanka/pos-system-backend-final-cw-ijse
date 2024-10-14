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
    private ItemCategoryRepository itemCategoryRepository;

    // Endpoint to create a new item
//    @PostMapping("/create")
//    public ResponseEntity<Item> createItem(@RequestBody ItemDto itemDto) {
//        Item item = new Item();
//        item.setItemCode(itemDto.getItemCode());
//        item.setItemName(itemDto.getItemName());
//        item.setDescription(itemDto.getDescription());
//        item.setQty(itemDto.getQty());
//        item.setUnitPrice(itemDto.getUnitPrice());
//
//        // Fetch the category based on category ID provided in DTO
//        ItemCategory itemCategory = itemCategoryRepository.findById(itemDto.getCategoryId());
//        if (itemCategory == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Handle not found category
//        }
//        item.setCategory(itemCategory); // Set the fetched category
//
//        Item createdItem = itemService.createItem(item); // Create the item
//        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
//    }

    // Endpoint to update an existing item
    @PutMapping("/update/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable int id, @RequestBody Item item) {
        Item updatedItem = itemService.updateItem(id, item);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    // Endpoint to delete an item by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable int id) {
        itemService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Endpoint to get all items
    @GetMapping("/all")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}
