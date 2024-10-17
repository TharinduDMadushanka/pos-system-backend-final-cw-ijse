package lk.ijse.pos.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String itemCode;

    private String itemName;

    private String description;

    private int qty;

    private double unitPrice;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ItemCategory category;

}