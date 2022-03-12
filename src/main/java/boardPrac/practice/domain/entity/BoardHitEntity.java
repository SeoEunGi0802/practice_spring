package boardPrac.practice.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
@Table(name = "board_hit", indexes = {
        @Index(name="idx_id", columnList = "id"),
        @Index(name="idx_ip", columnList = "ip"),
})
public class BoardHitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(20) AUTO_INCREMENT")
    private Long sno;

    @Column(columnDefinition = "BIGINT(20)")
    private Long id;

    @Column(columnDefinition = "VARCHAR(20)")
    private String ip;

    @CreatedDate
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime regdt;

    @Builder
    public BoardHitEntity(Long id, String ip) {
        this.id = id;
        this.ip = ip;
    }
}
