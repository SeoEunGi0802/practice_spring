package boardPrac.practice.board.domain.repository;

import boardPrac.practice.board.domain.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    List<BoardEntity> findAllByDelfl(String delfl); // WHERE delfl = ?

    @Modifying
    @Query("UPDATE BoardEntity b SET b.hitcnt = b.hitcnt + 1 WHERE b.id = :id")
    void increaseHitCnt(Long id);

    @Modifying
    @Query("UPDATE BoardEntity b SET b.title = :title, b.content = :contents, b.moddt = :modDt WHERE b.id = :id")
    void updateBoard(Long id, String title, String contents, LocalDateTime modDt);

    @Modifying
    @Query("UPDATE BoardEntity b SET b.delfl = :mode, b.moddt = :modDt WHERE b.id = :id")
    void actionBoard(Long id, String mode, LocalDateTime modDt);
}
