package mashup.backend.tich.device.domain;

import mashup.backend.tich.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findAllByUserId(Long userId);
    Device findByTokenAndUserId(String token, Long userId);
    boolean existsAllByUserId(Long userId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("delete from Device d where d.user = :user")
    void deleteAllByUser(@Param("user") User user);
}
