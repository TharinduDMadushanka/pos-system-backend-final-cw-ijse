package lk.ijse.pos.controller;

import lk.ijse.pos.entity.ItemCategory;
import lk.ijse.pos.service.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/item-categories")
public class ItemCategoryController {

    @Autowired
    private ItemCategoryService itemCategoryService;

    @PostMapping("/create")
    public ResponseEntity<ItemCategory> createCategory(@RequestParam("name") String name,
                                                       @RequestParam("image") MultipartFile imageFile) {
        try {
            ItemCategory createdCategory = itemCategoryService.create(name, imageFile);
            return ResponseEntity.ok(createdCategory);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ItemCategory> updateCategory(@PathVariable int id,
                                                       @RequestParam("name") String name,
                                                       @RequestParam(value = "image", required = false) MultipartFile imageFile) {
        try {
            ItemCategory updatedCategory = itemCategoryService.update(id, name, imageFile);
            return ResponseEntity.ok(updatedCategory);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        itemCategoryService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<ItemCategory>> getAllCategories() {
        return ResponseEntity.ok(itemCategoryService.getAllCategory());
    }
}
