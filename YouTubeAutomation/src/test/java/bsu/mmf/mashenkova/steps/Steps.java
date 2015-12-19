package bsu.mmf.mashenkova.steps;

import bsu.mmf.mashenkova.pages.MainPage;
import bsu.mmf.mashenkova.pages.VideoPage;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Steps
{
	private final static String SEARCH_QUERY = "billy talent";
	private WebDriver driver;

	static {
		BasicConfigurator.configure();
	}

	private final Logger logger = Logger.getLogger(Steps.class);

	{
		logger.setLevel(Level.DEBUG);
	}

	public void initBrowser()
	{
		driver = new FirefoxDriver();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		logger.info("Browser started");
	}

	public void closeDriver()
	{
		driver.quit();
		logger.info("Browser closed");
	}

	public void searchForVideos() {
		logger.info("Init search for videos");
		logger.debug("Search query: " + SEARCH_QUERY);
		MainPage mainPage = new MainPage(driver);
		mainPage.openPage();
		mainPage.search(SEARCH_QUERY);
	}

	public boolean videoSearchResultsContainQuery() {
		boolean result = true;
		searchForVideos();
		logger.info("Obtain search results");
		List<WebElement> searchResults = driver.findElement(By.className("section-list")).findElements(By.className("yt-lockup-video"));
		if(searchResults.size() == 0) {
			logger.error("No search results");
			result = false;
		} else {
			for (WebElement searchResult : searchResults) {
				if (!searchResult.getText().toLowerCase().contains(SEARCH_QUERY.toLowerCase())) {
					logger.error("Incorrect result: " + searchResult.getText() + " doesn't contain query " + SEARCH_QUERY);
					result = false;
				}
			}
		}
		logger.debug("Test result = " + result);
		return result;
	}

	public boolean videoOpens() {
		searchForVideos();
		logger.info("Obtain search results");
		List<WebElement> searchVideoResults = driver.findElement(By.className("section-list")).findElements(By.className("yt-lockup-video"));
		if(searchVideoResults.size() == 0) {
			logger.error("No search results");
			return false;
		}
		logger.info("Open video in the first result");
		searchVideoResults.get(0).click();
		logger.info("Check if video player is present on the page");
		WebElement videoPlayer = driver.findElement(By.className("html5-main-video"));
		boolean result = videoPlayer != null;
		logger.debug("Test result = " + result);
		return result;
	}

	public boolean videoPlays() {
		logger.info("Open video");
		VideoPage p = new VideoPage(driver);
		p.openPage();
		boolean result = p.videoTimeChanges(true);
		logger.debug("Test result = " + result);
		return result;
	}

	public boolean videoPauses() {
		logger.info("Open video");
		VideoPage videoPage = new VideoPage(driver);
		videoPage.openPage();
		if(!sleep(3000)) {
			return false;
		}
		logger.info("Pause video");
		videoPage.startStopVideo();
		logger.info("Check if time is changing");
		boolean result = !videoPage.videoTimeChanges(false);
		logger.debug("Test result = " + result);
		return result;
	}

	public boolean canGoToWideScreen() {
		logger.info("Open video");
		VideoPage videoPage = new VideoPage(driver);
		videoPage.openPage();
		if(!sleep(3000)) {
			return false;
		}
		logger.info("Pause video");
		videoPage.startStopVideo();
		logger.info("Find player width");
		int playerInitialWidth = videoPage.getPlayerWidth();
		logger.debug("Width = " + playerInitialWidth);
		logger.info("Go to wide screen");
		videoPage.goToWideScreen();
		if(!sleep(5000)) {
			return false;
		}
		logger.info("Find player width");
		int playerFinalWidth = videoPage.getPlayerWidth();
		logger.debug("Width = " + playerFinalWidth);
//		WebElement fullscreenPlayer = driver.findElement(By.className("ytp-fullscreen"));
		logger.info("Check if player width has become bigger");
		boolean result = playerInitialWidth < playerFinalWidth;
		logger.debug("Test result = " + result);
		return result;
	}

	public boolean canGoToNextVideo() {
		logger.info("Open video");
		VideoPage videoPage = new VideoPage(driver);
		videoPage.openPage();
		logger.info("Get video title");
		String firstTitle = videoPage.getVideoTitle();
		if(!sleep(5000)) {
			return false;
		}
		logger.info("Go to next video");
		videoPage.goToNextVideo();
		logger.debug("First title: " + firstTitle);
		if(!sleep(10000)) {
			return false;
		}
		logger.info("Get current video title");
		String secondTitle = videoPage.getVideoTitle();
		logger.debug("Second title: " + secondTitle);
		logger.info("Check if video titles are different");
		boolean result = !firstTitle.equals(secondTitle);
		logger.debug("Test result = " + result);
		return result;
	}

	public boolean sleep(int timeout) {
		try {
			Thread.sleep(timeout);
			return true;
		} catch (InterruptedException e) {
			return false;
		}
	}

	public boolean openChannel() {
		searchForVideos();
		List<WebElement> searchResults = driver.findElements(By.className("yt-lockup-channel"));
		if(searchResults.size() == 0) {
			return false;
		}
		searchResults.get(0).click();
		return true;
	}
}
