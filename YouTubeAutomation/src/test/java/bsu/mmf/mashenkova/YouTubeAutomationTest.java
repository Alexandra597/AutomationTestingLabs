package bsu.mmf.mashenkova;

import bsu.mmf.mashenkova.steps.Steps;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class YouTubeAutomationTest
{
	private Steps steps;

	@Before
	public void setUp()
	{
		steps = new Steps();
		steps.initBrowser();
	}

	@Test
	public void correctSearchResultsTest() {
		Assert.assertTrue("Incorrect search results", steps.videoSearchResultsContainQuery());
	}

	@Test
	public void videoOpensTest() {
		Assert.assertTrue("Video player didn't open", steps.videoOpens());
		Assert.assertTrue("Video doesn't play", steps.videoPlays());
	}

	@Test
	public void videoCanBePaused() {
		Assert.assertTrue("Video can't be paused", steps.videoPauses());
	}

	@Test
	public void videoCanBeOpenedInWideScreen() {
		Assert.assertTrue("Video can't go to wide screen", steps.canGoToWideScreen());
	}

	@Test
	public void canMoveToNextVideo() {
		Assert.assertTrue("Can't go to next video", steps.canGoToNextVideo());
	}

	@After
	public void stopBrowser()
	{
		steps.closeDriver();
	}
}
