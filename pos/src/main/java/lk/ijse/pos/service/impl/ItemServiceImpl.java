package lk.ijse.pos.service.impl;


import lk.ijse.pos.entity.Item;
import lk.ijse.pos.repository.ItemRepository;
import lk.ijse.pos.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item updateItem(int id, Item item) {
        Optional<Item> itemOptional = itemRepository.findById(id);

        if (itemOptional.isPresent()) {
            Item existItem = itemOptional.get();

            existItem.setId(item.getId());
            existItem.setItemCode(item.getItemCode());
            existItem.setItemName(item.getItemName());
            existItem.setDescription(item.getDescription());
            existItem.setQty(item.getQty());
            existItem.setUnitPrice(item.getUnitPrice());

            return itemRepository.save(existItem);
        }else {
            return null;
        }
    }

    @Override
    public void deleteItem(int id) {
        itemRepository.deleteById(id);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item getItemById(int id) {
        return itemRepository.findById(id).orElse(null);
    }
}
