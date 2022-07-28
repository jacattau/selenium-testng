package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DynamicLoadingLoadedPage;
import pages.DynamicLoadingPage;

public class DynamicLoadingTest extends BaseTest {
    String topSubheaderText = "It's common to see an action get triggered that returns a result dynamically. " +
            "It does not rely on the page to reload or finish loading. The page automatically gets updated " +
            "(e.g. hiding elements, showing elements, updating copy, etc) through the use of JavaScript.";
    String bottomSubheaderText = "There are two examples. One in which an element already exists on the page but it" +
            " is not displayed. And anonther where the element is not on the page and gets added in.";

    static DynamicLoadingPage dynamicLoadingPage;
    static DynamicLoadingLoadedPage dynamicLoadingLoadedPage;

    @BeforeClass
    public static void setUp(){
        dynamicLoadingPage = new DynamicLoadingPage(driver);
    }

    @BeforeMethod
    public void openPage(){
        dynamicLoadingPage.openURL();
    }

    @Test
    public void verifyPageDefaults(){
        Assert.assertEquals("Dynamically Loaded Page Elements", dynamicLoadingPage.pageTitle.getText());
        Assert.assertEquals(topSubheaderText, dynamicLoadingPage.subHeaderTop.getText());
        Assert.assertEquals(bottomSubheaderText, dynamicLoadingPage.subHeaderBottom.getText());
        Assert.assertTrue(dynamicLoadingPage.example1Link.isDisplayed());
        Assert.assertTrue(dynamicLoadingPage.example2Link.isDisplayed());
    }

    @Test
    public void clickExample1LinkAndTestFeature(){
        dynamicLoadingPage.example1Link.click();
        dynamicLoadingLoadedPage = new DynamicLoadingLoadedPage(driver);
        Assert.assertEquals("Dynamically Loaded Page Elements", dynamicLoadingLoadedPage.pageTitle.getText());
        Assert.assertEquals("Example 1: Element on page that is hidden", dynamicLoadingLoadedPage.subHeader.getText());
        Assert.assertTrue(elementShouldBeVisible(dynamicLoadingLoadedPage.finishedText,false));
        dynamicLoadingLoadedPage.startButton.click();
        wait.until(ExpectedConditions.visibilityOf(dynamicLoadingLoadedPage.finishedText));
        Assert.assertEquals("Hello World!", dynamicLoadingLoadedPage.finishedText.getText());
    }

    @Test
    public void clickExample2LinkAndTestFeature(){
        dynamicLoadingPage.example2Link.click();
        dynamicLoadingLoadedPage = new DynamicLoadingLoadedPage(driver);
        Assert.assertEquals("Dynamically Loaded Page Elements", dynamicLoadingLoadedPage.pageTitle.getText());
        Assert.assertEquals("Example 2: Element rendered after the fact", dynamicLoadingLoadedPage.subHeader.getText());
        Assert.assertFalse(elementDisplayed(dynamicLoadingLoadedPage.finishedText));
        dynamicLoadingLoadedPage.startButton.click();
        wait.until(ExpectedConditions.visibilityOf(dynamicLoadingLoadedPage.finishedText));
        Assert.assertEquals("Hello World!", dynamicLoadingLoadedPage.finishedText.getText());
    }
}
