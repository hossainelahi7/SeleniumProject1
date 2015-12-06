package Guru99bankPOM;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class loginScrn {
	
	WebDriver driver;
	By userid = By.name("uid");
	By password = By.name("password");
	By loginButton = By.name("btnLogin");
	
	
	
	
	
	public loginScrn(WebDriver driver){
		this.driver= driver;
	}
	public void initialization(){
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/V4/index.php");
		System.out.println("Initialization Successfull");
	}
	
	public void login(){
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(userid).sendKeys("mngr23137");
		driver.findElement(password).sendKeys("ytUdaqU");
		driver.findElement(loginButton).click();
		System.out.println("Login Successfull");
	}

	
	
	
}
