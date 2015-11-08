package bsu.mmf.mashenkova.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPage
{
	private final String BASE_URL = "https://www.youtube.com/";

	@FindBy(id = "masthead-search-term")
	private WebElement searchField;

	@FindBy(id = "search-btn")
	private WebElement searchButton;

	public MainPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void openPage()
	{
		driver.navigate().to(BASE_URL);
	}

	public void search(String searchQuery)
	{
		searchField.sendKeys(searchQuery);
		searchButton.click();
	}
}
