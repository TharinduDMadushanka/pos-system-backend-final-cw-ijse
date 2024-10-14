package lk.ijse.pos.service.impl;

import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.ItemCategory;
import lk.ijse.pos.repository.ItemCategoryRepository;
import lk.ijse.pos.repository.ItemRepository;
import lk.ijse.pos.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository; // For Item CRUD operations

    @Autowired
    private ItemCategoryRepository itemCategoryRepository; // For Category retrieval

    @Override
    public Item createItem(Item item) {
        return itemRepository.save(item); // Save the item to the database
    }

    @Override
    public Item updateItem(int id, Item updatedItem) {
        Optional<Item> existingItemOptional = itemRepository.findById(id);

        if (existingItemOptional.isPresent()) {
            Item existingItem = existingItemOptional.get();
            existingItem.setItemCode(updatedItem.getItemCode());
            existingItem.setItemName(updatedItem.getItemName());
            existingItem.setDescription(updatedItem.getDescription());
            existingItem.setQty(updatedItem.getQty());
            existingItem.setUnitPrice(updatedItem.getUnitPrice());
            existingItem.setCategory(updatedItem.getCategory()); // Set category if provided

            return itemRepository.save(existingItem);
        } else {
            throw new RuntimeException("Item not found with ID: " + id);
        }
    }

    @Override
    public void deleteItem(int id) {
        itemRepository.deleteById(id); // Delete item by ID
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll(); // Retrieve all items
    }

    @Override
    public List<Item> getItemsByCategoryId(int categoryId) {
        return itemRepository.findAll().stream()
                .filter(item -> item.getCategory().getId() == categoryId)
                .toList(); // Get items by category ID
    }
}
