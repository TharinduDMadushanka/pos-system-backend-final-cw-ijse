package lk.ijse.pos.service.impl;

import lk.ijse.pos.dto.ItemDto;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.ItemCategory;
import lk.ijse.pos.repository.ItemCategoryRepository;
import lk.ijse.pos.repository.ItemRepository;
import lk.ijse.pos.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository; // For Item CRUD operations

    @Override
    public Item createItem(Item item) {
        return itemRepository.save(item); // Save the item to the database
    }

    @Override
    public Item updateItem(Long id, Item item) {
        Optional<Item> existItemOptional = itemRepository.findById(id);

        if (existItemOptional.isPresent()) {
            Item existItem = existItemOptional.get();

            // Update the fields
            existItem.setItemCode(item.getItemCode());
            existItem.setItemName(item.getItemName());  // You might have missed this in your version
            existItem.setDescription(item.getDescription());
            existItem.setQty(item.getQty());  // Assuming "qty" for quantity on hand
            existItem.setUnitPrice(item.getUnitPrice());

            // Save the updated item
            return itemRepository.save(existItem);
        } else {
            // Item not found
            return null;
        }
    }


    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id); // Delete item by ID
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll(); // Retrieve all items
    }
}

