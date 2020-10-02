package mashup.backend.tich.device.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findAllByUserId(Long userId);
    Device findByTokenAndUserId(String token, Long userId);
}
