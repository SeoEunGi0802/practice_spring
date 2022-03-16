package boardPrac.practice.member.domain.entity;

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
@Table(name = "member", indexes = {
        @Index(name="idx_id", columnList = "id"),
})
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(20) AUTO_INCREMENT")
    private Long sno;

    @Column(length = 20, nullable = false)
    private String id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String pwd;

    @Column(length = 10, nullable = false)
    private String nick;

    @Column(columnDefinition = "CHAR(1) DEFAULT 'N'")
    private String hackout;

    @CreatedDate
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime regdt;

    @Column(columnDefinition = "TIMESTAMP", nullable = true)
    private LocalDateTime moddt;

    @Builder
    public MemberEntity(Long sno, String id, String pwd, String nick, String hackout) {
        this.sno = sno;
        this.id = id;
        this.pwd = pwd;
        this.nick = nick;
        this.hackout = hackout;
    }
}
