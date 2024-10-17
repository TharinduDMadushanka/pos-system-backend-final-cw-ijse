package lk.ijse.pos.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemReqDto {
    private String itemCode;
    private String itemName;
    private String description;
    private int qty;
    private double unitPrice;
    private int categoryId;
}
