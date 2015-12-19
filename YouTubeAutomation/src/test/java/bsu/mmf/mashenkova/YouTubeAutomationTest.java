package bsu.mmf.mashenkova;

import bsu.mmf.mashenkova.steps.Steps;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class YouTubeAutomationTest
{
	Logger logger = Logger.getLogger(YouTubeAutomationTest.class);

	{
		logger.setLevel(Level.DEBUG);
	}

	private Steps steps;

	@Before
	public void setUp()
	{
		logger.info("Setting up test");
		steps = new Steps();
		steps.initBrowser();
	}

	@Test
	public void correctSearchResultsTest() {
		logger.info("Starting test to check whether search results contain query string");
		Assert.assertTrue("Incorrect search results", steps.videoSearchResultsContainQuery());
	}

	@Test
	public void videoPlaysTest() {
		logger.info("Check whether video starts playing at once");
		Assert.assertTrue("Video doesn't play", steps.videoPlays());
	}

	@Test
	public void videoCanBePaused() {
		logger.info("Starting test to check whether video can be paused");
		Assert.assertTrue("Video can't be paused", steps.videoPauses());
	}

	@Test
	public void videoCanBeOpenedInWideScreen() {
		logger.info("Starting test to check whether video can be watched with wide screen");
		Assert.assertTrue("Video can't go to wide screen", steps.canGoToWideScreen());
	}

	@Test
	public void canMoveToNextVideo() {
		logger.info("Starting test to check whether it is possible to navigate to the next video");
		Assert.assertTrue("Can't go to next video", steps.canGoToNextVideo());
	}

	@After
	public void stopBrowser()
	{
		logger.info("Finishing test");
		steps.closeDriver();
	}
}
