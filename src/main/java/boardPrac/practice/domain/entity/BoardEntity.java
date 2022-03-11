package boardPrac.practice.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
@Table(name = "board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(length = 10, nullable = false)
    private String writer;

    @Column(columnDefinition = "INT(11) DEFAULT 0")
    private Integer hitcnt;

    @Column(columnDefinition = "CHAR(1) DEFAULT 'N'")
    private String delfl;

    @CreatedDate
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime regdt;

    @LastModifiedDate
    @Column(nullable = true)
    private LocalDateTime moddt;

    @Builder
    public BoardEntity(Long id, String title, String content, String writer, Integer hitcnt, String delfl) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.hitcnt = hitcnt;
        this.delfl = delfl;
    }
}