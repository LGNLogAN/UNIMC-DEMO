package com.application.unimc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ocr")
public class OCRController {
	private final OcrDataExtractor ocrDataExtractor;

    public OcrController(OcrDataExtractor ocrDataExtractor) {
        this.ocrDataExtractor = ocrDataExtractor;
    }

    @PostMapping("/upload")
    public UserProfileDTO handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        // 1. 파일을 Clova OCR API에 전송해 텍스트 추출
        String extractedText = clovaOcrService.extractText(file);  // 클로바 OCR API를 호출하는 서비스 메서드

        // 2. 추출된 텍스트에서 정보 추출
        return ocrDataExtractor.extractUserProfile(extractedText);
    }
}
