package com.realtycrunch.exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realtycrunch.exercise.dto.HtmlDto;
import com.realtycrunch.exercise.service.ExtractHtmlService;

@RestController
@RequestMapping("/parser")
public class HtmlExtractController {
    
    @Autowired
    private ExtractHtmlService service;
    
    @GetMapping("/html/listing1")
    public HtmlDto extractHtmlListting1() {
        
        return service.extractHtmlListing1();
    }
}
