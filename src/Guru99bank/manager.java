package Guru99bank;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Guru99bankPOM.loginScrn;
import Guru99bankPOM.postLoginScrn;

public class manager {

	public static void main(String arg[]){
		WebDriver driver = new FirefoxDriver();
		loginScrn loginpage = new loginScrn(driver);
		postLoginScrn post = new postLoginScrn(driver);
		loginpage.initialization();
		loginpage.login();
		
		try {
			post.newAcc(1, 500);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
				

		//post.logout();
		driver.close();
	}
	
}
