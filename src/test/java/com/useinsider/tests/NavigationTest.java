package com.useinsider.tests;

import com.useinsider.utilities.BrowserUtils;
import com.useinsider.utilities.Driver;
import org.testng.annotations.Test;

public class NavigationTest extends TestBase {


    @Test(description = "check Insider home page is opened or not")
    public void t01_navigateToHomePage() {

        extentTest = report.createTest("Verify that Insider Home Page is opened");
        extentTest.info("Navigate to " + url + " web site");
        Driver.get().get(url);
        extentTest.info("Verify that the user lands on " + url + " url");
        homePage.verifyCurrentUrl(url);
        extentTest.pass("Test PASSED");
    }

    @Test(description = "Select the “Company” menu in the navigation bar, select “Careers” and check Career\n" +
            "page, its Locations, Teams, and Life at Insider blocks are open or not")
    public void t02_navigateToCompany_CareersSubMenu_and_VerifyBlocks() {

        extentTest = report.createTest("Verify that Locations, Teams and Life at Insider blocks are opened on Careers page");
        extentTest.info("Navigate to " + url + " web site");
        Driver.get().get(url);
        extentTest.info("Accept All Cookies");
        homePage.acceptCookies.click();
        extentTest.info("Navigate to " + careersUrl + " page");
        homePage.navigateToMenu("Company", "Careers");
        extentTest.info("Verify that the " + careersPage.seeAllTeams + " link is displayed");
        careersPage.isDisplayed(careersPage.seeAllTeams);
        extentTest.info("Verify that the " + careersPage.ourLocation + " block is displayed");
        careersPage.isDisplayed(careersPage.ourLocation);
        extentTest.info("Verify that the " + careersPage.lifeAtInsider + " block is displayed");
        careersPage.isDisplayed(careersPage.lifeAtInsider);
        extentTest.pass("Test PASSED");
    }

    @Test(description = "Go to https://useinsider.com/careers/quality-assurance/, click “See all QA jobs”, filter\n" +
            "jobs by Location: “Istanbul, Turkey”, and Department: “Quality Assurance”, check the\n" +
            "presence of the job list")
    public void t03_checkThePresenceOfTheJobList() {

        extentTest = report.createTest("Verify job list filtering by Location: 'Istanbul, Turkey' and Department: 'Quality Assurance'");
        extentTest.info("Navigate to " + careersQAUrl + " page");
        Driver.get().get(careersQAUrl);
        extentTest.info("Accept All Cookies");
        qualityAssurancePage.acceptCookies.click();
        extentTest.info("Click on '" + qualityAssurancePage.seeAllQAJobs + "' link");
        qualityAssurancePage.click(qualityAssurancePage.seeAllQAJobs);
        extentTest.info("job list filtering by location : 'Istanbul, Turkey'");
        browseOpenPositionPage.selectFilterOptions(browseOpenPositionPage.locationFilter);
        extentTest.info("Verify Location is 'Istanbul, Turkey");
        browseOpenPositionPage.verifySelectedFilterOption(browseOpenPositionPage.locationFilter);
        extentTest.info("job list filtering by department : 'Quality Assurance'");
        browseOpenPositionPage.selectFilterOptions(browseOpenPositionPage.departmentFilter);
        extentTest.info("Verify Department is 'Quality Assurance");
        browseOpenPositionPage.verifySelectedFilterOption(browseOpenPositionPage.departmentFilter);
        extentTest.info("Verify that presence of the job list");
        browseOpenPositionPage.verifyPositionList();
        extentTest.fail("The position list does not appear properly on some occasions. " +
                "It is inconsistent and does not match the expected output every time.");
    }

    @Test(description = "Check that all jobs’ Position contains “Quality Assurance”, Department contains\n" +
            "“Quality Assurance”, and Location contains “Istanbul, Turkey”")
    public void t04_checkAllJobsContainsRelated_Position_Department_Location() {

        expectedLocation = browseOpenPositionPage.locationFilter[0][1];
        expectedDepartment = browseOpenPositionPage.departmentFilter[0][1];
        expectedPosition = expectedDepartment;

        extentTest = report.createTest("Verify that the position information for the selected location and department matches the expected values ");
        extentTest.info("Navigate to " + careersQAUrl + " page");
        Driver.get().get(careersQAUrl);
        extentTest.info("Accept All Cookies");
        qualityAssurancePage.acceptCookies.click();
        extentTest.info("Click on '" + qualityAssurancePage.seeAllQAJobs + "' link");
        qualityAssurancePage.click(qualityAssurancePage.seeAllQAJobs);
        extentTest.info("job list filtering by location : 'Istanbul, Turkey'");
        browseOpenPositionPage.selectFilterOptions(browseOpenPositionPage.locationFilter);
        extentTest.info("job list filtering by department : 'Quality Assurance'");
        browseOpenPositionPage.selectFilterOptions(browseOpenPositionPage.departmentFilter);
        extentTest.info("Verify that positions info matches the expected data");
        browseOpenPositionPage.verifyJobDetails(expectedPosition, expectedDepartment, expectedLocation);
        extentTest.fail("The position/job list is NOT stable");
    }

    @Test (description = "Click the “View Role” button and check that this action redirects us to the Lever\n" +
            "Application form page")
    public void t05_viewRoleRedirection() {

        extentTest= report.createTest("Verify that clicking the 'View Role' button successfully redirects the user to the Lever Application form page.");
        extentTest.info("Navigate to " + careersQAUrl + " page");
        Driver.get().get(careersQAUrl);
        extentTest.info("Accept All Cookies");
        qualityAssurancePage.acceptCookies.click();
        extentTest.info("Click on '" + qualityAssurancePage.seeAllQAJobs + "' link");
        qualityAssurancePage.click(qualityAssurancePage.seeAllQAJobs);
        extentTest.info("job list filtering by location : 'Istanbul, Turkey'");
        browseOpenPositionPage.selectFilterOptions(browseOpenPositionPage.locationFilter);
        BrowserUtils.waitFor(2);
        extentTest.info("job list filtering by department : 'Quality Assurance'");
        browseOpenPositionPage.selectFilterOptions(browseOpenPositionPage.departmentFilter);
        browseOpenPositionPage.verifyViewRoleRedirection();
        extentTest.fail("The position/job list is unstable due to issues in the 'Filter by Location' and 'Filter by Department' sections");

        /*  Defects were detected in the 'Filter by Location' and 'Filter by Department' sections in the given task.
           The observation of these problems during manual testing suggests that they may be bugs.
           In such cases, the test steps are not continued, and automation is not performed.
           The issue is reported for fixing.
           However, to evaluate the functionality of the code, the tasks were completed as required despite these known issues.
           Additionally, since this situation applied to Tasks 3, 4, and 5,
           the test results in the extended report were processed as 'fail.'"
         */

    }
}
