package com.springAPI.utils;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Component;

import com.google.common.hash.Hashing;

@Component
public class EncrytedPasswordUtils {
	
	  public String encrytePassword(String password) {
		  String sha256hex = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
	        return sha256hex;
	    }
	 
	

}
