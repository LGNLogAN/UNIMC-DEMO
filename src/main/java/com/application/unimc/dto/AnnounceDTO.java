package com.application.unimc.dto;

import java.util.Date;

import lombok.Data;

@Data
public class AnnounceDTO {
	private Long announceId;   // 공지번호
    private String header;     // 공지제목
    private String meta;       // 작성자 , 작성일
    private String body;       // 본문
    private Date postDate; // 작성일자
}
