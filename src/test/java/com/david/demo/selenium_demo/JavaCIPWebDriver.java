package com.david.demo.selenium_demo;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class JavaCIPWebDriver {
	private WebDriver driver;
	private TakesScreenshot screenDriver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		screenDriver = (TakesScreenshot) driver;
		baseUrl = "http://m033909:8080";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testJavaCIPWebDriver() throws Exception {
		String fileName="testJavaCIPWebDriver";
		int count=0;
		driver.get(baseUrl + "/cip/aau/web/");
		saveFile(fileName+(count++)+".jpg");
		//assertTrue("output are same",fileCompare("testcase10.jpg",fileName+(count++)+".jpg"));
		assertFalse("Message cannot contain 404", driver.getPageSource().contains("HTTP ERROR: 404"));
		driver.findElement(By.id("doLogin_userId")).clear();
		driver.findElement(By.id("doLogin_userId")).sendKeys("YOUR_USERID");
		driver.findElement(By.id("doLogin_password")).clear();
		driver.findElement(By.id("doLogin_password")).sendKeys("YOUR_PASSWORD");
		driver.findElement(By.id("doLogin_0")).click();
		//pulse(1);
		driver.findElement(By.cssSelector("#enquireAllot > span")).click();
		//pulse(1);
		saveFile(fileName+(count++)+".jpg");
		//assertTrue("output are same",fileCompare("testcase11.jpg",fileName+(count++)+".jpg"));
		assertFalse("Message cannot contain 404", driver.getPageSource().contains("HTTP ERROR: 404"));
		driver.findElement(By.name("search")).click();
		pulse(3);
		saveFile(fileName+(count++)+".jpg");
		//assertTrue("output are same",fileCompare("testcase12.jpg",fileName+(count++)+".jpg"));
		assertFalse("Message cannot contain 404", driver.getPageSource().contains("HTTP ERROR: 404"));
		pulse(1);
		driver.findElement(By.name("logout")).click();
		//pulse(3);
		saveFile(fileName+(count++)+".jpg");
		//assertTrue("output are same",fileCompare("testcase13.jpg",fileName+(count++)+".jpg"));
		assertFalse("Message cannot contain 404", driver.getPageSource().contains("HTTP ERROR: 404"));

	}

	private void pulse(int i) {
		try {
			Thread.sleep(i*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private boolean fileCompare(String src, String target) throws IOException{
		saveFile(target);
		return ImgDiffPercent.compare(new File(src), new File(target))<0.01;
	}



	private void saveFile(String fileName) throws IOException {
		File srcFile = screenDriver.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(fileName));
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
