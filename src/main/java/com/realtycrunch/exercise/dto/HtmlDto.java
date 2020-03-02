package com.realtycrunch.exercise.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class HtmlDto implements Serializable {

    private static final long serialVersionUID = -1322313934820790344L;
    private String price;
    private String bedrooms;
    private String bathrooms;
    private String address;
    private List imageUrl;
}
