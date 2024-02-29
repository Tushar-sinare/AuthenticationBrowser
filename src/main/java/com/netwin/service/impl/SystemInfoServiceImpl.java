package com.netwin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import com.netwin.dto.SystemInfoDto;
import com.netwin.entity.SystemInfo;
import com.netwin.repo.SystemInfoRepository;
import com.netwin.service.SystemInfoService;
@Service
public class SystemInfoServiceImpl implements SystemInfoService {
@Autowired
SystemInfoRepository repo;
	@Override
	public SystemInfo getSystemDetails() {
		SystemInfo systemInfo = new SystemInfo();
	       systemInfo.setHostName(getHostName());
	       systemInfo.setMacAddress(getMACAddress());
	       systemInfo.setHardDiskId(getHardDiskId());
	       systemInfo.setProductId(getProductId());
	       systemInfo.setIpAddress(getIPAddress());
	        
	         SystemInfo sys = repo.save(systemInfo);
	        return sys;
	    }

		 
		  
		 
	    private String getHostName() {
	        try {
	            return InetAddress.getLocalHost().getHostName();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return "Unknown";
	    }

	    private String getIPAddress() {
	        try {
	            return InetAddress.getLocalHost().getHostAddress();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return "Unknown";
	    }

	    private String getMACAddress() {
	        try {
	            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
	            while (networkInterfaces.hasMoreElements()) {
	                NetworkInterface networkInterface = networkInterfaces.nextElement();
	               
	                byte[] mac = networkInterface.getHardwareAddress();
	                if (mac != null) {
	                    StringBuilder macAddress = new StringBuilder();
	                    for (int i = 0; i < mac.length; i++) {
	                        macAddress.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
	                    }
	                    return macAddress.toString();
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return "Unknown";
	    }

	    private String getHardDiskId() {
	    	 try {
	    	        Process process = Runtime.getRuntime().exec("wmic diskdrive get serialnumber");

	    	        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
	    	            String line;
	    	            
	    	            while ((line = reader.readLine()) != null) {
	    	            	System.out.println(line);
	    	                if (!line.trim().isEmpty()&& !line.contains("SerialNumber")) {
	    	                   
	    	                    return line.trim();
	    	                }
	    	            }
	    	        }
	    	    } catch (IOException e) {
	    	        e.printStackTrace();
	    	    }
	        return "Unknown";
	    }

	    private String getProductId() {
	    	 String productId = null;
	         String osName = System.getProperty("os.name").toLowerCase();
	         
	         if (osName.contains("windows")) {
	             productId = getWindowsProductID();
	         }
	         
	         return productId;
	    }
	    private static String getWindowsProductID() {
	        try {
	            Process process = Runtime.getRuntime().exec("wmic os get SerialNumber");
	            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	            String line;
	            while ((line = reader.readLine()) != null) {
	                if (!line.trim().isEmpty() && !line.contains("SerialNumber")) {
	                    return line.trim();
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }




		@Override
		public SystemInfo getSystemDetails1() {
			SystemInfo systemInfo = new SystemInfo();
			SystemInfo systemInfo1 = new SystemInfo();
		       systemInfo.setHostName(getHostName());
		       systemInfo.setMacAddress(getMACAddress());
		       systemInfo.setHardDiskId(getHardDiskId());
		       systemInfo.setProductId(getProductId());
		       systemInfo.setIpAddress(getIPAddress());
		        try {
		         SystemInfo sys = repo.findByHardDiskIdAndMacAddress(systemInfo.getHardDiskId(),systemInfo.getMacAddress());
		         systemInfo1.setHardDiskId(sys.getHardDiskId());
		         systemInfo1.setHostName(sys.getHostName());
		         systemInfo1.setIpAddress(sys.getIpAddress());
		         systemInfo1.setProductId(sys.getProductId());
		         systemInfo1.setMacAddress(sys.getMacAddress());
		         if(systemInfo.equals(systemInfo1)) {
		        	 
		        return systemInfo1;
		         }
		        }
		         catch(Exception ex) {
		        	ex.printStackTrace();
		         }
		         return null;
			
		}
	}