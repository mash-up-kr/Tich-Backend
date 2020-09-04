package mashup.backend.tich.item.domain;

        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Modifying;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.query.Param;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findAllByUserIdAndItemId(Long userId, Long itemId);
    boolean existsAllByItemId(Long itemId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("delete from History h where h.user = :userId and h.item = :itemId")
    void deleteAllByUserIdAndItemId(@Param("userId") Long userId, @Param("itemId") Long itemId);
}
