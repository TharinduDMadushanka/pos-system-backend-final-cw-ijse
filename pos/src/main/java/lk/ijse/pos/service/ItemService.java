package lk.ijse.pos.service;

import lk.ijse.pos.entity.Item;

import java.util.List;

public interface ItemService {
    Item createItem(Item item); // Create a new item
    Item updateItem(int id, Item item); // Update an existing item by ID
    void deleteItem(int id); // Delete an item by ID
    List<Item> getAllItems(); // Retrieve all items
    Item getItemById(int id);
}
