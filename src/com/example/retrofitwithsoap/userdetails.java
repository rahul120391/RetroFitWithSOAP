package com.example.retrofitwithsoap;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

public class userdetails {
	@Attribute
	String xmlns;
	@Element
	String UserName;
	@Element
	String Password;
	public userdetails(String xmlns,String UserName,String Password)
	{
		this.xmlns=xmlns;
		this.UserName=UserName;
		this.Password=Password;
	}
	public String getXmlns() {
		return xmlns;
	}
	public String getUserName() {
		return UserName;
	}
	public String getPassword() {
		return Password;
	}
}
