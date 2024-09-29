package com.application.unimc;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.application.unimc.dao.UserDAO;
import com.application.unimc.service.UniversityDomainCheckService;
import com.application.unimc.service.UserService;


@SpringBootTest
class UnimcApplicationTests {
	
	@Test
	void univerysityNameCheck(){
		JSONParser parser = new JSONParser();
		Reader reader = null;
		String korea_email = "logan061175@kore.ac.kr"; // 잘못된 이메일 형식
		String kwangju_email = "anqls.c@kjcatholic.ac.kr";
		String saejong_email = "qwer123@sju.ac.kr";
		
		
		try {
			reader = new InputStreamReader(getClass().getResourceAsStream("/static/data/university_domains.json"));
			JSONObject jsonObject = (JSONObject) parser.parse(reader);
			String uniName = (String)jsonObject.get(korea_email.split("@")[1]);
			System.out.println(uniName);
		} catch (IOException e) {
			e.printStackTrace(); // 파일 읽기 오류
		} catch (ParseException e) {
			e.printStackTrace(); // JSON 파싱 오류
		} finally {if(reader != null) {try {reader.close();}catch(IOException e){e.printStackTrace();}}}
		
		
	}
	
	
	@Autowired
	private UserService userService;
	@Autowired
	private UniversityDomainCheckService universityDomainCheckService;
	
	@Test
	void isEmailDuplicate() {
//		String uniEmail = "logan061175@sju.ac.kr";
		String uniEmail = "logan061175@kore.ac.kr";
		String emailCheckResult = "pass";
		boolean isEmailDuplicate = userService.isEmailExists(uniEmail);
		
		if(isEmailDuplicate) {
			emailCheckResult = "Duplicate";
		}else if(universityDomainCheckService.univerysityNameCheck(uniEmail) == null) {
			emailCheckResult = "NotFound";
		}
		
		System.out.println(emailCheckResult);
	}

}
