package boardPrac.practice.member.dto;

import boardPrac.practice.member.domain.entity.MemberEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {
    private Long sno;
    private String id;
    private String pwd;
    private String nick;
    private String hackout;
    private LocalDateTime regdt;
    private LocalDateTime moddt;

    public MemberEntity toEntity() {
        MemberEntity bulid = MemberEntity.builder()
                .id(id)
                .pwd(pwd)
                .nick(nick)
                .hackout(hackout)
                .build();
        return bulid;
    }

    @Builder
    public MemberDto(Long sno, String id, String pwd, String nick, String hackout, LocalDateTime regdt) {
        this.sno = sno;
        this.id = id;
        this.pwd = pwd;
        this.nick = nick;
        this.hackout = hackout;
        this.regdt = regdt;
    }
}
