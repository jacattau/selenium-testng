package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.AddRemovePage;

public class AddRemoveTest extends BaseTest {

    static AddRemovePage addRemovePage;

    @BeforeClass
    public static void setUp(){
        addRemovePage = new AddRemovePage(driver);
    }

    @BeforeMethod
    public void openPage(){
        addRemovePage.openURL();
    }

    @Test
    public void verifyPageDefaults(){
        Assert.assertEquals("Add/Remove Elements", addRemovePage.pageTitle.getText());
        Assert.assertTrue(addRemovePage.addButton.isDisplayed());
        Assert.assertEquals(0, addRemovePage.deleteButton.size());
    }

    @Test
    public void addElementsAndCountThem(){
        Assert.assertEquals(0, addRemovePage.deleteButton.size());
        addRemovePage.addButton.click();
        Assert.assertEquals(1, addRemovePage.deleteButton.size());
        addRemovePage.addButton.click();
        Assert.assertEquals(2, addRemovePage.deleteButton.size());
    }

    @Test
    public void addElementsAndDeleteThem(){
        addElementsAndCountThem();
        addRemovePage.deleteButton.get(0).click();
        Assert.assertEquals(1, addRemovePage.deleteButton.size());
        addRemovePage.deleteButton.get(0).click();
        Assert.assertEquals(0, addRemovePage.deleteButton.size());
    }

}
