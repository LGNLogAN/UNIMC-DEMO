package com.application.unimc;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.application.unimc.dao.UserDAO;
import com.application.unimc.service.OcrService;
import com.application.unimc.service.UniversityDomainCheckService;
import com.application.unimc.service.UserService;


@SpringBootTest
class UnimcApplicationTests {
	
	@Test
	void testUniverysityNameCheck(){
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
	void testIsEmailDuplicate() {
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
	
	 @Autowired
    private OcrService ocrService;
	
    @Test
    public void testOcrTextExtract() throws Exception {
    	
        // 이미지 경로에서 파일 읽기
        Path path = Paths.get("src/main/resources/static/img/everytime/myung.jpg");  // 테스트 이미지 경로
        byte[] fileContent = Files.readAllBytes(path);

        // MockMultipartFile 객체 생성
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "file",                                // 파일 이름
                "test-image.jpg",                      // 오리지널 파일 이름
                "image/jpeg",                          // 파일 타입 (MIME 타입)
                fileContent                            // 파일 데이터
        );
        
        // MultipartFile 타입으로 변환
        MultipartFile multipartFile = mockMultipartFile;

        // ocrTextExtract에 전달
        String result = ocrService.extractTextToImage(multipartFile);

        // 결과 값 검증
        System.out.println(result);  // 결과 출력
    }
    
    
    @Test
    public void testEverytimeJsonParse() throws Exception {
    	// 이미지 경로에서 파일 읽기
        Path path = Paths.get("src/main/resources/static/img/everytime/myung.jpg");  // 테스트 이미지 경로
        byte[] fileContent = Files.readAllBytes(path);

        // MockMultipartFile 객체 생성
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "file",                                // 파일 이름
                "test-image.jpg",                      // 오리지널 파일 이름
                "image/jpeg",                          // 파일 타입 (MIME 타입)
                fileContent                            // 파일 데이터
        );
        
        // MultipartFile 타입으로 변환
        MultipartFile multipartFile = mockMultipartFile;

        // ocrTextExtract에 전달
        String jsonString = ocrService.extractTextToImage(multipartFile);
        List<Object> list = new ArrayList<>();
        // java.lang.NullPointerException: Cannot invoke "org.json.simple.JSONArray.iterator()" because "fields" is null 
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
            
            // images 배열에 첫 번째 이미지의 값을 firstImage에 저장
            JSONArray images = (JSONArray) jsonObject.get("images");
            JSONObject firstImage = (JSONObject) images.get(0);
            
            JSONArray fields = (JSONArray) firstImage.get("field");
            
            for(Object fieldObj : fields) {
                JSONObject field = (JSONObject) fieldObj;
                
                String inferText = (String) field.get("inferText");
                double inferConfidence = (double) field.get("inferConfidence");
                
                list.add(inferText);
                list.add(inferConfidence);
            }
            
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
