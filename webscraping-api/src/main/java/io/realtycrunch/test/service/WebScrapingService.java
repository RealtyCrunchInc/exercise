package io.realtycrunch.test.service;

import java.util.List;

import io.realtycrunch.test.domain.ResponseElement;

/**
 * 
 * @comment 
 * @author <a href="mailto:bsllozad@gmail.com">Bernardo Lopez</a>
 * @project webscraping-api
 * @class WebScrapingService
 * @date Mar 8, 2020
 *
 */
public interface WebScrapingService {
	
	public List<ResponseElement> searchProperties();

}
