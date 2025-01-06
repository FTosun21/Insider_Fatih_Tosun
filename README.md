# Insider Selenium Automation Test
This project is created to perform automation tests using Selenium and TestNG on the Insider website.

### Codes and Tests Prepared by: 
Fatih Tosun

QA Test Automation Engineer

email: fatihtosun.qa@gmail.com

https://www.linkedin.com/in/fatih-tosun-qa/

### Getting Started
To get start with this framework, you will need to have the following software on your system.

• Java 8 or later
• Maven 4 or later
• Chrome or Firefox Web Browser
• Clone the project to your computer: git clone https://github.com/FTosun21/Insider_Fatih_Tosun.git

1. Open terminal or command prompt and navigate to the root directory of the project.
2. Run the following command to download the required dependencies:
    ```mvn clean install```
3. Run the following command to run the tests:
    ```mvn test```
4. Open the project with Intellij IDEA
  - Go to TestRunner class inside the project
  - Click Run button
5. Open the project with Intellij IDEA
  - Go to Maven User Interface from left sidebar
  - Double Click "clean"
  - Double Click "test"
### Used Libraries
  Selenium Java
  TestNG
  ExtentReports
  webdrivermanager
  Maven Surefire Plugin
### Installation
 
  Clone the project to your computer: git clone
    ```https://github.com/FTosun21/Insider_Fatih_Tosun.git```
  
  Navigate to the project directory: 
    ```cd Insider_Fatih_Tosun```
  
  Install Maven dependencies: 
    ```mvn clean install```

### How to Run:
  You can run the tests using Maven commands, TestRunner class and by Test Annotations inside InsiderTest class.
  Test reports are generated in the target/surefire-reports andtest-output folders.
 ### Test Reports:
    TestNG reports
    Extent reports
    JUnit reports
    About Framework:
• I built my framework by using Java Programming Language.\
• I used maven as a build automation tool for this framework.\
• I used Selenium (4.27.0) and TestNG to orchestrate my tests, and put the dependencies and plugin inside pom.xml file.\
• For assertions/verifications, I utilized TestNG assertions to compare actual and expected results.\
• I created a properties file to store related data such as browser and urls.\
• I used Page Object Model Design Pattern to enhance test maintenance and reducing code duplication.\
• Inside pages package; I used Page Classes to store and identify the elements that I worked on. Also I stored related methods in page classes.\
• I used the PageFactory class and initElements method to initialize them.\
• Inside tests package;\
• I used TestRunner class and linked it to testRunner.xml file to run declared tests or suits.\
• I created TestBase class to run and instantiate related steps and initialize ExtendReport.\
• I stored test cases inside InsiderTests class.
• I added a screenshot interface in After method in Hooks class; when the scenario fails, it takes a screenshot.\
• Inside utilities package, utility classes such as BrowserUtils, Driver, and ConfigurationReader were stored.\
• I used the Singleton Design Pattern in Driver class by declaring constructor of class as private, so that no one instantiates class outside of it. And declared a static method with return type as object of class which should check if class is already instantiated once.\
• Additionally after running tests and if any of them fails, you can have the screenshot attached report when you open the "test-output/report.html" on explorer.\
• If you run the test by TestRunner class, you can have JUnitReports inside test-output folder.\
• My framework is easy to maintain since I have elements stored in one centralized place. If any changes happen on the application about the elements, I know where to go and how to fix it to run test scripts correctly.\

### N11 LOAD TEST TASK
• TASK: Load tests and scenarios are needed to investigate the behavior of the search module of the https://www.n11.com/ header and list the results after the search.\
• Load test cases which are related to the task are stored in insider_load_task directory\
• There is 1 User Story, 4 Acceptance Criterias, 5 Test Cases inside .pdf file.\
• You can reach out test codes via insider_load_task directory/Insider N11 Load Test Fatih Tosun.jmx\
• To have the report of the load test run the below command on terminal;\

jmeter -n -t <location of jmeter script> -l <location of result file> -e -o <location of report folder>\
Sample path:\
• To read the report of test go to Report folder and click on .html file\
• You can reach out test report via insider_load_task directory/Report\

### PET SORE API TEST CASES TASK
• TASK: Using “pet” endpoints from https://petstore.swagger.io/ write CRUD operations API tests with positive and negative scenarios.\
• Positive and negative test cases which are related to the task are stored in insider_api_task/Insider_API_Task_Fatih_Tosun.pdf\
• There is 1 User Story, 4 Acceptance Criterias, 4 Positive, 4 Negative, Totally 8 Test Cases inside .pdf file.\

### Contact:
If you have any questions or feedback, please contact me via fatihtosun.qa@gmail.com\
