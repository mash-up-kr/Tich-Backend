package mashup.backend.tich.item.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByUserId(Long userId);
    Optional<Item> findById(Long id);
    List<Item> findAllByScheduledDate(LocalDate scheduledDate);
}
