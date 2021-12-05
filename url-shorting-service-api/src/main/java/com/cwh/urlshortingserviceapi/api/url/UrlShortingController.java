package com.cwh.urlshortingserviceapi.api.url;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/short/")
public class UrlShortingController {
    
    @GetMapping("{url}")
    public String getShortenUrl() {

        



        return null;
    }

}
