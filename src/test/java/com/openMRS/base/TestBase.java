package com.openMRS.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;



public class TestBase {


	/*
	 * Initialize excel
	 * Initialize Webdriver
	 * Initialize Logs
	 * Initialize extend report
	 * Initialize DB
	 * Initialize mail
	 *
	 * 
	 */

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;

	public static Logger log = Logger.getLogger("devpinoyLogger");  //Log code 


	@BeforeSuite
	public void StartUp()
	{

		if(driver == null)
		{


			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				config.load(fis);

				log.debug("config file loaded");
			} catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file loaded");

			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			if(config.getProperty("browser").equals("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");  
				driver = new ChromeDriver();

				log.debug("Chrome launched !!!");



			}
			driver.manage().window().maximize();

			driver.get(config.getProperty("testsiteurl"));


			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);


		} 


	}


	@AfterSuite
	public void TearDown()
	{


		driver.quit();

		log.debug("Application closed.");


	}
}



