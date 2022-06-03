package Assignment_1_TestingFunctionality;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class FunctinalityValidation_LinearWay {
	
	WebDriver driver;
	WebDriverWait wait;
	String url ="https://www.rahulshettyacademy.com/AutomationPractice/";
	
	@Before
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		wait = new WebDriverWait(driver,20);
		driver.get(url);
	}
	
	@Test
	public void radioBtnValidation()
	{
		WebElement radioBtn = driver.findElement(By.xpath("//input[@value='radio2']"));
		radioBtn.click();
		
		Assert.assertEquals("Radio Btn not clicked",true,radioBtn.isSelected());
		System.out.println("Test case_1 passed => Radion2 clicked");
	}
	@Test
	
	public void dropDown()
	{
		WebElement dropDownList = driver.findElement(By.xpath("//select[@id='dropdown-class-example']"));
		Select optionSelect = new Select(dropDownList);
		optionSelect.selectByIndex(3);
		System.out.println("Test case_2 passed => option3 selected");
	}
	
	@Test
	public void checkboxSelection()
	{
		WebElement checkBox = driver.findElement(By.xpath("//input[@value=\"option1\"]"));
		checkBox.click();
		Assert.assertEquals("CheckBox is not selected",true,checkBox.isSelected());
		System.out.println("Test case_3 =>option1 selected");
	}
	
	@Test
	public void switchToNewBrowser() throws InterruptedException
	{
		WebElement OpenNewWindow=driver.findElement(By.xpath("//button[@id='openwindow']"));
		OpenNewWindow.click();
		
		ArrayList<String> windowHandle=new ArrayList<String>(driver.getWindowHandles());
		
		String ParentID=windowHandle.get(0);
		String ChildID=windowHandle.get(1);
		
		driver.switchTo().window(ChildID);
		Assert.assertEquals("New Window URL not Match","http://www.qaclickacademy.com/", driver.getCurrentUrl());
		System.out.println("URL on new window is: "+ driver.getCurrentUrl());
		System.out.println(" Test Case_4 passed => New Window URL  Match");
		
		Thread.sleep(2000);
	}
	
	@Test
	public void alertHandling() throws InterruptedException
	{
		WebElement alertBox = driver.findElement(By.xpath("//input[@id='name']"));
		alertBox.sendKeys("Hi");
		
		WebElement clickBtn = driver.findElement(By.xpath("//input[@id='alertbtn']"));
		clickBtn.click();
		
		Alert generateAlert = driver.switchTo().alert();		
    	String AlertMsg = generateAlert.getText();
     	Thread.sleep(3000);
			
    	Assert.assertTrue("Alert massage not Match", AlertMsg.contains("Hi"));
    	generateAlert.accept();
    	System.out.println("alert message match");
    	Thread.sleep(2000);
		
		System.out.println("Teas case_5 => passed");
		
	}
	
	 @After
	    public void tearDown() throws InterruptedException
	    {
	    	Thread.sleep(3000);
	    	driver.quit();
	    }

}
