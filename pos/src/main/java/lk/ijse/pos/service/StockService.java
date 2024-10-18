package lk.ijse.pos.service;

import lk.ijse.pos.entity.Stock;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StockService {
    Stock createStock(Stock stock);
    Stock updateStock(int id, Stock stock);
    void deleteStock(int id);
    Stock getStockById(int id);
    List<Stock> getAllStock();
}
