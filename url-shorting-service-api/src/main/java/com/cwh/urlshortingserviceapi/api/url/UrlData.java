package com.cwh.urlshortingserviceapi.api.url;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UrlData {
    private long inquiryCount;
    private String originalUrl;
    private String shortenUrl;

    public void makeShortenUrl() {
        if(!StringUtils.hasLength(originalUrl)) return;

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(originalUrl.getBytes());
            byte[] byteArray = messageDigest.digest();

            StringBuffer stringBuffer = new StringBuffer(); 
            
            for(int i = 0; i < byteArray.length; i++){
                stringBuffer.append(Integer.toString((byteArray[i]&0xff) + 0x100, 16).substring(1));
            }

            shortenUrl = stringBuffer.toString().substring(0, 8);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if(o == null || this.getClass() != o.getClass()) return false;

        UrlData shortenUrlObj = (UrlData) o;
        

        
        return true;
    }

    @Override
    public int hashCode() {

        return 1;
    }

}
