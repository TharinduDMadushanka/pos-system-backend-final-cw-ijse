package lk.ijse.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {

    private int id;
    private int orderId;
    private int itemId;
    private int quantity;
    private double price;

}
