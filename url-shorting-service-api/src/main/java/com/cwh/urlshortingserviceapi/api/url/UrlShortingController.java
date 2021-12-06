package com.cwh.urlshortingserviceapi.api.url;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("short")
@CrossOrigin("*")
public class UrlShortingController {
    
    private final UrlShortingService urlShortingService;

    @PostMapping("/url")
    public UrlData registerUrl(@RequestBody Map<String, Object> param) {
        String originalUrl = (String)param.get("originalUrl");
        UrlData urlData = urlShortingService.registerUrlData(originalUrl);
        return urlData;
    }

    @GetMapping("/url/{shortenUrl}")
    public UrlData inquiryUrlData(@PathVariable("shortenUrl") String shortenUrl) {
        return urlShortingService.inquiryUrlData(shortenUrl);
    }
}
