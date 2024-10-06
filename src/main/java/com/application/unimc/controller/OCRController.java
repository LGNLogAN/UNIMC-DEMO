//package com.application.unimc.controller;
//
//import java.io.IOException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.application.unimc.dto.UserDTO;
//import com.application.unimc.service.OcrService;
//
//@RestController
//@RequestMapping("/ocr")
//public class OCRController {
//	@Autowired
//	private OcrService ocrService;
//
//
//    @PostMapping("/upload")
//    public UserDTO handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
//        // 1. 파일을 Clova OCR API에 전송해 텍스트 추출
//        String extractedText = OcrService.ocrTextExtarct(file);  // 클로바 OCR API를 호출하는 서비스 메서드
//
//        // 2. 추출된 텍스트에서 정보 추출
//        return ocrDataExtractor.extractUserProfile(extractedText);
//    }
//}
