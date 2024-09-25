package com.application.unimc.auth;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

@Component
public class UniAuth {
	
	
	public String univerysityNameCheck(String uniEmail) {
		String university = "";
		JSONParser parser = new JSONParser();
		Reader reader = null;
		
		
		try {
			reader = new InputStreamReader(getClass().getResourceAsStream("/static/data/university_domains.json"));
			JSONObject jsonObject = (JSONObject) parser.parse(reader);
			university = (String)jsonObject.get(uniEmail.split("@")[1]);
		} catch (IOException e) {
			e.printStackTrace(); // 파일 읽기 오류
		} catch (ParseException e) {
			e.printStackTrace(); // JSON 파싱 오류
		} finally {if(reader != null) {try {reader.close();}catch(IOException e){e.printStackTrace();}}}
		
		return university;
	}
}
