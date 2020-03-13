package com.realtycrunchInc.christiancachaya.exercise.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.realtycrunchInc.christiancachaya.exercise.parser.HTMLParserService;
import com.realtycrunchInc.christiancachaya.exercise.response.ParserResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/parser")
public class ParserController {
		
	private final HTMLParserService parserService;
	
	@GetMapping("/html")
    public ResponseEntity<ParserResponse> register(@RequestParam String desiredOption) {
		
		ResponseEntity<ParserResponse> response;
        try {        	
			response = ResponseEntity.ok().body(parserService.parser(desiredOption));
		} catch (Exception e) {
			log.error(e.getMessage());
			response = ResponseEntity.badRequest().build();
		}
        return response;
    }

}
