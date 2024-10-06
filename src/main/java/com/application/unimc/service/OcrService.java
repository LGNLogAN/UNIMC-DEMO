package com.application.unimc.service;

import java.util.Base64;
import org.json.simple.JSONObject;
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
			headers.set("X-OCR-SECRET" , CLOVA_API_KEY);
			
			// JSON 요청 바디 생성
			JSONObject requestBody = new JSONObject();
			requestBody.put("version", "V1");
			requestBody.put("requestId" , "logan");
			requestBody.put("timestamp" , System.currentTimeMillis());

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

            // 응답 결과 출력
            return response.getBody();  // 응답된 결과를 반환하여 콘솔에 출력
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
