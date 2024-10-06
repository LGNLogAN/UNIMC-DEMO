package com.application.unimc.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserDTO {
	private String uniEmail;   // 대학교 전용 이메일
    private String name;       // 이름(실명)
    private String mcName;     // 마인크래프트 닉네임
    private String password;   // 비밀번호
    private String university; // 대학교 이름
    private String major;      // 학과
    private String campus;     // 캠퍼스 이름 (optional)
    private String everytime;  // 에브리타임 사진 유무 (optional)
    private String isVerified; // 인증유무
    private Date   regDate;    // 가입일
}
