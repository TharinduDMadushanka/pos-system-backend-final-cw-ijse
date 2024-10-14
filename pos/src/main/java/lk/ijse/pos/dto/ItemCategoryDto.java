package lk.ijse.pos.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
public class ItemCategoryDto {

    private int id;
    private String name;

    // Field to represent the image file name in DTO
    private String image;
}
