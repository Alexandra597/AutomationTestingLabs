package bsu.mmf.mashenkova.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VideoPage extends AbstractPage
{
	private final String BASE_URL = "https://www.youtube.com/watch?v=RAOnUF8t20w";

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

	public boolean videoTimeChanges() {
		String startTime = getCurrentTime();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			return false;
		}
		String endTime = getCurrentTime();
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
