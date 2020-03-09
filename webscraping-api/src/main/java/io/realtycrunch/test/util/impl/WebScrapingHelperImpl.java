package io.realtycrunch.test.util.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import io.realtycrunch.test.common.ConstantstWebScrpaing;
import io.realtycrunch.test.domain.Element;
import io.realtycrunch.test.domain.ImageElement;
import io.realtycrunch.test.domain.ResponseElement;
import io.realtycrunch.test.util.WebScrapingHelper;

/**
 * 
 * @comment 
 * @author <a href="mailto:bsllozad@gmail.com">Bernardo Lopez</a>
 * @project webscraping-api
 * @class WebScrapingHelperImpl
 * @date Mar 8, 2020
 *
 */
@Component
public class WebScrapingHelperImpl implements WebScrapingHelper{

	private final Logger LOG = LoggerFactory.getLogger(WebScrapingHelperImpl.class);
	private final int TIME_OUT_REQUEST = 100000; 
	
	private ResponseElement responseElement;

	private int getStatusConnectionCode(String url) {
		Response response = null;
		try {
			response = Jsoup.connect(url).userAgent(HttpConnection.DEFAULT_UA).timeout(TIME_OUT_REQUEST).ignoreHttpErrors(true).execute();
		} catch (IOException e) {
			LOG.error("Error trying to get a Status Code: ", e.getMessage());
		}
		return response.statusCode();
	}

	private Document getHtmlDocument(String url) {
		Document doc = null;
		try {
			doc = Jsoup.connect(url).userAgent(HttpConnection.DEFAULT_UA).timeout(TIME_OUT_REQUEST).get();
		} catch (IOException e) {
			LOG.error("Error trying to get HTML web page: ", e.getMessage());
		}
		return doc;
	}
	
	public ResponseElement processElement(Element element) {
		
		try {
			if (getStatusConnectionCode(element.getUrl()) == HttpStatus.OK.value()) {
				
				responseElement = new ResponseElement();
				Document document = getHtmlDocument(element.getUrl());
				
				Elements inPrice = document.getElementsByAttributeValue(element.getTagPrice().split(ConstantstWebScrpaing.TAGS_SEPARATOR)[0], element.getTagPrice().split(ConstantstWebScrpaing.TAGS_SEPARATOR)[1]);
				Elements inBedrooms = document.getElementsByAttributeValue(element.getTagNumberBedrooms().split(ConstantstWebScrpaing.TAGS_SEPARATOR)[0], element.getTagNumberBedrooms().split(ConstantstWebScrpaing.TAGS_SEPARATOR)[1]);
				Elements inBathrooms = document.getElementsByAttributeValue(element.getTagNumberBathrooms().split(ConstantstWebScrpaing.TAGS_SEPARATOR)[0], element.getTagNumberBathrooms().split(ConstantstWebScrpaing.TAGS_SEPARATOR)[1]);
				Elements inAddressSubtitle = document.getElementsByAttributeValue(element.getTagAddressSubtitle().split(ConstantstWebScrpaing.TAGS_SEPARATOR)[0], element.getTagAddressSubtitle().split(ConstantstWebScrpaing.TAGS_SEPARATOR)[1]);
				Elements inImages = document.getElementsByClass(element.getTagImages());
				
				List<ImageElement> images = new ArrayList<>();
				
				inImages.forEach(obj -> {
					org.jsoup.nodes.Element ele = obj.getElementsByTag("img").first();
					String src = ele.attr("src");
					if (src == null || src.isEmpty()) {
						images.add(new ImageElement(ele.attr("data-flickity-lazyload"), null, null));
					} else {
						images.add(new ImageElement(ele.attr("src"), null, null));
					}
				});
				
				
				responseElement.setPrice(inPrice.text());
				responseElement.setAddress(inAddressSubtitle.text());
				responseElement.setNumberBathrooms(inBathrooms.text());
				responseElement.setNumberBedrooms(inBedrooms.text());
				responseElement.setImages(images);
				
				
			} else {
				LOG.error("Error conection status: ", getStatusConnectionCode(element.getUrl()));
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		
		
		return responseElement;
		
	}

}
