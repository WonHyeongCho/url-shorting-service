package com.cwh.urlshortingserviceapi.api.url;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UrlShortingService {
    private Map<String, UrlData> urlHashMap;

    @PostConstruct
    public void initialize() {
        urlHashMap = Collections.synchronizedMap(new HashMap<>());
    }

    public UrlData inquiryUrlData(String shortenUrl) {
        UrlData urlData = null;

        if(urlHashMap.containsKey(shortenUrl)) {
            urlData = urlHashMap.get(shortenUrl);
            urlData.incInquiryCount();
        }
        
        return urlData;
    }

    public UrlData registerUrlData(String originalUrl) {
        UrlData urlData = null;
        
        originalUrl = changeUrlForm(originalUrl);

        String shortenUrl = getShortenUrl(originalUrl);

        if(StringUtils.hasLength(shortenUrl)) {
            if(urlHashMap.containsKey(shortenUrl)) {
                urlData = urlHashMap.get(shortenUrl);
                urlData.incInquiryCount();
            }
            else {
                urlData = new UrlData(0, originalUrl, shortenUrl);
                urlHashMap.put(urlData.getShortenUrl(), urlData);
            }
        }

        return urlData;
    }

    private String getShortenUrl(String originalUrl) {
        Optional<String> shortenUrlOpt = UrlData.makeShortenUrl(originalUrl);
        String shortenUrl = null;

        if(shortenUrlOpt.isPresent()) {
            shortenUrl = shortenUrlOpt.get();
        }

        return shortenUrl;
    }

    private String changeUrlForm(String originalUrl) {
        if(StringUtils.hasLength(originalUrl) && !originalUrl.startsWith("http://")) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("https://").append(originalUrl);
            originalUrl = stringBuffer.toString();
        }
        
        return originalUrl;
    }
}
