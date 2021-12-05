package com.cwh.urlshortingserviceapi.api.url;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.util.StringUtils;

import lombok.Getter;

@Getter
public class UrlData {
    private long inquiryCount;
    private String originalUrl;
    private String shortenUrl;

    public UrlData(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public UrlData(String originalUrl, String shortenUrl) {
        this.originalUrl = originalUrl;
        this.shortenUrl = shortenUrl;
    }
    
    public void makeShortenUrl() {
        if(!StringUtils.hasLength(originalUrl)) return;

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(originalUrl.getBytes());
            shortenUrl = new String(messageDigest.digest()).substring(0, 9);
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
    
}
