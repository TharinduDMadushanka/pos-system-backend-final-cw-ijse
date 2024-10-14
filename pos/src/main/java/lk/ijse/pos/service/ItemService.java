package lk.ijse.pos.service;

import lk.ijse.pos.dto.ItemDto;
import lk.ijse.pos.entity.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {

    Item createItem(Item item); // Create a new item

    Item updateItem(Long id, Item item); // Update an existing item by ID

    void deleteItem(Long id); // Delete an item by ID

    List<Item> getAllItems(); // Retrieve all items
}
