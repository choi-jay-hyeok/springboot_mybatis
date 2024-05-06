package com.codingrecipe.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter // getter 자동 생성
@Setter // setter 자동 생성
@ToString // '필드명=필드값' 자동 생성
public class BoardDTO {

    private Long id;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private String createdAt;
    private int fileAttached;
    private MultipartFile boardFile;

}
