package boardPrac.practice.member.service;

import boardPrac.practice.member.domain.entity.MemberEntity;
import boardPrac.practice.member.domain.repository.MemberRepository;
import boardPrac.practice.member.dto.MemberDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

@Service
public class MemberService {
    private MemberRepository memberRepository;
    private LocalDateTime nowDt = LocalDateTime.now();
    private PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public String saveMember(MemberDto memberDto) {
        String encodePwd = passwordEncoder.encode(memberDto.getPwd());
        memberDto.setPwd(encodePwd);
        return memberRepository.save(memberDto.toEntity()).getId();
    }

    @Transactional
    public HashMap loginAction(MemberDto memberDto) {
        Optional<MemberEntity> memberEntity = memberRepository.findById(memberDto.getId());
        HashMap result = new HashMap();
        result.put("flag", false);
        result.put("data", null);

        memberEntity.ifPresent(existMember -> {
            if (passwordEncoder.matches(memberDto.getPwd(), existMember.getPwd())) {
                result.put("flag", true);
                result.put("data", existMember);
            }
        });

        return result;
    }
}
