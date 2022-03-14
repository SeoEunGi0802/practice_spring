package boardPrac.practice.board.dto;

import boardPrac.practice.board.domain.entity.BoardHitEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardHitDto {
    private Long sno;
    private Long id;
    private String ip;
    private LocalDateTime regdt;

    public BoardHitEntity toEntity() {
        BoardHitEntity build = BoardHitEntity.builder()
                .id(id)
                .ip(ip)
                .build();
        return build;
    }

    @Builder
    public BoardHitDto(Long id, String ip, LocalDateTime regdt) {
        this.id = id;
        this.ip = ip;
        this.regdt = regdt;
    }
}
