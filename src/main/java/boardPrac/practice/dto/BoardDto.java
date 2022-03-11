package boardPrac.practice.dto;

import boardPrac.practice.domain.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private Integer hitcnt;
    private String delfl;
    private LocalDateTime regdt;
    private LocalDateTime moddt;

    public BoardEntity toEntity() {
        BoardEntity build = BoardEntity.builder()
                .id(id)
                .title(title)
                .content(content)
                .writer(writer)
                .build();
        return build;
    }

    @Builder
    public BoardDto(Long id, String title, String content, String writer, Integer hitcnt, String delfl, LocalDateTime regdt, LocalDateTime moddt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.hitcnt = hitcnt;
        this.delfl = delfl;
        this.regdt = regdt;
        this.moddt = moddt;
    }
}
