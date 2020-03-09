package io.realtycrunch.test.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * @comment 
 * @author <a href="mailto:bsllozad@gmail.com">Bernardo Lopez</a>
 * @project webscraping-api
 * @class ImageElement
 * @date Mar 8, 2020
 *
 */
@Data
public class ImageElement implements Serializable{

	private static final long serialVersionUID = -6415629418242866757L;
	
	private String url;
	private String alt;
	private String title;
	
	public ImageElement(String url, String alt, String title) {
		super();
		this.url = url;
		this.alt = alt;
		this.title = title;
	}

	public ImageElement() {
		super();
	}
	
	
	

}
