package com.cwh.urlshortingserviceapi.api.url;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class UrlData {
    private long inquiryCount;
    private String originalUrl;
    private String shortenUrl;

    public static Optional<String> makeShortenUrl(String originalUrl) {
        if(!StringUtils.hasLength(originalUrl)) return Optional.empty();

        String shortenUrl = null;

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

        return Optional.ofNullable(shortenUrl);
    }

    public void incInquiryCount() {
        inquiryCount++;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null || this.getClass() != o.getClass()) return false;

        UrlData shortenUrlObj = (UrlData) o;
    
        if(!(this.shortenUrl.equals(shortenUrlObj.shortenUrl) && this.originalUrl.equals(shortenUrlObj.originalUrl))) return false;
        
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(makeHashKey());
    }

    private String makeHashKey() {
        if(!StringUtils.hasLength(originalUrl) || !StringUtils.hasLength(shortenUrl)) return null;

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(originalUrl.substring(originalUrl.length()-4, originalUrl.length()));
        stringBuffer.append(shortenUrl.substring(0, 4));

        return stringBuffer.toString();
    }
}
