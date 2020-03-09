package io.realtycrunch.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.realtycrunch.test.domain.ResponseElement;
import io.realtycrunch.test.service.WebScrapingService;

/**
 * @comment Rest controller class 
 * @author <a href="mailto:bsllozad@gmail.com">Bernardo Lopez</a>
 * @project webscraping-api
 * @class WebScrapingController
 * @date Mar 8, 2020
 *
 */
@RestController
@RequestMapping("/pages/properties")
public class WebScrapingController {
	
	@Autowired
	private WebScrapingService webScrapingService;
	
	@GetMapping("/search")
	public ResponseEntity<List<ResponseElement>> searchProperties() {
		List<ResponseElement> response = null;
		try {
			response = webScrapingService.searchProperties();
			return ResponseEntity.ok(response);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

}
