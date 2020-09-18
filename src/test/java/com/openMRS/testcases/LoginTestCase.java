package com.openMRS.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.openMRS.base.TestBase;




public class LoginTestCase extends TestBase{
	
	
	@Test
	public void LoginTest() throws InterruptedException
	{
		log.debug("Inside Test");
		driver.findElement(By.xpath(OR.getProperty("location"))).click();;
		Thread.sleep(2000);
		
		log.debug("login succesfull");
		
		
	}
	

}
