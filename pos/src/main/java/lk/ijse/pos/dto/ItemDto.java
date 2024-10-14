package lk.ijse.pos.dto;

import lombok.Data;

@Data
public class ItemDto {

    private int id;
    private String itemCode;
    private String itemName;
    private String description;
    private int qty;
    private double unitPrice;
    private int qoh;
    private int categoryId;

}
