package boardPrac.practice.board.dto;

import boardPrac.practice.board.domain.entity.BoardReplyEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardReplyDto {
    private Long sno;
    private Long id;
    private String content;
    private String writer;
    private String ip;
    private String delfl;
    private LocalDateTime regdt;
    private LocalDateTime moddt;

    public BoardReplyEntity toEntity() {
        BoardReplyEntity build = BoardReplyEntity.builder()
                .sno(sno)
                .id(id)
                .content(content)
                .writer(writer)
                .ip(ip)
                .delfl(delfl)
                .build();
        return build;
    }

    @Builder
    public BoardReplyDto(Long sno, Long id, String content, String writer, String ip, String delfl, LocalDateTime regdt, LocalDateTime moddt) {
        this.sno = sno;
        this.id = id;
        this.content = content;
        this.writer = writer;
        this.ip = ip;
        this.delfl = delfl;
        this.regdt = regdt;
        this.moddt = moddt;
    }
}
