/**
 * 
 */
package com.brownfield.pss.book.component;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author vivekmalhotra
 *
 */
@RibbonClient(name="fares")
@FeignClient(name = "fares-proxy")
public interface FareServiceProxy {

	@RequestMapping(value = "fares/get", method = RequestMethod.GET)
	Fare getFare(@RequestParam(value = "flightNumber") String flightNumber,
			@RequestParam(value = "flightDate") String flightDate);
}
