package com.cwh.urlshortingserviceapi;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UrlShortingServiceApiApplicationTests {

	@Test
	void makeShortenUrl() {
		String originalUrl = "https://www.naver.com";
		String shortenUrl = null;

		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(originalUrl.getBytes());
            byte[] byteArray = messageDigest.digest();

            StringBuffer stringBuffer = new StringBuffer(); 
            
            for(int i = 0 ; i < byteArray.length ; i++){
                stringBuffer.append(Integer.toString((byteArray[i]&0xff) + 0x100, 16).substring(1));
            }

            shortenUrl = stringBuffer.toString().substring(0, 8);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(shortenUrl);
	}
}
