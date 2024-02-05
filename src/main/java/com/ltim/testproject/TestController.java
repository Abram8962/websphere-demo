package com.ltim.testproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tests")
public class TestController {
	
	@GetMapping("/hi")
	public String getText() {
		return "hi I am executed successfuly";
	}

}
