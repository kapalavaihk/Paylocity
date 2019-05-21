package qaChallenges;

import org.openqa.selenium.chrome.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
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

public class scenario2TestCase {
	
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
			
	   //Scenario 2 Adding Employees where first name contains either "A" or "a"
			
			String [] employeeFirstName  = {"Andy", "aaron"};
				
			for (int i = 0; i < 2; i++) 
				{
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
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
			driver.findElement(By.xpath("//*[@id=\"employees-form\"]/div[1]/div/input")).sendKeys(employeeFirstName[i]);
			driver.findElement(By.xpath("//*[@id=\"employees-form\"]/div[1]/div/input")).sendKeys(Keys.TAB);
			
			driver.findElement(By.xpath("//*[@id=\"employees-form\"]/div[2]/div/input\r\n")).click();
			driver.findElement(By.xpath("//*[@id=\"employees-form\"]/div[2]/div/input\r\n")).sendKeys("Ftacek");
			driver.findElement(By.xpath("//*[@id=\"employees-form\"]/div[2]/div/input\r\n")).sendKeys(Keys.TAB);	
			
						
			driver.findElement(By.xpath("//*[@id=\"employees-form\"]/div[3]/div/input")).click();
			driver.findElement(By.xpath("//*[@id=\"employees-form\"]/div[3]/div/input")).sendKeys("1");
			driver.findElement(By.xpath("//*[@id=\"employees-form\"]/div[3]/div/input")).sendKeys(Keys.TAB);
			
			
			driver.findElement(By.xpath("//*[@id=\"employees-form\"]/div[4]/div/button[1]")).click();
			
			
			System.out.println("All Employee details entered correctly where the Firstname started with either A or a and benefit costs calculated as Expected with discount");
				}
			
       //Validate the Benefit Cost for the new Employee Added have discount
			
			WebElement tbl = driver.findElement(By.id("employee-table"));
			WebElement tableRow = tbl.findElement(By.xpath("//*[@id=\"employee-table\"]/tbody/tr[2]/td[7]"));
			String empFirstName = tbl.findElement(By.xpath("//*[@id=\"employee-table\"]/tbody/tr[2]/td[2]")).getText();
			String empFirstName2 = tbl.findElement(By.xpath("//*[@id=\"employee-table\"]/tbody/tr[3]/td[2]")).getText();
			WebElement tableRow2 = tbl.findElement(By.xpath("//*[@id=\"employee-table\"]/tbody/tr[3]/td[7]"));
			
			 boolean benefitCostINeed = tableRow.getText().equals("51.92");
			 boolean benefitCostINeed2 = tableRow2.getText().equals("51.92");
			 
			  System.out.println("Benefit cost for the employee" + " " + empFirstName+ " " + "added has discount as Expected: " + benefitCostINeed);
			  System.out.println("Benefit cost for the employee" + " " + empFirstName2+ " " +"added has discount as Expected: " + benefitCostINeed2);
			
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


