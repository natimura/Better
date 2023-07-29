package Group.Better.service;

import Group.Better.entity.ImageData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface StorageService {
    Optional<ImageData> findById(Long id);

    Long uploadImage(MultipartFile file) throws IOException;

    byte[] downloadImage(long id);

    void deleteImage(Long imageId);
}
