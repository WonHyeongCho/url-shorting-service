package com.cwh.urlshortingserviceapi.api.url;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class UrlShortingService {
    private Set<Object> urlHashSet;

    public UrlShortingService() {
        urlHashSet = Collections.synchronizedSet(new HashSet<>());
    }

    public UrlData getUrlData(String originalUrl) {
        UrlData urlData = UrlData.builder()
                            .originalUrl(originalUrl)
                            .build();

        urlData.makeShortenUrl();
        return urlData;
    }

    public UrlData setUrlData(String originalUrl) {
        UrlData urlData = UrlData.builder()
                            .originalUrl(originalUrl)
                            .build();
        urlData.makeShortenUrl();
        return urlData;
    }

}
