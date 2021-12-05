package com.cwh.urlshortingserviceapi.api.url;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("short")
@Slf4j
public class UrlShortingController {
    
    private final UrlShortingService urlShortingService;

    @PostMapping("/url")
    public Object registerUrl(@RequestBody Map<String, Object> param) {
        String originalUrl = (String)param.get("originalUrl");
        return urlShortingService.setUrlData(originalUrl);
    }
}
