package lk.ijse.pos.service;

import lk.ijse.pos.entity.ItemCategory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ItemCategoryService {

    ItemCategory create(String name, MultipartFile imageFile) throws IOException;

    ItemCategory update(int id, String name, MultipartFile imageFile) throws IOException;

    void delete(int id);

    List<ItemCategory> getAllCategory();

    ItemCategory getCategoryById(int id);
}
