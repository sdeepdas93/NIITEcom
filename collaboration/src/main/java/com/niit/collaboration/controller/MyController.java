package com.niit.collaboration.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	
	
	
	@GetMapping(value="/test")
    public String onLoad() {
    	
    	return "niit"; 
    }
	
	@GetMapping(value="/home")
    public String goHome() {
    	
    	return "home"; 
    } 
	
	@GetMapping(value="/like")
    public String like() {
    	
    	return "like"; 
    } 
	
	
}
