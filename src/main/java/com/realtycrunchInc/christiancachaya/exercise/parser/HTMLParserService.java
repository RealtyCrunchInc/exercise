package com.realtycrunchInc.christiancachaya.exercise.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.realtycrunchInc.christiancachaya.exercise.interfaces.HTMLParser;
import com.realtycrunchInc.christiancachaya.exercise.response.ParserResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class HTMLParserService implements HTMLParser{

	private static final String DATA_SRC_ATTR = "data-src";
	private static final String DATA_FLICKITY_LAZYLOAD_ATTR = "data-flickity-lazyload";
	private static final String SRC_ATTR = "src";
	private static final String IMAGES_TAG = "img";
	private static final String SECOND_COMPONENT_ADDRESS = "summary__SummaryCaption-e4c4ok-5 summary__StyledAddressCaption-e4c4ok-7 kvxbxd textIntent-caption2";
	private static final String FIRST_COMPONENT_ADDRESS = "summary__StyledAddress-e4c4ok-6 zWwUF textIntent-title1";
	private static final String PRICE_BEDS_BATHS_CLASS = ".summary__StyledSummaryDetailUnit-e4c4ok-13 dsPYTb,.textIntent-title2";
	private static final String HTML_LINK_ONE = "https://www.compass.com/listing/1049-5th-avenue-unit-6a-manhattan-ny-10028/29419790414139537/";
	private static final String HTML_LINK_TWO = "https://www.compass.com/listing/164-west-74th-street-unit-3b-manhattan-ny-10023/296952685807177073/";

	@Override
	public ParserResponse parser (String desiredOption) throws IOException, Exception {

		List<String> imageURLs = new ArrayList<>();

		if(!desiredOption.equals("1") && !desiredOption.equals("2")) {
			throw new Exception(String.format("Option %s is not allowed", desiredOption));
		}
		log.info("About to process option {}", desiredOption);
		Document doc = Jsoup.connect(desiredOption.equals("1") ? HTML_LINK_ONE : HTML_LINK_TWO).get();

		Elements elements = doc.select(PRICE_BEDS_BATHS_CLASS);
		String price = elements.get(0).text();
		String numberOfBedrooms = elements.get(1).text();
		String numberOfBathrooms = elements.get(2).text();
		String firstAdress = doc.getElementsByClass(FIRST_COMPONENT_ADDRESS).text();
		String secondAdress = doc.getElementsByClass(SECOND_COMPONENT_ADDRESS).text();
		Elements images = doc.select(IMAGES_TAG);
		processImagelinks(imageURLs, images);		

		return buildParserResponse(imageURLs, price, numberOfBedrooms, numberOfBathrooms, firstAdress,
				secondAdress);
	}

	private ParserResponse buildParserResponse(List<String> imageURLs, String price,String numberOfBedrooms, 
			String numberOfBathrooms, String firstAdress, String secondAdress) {
		ParserResponse parserResponse = new ParserResponse();

		parserResponse.setPrice(price);
		parserResponse.setNumberOfBedrooms(numberOfBedrooms);
		parserResponse.setNumberOfBathrooms(numberOfBathrooms);
		parserResponse.setAddress(firstAdress.concat(" - ").concat(secondAdress));
		parserResponse.setImageURLs(imageURLs);

		return parserResponse;
	}

	private void processImagelinks(List<String> imageURLs, Elements images) {
		images.stream().forEach(image -> {
			if(StringUtils.isNotBlank(image.attr(SRC_ATTR))) {
				imageURLs.add(image.attr(SRC_ATTR));
			}
			if(StringUtils.isNotBlank(image.attr(DATA_FLICKITY_LAZYLOAD_ATTR))) {
				imageURLs.add(image.attr(DATA_FLICKITY_LAZYLOAD_ATTR));
			}
			if(StringUtils.isNotBlank(image.attr(DATA_SRC_ATTR))) {
				imageURLs.add(image.attr(DATA_SRC_ATTR));
			}			
		});
	}
}
