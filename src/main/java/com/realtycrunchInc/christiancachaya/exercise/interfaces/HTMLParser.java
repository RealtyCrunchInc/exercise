package com.realtycrunchInc.christiancachaya.exercise.interfaces;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.realtycrunchInc.christiancachaya.exercise.response.ParserResponse;

@Component
public interface HTMLParser {
	
	ParserResponse parser (String desiredOption) throws IOException, Exception;

}
