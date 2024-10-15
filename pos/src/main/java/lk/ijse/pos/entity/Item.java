package lk.ijse.pos.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Primary Key for Item

    private String itemCode; // Unique code for the item

    private String itemName; // Name of the item

    private String description; // Description of the item

    private int qty; // Quantity of the item

    private double unitPrice; // Price of the item


}
