package lk.ijse.pos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantity;

    @OneToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

}
