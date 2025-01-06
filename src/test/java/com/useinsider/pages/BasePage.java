package com.useinsider.pages;

import com.useinsider.utilities.BrowserUtils;
import com.useinsider.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.testng.Assert;

import static org.testng.Assert.assertTrue;

public abstract class BasePage {

    public BasePage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(id = "wt-cli-accept-all-btn")
    public WebElement acceptCookies;

    public void verifyCurrentUrl(String expectedUrl) {
        assertTrue(Driver.get().getCurrentUrl().equals(expectedUrl));
    }

    public void click(String elem) {
        By element=By.linkText(elem);
        try {
            WebElement menu = Driver.get().findElement(element);
            BrowserUtils.waitForClickablility(menu, 10).click();
        } catch (Exception e) {
            BrowserUtils.waitForClickablility(element, 5).click();
        }
    }
    public void click(String text,int index) {
        By element=By.xpath("(//*[.='"+text+"'])["+index+"]");
        try {
            WebElement link = Driver.get().findElement(element);
            BrowserUtils.waitForClickablility(link, 10).click();
        } catch (Exception e) {
            BrowserUtils.waitForClickablility(element, 5).click();
        }
    }

    public void navigateToMenu(String menuName, String subMenuName) {
        click(menuName);
        click(subMenuName);
    }

    public WebElement element(String elemText) {
        return Driver.get().findElement(By.xpath("//*[contains(text(),'" + elemText + "')]"));
    }

    public void isDisplayed(String elemText) {

        try {
            BrowserUtils.scrollToElement(element(elemText));
            boolean visible = element(elemText).isDisplayed();
            Assert.assertTrue(visible, "Element is visible");
        } catch (Exception e) {
            Assert.fail("Element is NOT visible: " + e.getMessage());
        }
    }
    private WebElement getDropDown(String filterType) {
        try {
            return Driver.get().findElement(By.id("filter-by-" + filterType));
        } catch (NoSuchElementException e) {
            System.out.println("Drop-down element not found for filter type: " + filterType);
            throw e; // Re-throw the exception if necessary
        }
    }

    private Select selectDropDown(WebElement dropDown) {
        try {
            return new Select(dropDown);
        } catch (UnexpectedTagNameException e) {
            System.out.println("The element is not a select tag: " + dropDown);
            throw e;
        }
    }

    private void selectOptionInDropDown(WebElement dropDown, String option) {
        try {
            selectDropDown(dropDown).selectByVisibleText(option);
        } catch (NoSuchElementException e) {
            System.out.println("Option '" + option + "' not found in drop-down: " + dropDown);
            throw e;
        }
    }
    public void selectFilterOptions(String[][] options) {
        for (String[] pair : options) {
            String filterType = pair[0];
            String option = pair[1];
            try {
                WebElement dropDown = getDropDown(filterType);
                BrowserUtils.waitForVisibility(dropDown, 5);
                selectOptionInDropDown(dropDown, option);
            } catch (StaleElementReferenceException e) {
                System.out.println("Stale element exception for filter type: " + filterType);
                WebElement dropDown = getDropDown(filterType);
                BrowserUtils.waitForVisibility(dropDown, 5);
                selectOptionInDropDown(dropDown, option);
            } catch (Exception e) {
                System.out.println("Failed to select option '" + option + "' for filter type: " + filterType);
            }
        }
    }


    public void verifySelectedFilterOption(String[][] options) {
        for (String[] pair : options) {
            String filterType = pair[0];
            String option = pair[1];

            try {
                WebElement dropdown = getDropDown(filterType);
                String selectedOption = selectDropDown(dropdown).getFirstSelectedOption().getText();
                Assert.assertEquals(selectedOption, option,
                        "Verification failed for filter: " + filterType + ". Expected: " + option + ", Found: " + selectedOption);
                System.out.println("Verified: Filter '" + filterType + "' has selected option '" + selectedOption + "'.");
            } catch (NoSuchElementException e) {
                Assert.fail("Element not found for filter: " + filterType + ". Error: " + e.getMessage());
            } catch (Exception e) {
                Assert.fail("An unexpected error occurred for filter: " + filterType + ". Error: " + e.getMessage());
            }
        }
    }


}
