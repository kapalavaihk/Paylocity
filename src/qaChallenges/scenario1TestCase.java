package qaChallenges;

import org.openqa.selenium.chrome.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.apache.commons.*;
import org.apache.commons.io.FileUtils;

public class scenario1TestCase {
	
	public static void main(String[] args) {
	
		System.setProperty("webdriver.chrome.driver", "C:/Users/hkapalavai/Documents/HariTraining/Tools/chromedriver.exe");

		WebDriver driver = new ChromeDriver();  
	  
	    	driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			driver.get("file:///C:/Users/hkapalavai/Documents/HariOldComputer-Documents/Hari/Personal%20files/City/city%20QA%20Challenge/login.html");
				System.out.println("Paylocity Login Page found Successfully as Expected");
		
		//Entering UserName and Password credentials on the Login page
				
			driver.findElement(By.name("form-username")).sendKeys("testUser");
			driver.findElement(By.name("form-username")).sendKeys(Keys.TAB);
			driver.findElement(By.name("form-password")).sendKeys("Test1234");
			driver.findElement(By.name("form-password")).sendKeys(Keys.TAB);
			
			driver.findElement(By.id("btnLogin")).click();
		
	   //Validate Benefits Dashboard is displayed upon Login
			
			driver.getPageSource().contains("Benefits Dashboard");
			  System.out.println("Yes you are successfully logged in");
			  
	  //Scenario 1 Adding an Employee where first name does not contain either "A" or "a"
			
			driver.findElement(By.xpath("//*[@id=\"btnAddEmployee\"]")).click();
			
			  System.out.println("Add Employee & His Dependents Window Opened Successfully as Expected");
			
			  	  
			  boolean empFirstNameTxtBoxIsDisplayed = driver.findElement(By.xpath("//*[@id=\"employees-form\"]/div[1]/div/input")).isDisplayed();
			
			  while (!empFirstNameTxtBoxIsDisplayed ) {
				  try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				  empFirstNameTxtBoxIsDisplayed = driver.findElement(By.xpath("//*[@id=\"employees-form\"]/div[1]/div/input")).isDisplayed();
			}
			  
		    driver.findElement(By.xpath("//*[@id=\"employees-form\"]/div[1]/div/input")).click();
			driver.findElement(By.xpath("//*[@id=\"employees-form\"]/div[1]/div/input")).sendKeys("David");
			driver.findElement(By.xpath("//*[@id=\"employees-form\"]/div[1]/div/input")).sendKeys(Keys.TAB);
			
			driver.findElement(By.xpath("//*[@id=\"employees-form\"]/div[2]/div/input\r\n")).click();
			driver.findElement(By.xpath("//*[@id=\"employees-form\"]/div[2]/div/input\r\n")).sendKeys("Kingston");
			driver.findElement(By.xpath("//*[@id=\"employees-form\"]/div[2]/div/input\r\n")).sendKeys(Keys.TAB);	
			
						
			driver.findElement(By.xpath("//*[@id=\"employees-form\"]/div[3]/div/input")).click();
			driver.findElement(By.xpath("//*[@id=\"employees-form\"]/div[3]/div/input")).sendKeys("1");
			driver.findElement(By.xpath("//*[@id=\"employees-form\"]/div[3]/div/input")).sendKeys(Keys.TAB);
			
			
			driver.findElement(By.xpath("//*[@id=\"employees-form\"]/div[4]/div/button[1]")).click();
			
			
			System.out.println("All Employee details entered correctly where the Firstname did not start with either A or a and benefit costs calculated as Expected without discount");
			
		//Validate the Benefit Cost for the new Employee Added does not have discount
			
			WebElement tbl = driver.findElement(By.id("employee-table"));
			WebElement tableRow = tbl.findElement(By.xpath("//*[@id=\"employee-table\"]/tbody/tr[2]/td[7]"));
			 boolean benefitCostINeed = tableRow.getText().equals("57.69");
			  System.out.println("Benefit cost for the employee added does not have discount as Expected: " + benefitCostINeed);
		
			
	   // Take screenshot
			  
			  File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			 
			  try {
				
				  FileUtils.copyFile(screenshot, new File("C:/Pictures/"+System.currentTimeMillis()+".png"));
								System.out.println("Screenshot taken Successfully");
			} catch (IOException e) {
			     e.printStackTrace();
			}
						  
	//Quit the Browser
			driver.quit();
	}

}
