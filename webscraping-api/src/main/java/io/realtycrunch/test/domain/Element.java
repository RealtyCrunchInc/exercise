package io.realtycrunch.test.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * @comment 
 * @author <a href="mailto:bsllozad@gmail.com">Bernardo Lopez</a>
 * @project webscraping-api
 * @class Element
 * @date Mar 8, 2020
 *
 */
@Data
public class Element implements Serializable{

	private static final long serialVersionUID = 8429645999352442963L;

	private String url;
	private String tagRoot;
	private String tagPrice;
	private String tagNumberBedrooms;
	private String tagNumberBathrooms;
	private String tagAddress;
	private String tagAddressSubtitle;
	private String tagImages;
	
	public Element() {
		super();
	}
	
	public Element(String url, String tagRoot, String tagPrice, String tagNumberBedrooms, String tagNumberBathrooms, String tagAddress, String tagAddressSubtitle, String tagImages) {
		super();
		this.url = url;
		this.tagRoot = tagRoot;
		this.tagPrice = tagPrice;
		this.tagNumberBedrooms = tagNumberBedrooms;
		this.tagNumberBathrooms = tagNumberBathrooms;
		this.tagAddress = tagAddress;
		this.tagAddressSubtitle = tagAddressSubtitle;
		this.tagImages = tagImages;
	}

}
