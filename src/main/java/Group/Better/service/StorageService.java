package Group.Better.service;

import Group.Better.entity.ImageData;
import Group.Better.repository.StorageRepository;
import Group.Better.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private StorageRepository storageRepository;

    public Optional<ImageData> findById(Long id) {
        return storageRepository.findById(id);
    }

    public Long uploadImage(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            ImageData imageData = storageRepository.save(ImageData.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .imageData(ImageUtils.compressImage(file.getBytes()))
                    .build());
            return imageData.getId();
        }
        return null;
    }    
        
    public byte[]downloadImage(long id){
        Optional<ImageData> dbImageData = storageRepository.findById(id);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }

    public void deleteImage(Long imageId) {
        Optional<ImageData> imageData = storageRepository.findById(imageId);
        if (imageData.isPresent()) {
            storageRepository.delete(imageData.get());
        }
    }
}
