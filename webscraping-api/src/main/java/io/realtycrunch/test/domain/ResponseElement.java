package io.realtycrunch.test.domain;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 
 * @comment 
 * @author <a href="mailto:bsllozad@gmail.com">Bernardo Lopez</a>
 * @project webscraping-api
 * @class ResponseElement
 * @date Mar 8, 2020
 *
 */
@Data
public class ResponseElement implements Serializable{

	private static final long serialVersionUID = 8429645999352442963L;

	private String price;
	private String numberBedrooms;
	private String numberBathrooms;
	private String address;
	private List<ImageElement> images;
	
	public ResponseElement() {
		super();
	}

	public ResponseElement(String price, String numberBedrooms, String numberBathrooms, String address, List<ImageElement> images) {
		super();
		this.price = price;
		this.numberBedrooms = numberBedrooms;
		this.numberBathrooms = numberBathrooms;
		this.address = address;
		this.images = images;
	}

}
