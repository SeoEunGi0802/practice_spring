package boardPrac.practice.board.domain.repository;

import boardPrac.practice.board.domain.entity.BoardHitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardHitRepository extends JpaRepository<BoardHitEntity, Long> {
    List<BoardHitEntity> findByIdAndIp(Long id, String ip); // WHERE id = ? AND ip = ?
}
