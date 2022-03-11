package boardPrac.practice.domain.repository;

import boardPrac.practice.domain.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findAllByDelfl(String delfl); // WHERE delfl = ?

    @Modifying
    @Query("UPDATE BoardEntity b SET b.hitcnt = b.hitcnt + 1 WHERE b.id = :id")
    int increaseHitCnt(Long id);
}
