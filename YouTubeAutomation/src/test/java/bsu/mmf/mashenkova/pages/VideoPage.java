package bsu.mmf.mashenkova.pages;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VideoPage extends AbstractPage
{
	private final String BASE_URL = "https://www.youtube.com/watch?v=RAOnUF8t20w";

	private final Logger logger = Logger.getLogger(VideoPage.class);

	@FindBy(className = "html5-video-player")
	private WebElement videoPlayer;

	@FindBy(className = "ytp-play-button")
	private WebElement playButton;

	@FindBy(className = "ytp-time-current")
	private WebElement currentTime;

	@FindBy(className = "ytp-fullscreen-button")
	private WebElement fullscreenButton;

	@FindBy(className = "ytp-size-button")
	private WebElement wideSizeButton;

	@FindBy(className = "ytp-settings-button")
	private WebElement settingsButton;

	@FindBy(className = "ytp-next-button")
	private WebElement nextButton;

	@FindBy(className = "watch-title")
	private WebElement title;

	public VideoPage(WebDriver driver)
	{
		super(driver);
		logger.setLevel(Level.DEBUG);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void openPage()
	{
		driver.navigate().to(BASE_URL);
	}

	public void startStopVideo() {
		playButton.click();
	}

	public boolean videoTimeChanges(boolean needPause) {
		logger.info("Find start time");
		String startTime = getCurrentTime();
		logger.debug("Start time: " + startTime);
		logger.info("Wait..");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			return false;
		}
		if(needPause) {
			logger.info("Click player to make time label visible");
			startStopVideo();
		}
		logger.info("Find current time");
		String endTime = getCurrentTime();
		logger.debug("Current time: " + endTime);
		logger.info("Check if time has changed");
		return !startTime.equals(endTime);
	}

	public void goToWideScreen() {
		wideSizeButton.click();
	}

	public void goToNextVideo() {
		nextButton.click();
	}

	public String getVideoTitle() {
		return title.getAttribute("innerHTML");
	}

	public String getCurrentTime() {
		return currentTime.getAttribute("innerHTML");
	}

	public int getPlayerWidth() {
		return videoPlayer.getSize().getWidth();
	}
}
