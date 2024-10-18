package lk.ijse.pos.service.impl;

import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.Stock;
import lk.ijse.pos.repository.StockRepository;
import lk.ijse.pos.service.ItemService;
import lk.ijse.pos.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ItemService itemService;

    @Override
    public Stock createStock(Stock stock) {
        // Update the qty in Item when stock is created
        Item item = stock.getItem();
        if (item != null) {
            item.setQty(item.getQty() + stock.getQuantity());
            itemService.updateItem(item.getId(), item);
        }

        return stockRepository.save(stock);
    }

    @Override
    public Stock updateStock(int id, Stock stock) {
        Optional<Stock> stockOptional = stockRepository.findById(id);

        if (stockOptional.isPresent()) {
            Stock existingStock  = stockOptional.get();

            Item item = stock.getItem();

            if (item != null) {
                // Adjust item's quantity based on the stock change
                int previousQty  = existingStock.getQuantity();
                int newQty = stock.getQuantity();

                // Update the item's qty: (Current qty - old stock qty) + new stock qty
                item.setQty(item.getQty() - previousQty + newQty);
                itemService.updateItem(item.getId(), item);

            }

            existingStock.setQuantity(stock.getQuantity());
            existingStock.setItem(stock.getItem());

            return stockRepository.save(existingStock);
        }else {
            return null;
        }
    }

    @Override
    public void deleteStock(int id) {
        Optional<Stock> stockOptional = stockRepository.findById(id);

        if (stockOptional.isPresent()) {
            Stock stock = stockOptional.get();
            Item item = stock.getItem();

            if (item != null) {
                item.setQty(item.getQty() - stock.getQuantity());
                itemService.updateItem(item.getId(), item);
            }
        }

        stockRepository.deleteById(id);
    }

    @Override
    public Stock getStockById(int id) {
        return stockRepository.findById(id).orElse(null);
    }

    @Override
    public List<Stock> getAllStock() {
        return stockRepository.findAll();
    }
}
