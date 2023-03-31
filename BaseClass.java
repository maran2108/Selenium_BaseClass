package org.baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
	static ExtentReports report;
	static ExtentHtmlReporter htmlReport;
	
	//Chrome-Browser launch
	public static WebDriver chromeLaunch() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver=new ChromeDriver(options);
		return driver;
	}
	
	// edge-launch
	public static WebDriver edgeLaunch() {
		WebDriverManager.edgedriver().setup();
		driver=new EdgeDriver();
		return driver;
	}
	
	// firefox-launch
	public static WebDriver firefoxLaunch() {
		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
		return driver;
		
	}
	
//	all Browser-launch
	public static void browserLaunch(String browserName) {
		
		switch (browserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();			
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;
		default:
			System.out.println("invalid browser name");
			break;
		}

	}
	
//	 Get title
	public static String getTitle() {
		String title = driver.getTitle();
		return title;
	}
//	URL launch
	public static  void urlLaunch(String url) {
		driver.get(url);

	}
//	Implicity time wait
	public static void impWait(int sec) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
		driver.manage().window().maximize();
	}

	
//	getting current url
	public static String getCurrentUrl() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}
		
//  sendkeys
	public static  void sendKeys(WebElement e,String data) {
		e.sendKeys(data);		
	}
	
//	click
	public static void clickButton(WebElement clk) {
		clk.click();
	}
	
//	quit
	public static void quitBrowser() {
		driver.quit();
	}
	
//	get text
	public static String getText(WebElement e) {
		String getText = e.getText();
		return getText;
	}
	
//	get attribute
	public static String getAttribute(WebElement e) {
		String getAttribute = e.getAttribute("value");
		return  getAttribute;
	}
	
//	get Attribute from webpage
	public static String getAttributeWebPage(WebElement e) {
		String webPageText = e.getAttribute("innnerText");
		return webPageText;
	}
	
// ---------------------Alert---------------------------------------------------

// simple alert	
	public static void simpleAlert() {
		Alert a=driver.switchTo().alert();
		a.accept();		
	}
	
// confirm alert
	public static void confirmAlert() {
		Alert a=driver.switchTo().alert();
		a.dismiss();
	}
	
//	prompt alert
	public static void promptAlert(String datas) {
		Alert a=driver.switchTo().alert();
		a.sendKeys(datas);
		a.accept();
	}
	
//	get text from alert box
	public static String getTextAlert() {
		Alert a=driver.switchTo().alert();
		String AlertText = a.getText();
		return AlertText;
	}	
	
//	------------------------Actions---------------------------------------------------
	
//	move to element
	public static void moveToElement(WebElement e) {
		Actions ac=new Actions(driver);
		ac.moveToElement(e).perform();
	}
	
//	action click
	public static void actionClick(WebElement e) {
		Actions ac=new Actions(driver);
		ac.click(e).perform();
	}
	
//	drag and drop
	public static void dragAndDrop(WebElement from,WebElement to) {
		Actions ac=new Actions(driver);
		ac.dragAndDrop(from, to).perform();	
	}
	
//	click,hold,moveto and release
	public static void clickHoldMoveToRelease(WebElement from,WebElement to) {
		Actions ac=new Actions(driver);
		ac.clickAndHold(from).moveToElement(to).release().perform();
	}
	
//	click, hold and release
	public static void clickHoldRelaese(WebElement from,WebElement to) {
		Actions ac=new Actions(driver);
		ac.clickAndHold(from).release(to).perform();
	}
	
//	Context click(right click)
	public static void contextClick(WebElement e) {
		Actions ac=new Actions(driver);
		ac.contextClick(e);
	}
	
//	double click
	public static void doubleClick(WebElement e) {
		Actions ac=new Actions(driver);
		ac.doubleClick(e);
		
	}
	
//	------------Select ----------Drop Down----------------------------------------------------
	
//	select by index
	public static void dropDownSelectByIndex(WebElement e, int index) {
		Select sc=new Select(e);
		sc.selectByIndex(index);
	}
	
//	select by value	
	public static void dropDownSelectByValue(WebElement e, String value) {
		Select sc=new Select(e);
		sc.selectByValue(value);
	}
	
//	select by visible text
	public static void dropDownSelectByVisibletext(WebElement e, String text) {
		Select sc=new Select(e);
		sc.selectByVisibleText(text);
	}
	
//	is multiple
	public static boolean isMultiple(WebElement e) {
		Select sc=new Select(e);
		boolean multiple = sc.isMultiple();
		return multiple;
	}
	
//	get options
	public static String getOptions(WebElement e) {
		Select sc=new Select(e);
		List<WebElement> options = sc.getOptions();
		for(int i=0;i<options.size();i++) {
			WebElement wee = options.get(i);
			String text = wee.getText();
			return text;
		}
		return null;

	}
	
//	All selected options
	public static String getAllSelectedOptions(WebElement e) {
		Select sc=new Select(e);
		List<WebElement> allSelectedOptions = sc.getAllSelectedOptions();
		for(int i=0;i<allSelectedOptions.size();i++) {
			WebElement wee = allSelectedOptions.get(i);
			String t1 = wee.getText();
			return t1;
		}
		return null;	
	}
	
//	get first selected options
	public static String getFristSelectedOptions(WebElement e) {
		Select sc=new Select(e);
		WebElement firstSelectedOption = sc.getFirstSelectedOption();
		String t1 = firstSelectedOption.getText();
		return t1;
	}
	
//	deselect by index
	public static void deSelectByIndex(WebElement e, int index) {
		Select sc=new Select(e);
		sc.deselectByIndex(index);
	}
	
//	deselect by value
	public static void deSelectByValue(WebElement e, String value) {
		Select sc=new Select(e);
		sc.deselectByValue(value);
	}
	
//	deselect by visible text
	public static void deSelectByVisibletext(WebElement e, String text) {
		Select sc=new Select(e);
		sc.deselectByVisibleText(text);
	}
	
//	deselectall
	public static void deSelectAll(WebElement e) {
		Select sc=new Select(e);
		sc.deselectAll();		
	}
	
//	-------------------------Frames---------------------------------------------
	
	
//	-------------takescreenshot -----------------------------------------------
	public static void takeScreenShot(String imageName) throws IOException {
		TakesScreenshot ts=(TakesScreenshot) driver;
		File from = ts.getScreenshotAs(OutputType.FILE);
		File to=new File("C:\\Users\\Admin\\eclipse-workspace\\MavenAugust\\MavenScreenshot\\"+imageName+".png");
		FileUtils.copyFile(from, to);
	}
//	take screenShot in testNg project
	public static void takeScreenShotTestNG(String imageName) throws IOException {
		TakesScreenshot ts=(TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File des=new File(System.getProperty("user.dir")+"\\src\\test\\resources\\ScreenShotss\\"+imageName+".png");
		FileUtils.copyFile(src, des);
	}
	
	public static void takeSC(String loction,String imageName) throws IOException {
		TakesScreenshot ts=(TakesScreenshot) driver;
		File from = ts.getScreenshotAs(OutputType.FILE);
		File to=new File(loction+"\\"+imageName+".png");
		FileUtils.copyFile(from, to);
	}
//	final take screenshot
	public static void screenShot(String imgName) throws IOException {
		TakesScreenshot x = (TakesScreenshot)driver;
		File frm = x.getScreenshotAs(OutputType.FILE);
		File to = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\"+imgName+".png");
		FileUtils.copyFile(frm, to);
	}
// ----------------------Java script executor----------------------------------------------
	public static void javaScriptExecutor() {
		JavascriptExecutor js=(JavascriptExecutor) driver;
	}
	
//	ScrollDown
	public static void scrollDown(Object dwn) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", dwn);
	}
//	scrollUp
	public static void scrollUp(Object upp) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(false)",upp);
	}
	
//	get Attribute using java scrip executor
	public static Object getAttributeUsingJs(WebElement e) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		Object executeScriptAttribute = js.executeScript("return arguments[0].getAttribute('value')", e);
		return executeScriptAttribute;
	}
	
//	click using java script executor
	public static void javaScriptClick(WebElement btn) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()",btn);

	}

	
//	----------navigatioon commands------------------------------------------------
	
//	navigate to 
	public static void navigateTo(String url) {
		driver.navigate().to(url);
	}
	
//	navigate back
	public static void navigateBack() {
		driver.navigate().back();
	}
	
//	navigate forward
	public static void navigateForward() {
		driver.navigate().forward();
	}
	
//	navigate refresh
	public static void navigateRefresh() {
		driver.navigate().refresh();		
	}
	
//	is displayed
	public static boolean isDispalyed(WebElement e) {
		boolean displayed = e.isDisplayed();
		return displayed;
	}
	
//	is Enabled
	public static boolean isEnabled(WebElement e) {
		boolean enabled = e.isEnabled();
		return enabled;
	}
	
//	is selected
	public static boolean isSelected(WebElement e) {
		boolean selected = e.isSelected();
		return selected;
	}
	
//	-------------------Windows handing---------------------------------------
	
//	get window handles
//	first way to handle
	public static void windowsHandleOne() {
		String oneIdd = driver.getWindowHandle();
		Set<String> allIdd = driver.getWindowHandles();//101,102
		for(String eachId:allIdd) {
			if(oneIdd!=eachId) {
				driver.switchTo().window(eachId);
			}		
		}	
	}
	
//	Second way to Handle
	public static void secondHandle() {
		String oneIdd = driver.getWindowHandle();
		Set<String> allIdd = driver.getWindowHandles();//101,102
		int count=1;
		for(String eachId:allIdd) {
			if(count==2) {
				driver.switchTo().window(eachId);
			}
			count++;
		}	
	}
	
//	third way to handle
	public static void windowsHandleThird(int index) {
		String oneIdd = driver.getWindowHandle();
		Set<String> allIdd = driver.getWindowHandles();
		List<String> li=new ArrayList<>();
		li.addAll(allIdd);
		driver.switchTo().window(li.get(index));
	}	
	
//	-------getting data from excel----------
	public static String getDataFromExcel(String fileName, String sheetname, int RowNo, int CellNo) throws IOException {
		File loc=new File(System.getProperty("user.dir")+"\\src\\test\\resources\\"+fileName+".xlsx");
		FileInputStream st=new FileInputStream(loc);		
//		workbook
		Workbook w=new XSSFWorkbook(st);		
//		sheet
		Sheet sheet=w.getSheet(sheetname);		
//		row
		Row row = sheet.getRow(RowNo);		
//		cell
		Cell cell = row.getCell(CellNo);		
//		get cell type 
		int type = cell.getCellType();
//		type 1---> String
//		type 0---> number, Date	
		String value=null;
		if(type==1) {
			value = cell.getStringCellValue();
		}
		else {
			if(DateUtil.isCellDateFormatted(cell)) {
				Date date = cell.getDateCellValue();
				SimpleDateFormat sf=new SimpleDateFormat("dd/MM/yyyy");
				value = sf.format(date);
			}
			else {
				double db = cell.getNumericCellValue();
				long num=(long)db;
				value = String.valueOf(num);
			}
		}
		return value;
	}
	
//	 extent report
//	start report
	public static void startReport(String loc,String DocTitle, String reportName) {
		htmlReport = new ExtentHtmlReporter(loc);
		htmlReport.config().setDocumentTitle(DocTitle);
		htmlReport.config().setReportName(reportName);
		htmlReport.config().setTheme(Theme.DARK);
		
		report =new ExtentReports();
		report.attachReporter(htmlReport);
		report.setSystemInfo("Browser", "Chrome");
		report.setSystemInfo("Browser version", "105");
		report.setSystemInfo("OS", "Windows 11");
		report.setSystemInfo("Environment", "QA");
		report.setSystemInfo("Sprint", "20");
	}
//	creating test log
	public static void CreateTestLog(ITestResult r) {
		String name = r.getName();
		int status = r.getStatus();
		switch (status) {
		case 1:			
			report.createTest(name).pass("test is passed");
			break;
		case 2:
			report.createTest(name).fail("test is failed");
			break;
		default:
			report.createTest(name).skip("test is skipped");
			break;
		}
	}
	
//	ending report
	public static void endReport() {
		report.flush();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}