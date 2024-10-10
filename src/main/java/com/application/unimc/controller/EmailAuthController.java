package com.application.unimc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.unimc.service.EmailSenderService;
import com.application.unimc.service.RedisService;
import com.application.unimc.service.RegexService;
import com.application.unimc.service.UniversityDomainCheckService;
import com.application.unimc.service.UserService;

import java.util.Random;

@RestController
public class EmailAuthController {

    @Autowired
    private EmailSenderService emailSenderService;
    
    @Autowired
    private RedisService redisService;
    
    @Autowired
    private UniversityDomainCheckService domainCheckService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RegexService regexService;
    
    // 6자리 랜덤 코드 생성 메서드
    private String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000; // 6자리 숫자 생성
        return String.valueOf(code);
    }

    @GetMapping("/send-verification-code")
    public String sendVerificationCode(@RequestParam("uniEmail") String uniEmail) {
    	
		if(!regexService.emailMatchesPattern(uniEmail)) {
			return "[ Error ] : 400 Bad Request\n[ Comment ] : 지원하지않은 이메일입니다.";
    	}else if(domainCheckService.univerysityNameCheck(uniEmail) == null) {
    		return "[ Error ] : 404 Not Found\n[ Comment ] : 해당 대학교 이메일은 지원하지않습니다.\n[ Inquiry ] : 디스코드(mubin_c)";
    	}else if(userService.isEmailExists(uniEmail)){
    		return "[ Error ] : 409 Conflict\n[ Commnet ] : 이미 등록되어있는 이메일입니다.\n[ Inquiry ] : 디스코드(mubin_c)";
    	}else{
    		// 6자리 랜덤 인증 코드 생성
    		String verificationCode = generateVerificationCode();
    		
    		// 이메일 본문 내용
    		String subject = "이메일 인증 코드";
    		String body = "인증 코드는 다음과 같습니다: " + verificationCode;
    		
    		// 이메일 인증코드 전송
    		emailSenderService.sendEmail(uniEmail, subject, body);
    		
    		// Redis를 사용하여 이메일과 인증 코드를 저장
    		redisService.setVerificationCode(uniEmail, verificationCode);
    		
    		return "인증 코드가 이메일로 전송되었습니다.email";
    	}
    	
    }

    
    @GetMapping("/verify-code")
    public boolean verifyCode(@RequestParam("uniEmail") String uniEmail, @RequestParam("code") String code) {
        // Redis 에서 이메일에 해당되는 인증코드를 가져와서 변수에 저장
        String storedCode = redisService.getVerificationCode(uniEmail);
        
        if (storedCode != null && storedCode.equals(code)) {
            return true;
        } else {
            return false;
        }
    }
    
    
    @GetMapping("/emailCheck")
	@ResponseBody
	public String emailCheck(@RequestParam("uniEmail") String uniEmail) {
		String emailCheckResult = "pass";
		boolean isEmailDuplicate = userService.isEmailExists(uniEmail);
		
		if(isEmailDuplicate) {
			emailCheckResult = "Duplicate";
		}else if(domainCheckService.univerysityNameCheck(uniEmail) == null) {
			emailCheckResult = "NotFound";
		}
		System.out.println("emailCheck : " + uniEmail);
		return emailCheckResult;
	}
}
