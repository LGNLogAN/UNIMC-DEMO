package com.application.unimc.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class RegexService {
	
	// 기본 정규식
	public boolean matchesPattern(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
	
	// 이메일 정규식
	public boolean emailMatchesPattern(String email) {
		String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.ac\\.kr$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		
		return matcher.matches();
	}
	
	// 이름 정규식 
	public boolean nameMatchesPattern(String name) {
		String regex = "^[가-힣]{2,4}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(name);
		
		return matcher.matches();
	}
	
	// 마인크래프트 닉네임 정규식
	public boolean mcNameMatchesPattern(String name) {
		String regex = "^[a-zA-Z0-9_]{3,16}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(name);
		
		return matcher.matches();
	}
	
	// 학과/전공/학부 판별 정규식
	public boolean majorMatchesPattern(String major) {
		String majorRegex = "^[가-힣a-zA-Z]+(과|전공|학부)$";
		return major.matches(majorRegex);
	}
	
	// 캠퍼스 여부 판별
	public boolean campusMatchesPattern(String campus) {
		String campusRegex = "^[가-힣]+캠$";
		return campus.matches(campusRegex);
	}
	
	// 재학생 판별
	public boolean containsStudentKeyword(String input) {
	    return input.contains("재학생");
	}
	

}
