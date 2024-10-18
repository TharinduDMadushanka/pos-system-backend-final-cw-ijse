package lk.ijse.pos.controller;

import lk.ijse.pos.dto.StockDto;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.Stock;
import lk.ijse.pos.service.ItemService;
import lk.ijse.pos.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStock() {
        List<Stock> stocks = stockService.getAllStock();
        return ResponseEntity.status(200).body(stocks);
    }

    @PostMapping
    public ResponseEntity<Stock> addStock(@RequestBody StockDto stockDto) {
        Stock stock = new Stock();
        stock.setId(stockDto.getId());
        stock.setQuantity(stockDto.getQuantity());

        Item itemById = itemService.getItemById(stockDto.getItemId());
        stock.setItem(itemById);

        Stock createdStock = stockService.createStock(stock);
        return ResponseEntity.status(201).body(createdStock);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable int id, @RequestBody StockDto stockDto) {
        Stock stock = new Stock();
        stock.setId(id);
        stock.setQuantity(stockDto.getQuantity());

        Item itemById = itemService.getItemById(stockDto.getItemId());
        stock.setItem(itemById);

        Stock updateStock = stockService.updateStock(id, stock);
        return ResponseEntity.status(201).body(updateStock);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable int id) {
        stockService.deleteStock(id);
        return ResponseEntity.status(200).body("Stock deleted");
    }

}
