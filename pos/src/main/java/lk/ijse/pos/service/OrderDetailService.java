package lk.ijse.pos.service;

import lk.ijse.pos.dto.OrderDetailDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderDetailService {

    OrderDetailDto createOrderDetail(OrderDetailDto orderDetailDto);
    List<OrderDetailDto> getAllOrderDetails();
    OrderDetailDto getOrderDetailById(int id);
    void deleteOrderDetail(int id);

}
