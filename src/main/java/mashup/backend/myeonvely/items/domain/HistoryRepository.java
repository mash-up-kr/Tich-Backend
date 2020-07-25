package mashup.backend.myeonvely.items.domain;

import mashup.backend.myeonvely.users.domain.Devices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<Devices, Long> {
}
