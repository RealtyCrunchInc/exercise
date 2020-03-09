package io.realtycrunch.test.util;

import io.realtycrunch.test.domain.Element;
import io.realtycrunch.test.domain.ResponseElement;

/**
 * 
 * @comment 
 * @author <a href="mailto:bsllozad@gmail.com">Bernardo Lopez</a>
 * @project webscraping-api
 * @class WebScrapingHelper
 * @date Mar 8, 2020
 *
 */
public interface WebScrapingHelper {
	
	public ResponseElement processElement(Element element);

}
