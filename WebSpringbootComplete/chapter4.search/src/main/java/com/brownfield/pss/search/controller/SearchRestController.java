package com.brownfield.pss.search.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brownfield.pss.search.component.SearchComponent;
import com.brownfield.pss.search.entity.Flight;

@RefreshScope
@CrossOrigin
@RestController
@RequestMapping("/search")
class SearchRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(SearchRestController.class);
	
	private SearchComponent searchComponent;
	
	@Value("${originairports.shutdown}")
	private String originAirportShutdownlist;
	
	@Autowired
	public SearchRestController(SearchComponent searchComponent){
		this.searchComponent = searchComponent;
	}
	
	@RequestMapping(value="/get", method = RequestMethod.POST)
	List<Flight> search(@RequestBody SearchQuery query){
		System.out.println("Input : "+ query);
		
		if(Arrays.asList(originAirportShutdownlist.split(",")).contains(query.getOrigin())){
			logger.info("airport is shutdown.");
			return new ArrayList<>();
		}
		
		return searchComponent.search(query);
	}
 
}
