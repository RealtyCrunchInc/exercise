package io.realtycrunch.test.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.realtycrunch.test.domain.ResponseElement;

/**
 * 
 * @comment 
 * @author <a href="mailto:bsllozad@gmail.com">Bernardo Lopez</a>
 * @project webscraping-api
 * @class WebScrapingServiceTest
 * @date Mar 8, 2020
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class WebScrapingServiceTest {
	
	@Autowired
	private WebScrapingService webScrapingService;
	
	@Test
	@DisplayName("Search Properties")
	public void searchProperties() {
		List<ResponseElement> response = webScrapingService.searchProperties();
		
		assertNotNull(response, "The response object DataResponse is not null");
	}

}
