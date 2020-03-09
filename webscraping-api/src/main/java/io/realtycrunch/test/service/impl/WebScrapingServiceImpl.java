package io.realtycrunch.test.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.realtycrunch.test.common.ConstantstWebScrpaing;
import io.realtycrunch.test.domain.Element;
import io.realtycrunch.test.domain.ResponseElement;
import io.realtycrunch.test.domain.impl.CompassElement;
import io.realtycrunch.test.service.WebScrapingService;
import io.realtycrunch.test.util.WebScrapingHelper;

/**
 * 
 * @comment 
 * @author <a href="mailto:bsllozad@gmail.com">Bernardo Lopez</a>
 * @project webscraping-api
 * @class WebScrapingServiceImpl
 * @date Mar 8, 2020
 *
 */
@Service
public class WebScrapingServiceImpl implements WebScrapingService {
	
	private final Logger LOG = LoggerFactory.getLogger(WebScrapingServiceImpl.class);
	
	@Autowired
	private WebScrapingHelper webScrapingHelper;
	
	@Value("${page.compass.url}")
	private String urlCompass;
	@Value("${page.compass.tag.root}")
	private String tagRootCompass;
	@Value("${page.compass.tag.price}")
	private String tagPriceCompass;
	@Value("${page.compass.tag.bedrooms}")
	private String tagBedroomCompass;
	@Value("${page.compass.tag.bathrooms}")
	private String tagBathroomCompass;
	@Value("${page.compass.tag.address}")
	private String tagAddressCompass;
	@Value("${page.compass.tag.address.subtitle}")
	private String tagAddressSubtitleCompass;
	@Value("${page.compass.tag.images}")
	private String tagImagesCompass;
	@Value("${page.compass.parse.timeout.ms}")
	private Integer parseTimeoutMillisCompass;
	private List<Element> response = new ArrayList<>();
	

	@Override
	public List<ResponseElement> searchProperties() {
		
		List<ResponseElement> responseElements = new ArrayList<>();
		String[] urls = urlCompass.split(ConstantstWebScrpaing.TAGS_SEPARATOR);
		
		Arrays.asList(urls).forEach(url ->{
			Element element = getCompassElement();
			element.setUrl(url.toString());
			ResponseElement responseElement = webScrapingHelper.processElement(element);
			responseElements.add(responseElement);
		});
		
		return responseElements;
	}
	
	private CompassElement getCompassElement() {
		CompassElement element = new CompassElement();
		element.setTagRoot(tagRootCompass);
		element.setTagPrice(tagPriceCompass);
		element.setTagNumberBedrooms(tagBedroomCompass);
		element.setTagNumberBathrooms(tagBathroomCompass);
		element.setTagAddress(tagAddressCompass);
		element.setTagAddressSubtitle(tagAddressSubtitleCompass);
		element.setTagImages(tagImagesCompass);
		return element;
		
	}

}
