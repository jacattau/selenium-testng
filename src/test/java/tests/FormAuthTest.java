package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.FormAuthPage;

public class FormAuthTest extends BaseTest {
    String subHeaderText = "This is where you can log into the secure area. Enter tomsmith for " +
            "the username and SuperSecretPassword! for the password. If the information is wrong you " +
            "should see error messages.";
    static FormAuthPage formAuthPage;

    @BeforeClass
    public static void setUp(){
        formAuthPage = new FormAuthPage(driver);
    }

    @BeforeMethod
    public void openPage(){
        formAuthPage.openURL();
    }

    @Test
    public void verifyTitleAndSubheader(){
        Assert.assertEquals("Login Page", formAuthPage.pageTitle.getText());
        Assert.assertEquals(subHeaderText, formAuthPage.subHeader.getText());
    }

    @Test
    public void enterWrongCredentials(){
        formAuthPage.usernameField.sendKeys("username");
        formAuthPage.passwordField.sendKeys("password");
        formAuthPage.loginButton.click();
        wait.until(ExpectedConditions.visibilityOf(formAuthPage.alertBanner));
        Assert.assertTrue(formAuthPage.alertBanner.getText().contains("Your username is invalid!"));
    }

    @Test
    public void enterCorrectCredentials(){
        formAuthPage.usernameField.sendKeys("tomsmith");
        formAuthPage.passwordField.sendKeys("SuperSecretPassword!");
        formAuthPage.loginButton.click();
        wait.until(ExpectedConditions.visibilityOf(formAuthPage.alertBanner));
        Assert.assertTrue(formAuthPage.alertBanner.getText().contains("You logged into a secure area!"));
        Assert.assertTrue(formAuthPage.logoutButton.isDisplayed());
    }

    @Test
    public void clickLogout(){
        enterCorrectCredentials();
        formAuthPage.logoutButton.click();
        wait.until(ExpectedConditions.visibilityOf(formAuthPage.alertBanner));
        Assert.assertTrue(formAuthPage.alertBanner.getText().contains("You logged out of the secure area!"));
    }
}
