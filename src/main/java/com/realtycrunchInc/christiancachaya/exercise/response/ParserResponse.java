package com.realtycrunchInc.christiancachaya.exercise.response;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ParserResponse {
	
	private String price;
	private String numberOfBedrooms;
	private String numberOfBathrooms;
	private String address;
	private List<String> imageURLs;

}
