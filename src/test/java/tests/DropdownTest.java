package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DropdownPage;

public class DropdownTest extends BaseTest {

    static DropdownPage dropdownPage;

    @BeforeClass
    public static void setUp(){
        dropdownPage = new DropdownPage(driver);
    }

    @BeforeMethod
    public void openPage(){
        dropdownPage.openURL();
    }

    @Test
    public void verifyPageDefaults(){
        Assert.assertEquals("Dropdown List", dropdownPage.pageTitle.getText());
        Assert.assertTrue(dropdownPage.dropdown.isDisplayed());
        Assert.assertEquals("Please select an option",selectDropdown(dropdownPage.dropdown).getFirstSelectedOption().getText());
    }

    @Test
    public void selectBothOptionsInDropdown(){
        selectDropdown(dropdownPage.dropdown).selectByVisibleText("Option 1");
        Assert.assertEquals("Option 1",selectDropdown(dropdownPage.dropdown).getFirstSelectedOption().getText());
        selectDropdown(dropdownPage.dropdown).selectByIndex(2);
        Assert.assertEquals("Option 2",selectDropdown(dropdownPage.dropdown).getFirstSelectedOption().getText());
    }
}
