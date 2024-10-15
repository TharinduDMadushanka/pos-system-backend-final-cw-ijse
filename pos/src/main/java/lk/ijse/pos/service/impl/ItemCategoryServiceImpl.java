package lk.ijse.pos.service.impl;

import lk.ijse.pos.entity.ItemCategory;
import lk.ijse.pos.repository.ItemCategoryRepository;
import lk.ijse.pos.service.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    private static final String UPLOAD_DIR = "util/assets/";

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Override
    public ItemCategory create(String name, MultipartFile imageFile) throws IOException {
        String imageName = saveImage(imageFile);

        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setName(name);
        itemCategory.setImage(imageName);

        return itemCategoryRepository.save(itemCategory);
    }

    @Override
    public ItemCategory update(int id, String name, MultipartFile imageFile) throws IOException {
        Optional<ItemCategory> existItemCatOptional = itemCategoryRepository.findById(id);

        if (existItemCatOptional.isPresent()) {
            ItemCategory existItemCat = existItemCatOptional.get();
            existItemCat.setName(name);

            if (imageFile != null && !imageFile.isEmpty()) {
                String imageName = saveImage(imageFile);
                existItemCat.setImage(imageName);
            }

            return itemCategoryRepository.save(existItemCat);
        } else {
            throw new IllegalArgumentException("ItemCategory not found with id: " + id);
        }
    }

    @Override
    public void delete(int id) {
        itemCategoryRepository.deleteById(id);
    }

    @Override
    public List<ItemCategory> getAllCategory() {
        return itemCategoryRepository.findAll();
    }

    // Helper method to save image
    private String saveImage(MultipartFile imageFile) throws IOException {
        String fileName = imageFile.getOriginalFilename();

        // Create directory if it doesn't exist
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Save file to the specified directory
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }

    @Override
    public ItemCategory getCategoryById(int id) {
        return itemCategoryRepository.findById(id).orElse(null);
    }

}
