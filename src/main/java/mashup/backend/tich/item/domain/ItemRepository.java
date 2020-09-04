package mashup.backend.tich.item.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByUserId(Long userId);
//    Page<Item> findAllByUsersId(Long userId, Pageable pageable);
    Optional<Item> findById(Long id);
}
