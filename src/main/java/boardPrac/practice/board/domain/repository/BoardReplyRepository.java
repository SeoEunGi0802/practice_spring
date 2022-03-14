package boardPrac.practice.board.domain.repository;

import boardPrac.practice.board.domain.entity.BoardReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BoardReplyRepository extends JpaRepository<BoardReplyEntity, Long> {
    List<BoardReplyEntity> findByIdAndDelfl(Long id, String delFl); // WHERE id = ? AND delfl = ?

    @Modifying
    @Query("UPDATE BoardReplyEntity b SET b.delfl = 'Y', b.moddt = :modDt WHERE b.sno = :sno")
    void actionReply(Long sno, LocalDateTime modDt);
}
