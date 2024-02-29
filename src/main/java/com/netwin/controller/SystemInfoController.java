package com.netwin.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netwin.entity.SystemInfo;
import com.netwin.service.SystemInfoService;



@RestController
public class SystemInfoController {

@Autowired
private SystemInfoService systemInfoService;
	   
	   
	 @GetMapping("/systemdetails")
	    public SystemInfo getSystemDetails() {
	     
	        SystemInfo systemInfo = systemInfoService.getSystemDetails();
	      
	        
	         
	        return systemInfo;
	    }

		@GetMapping("/login")
		public String getLogin() {
			SystemInfo systemInfo = systemInfoService.getSystemDetails1();
			System.out.println("systemInfo: "+systemInfo);
			if(systemInfo!=null) {
			return "login";
			}
			return "error";
		}
		  
		 
	    
	}