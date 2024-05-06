package com.codingrecipe.board.service;

import com.codingrecipe.board.dto.BoardDTO;
import com.codingrecipe.board.dto.BoardFileDTO;
import com.codingrecipe.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void save(BoardDTO boardDTO) throws IOException {
        // 파일 없는 경우
        if (boardDTO.getBoardFile().isEmpty()) {
            boardDTO.setFileAttached(0);
            boardRepository.save(boardDTO);

            // 파일 있는 경우
        } else {
            boardDTO.setFileAttached(1);
            BoardDTO savedBoard = boardRepository.save(boardDTO);

            MultipartFile boardFile = boardDTO.getBoardFile(); // 파일만 따로 가져오기

            String originalFilename = boardFile.getOriginalFilename(); // 파일 이름 가져오기
            System.out.println("originalFilename = " + originalFilename);

            // 저장용 이름 만들기
            System.out.println(System.currentTimeMillis());
            String storedFileName = System.currentTimeMillis() + "-" + originalFilename;
            System.out.println("storedFileName = " + storedFileName);

            // BoardFileDTO 세팅
            BoardFileDTO boardFileDTO = new BoardFileDTO();
            boardFileDTO.setOriginalFileName(originalFilename);
            boardFileDTO.setStoredFileName(storedFileName);
            boardFileDTO.setBoardId(savedBoard.getId());

            // 파일 저장용 폴더에 파일 저장 처리
            String savePath = "C:/Dev/coding_recipe/springboot_mybatis/spring_upload_files/" + storedFileName;

            boardFile.transferTo(new File(savePath));
            boardRepository.saveFile(boardFileDTO);
        }
    }

    public List<BoardDTO> findAll() {
       return boardRepository.findAll();
    }

    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDTO findById(Long id) {
        return boardRepository.findById(id);
    }

    public void update(BoardDTO boardDTO) {
        boardRepository.update(boardDTO);
    }

    public void delete(Long id) {
        boardRepository.delete(id);
    }

    public BoardFileDTO findFile(Long id) {
        return boardRepository.findFile(id);
    }
}
