package boardPrac.practice.domain.repository;

import boardPrac.practice.domain.entity.BoardEntity;
import boardPrac.practice.dto.BoardDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findAllByDelfl(String delfl); // WHERE delfl = ?
}
