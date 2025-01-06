package com.useinsider.pages;

import com.useinsider.utilities.BrowserUtils;
import com.useinsider.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import java.util.List;


public class BrowseOpenPositionPage extends BasePage {

    @FindBy(id = "select2-filter-by-location-container")
    public WebElement locationContainer;
    @FindBy(id = "career-position-list")
    public WebElement position;
    @FindBy(id = "resultCounter")
    public List<WebElement> positionList;
    @FindBy(tagName = "h2")
    public WebElement positionHeadline;

    public String[][] locationFilter = {
            {"location", "Istanbul, Turkey"}
    };

    public String[][] departmentFilter = {
            {"department", "Quality Assurance"}
    };
    String[][] filterOptions = {
            {"location", "Istanbul, Turkey"},
            {"department", "Quality Assurance"}
    };

    public void verifyPositionList() {
        try {
            BrowserUtils.waitForAttributeValue(locationContainer, "title", locationFilter[0][1], 5);
            BrowserUtils.scrollToElement(position);
            System.out.println("positionList.size() = " + positionList.size());
            //Assert.assertTrue( positionList.size()>0);
            Assert.assertTrue(positionList.size() == 3); // Added to demonstrate the issue in the position list
        } catch (Exception e) {
            Assert.fail("Error occurred while verifying result count: " + e.getMessage());

        }
    }

    public void verifyJobDetails(String expectedPosition, String expectedDepartment, String expectedLocation) {
        BrowserUtils.scrollToElement(position);
        BrowserUtils.waitFor(3);
        // List<WebElement> positionList = Driver.get().findElements(By.cssSelector("#jobs-list .position-list-item"));
        for (WebElement job : positionList) {
            String position = job.findElement(By.cssSelector(".position-title")).getText();
            String department = job.findElement(By.cssSelector(".position-department")).getText();
            String location = job.findElement(By.cssSelector(".position-location")).getText();
            System.out.println("position = " + position);
            System.out.println("department = " + department);
            System.out.println("location = " + location);
            Assert.assertTrue(
                    position.contains(expectedPosition) ||
                            (expectedPosition.equals("Quality Assurance") && position.contains("QA"))
            );
            Assert.assertTrue(department.contains(expectedDepartment));
            Assert.assertTrue(location.contains(expectedLocation));
        }
    }


    public void verifyViewRoleRedirection() {
        BrowserUtils.scrollToElement(position);
        BrowserUtils.waitFor(1);

        for (int i = 0; i < positionList.size(); i++) {
            WebElement role = positionList.get(i);
            String roleText = role.getText();

            System.out.println("roleText " + i + " = " + roleText);

            if (!roleText.contains("Istanbul, Turkey") || !roleText.contains("Quality Assurance")) {
                System.out.println("Role does not meet criteria, updating filters...");
                try {
                    Driver.get().navigate().refresh();
                    BrowserUtils.waitForPageToLoad(3);
                    selectFilterOptions(filterOptions);
                    System.out.println("Filters updated successfully.");
                    BrowserUtils.waitFor(3);

                    positionList = Driver.get().findElements(By.cssSelector("#jobs-list .position-list-item"));
                    i = -1;
                    continue;
                } catch (Exception e) {
                    System.err.println("Failed to update filters: " + e.getMessage());
                    Assert.fail("Test failed because filters could not be updated.");
                }
            }

            System.out.println("Processing roleText " + i + " = " + roleText);
            BrowserUtils.hover(role);
            click("View Role", (i+1));
            BrowserUtils.waitFor(1);
            BrowserUtils.switchToWindow("Insider. - " + roleText, positionHeadline, 5);
        }
    }
}
