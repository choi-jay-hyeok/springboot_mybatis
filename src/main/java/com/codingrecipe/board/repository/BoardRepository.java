package com.codingrecipe.board.repository;

import com.codingrecipe.board.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final SqlSessionTemplate sql; // mapper 호출하는 용도

    public void save(BoardDTO boardDTO) {
        sql.insert("Board.save", boardDTO); // board-mapper.xml 에서 namespace 가 Board 이고, id 가 save 인 태그를 호출하겠다. 객체 여러개를 넘기고 싶으면 HashMap 사용
    }

    public List<BoardDTO> findAll() {
        return sql.selectList("Board.findAll");
    }

    public void updateHits(Long id) {
        sql.update("Board.updateHits", id);
    }

    public BoardDTO findById(Long id) {
        return sql.selectOne("Board.findById", id);
    }
}
