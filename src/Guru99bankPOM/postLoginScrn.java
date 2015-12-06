package Guru99bankPOM;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class postLoginScrn {

	WebDriver driver;
	
	public postLoginScrn(WebDriver driver){
		this.driver = driver;
	}
	
	By newcustomer = By.linkText("New Customer");
	By customerName = By.name("name");
	By maleRadio = By.cssSelector("[type='radio'][value = 'm']");
	By fmailRadio = By.cssSelector("[type='radio'][value = 'f']");
	By dateofBirth = By.name("dob");
	By address = By.name("addr");
	By addcity = By.name("city");
	By addstate = By.name("state");
	By pin = By.name("pinno");
	By telephone = By.xpath("//tr[11]/td[2]/input");
	By email = By.xpath("//tr[12]/td[2]/input");
	By pass = By.xpath("//tr[13]/td[2]/input");
	By submite = By.xpath("//tr[14]/td[2]/input[1]");
	By reset = By.xpath("//tr[14]/td[2]/input[2]");
	By cusIDno = By.xpath(".//*[@id='customer']/tbody/tr[4]/td[2]");
	By conti = By.linkText("Continue");

	
	By edtCus = By.linkText("Edit Customer");
		By edtCusID = By.xpath("//tr[6]/td[2]/input");
		By edtSub = By.xpath("//tr[11]/td[2]/input[1]");
		
	By delCus = By.linkText("Delete Customer");
		By delCusID = By.xpath("//tr[2]/td[2]/input");
		By delSub = By.xpath("//tr[7]/td[2]/input[1]");
	
	By newAcc = By.linkText("New Account");
		By newAccID = By.xpath("//tr[2]/td[2]/input");
		By newAccType = By.xpath("//select");
		By newAccDep = By.xpath("//tr[4]/td[2]/input");
		By newAccSub = By.name("button2");
		
	By logout = By.linkText("Log out");
	
	

	
	
	public void logout(){
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		if(driver.findElement(logout).isDisplayed()){
			driver.findElement(logout).click();
			System.out.println("Login Successfull");
		}
		else
			System.out.println("Logout is not visibble");
		driver.close();
	}
	
	public void editCus(int cusID) throws IOException{
		String location = "C:/tmp/Customer.xlsx";
		driver.findElement(edtCus).click();
		driver.findElement(edtCusID).sendKeys(readdata(cusID, location));
		driver.findElement(edtSub).click();
	}
	
	public void delCus(int cusID) throws IOException{
		String location = "C:/tmp/Customer.xlsx";
		driver.findElement(delCus).click();
		driver.findElement(delCusID).sendKeys(readdata(cusID, location));
		driver.findElement(delSub).click();
		driver.switchTo().alert().accept();
	}
	
	public void newAcc(int cusID, int deposite) throws IOException{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String location = "C:/tmp/Customer.xlsx";
		driver.findElement(newAcc).click();
		driver.findElement(newAccID).sendKeys(readdata(cusID, location));
		WebElement selector = driver.findElement(newAccType);
		selector.click();
//		driver.findElement(By.linkText("Current")).click();
		Select dropdown = new Select(selector);
		dropdown.selectByValue("Current");;
		driver.findElement(newAccDep).sendKeys("500");
		driver.findElement(newAccSub).click();
	}

	public void inputvalues(WebDriver driver, int cusID) throws IOException{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(newcustomer).click();
		//open file
		String location = "C:/tmp/Customer.xlsx";
		FileInputStream file = new FileInputStream(new File(location));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet firstSheet = workbook.getSheetAt(0);
        
        
        driver.findElement(customerName).sendKeys(readcell(cusID, 1, firstSheet));
        String gender = readcell(cusID, 2, firstSheet);
        
        //gender
        if(gender.equals("male"))
        	driver.findElement(maleRadio).click();
        else if(gender.equals("female"))
        	driver.findElement(fmailRadio).click();
        else
        	System.out.println("gender input is not valid");
        
        driver.findElement(dateofBirth).sendKeys(readcell(cusID, 3, firstSheet));
        driver.findElement(address).sendKeys(readcell(cusID, 4, firstSheet));
        driver.findElement(addcity).sendKeys(readcell(cusID, 5, firstSheet));
        driver.findElement(addstate).sendKeys(readcell(cusID, 6, firstSheet));
        driver.findElement(pin).sendKeys(readcell(cusID, 7, firstSheet));
        driver.findElement(telephone).sendKeys(readcell(cusID, 8, firstSheet));
        driver.findElement(email).sendKeys(readcell(cusID, 9, firstSheet));
        driver.findElement(pass).sendKeys(readcell(cusID, 10, firstSheet));
        driver.findElement(submite).click();
        workbook.close();
        file.close();
        
        if(driver.findElement(cusIDno).isDisplayed()){
        	String data = driver.findElement(cusIDno).getText();
        	System.out.println(data);
        	writeData(data, cusID, location );
        }
        
        driver.findElement(conti).click();
	}
	
	public String readcell(int raw, int call, Sheet sheet){
		String output;
		Cell cell = sheet.getRow(raw).getCell(call);
			switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					output = cell.getStringCellValue();
					//System.out.print(cell.getStringCellValue());
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					output = String.valueOf(cell.getBooleanCellValue());
					//System.out.print(cell.getBooleanCellValue());
					break;
				case Cell.CELL_TYPE_NUMERIC:
					output = String.valueOf((int)cell.getNumericCellValue());
					//System.out.print(cell.getNumericCellValue());
					break;
				case Cell.CELL_TYPE_FORMULA:
					output = String.valueOf(cell.getCellFormula());
					//System.out.print(cell.getCellFormula());
				default:
					output = "";
					break;
				}
		
		return output;
	}
	
		public void writeData(String data, int raw, String location) throws IOException{
			FileOutputStream fileOut = new FileOutputStream(location);
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("POI Worksheet");
			Cell cell = sheet.getRow(raw).getCell(0);
			cell.setCellValue(data);
            workbook.write(fileOut);
			workbook.close();
			fileOut.close();
			//Some bug left
			
		}
		
		public String readdata(int raw, String location) throws IOException{
			FileInputStream fileIn = new FileInputStream(location);
			Workbook workbook = new XSSFWorkbook(fileIn);
			Sheet sheet = workbook.getSheetAt(0);
			String output = readcell(raw, 0, sheet);
			workbook.close();
			fileIn.close();
			return output;
		}
	
}
