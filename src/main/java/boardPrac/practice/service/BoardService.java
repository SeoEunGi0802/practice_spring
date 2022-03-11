package boardPrac.practice.service;

import boardPrac.practice.domain.entity.BoardEntity;
import boardPrac.practice.domain.repository.BoardRepository;
import boardPrac.practice.dto.BoardDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    @Transactional
    public List<BoardDto> getBoardList() {
        List<BoardEntity> boardList = boardRepository.findAllByDelfl("N");
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

        BoardDto boardDto = BoardDto.builder().id(board.getId()).writer(board.getWriter()).title(board.getTitle()).content(board.getContent()).regdt(board.getRegdt()).build();
        return boardDto;
    }

    @Transactional
    public void increaseHitCnt() {

    }
}