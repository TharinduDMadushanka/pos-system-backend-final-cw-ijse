package lk.ijse.pos.service.impl;

import lk.ijse.pos.dto.OrderDetailDto;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.OrderDetail;
import lk.ijse.pos.repository.ItemRepository;
import lk.ijse.pos.repository.OrderDetailRepository;
import lk.ijse.pos.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ItemRepository itemRepository; // Assuming you have an ItemRepository

    @Override
    public OrderDetailDto createOrderDetail(OrderDetailDto orderDetailDto) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setQuantity(orderDetailDto.getQuantity());
        orderDetail.setPrice(orderDetailDto.getPrice());

        Item item = itemRepository.findById(orderDetailDto.getItemId())
                .orElseThrow(() -> new RuntimeException("Item not found"));
        orderDetail.setItem(item);

        orderDetailRepository.save(orderDetail);

        // Create the OrderDetailDto to return
        orderDetailDto.setId(orderDetail.getId());
        return orderDetailDto;
    }

    @Override
    public List<OrderDetailDto> getAllOrderDetails() {
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        List<OrderDetailDto> orderDetailDtos = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails) {
            OrderDetailDto detailDto = new OrderDetailDto();
            detailDto.setId(orderDetail.getId());
            detailDto.setOrderId(orderDetail.getOrder().getId()); // Assuming Order has getId()
            detailDto.setItemId(orderDetail.getItem().getId()); // Assuming Item has getId()
            detailDto.setQuantity(orderDetail.getQuantity());
            detailDto.setPrice(orderDetail.getPrice());
            orderDetailDtos.add(detailDto);
        }
        return orderDetailDtos;
    }

    @Override
    public OrderDetailDto getOrderDetailById(int id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order detail not found"));

        OrderDetailDto detailDto = new OrderDetailDto();
        detailDto.setId(orderDetail.getId());
        detailDto.setOrderId(orderDetail.getOrder().getId()); // Assuming Order has getId()
        detailDto.setItemId(orderDetail.getItem().getId()); // Assuming Item has getId()
        detailDto.setQuantity(orderDetail.getQuantity());
        detailDto.setPrice(orderDetail.getPrice());
        return detailDto;
    }

    @Override
    public void deleteOrderDetail(int id) {
        orderDetailRepository.deleteById(id);
    }
}
