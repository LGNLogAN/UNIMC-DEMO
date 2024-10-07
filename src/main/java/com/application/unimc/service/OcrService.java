package com.application.unimc.service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OcrService {
	private final String CLOVA_OCR_URL = "https://g7irvrq5l2.apigw.ntruss.com/custom/v1/34744/5f7fa7e086f696e28adf7b85de9e2bf28f20984195d6c3a70ce6e05810a1e275/general";
	private final String CLOVA_API_KEY = "c0FTSWxqenhkeUdydmJyV1daS3dkYWpPY3FkSHBVb2M=";
	
	public String ocrTextExtract(MultipartFile file) {
	    try {
	        // 파일을 Base64로 인코딩
	        byte[] fileBytes = file.getBytes();
	        String encodedFile = Base64.getEncoder().encodeToString(fileBytes);
	        
	        // HTTP 헤더 설정
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.set("X-OCR-SECRET", CLOVA_API_KEY);
	        
	        // JSON 요청 바디 생성
	        JSONObject requestBody = new JSONObject();
	        requestBody.put("version", "V1");
	        requestBody.put("requestId", "logan");
	        requestBody.put("timestamp", System.currentTimeMillis());

	        // JSONArray를 사용하여 'images' 필드에 JSON 배열을 추가
	        JSONArray imagesArray = new JSONArray();
	        JSONObject imageObject = new JSONObject();
	        imageObject.put("format", "jpg");
	        imageObject.put("name", "image");
	        imageObject.put("data", encodedFile);

	        imagesArray.add(imageObject); // 배열에 이미지 객체 추가
	        requestBody.put("images", imagesArray); // JSON 바디에 이미지 배열 추가
	        
	        // 요청 객체 생성
	        HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);

	        // RestTemplate을 이용해 API 호출
	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<String> response = restTemplate.exchange(CLOVA_OCR_URL, HttpMethod.POST, entity, String.class);

	        // JSON 포맷팅
	        String responseBody = response.getBody();  // 응답된 결과를 문자열로 저장
	        JSONParser parser = new JSONParser();      // JSON 파서를 생성
	        JSONObject formattedJson = (JSONObject) parser.parse(responseBody);  // 응답 결과를 JSON으로 파싱
	        
	        return formattedJson.toJSONString();       // 포맷된 JSON 문자열을 반환
	    } catch (ParseException e) {
	        e.printStackTrace();
	        return "JSON Parsing Error";               // JSON 파싱 에러 처리
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public String extractTextToImage(MultipartFile file) {
		try {
			// 이미지를 Base64(이진)으로 인코딩
			byte[] fileBytes = file.getBytes();
			String encodedFile = Base64.getEncoder().encodeToString(fileBytes);
			
			// HTTP 헤더 설정
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("X-OCR-SECRET", CLOVA_API_KEY);
            
            
            // 요청 BODY 설정
            Map<String , Object> requestBody = new HashMap<>();
            // [ Clova OCR 공식 문서 ] * 필수 요청 사항 *
            requestBody.put("version", "V1");
            requestBody.put("requestId", "logan");
            requestBody.put("timestamp", System.currentTimeMillis());
            requestBody.put("images", new Object[] {
            	new HashMap<String , Object>() {{
            		put("format" , "jpg");
            		put("name" , "image");
            		put("data" , encodedFile);
            	}}
            });
			
            // RestTemplate 을 사용한 API 호출
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange
            		(CLOVA_OCR_URL, HttpMethod.POST , new HttpEntity<>(requestBody , headers) , String.class);
            
            
            return response.getBody();
            
		} catch (Exception e) {
			e.printStackTrace();
            return null;
		}
	}

}
