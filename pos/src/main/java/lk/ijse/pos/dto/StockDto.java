package lk.ijse.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockDto {

    private int id;
    private int quantity;
    private int itemId;

}
