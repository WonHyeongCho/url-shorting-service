package com.cwh.urlshortingserviceapi.api.url;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

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
        UrlData urlData = urlHashMap.get(shortenUrl);
        urlData.incInquiryCount();
        return urlData;
    }

    public UrlData registerUrlData(String originalUrl) {
        UrlData urlData = UrlData.builder()
                            .originalUrl(originalUrl)
                            .build();
        urlData.makeShortenUrl();

        urlHashMap.put(urlData.getShortenUrl(), urlData);
        
        return urlData;
    }
}
