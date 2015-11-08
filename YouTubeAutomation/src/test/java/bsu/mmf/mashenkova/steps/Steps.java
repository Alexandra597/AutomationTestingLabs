package bsu.mmf.mashenkova.steps;

import bsu.mmf.mashenkova.pages.MainPage;
import bsu.mmf.mashenkova.pages.VideoPage;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
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
	}

	public void searchForVideos() {
		MainPage mainPage = new MainPage(driver);
		mainPage.openPage();
		mainPage.search(SEARCH_QUERY);
	}

	public boolean videoSearchResultsContainQuery() {
		searchForVideos();
		List<WebElement> searchResults = driver.findElements(By.className("yt-uix-tile-link"));
		if(searchResults.size() == 0) {
			return false;
		}
		for (WebElement searchResult : searchResults) {
			if (!searchResult.getText().toLowerCase().contains(SEARCH_QUERY.toLowerCase())) {
				return false;
			}
		}
		return true;
	}

	public boolean videoOpens() {
		searchForVideos();
		List<WebElement> searchVideoResults = driver.findElements(By.className("yt-lockup-video"));
		if(searchVideoResults.size() == 0) {
			return false;
		}
		searchVideoResults.get(0).click();
		WebElement videoPlayer = driver.findElement(By.className("html5-video-player"));
		return videoPlayer != null;
	}

	public boolean videoPlays() {
		WebElement time = driver.findElement(By.className("ytp-time-current"));
		String startTime = time.getAttribute("innerHTML");
		logger.info(startTime);
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			return false;
		}
		String endTime = time.getAttribute("innerHTML");
		logger.info(endTime);
		return !startTime.equals(endTime);
	}

	public boolean videoPauses() {
		VideoPage videoPage = new VideoPage(driver);
		videoPage.openPage();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			return false;
		}
		videoPage.startStopVideo();
		return !videoPage.videoTimeChanges();
	}

	public boolean canGoToWideScreen() {
		VideoPage videoPage = new VideoPage(driver);
		videoPage.openPage();
		videoPage.startStopVideo();
		int playerInitialWidth = videoPage.getPlayerWidth();
		videoPage.goToWideScreen();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			return false;
		}
		int playerFinalWidth = videoPage.getPlayerWidth();
//		WebElement fullscreenPlayer = driver.findElement(By.className("ytp-fullscreen"));
		return playerInitialWidth < playerFinalWidth;
	}

	public boolean canGoToNextVideo() {
		VideoPage videoPage = new VideoPage(driver);
		videoPage.openPage();
		String firstTitle = videoPage.getVideoTitle();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			return false;
		}
		videoPage.goToNextVideo();
		logger.info(firstTitle);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			return false;
		}
		String secondTitle = videoPage.getVideoTitle();
		logger.info(secondTitle);
		return !firstTitle.equals(secondTitle);
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
