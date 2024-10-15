package lk.ijse.pos.dto;

import lombok.Data;

@Data
public class ItemDto {
    private String itemCode; // Code for the item
    private String itemName; // Name of the item
    private String description; // Description of the item
    private int qty; // Quantity of the item
    private double unitPrice; // Unit price of the item
}
