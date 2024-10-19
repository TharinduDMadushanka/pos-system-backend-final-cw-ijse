package lk.ijse.pos.service;

import lk.ijse.pos.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    OrderDto createOrder(OrderDto orderDto);
    List<OrderDto> getAllOrders();
    OrderDto getOrderById(int id);
    void deleteOrder(int id);

}
