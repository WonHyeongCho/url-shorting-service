package com.cwh.urlshortingserviceapi.api.url;

import java.util.Hashtable;

import org.springframework.stereotype.Service;

@Service
public class UrlShortingService {
    private Hashtable<String, UrlData> urlHashtable;

    public UrlShortingService() {
        urlHashtable = new Hashtable<>();
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
