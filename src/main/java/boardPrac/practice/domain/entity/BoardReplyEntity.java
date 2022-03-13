package boardPrac.practice.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@Table(name = "board_reply", indexes = {
        @Index(name="idx_id", columnList = "id"),
})
public class BoardReplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(20) AUTO_INCREMENT")
    private Long sno;

    @Column(columnDefinition = "BIGINT(20)")
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(length = 10, nullable = false)
    private String writer;

    @Column(columnDefinition = "VARCHAR(20)")
    private String ip;

    @Column(columnDefinition = "CHAR(1) DEFAULT 'N'")
    private String delfl;

    @CreatedDate
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime regdt;

    @LastModifiedDate
    @Column(columnDefinition = "TIMESTAMP", nullable = true)
    private LocalDateTime moddt;

    @Builder
    public BoardReplyEntity(Long sno, Long id, String content, String writer, String ip, String delfl) {
        this.sno = sno;
        this.id = id;
        this.content = content;
        this.writer = writer;
        this.ip = ip;
        this.delfl = delfl;
    }
}
