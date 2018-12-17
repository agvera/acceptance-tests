package EditTimePage;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

// Summary
// Browser Utility : This class contains all the browser related utilities common to framework.
// Summary
public class BrowserUtility {	

	// Get Driver and instantiate browser.
	public static WebDriver getDriver(String abc) {
		WebDriver webDriver = null;
		 // System.setProperty("webdriver.gecko.driver",
		 //"D:\\TimeDoctor\\geckodriver-v0.19.1-win64\\geckodriver.exe");
		//System.setProperty("webdriver.chrome.driver", "D:\\TimeDoctor\\chromedriver_win32\\chromedriver.exe");
		String webBrowser = "Chrome";
		switch (webBrowser) {
		case "Chrome":
			System.setProperty("webdriver.chrome.driver", "/Users/payalchoksey/git/payalchoksey/TDAutomationScript_4/src/test/resources/Source/chromedriver");
			webDriver = new ChromeDriver();
			break;
		case "Firefox":
			 System.setProperty("webdriver.gecko.driver",
					 "D:\\TimeDoctor\\geckodriver-v0.19.1-win64\\geckodriver.exe");
			webDriver = new FirefoxDriver();
			break;
		} // switch
		String environmentFocus = "Test";
		if(environmentFocus.equals("Live")) {
			webDriver.navigate().to("https://www.timedoctor.com");
		}
		else if(environmentFocus.equals("Staging")) {
			webDriver.navigate().to("https://www.timedoctorstaging.com");
		}
		else if(environmentFocus.equals("Test")) {
			
			webDriver.navigate().to("https://www.timedoctortest.com");
			
		}
		webDriver.manage().window().maximize();
		return webDriver;
	} // Get Driver and instantiate browser.

	// Get Report.
	public static void getReport() {
//		System.setProperty("webdriver.gecko.driver",
//				 "D:\\TimeDoctor\\geckodriver-v0.19.1-win64\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "D:\\TimeDoctor\\chromedriver_win32\\chromedriver.exe");
		WebDriver webDriver = new ChromeDriver();
	//	WebDriver	webDriver = new FirefoxDriver();
		File latestReport = latestReport();
		String latestReportPath = latestReport.getPath();
		webDriver.navigate().to(latestReportPath);
		webDriver.manage().window().maximize();
	} // Get Report.

	// Latest report
	private static File latestReport() {
		Path reportFolder = Paths.get(System.getProperty("user.dir") + "\\TDWebTestResults\\");
		Optional<File> recentReportFile = Arrays.stream(reportFolder.toFile().listFiles()).max((f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified()));
		return recentReportFile.get();
	} // Latest report

	// Element Highlight.
	public void ElementHighlighter(WebElement element, WebDriver driver) {
		for (int highlightIterator = 0; highlightIterator < 2; highlightIterator += 1) {
			try {
				JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
				String originalJavascript = "$(arguments[0]).css({ 'border-width' : 'none', 'border-style' : 'none', 'border-color' : 'none' });";
				String highlightJavascript = "$(arguments[0]).css({ 'border-width' : '4px', 'border-style' : 'solid', 'border-color' : 'red' });";				
				jsDriver.executeScript(highlightJavascript, new Object[] { element });
				Thread.sleep(300);
				jsDriver.executeScript(originalJavascript, new Object[] { element });
				Thread.sleep(300);
			} catch (Exception e) {
				System.out.println("Highlighter Exception");
			} // try catch

		} // for

	} // Element Highlight

} // Browser Utility.
