package com.example.demo.controller;

import com.example.demo.service.WebsiteBuilderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/website")
public class WebsiteBuilderController {

    private WebsiteBuilderService websiteBuilderService;

    public WebsiteBuilderController(WebsiteBuilderService websiteBuilderService){
        this.websiteBuilderService = websiteBuilderService;
    }

    @PostMapping
    public String generateWebsite(String message){
        return websiteBuilderService.generate(message);
    }
}
