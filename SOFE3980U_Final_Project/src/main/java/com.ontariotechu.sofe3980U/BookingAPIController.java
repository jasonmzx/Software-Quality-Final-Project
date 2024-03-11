package com.ontariotechu.sofe3980U;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

//Rest API portion of the Booking System

@RestController
public class BookingAPIController {

	@GetMapping("/bookadd")
	public String addString(@RequestParam(name="operand1", required=false, defaultValue="") String operand1,
                       @RequestParam(name="operand2", required=false, defaultValue="") String operand2) {

        String s = operand1+operand2;

        return s;
            // test ex: http://localhost:8080/bookadd?operand1=hello&operand2=world : helloworld
	}
	
}