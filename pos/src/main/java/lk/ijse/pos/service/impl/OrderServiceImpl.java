package lk.ijse.pos.service.impl;

import lk.ijse.pos.dto.OrderDetailDto;
import lk.ijse.pos.dto.OrderDto;
import lk.ijse.pos.entity.Order;
import lk.ijse.pos.entity.OrderDetail;
import lk.ijse.pos.entity.User;
import lk.ijse.pos.repository.ItemRepository;
import lk.ijse.pos.repository.OrderDetailRepository;
import lk.ijse.pos.repository.OrderRepository;
import lk.ijse.pos.repository.UserRepository;
import lk.ijse.pos.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    @Override
    public OrderDto createOrder(OrderDto orderDto) {

        Order order = new Order();
        order.setOrderDate(orderDto.getOrderDate());
        order.setTotalAmount(orderDto.getTotalAmount());

        User user = userRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        order.setUser(user);

        List<OrderDetail> orderDetails = new ArrayList<>();
        for (OrderDetailDto detailDto : orderDto.getOrderDetails()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setQuantity(detailDto.getQuantity());
            orderDetail.setPrice(detailDto.getPrice());
            orderDetail.setItem(itemRepository.findById(detailDto.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found")));
            orderDetail.setOrder(order); // Set the order reference
            orderDetails.add(orderDetail);
        }

        order.setOrderDetails(orderDetails);
        orderRepository.save(order);

        // Create the OrderDto to return

        OrderDto responseDto = new OrderDto();
        responseDto.setId(order.getId());
        responseDto.setUserId(orderDto.getUserId());
        responseDto.setOrderDate(order.getOrderDate());
        responseDto.setTotalAmount(order.getTotalAmount());

        List<OrderDetailDto> responseOrderDetails = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails) {
            OrderDetailDto orderDetailDto = new OrderDetailDto();
            OrderDetailDto detailDto = new OrderDetailDto();
            detailDto.setId(orderDetail.getId());
            detailDto.setOrderId(order.getId());
            detailDto.setItemId(orderDetail.getItem().getId()); // Assuming Item has getId()
            detailDto.setQuantity(orderDetail.getQuantity());
            detailDto.setPrice(orderDetail.getPrice());
            responseOrderDetails.add(detailDto);
        }
        responseDto.setOrderDetails(responseOrderDetails);
        return responseDto;

    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> orderDtos = new ArrayList<>();

        for (Order order : orders) {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setUserId(order.getUser().getId()); // Assuming User has getId()
            orderDto.setOrderDate(order.getOrderDate());
            orderDto.setTotalAmount(order.getTotalAmount());

            List<OrderDetailDto> orderDetailDtos = new ArrayList<>();
            for (OrderDetail orderDetail : order.getOrderDetails()) {
                OrderDetailDto detailDto = new OrderDetailDto();
                detailDto.setId(orderDetail.getId());
                detailDto.setOrderId(order.getId());
                detailDto.setItemId(orderDetail.getItem().getId()); // Assuming Item has getId()
                detailDto.setQuantity(orderDetail.getQuantity());
                detailDto.setPrice(orderDetail.getPrice());
                orderDetailDtos.add(detailDto);
            }
            orderDto.setOrderDetails(orderDetailDtos);
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }

    @Override
    public OrderDto getOrderById(int id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setUserId(order.getUser().getId()); // Assuming User has getId()
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setTotalAmount(order.getTotalAmount());

        List<OrderDetailDto> orderDetailDtos = new ArrayList<>();
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            OrderDetailDto detailDto = new OrderDetailDto();
            detailDto.setId(orderDetail.getId());
            detailDto.setOrderId(order.getId());
            detailDto.setItemId(orderDetail.getItem().getId()); // Assuming Item has getId()
            detailDto.setQuantity(orderDetail.getQuantity());
            detailDto.setPrice(orderDetail.getPrice());
            orderDetailDtos.add(detailDto);
        }
        orderDto.setOrderDetails(orderDetailDtos);
        return orderDto;
    }

    @Override
    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }
}
