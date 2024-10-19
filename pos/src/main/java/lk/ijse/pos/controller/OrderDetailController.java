package lk.ijse.pos.controller;

import lk.ijse.pos.dto.OrderDetailDto;
import lk.ijse.pos.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/order-details")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping
    public ResponseEntity<OrderDetailDto> addOrderDetail(@RequestBody OrderDetailDto orderDetailDto) {
        OrderDetailDto createdOrderDetail = orderDetailService.createOrderDetail(orderDetailDto);
        return ResponseEntity.status(201).body(createdOrderDetail);
    }

    @GetMapping
    public ResponseEntity<List<OrderDetailDto>> getAllOrderDetails() {
        List<OrderDetailDto> allOrderDetails = orderDetailService.getAllOrderDetails();
        return ResponseEntity.ok(allOrderDetails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailDto> getOrderDetail(@PathVariable int id) {
        OrderDetailDto orderDetailById = orderDetailService.getOrderDetailById(id);
        if (orderDetailById == null) {
            return ResponseEntity.status(404).body(null); // or use ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderDetailById);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable int id) {
        orderDetailService.deleteOrderDetail(id);
        return ResponseEntity.noContent().build(); // No content after deletion
    }
}
