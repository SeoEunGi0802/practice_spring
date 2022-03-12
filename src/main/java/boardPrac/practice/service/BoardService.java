package boardPrac.practice.service;

import boardPrac.practice.domain.entity.BoardEntity;
import boardPrac.practice.domain.entity.BoardHitEntity;
import boardPrac.practice.domain.repository.BoardRepository;
import boardPrac.practice.domain.repository.BoardHitRepository;
import boardPrac.practice.dto.BoardDto;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    private BoardRepository boardRepository;
    private BoardHitRepository boardHitRepository;
    private LocalDateTime nowDt = LocalDateTime.now();

    public BoardService(BoardRepository boardRepository, BoardHitRepository boardHitRepository) {
        this.boardRepository = boardRepository;
        this.boardHitRepository = boardHitRepository;
    }

    @Transactional
    public Long saveBoard(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    @Transactional
    public void updateBoard(BoardDto boardDto) {
        boardRepository.updateBoard(boardDto.getId(), boardDto.getTitle(), boardDto.getContent(), nowDt);
    }

    @Transactional
    public void actionBoard(Long id, String mode) {
        boardRepository.actionBoard(id, mode, nowDt);
    }

    @Transactional
    public List<BoardDto> getBoardList(String delFl) {
        if (delFl.isEmpty()) {
            delFl = "Y";
        }

        List<BoardEntity> boardList = boardRepository.findAllByDelfl(delFl);
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (BoardEntity board : boardList) {
            BoardDto boardDto = BoardDto.builder().id(board.getId()).writer(board.getWriter()).title(board.getTitle()).hitcnt(board.getHitcnt()).regdt(board.getRegdt()).build();
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

    @Transactional
    public BoardDto getBoardId(Long id) {
        BoardEntity board = boardRepository.findById(id).get();
        String ip = getClientIp();

        List<BoardHitEntity> res = boardHitRepository.findByIdAndIp(id, ip);
        if (res.isEmpty()) {
            boardHitRepository.save(BoardHitEntity.builder().id(id).ip(ip).build());
            boardRepository.increaseHitCnt(id);
        }

        BoardDto boardDto = BoardDto.builder().id(board.getId()).writer(board.getWriter()).title(board.getTitle()).content(board.getContent()).delfl(board.getDelfl()).regdt(board.getRegdt()).build();
        return boardDto;
    }

    public static String getClientIp() {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String ip = req.getHeader("X-FORWARDED-FOR");

        if (ip == null) {
            ip = req.getHeader("Proxy-Client-IP");
        }

        if (ip == null) {
            ip = req.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null) {
            ip = req.getHeader("HTTP_CLIENT_IP");
        }

        if (ip == null) {
            ip = req.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (ip == null) {
            ip = req.getRemoteAddr();
        }

        if (ip == "0:0:0:0:0:0:0:1") {
            ip = "127.0.0.1";
        }

        return ip;
    }
}