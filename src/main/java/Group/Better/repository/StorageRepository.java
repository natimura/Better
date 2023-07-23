package Group.Better.repository;

import Group.Better.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageRepository extends JpaRepository<ImageData,Long> {
}
