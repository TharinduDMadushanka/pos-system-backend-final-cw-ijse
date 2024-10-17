package lk.ijse.pos.controller;

import lk.ijse.pos.dto.ItemReqDto;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.ItemCategory;
import lk.ijse.pos.service.ItemCategoryService;
import lk.ijse.pos.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ItemCategoryService itemCategoryService;

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return ResponseEntity.status(200).body(items);
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody ItemReqDto itemReqDto) {

//        System.out.println(itemReqDto.getCategoryId());

        Item item = new Item();
        item.setItemCode(itemReqDto.getItemCode());
        item.setItemName(itemReqDto.getItemName());
        item.setDescription(itemReqDto.getDescription());
        item.setQty(itemReqDto.getQty());
        item.setUnitPrice(itemReqDto.getUnitPrice());

        ItemCategory category = itemCategoryService.getCategoryById(itemReqDto.getCategoryId());
        item.setCategory(category);

//        System.out.println(category.getId());

        Item createdItem = itemService.createItem(item);

//        System.out.println(createdItem.toString());

        return ResponseEntity.status(201).body(createdItem);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable int id, @RequestBody ItemReqDto itemReqDto) {

//        System.out.println(id);

        Item item = new Item();
        item.setId(id);
        item.setItemCode(itemReqDto.getItemCode());
        item.setItemName(itemReqDto.getItemName());
        item.setDescription(itemReqDto.getDescription());
        item.setQty(itemReqDto.getQty());
        item.setUnitPrice(itemReqDto.getUnitPrice());

        ItemCategory category = itemCategoryService.getCategoryById(itemReqDto.getCategoryId());
        item.setCategory(category);

        Item updatedItem = itemService.updateItem(id, item);
        return ResponseEntity.status(200).body(updatedItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable int id) {
        itemService.deleteItem(id);
        return ResponseEntity.status(200).body("Item deleted");
    }

}
