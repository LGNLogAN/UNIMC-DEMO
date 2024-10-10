package com.application.unimc;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.application.unimc.service.OcrService;
import com.application.unimc.service.RegexService;
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
		//String uniEmail = "logan061175@sju.ac.kr";
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
    public void clovaImageTextExtractor() throws Exception {
    	 	
    	// 이미지 경로에서 파일 읽기
        Path path = Paths.get("src/main/resources/static/img/everytime/ByunHyungWook.jpg");  // 테스트 이미지 경로
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
        System.out.println(jsonString);
        JSONParser parser = new JSONParser();
        Map<String , Double> resultData = new HashMap<>();
        try {
			JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
			
            JSONArray images = (JSONArray) jsonObject.get("images");
            JSONObject firstImage = (JSONObject) images.get(0);
            JSONArray fields = (JSONArray) firstImage.get("fields");
            System.out.println("fields : " + fields);
            for(Object fieldObj : fields) {
                JSONObject field = (JSONObject) fieldObj;
                
                String inferText = (String) field.get("inferText");
                double inferConfidence = (double) field.get("inferConfidence");
                
                resultData.put(inferText , inferConfidence);
            }
            for (Map.Entry<String, Double> entry : resultData.entrySet()) {
                System.out.println("inferText: " + entry.getKey() + ", inferConfidence: " + entry.getValue());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Autowired
    private RegexService regexService;
    
    @Test
    public void parseLocalJsonData() throws Exception {
        // ByunHyungWook , JinSiWoo , KimNaYeon , KimYuBin , LeeHoChan , MyungJaeYoung , YukJunHyung
        JSONParser parser = new JSONParser();
        Reader reader = null;
        Map<String , Double> resultData = new HashMap<>();
        List<String> inferTextList = new ArrayList<>();
        List<String> blackListKeyWord = new ArrayList<>(Arrays.asList
        		("내 정보","내 프로필","실명","게시판","프로필 완성하기","계정 아이디",
        				"비밀번호 변경","프로필 사진 추가","프로필","완성하기","등록해보세요.",
        				"표현할","사진","추가","채팅방에서","나를","닉네임","사진을","계정","대표할",
        				"있는","커뮤니티","변경","비밀번호","아이디",">","<","3/5","수",".40"));
        
        
        String jsonFilePath = "/static/data/everytime/KimYuBin.json";
        try {
        	reader = new InputStreamReader(getClass().getResourceAsStream(jsonFilePath));
        	JSONObject exampleJson = (JSONObject) parser.parse(reader);
        	
            JSONArray images = (JSONArray) exampleJson.get("images");
            JSONObject image = (JSONObject) images.get(0);
            JSONArray fields = (JSONArray) image.get("fields");
            // JSONArray fields = (JSONArray) ((JSONObject) ((JSONArray) exampleJson.get("images")).get(0)).get("fields");
            System.out.println("fields : " + fields);
            for(Object fieldObj : fields) {
                JSONObject field = (JSONObject) fieldObj;
                
                String inferText = (String) field.get("inferText");
                double inferConfidence = (double) field.get("inferConfidence");
                
                
                inferTextList.add(inferText);
                resultData.put(inferText , inferConfidence);
            }
            //for (Map.Entry<String, Double> entry : resultData.entrySet()) {
            //    System.out.println("inferText(텍스트): " + entry.getKey() + "|| inferConfidence(신뢰도): " + entry.getValue());
            //}
            
            List<String> filteredTextList = new ArrayList<>();
            for (String text : inferTextList) {
                if (!blackListKeyWord.contains(text)) {
                    filteredTextList.add(text);
                }
            }
            inferTextList = filteredTextList; // 필터링된 리스트로 업데이트

            // 결과 출력
            for (String text : inferTextList) {
                System.out.println(text);
            }
            
            System.out.println("\n======== Regex Data ========\n");
            for (String data : inferTextList) {
				if(regexService.majorMatchesPattern(data)) {
					System.out.println("학과 : " + data);
				}else if(regexService.containsStudentKeyword(data)) {
					System.out.println("재학여부 : " + data);
				}else if(regexService.campusMatchesPattern(data)) {
					System.out.println("캠퍼스 : " + data);
				}
			}
            System.out.println("\n======== Regex Data ========\n");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
