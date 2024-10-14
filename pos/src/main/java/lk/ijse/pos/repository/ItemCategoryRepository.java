package lk.ijse.pos.repository;

import lk.ijse.pos.entity.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Integer> {
}
