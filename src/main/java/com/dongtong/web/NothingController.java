package com.dongtong.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dongtong.core.ApiException;

import io.swagger.annotations.ApiParam;

@RestController
public class NothingController {

    
	@RequestMapping(value = "/no/{date}", method = { RequestMethod.GET, RequestMethod.POST })
	public String rantRequest(@ApiParam(value = "时间格式:MM-dd", required = true)@PathVariable String date) {
		try {
			System.out.println(date);
		} catch (ApiException e) {
			return e.getStatusMessage();
		}
		return "现已加入肯德基豪华午餐";
	}
    

}
