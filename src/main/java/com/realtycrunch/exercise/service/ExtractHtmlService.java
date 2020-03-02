package com.realtycrunch.exercise.service;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.realtycrunch.exercise.dto.HtmlDto;

@Service
public class ExtractHtmlService {
    
    @Value("classpath:html/listing1.html")
    Resource listing1;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public HtmlDto extractHtmlListing1() {
        
        HtmlDto dto = new HtmlDto();
        
        try {
            Document doc = Jsoup.parse(listing1.getFile(),null);
            dto.setPrice(doc.getElementsByClass("textIntent-title2").get(0).text());
            dto.setBedrooms(doc.getElementsByClass("textIntent-title2").get(2).text());
            dto.setBathrooms(doc.getElementsByClass("textIntent-title2").get(3).text());
            dto.setAddress(doc.getElementsByAttributeValue("data-tn", "listing-page-address").get(0).text()+" "+
                    doc.getElementsByAttributeValue("data-tn", "listing-page-address-subtitle").get(0).text());
            
            List imgSrc = new ArrayList<>();
            for(Element ele : doc.getElementsByTag("img")) {
                if(!ele.attr("src").isEmpty()) {
                    imgSrc.add(ele.attr("src"));
                }
                if(!ele.attr("data-src").isEmpty()) {
                    imgSrc.add(ele.attr("data-src"));
                }
            }
            dto.setImageUrl(imgSrc);;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
        
    }

}
