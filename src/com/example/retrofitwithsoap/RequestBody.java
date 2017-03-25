package com.example.retrofitwithsoap;

import org.simpleframework.xml.Element;


public class RequestBody {
	
	@Element(name = "GetUserDetails")
	userdetails GetUserDetails;

	public userdetails getDetails() {
		return GetUserDetails;
	}

	public void setDetails(userdetails GetUserDetails) {
		this.GetUserDetails = GetUserDetails;
	}
	
}
